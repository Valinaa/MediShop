package tech.valinaa.medishop.utils.cache;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.util.CollectionUtils;
import tech.valinaa.medishop.utils.exception.RedisOperationException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Valinaa
 * @Date 2023/9/27 11:10
 * @Description redis工具类，封装并简化redis功能的实现
 */
@Slf4j
@SuppressWarnings("unused")
public final class RedisUtil {
    
    private RedisUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    
    private static RedisTemplate<String, Object> redisTemplate;
    @Resource
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }
    
    //! =============================common============================
    
    /**
     * 指定缓存失效时间
     *
     * @param key Key值  键
     * @param time 时间(秒)
     */
    public static Boolean expire(String key, long time) {
        return expire(key, time, TimeUnit.SECONDS);
    }
    
    /**
     * 指定缓存失效时间,携带失效时间的类型
     *
     * @param key Key值  键
     * @param time 时间(秒)
     * @param unit 时间的类型  TimeUnit枚举
     */
    public static Boolean expire(String key, long time, TimeUnit unit) {
            if (time > 0) {
                return redisTemplate.expire(key, time, unit);
            }
            log.warn("[RedisUtil.expire] [The key: {} <0, invalid]", key);
            return false;
    }
    
    /**
     * 根据key 获取过期时间
     *
     * @param key Key值 键 不能为null
     * @return 时间(秒) 返回 0 代表为永久有效
     */
    public static Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }
    
    /**
     * 判断key是否存在
     *
     * @param key Key值 键
     * @return true 存在 false不存在
     */
    public static Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }
    
    /**
     * 删除缓存
     *
     * @param key Key值 可以传一个值 或多个
     */
    public static Boolean delete(String... key) {
        if(key == null || key.length == 0){
            log.warn("[RedisUtil.delete] [The key: {} is null or length = 0, invalid]", (Object) key);
            return false;
        }
        if(key.length==1){
            return redisTemplate.delete(key[0]);
        }
        var deleteNums = redisTemplate.delete(CollectionUtils.arrayToList(key)
                .stream()
                .map(String::valueOf)
                .toList());
        return deleteNums != null && deleteNums > 0;
    }
    
    //! ============================String=============================
    
    /**
     * 普通缓存获取
     *
     * @param key Key值 键
     * @return 值
     */
    public static Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }
    
    /**
     * 普通缓存放入
     *
     * @param key Key值   键
     * @param value 值
     */
    
    public static void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }
    
    /**
     * 普通缓存放入并设置时间
     *
     * @param key Key值   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     */
    public static void set(String key, Object value, long time) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            set(key, value);
        }
    }
    
    /**
     * 递增
     *
     * @param key Key值   键
     * @param delta 要增加几(大于0)
     */
    public static Long increment(String key, long delta) {
        if (delta < 0) {
            throw new RedisOperationException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }
    
    /**
     * 递减
     *
     * @param key Key值   键
     * @param delta 要减少几(小于0)
     */
    public static Long decrement(String key, long delta) {
        if (delta < 0) {
            throw new RedisOperationException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }
    
    //! ================================Map=================================
    
    /**
     * HashGet
     *
     * @param key Key值  键 不能为null
     * @param item 项 不能为null
     */
    public static Object hashGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }
    
    /**
     * 获取hashKey对应的所有键值
     *
     * @param key Key值 键
     * @return 对应的多个键值
     */
    public static Map<Object, Object> hashEntries(String key) {
        return redisTemplate.opsForHash().entries(key);
    }
    
    /**
     * HashSet
     *
     * @param key Key值 键
     * @param map 对应多个键值
     */
    public static void hashSetMap(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }
    
    /**
     * HashSet 并设置时间
     *
     * @param key Key值  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     */
    public static void hashSetMapWithTime(String key, Map<String, Object> map, long time) {
        redisTemplate.opsForHash().putAll(key, map);
        if (!(time > 0 && expire(key, time))) {
            throw new RedisOperationException("10001", new Object[]{key,map,time},
                    "Failed to set cache expiration time for the key: "+key);
        }
    }
    
    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key Key值   键
     * @param item  项
     * @param value 值
     */
    public static void hashSet(String key, String item, Object value) {
        redisTemplate.opsForHash().put(key, item, value);
    }
    
    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key Key值   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     */
    public static void hashSet(String key, String item, Object value, long time) {
        redisTemplate.opsForHash().put(key, item, value);
        if (!(time > 0 && expire(key, time))) {
            throw new RedisOperationException("10001", new Object[]{key,item,value,time},
                    "Failed to set cache expiration time for the key: "+key);
        }
    }
    
    /**
     * 删除hash表中的值
     *
     * @param key Key值  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public static void hashDelete(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }
    
    /**
     * 判断hash表中是否有该项的值
     *
     * @param key Key值  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public static Boolean hashHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }
    
    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key Key值  键
     * @param item 项
     * @param by   要增加几(大于0)
     */
    public static double hashIncrement(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }
    
    /**
     * hash递减
     *
     * @param key Key值  键
     * @param item 项
     * @param by   要减少记(小于0)
     */
    public static double hashDecrement(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }
    
    //! ============================set=============================
    
    /**
     * 根据key获取Set中的所有值
     *
     * @param key Key值 键
     */
    public static Set<Object> setGet(String key) {
        return redisTemplate.opsForSet().members(key);
    }
    
    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key Key值   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public static Boolean setHasKey(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }
    
    /**
     * 将数据放入set缓存
     *
     * @param key Key值    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public static Long setSet(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }
    
    /**
     * 将set数据放入缓存
     *
     * @param key Key值    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public static Long setSetWithTime(String key, long time, Object... values) {
        var count = Optional.ofNullable(redisTemplate.opsForSet().add(key, values))
                .orElseThrow(()->new RedisOperationException("10009",new Object[]{key,values},
                        "Failed to set Set cache with Time for the key: "+key));
        if(count<0){
            throw new RedisOperationException("10009",new Object[]{key,values},
                    "Failed to set Set cache with Time for the key: "+key);
        }
        expire(key, time);
        return count;
    }
    
    /**
     * 获取set缓存的长度
     *
     * @param key Key值 键
     */
    public static Long setGetSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }
    
    /**
     * 移除值为value的
     *
     * @param key Key值    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    
    public static Long setRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }
    
    //! ============================Sorted Set=============================
    /**
     * 增加有序集合
     *
     * @param key Key值
     * @param value Value值
     * @param seqNo 序号
     * @return Boolean
     */
    public static Boolean addZset(String key, Object value, double seqNo) {
        return redisTemplate.opsForZSet().add(key, value, seqNo);
    }
    /**
     * 获取zset集合数量
     *
     * @param key Key值
     * @return Long
     */
    public static Long countZset(String key) {
        return redisTemplate.opsForZSet().size(key);
    }
    /**
     * 获取zset指定范围内的集合
     *
     * @param key Key值
     * @param start 起始值
     * @param end 结束值
     * @return Set<Object>
     */
    public static Set<Object> rangeZset(String key, long start, long end) {
        try {
            return redisTemplate.opsForZSet().range(key, start, end);
        } catch (Exception e) {
            log.error("[RedisUtils.rangeZset] [key is {},start is {},end is {}]", key, start, end, e);
            return Set.of();
        }
    }
    /**
     * 根据key和value移除指定元素
     *
     * @param key Key值
     * @param value Value值
     * @return Long
     */
    public static Long removeZset(String key, Object value) {
        return redisTemplate.opsForZSet().remove(key, value);
    }
    /**
     * 获取对应key和value的score
     *
     * @param key Key值
     * @param value Value值
     * @return Double
     */
    public static Double score(String key, Object value) {
        return redisTemplate.opsForZSet().score(key, value);
    }
    /**
     * 指定范围内元素排序
     *
     * @param key Key值
     * @param v1 起始值
     * @param v2 结束值
     * @return Set<Object>
     */
    public static Set<Object> rangeByScore(String key, double v1, double v2) {
        return redisTemplate.opsForZSet().rangeByScore(key, v1, v2);
    }
    /**
     * 指定元素增加指定值
     *
     * @param key Key值
     * @param obj Value值
     * @param score 增加值
     * @return Double
     */
    public static Double addScore(String key, Object obj, double score) {
        return redisTemplate.opsForZSet().incrementScore(key, obj, score);
    }
    /**
     * 获取指定范围的元素排名(逆序)
     *
     * @param key Key值
     * @param start 起始值
     * @param end 结束值
     * @return Set<Object>
     */
    public static Set<Object> reverseRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }
    /**
     * 获取指定范围的元素排名(逆序)(附有scores)
     *
     * @param key Key值
     * @param start 起始值
     * @param end 结束值
     * @return Set
     */
    public static Set<ZSetOperations.TypedTuple<Object>> reverseRangeWithScores(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
    }
    /**
     * 获取指定元素排名
     *
     * @param key Key值
     * @param obj Value值
     * @return Long
     */
    public static Long rank(String key, Object obj) {
        return redisTemplate.opsForZSet().rank(key, obj);
    }
    /**
     * 获取指定元素排名(逆序)
     *
     * @param key Key值
     * @param obj Value值
     * @return Long
     */
    public static Long reverseRank(String key, Object obj) {
        return redisTemplate.opsForZSet().reverseRank(key, obj);
    }
    
    //! ===============================list=================================
    
    /**
     * 获取list缓存的内容
     *
     * @param key Key值   键
     * @param start 开始
     * @param end   结束 0 到 -1 代表所有值
     */
    public static List<Object> listGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            log.error("[RedisUtil.listGet] [key: {},start: {},end: {}]", key, start, end, e);
            return List.of();
        }
    }
    
    /**
     * 获取list缓存的长度
     *
     * @param key Key值 键
     */
    public static Long listGetSize(String key) {
        return redisTemplate.opsForList().size(key);
    }
    
    /**
     * 通过索引 获取list中的值
     *
     * @param key Key值   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     */
    public static Object listGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            log.error("[RedisUtil.listGetIndex] [key: {},index: {}]", key, index, e);
            return null;
        }
    }
    
    /**
     * 将list放入缓存
     *
     * @param key Key值   键
     * @param value 值
     */
    public static Boolean listSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            log.error("[RedisUtil.listSet] [key: {},value: {}]", key, value, e);
            return false;
        }
    }
    
    /**
     * 将list放入缓存
     *
     * @param key Key值   键
     * @param value 值
     * @param time  时间(秒)
     */
    public static Boolean listSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("[RedisUtil.listSet] [key: {},value: {},time: {}]", key, value, time, e);
            return false;
        }
        
    }
    
    /**
     * 将list放入缓存
     *
     * @param key Key值   键
     * @param value 值
     */
    public static Boolean listSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            log.error("[RedisUtil.listSet] [key: {},value: {}]", key, value, e);
            return false;
        }
        
    }
    
    /**
     * 将list放入缓存
     *
     * @param key Key值   键
     * @param value 值
     * @param time  时间(秒)
     */
    public static Boolean listSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("[RedisUtil.listSet] [key: {},value: {},time: {}]", key, value, time, e);
            return false;
        }
    }
    
    /**
     * 功能描述：在list的右边添加元素
     * 如果键不存在，则在执行推送操作之前将其创建为空列表
     *
     * @param key Key值   键
     * @param value 值
     */
    public static Long listRightPush(String key, Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }
    
    /**
     * 功能描述：在list的右边添加集合元素
     * 如果键不存在，则在执行推送操作之前将其创建为空列表
     *
     * @param key Key值    键
     * @param values 值
     */
    public static Long listRightPushList(String key, List<Object> values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }
    
    /**
     * 根据索引修改list中的某条数据
     *
     * @param key Key值   键
     * @param index 索引
     * @param value 值
     */
    
    public static Boolean listUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            log.error("[RedisUtil.listUpdateIndex] [key: {},index: {},value: {}]", key, index, value, e);
            return false;
        }
    }
    
    /**
     * 移除N个值为value
     *
     * @param key Key值   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    
    public static Long listRemove(String key, long count, Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }
    
    //! ===============================stream=================================
    //? 待补充
}
