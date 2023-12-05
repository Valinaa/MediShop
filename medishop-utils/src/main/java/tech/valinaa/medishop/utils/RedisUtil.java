package tech.valinaa.medishop.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.DependsOn;
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
@Log4j2
@UtilityClass
@DependsOn("springContextHolder")
@SuppressWarnings({"unused", "unchecked"})
public final class RedisUtil {
    
    private static final RedisTemplate<String, Object> REDIS_TEMPLATE = SpringContextHolder.getBean(RedisTemplate.class);
    //! =============================common============================
    
    /**
     * 指定缓存失效时间
     *
     * @param key  Key值  键
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public static Boolean expire(String key, long time) {
        return expire(key, time, TimeUnit.SECONDS);
    }
    
    /**
     * 指定缓存失效时间,携带失效时间的类型
     *
     * @param key  Key值  键
     * @param time 时间(秒)
     * @param unit 时间的类型  TimeUnit枚举
     * @return true成功 false失败
     */
    public static Boolean expire(String key, long time, TimeUnit unit) {
        if (time > 0) {
            return REDIS_TEMPLATE.expire(key, time, unit);
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
        return REDIS_TEMPLATE.getExpire(key);
    }
    
    /**
     * 判断key是否存在
     *
     * @param key Key值 键
     * @return true 存在 false不存在
     */
    public static Boolean hasKey(String key) {
        return REDIS_TEMPLATE.hasKey(key);
    }
    
    /**
     * 删除缓存
     *
     * @param key Key值 可以传一个值 或多个
     * @return true删除成功 false删除失败
     * @throws RedisOperationException key为null或者长度为0时，抛出异常
     */
    public static Boolean delete(String... key) {
        if (key == null || key.length == 0) {
            log.warn("[RedisUtil.delete] [The key: {} is null or length = 0, invalid]", (Object) key);
            return false;
        }
        if (key.length == 1) {
            return REDIS_TEMPLATE.delete(key[0]);
        }
        var deleteNums = REDIS_TEMPLATE.delete(CollectionUtils.arrayToList(key)
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
        return key == null ? null : REDIS_TEMPLATE.opsForValue().get(key);
    }
    
    /**
     * 普通缓存放入
     *
     * @param key   Key值   键
     * @param value 值
     */
    
    public static void set(String key, Object value) {
        REDIS_TEMPLATE.opsForValue().set(key, value);
    }
    
    /**
     * 普通缓存放入并设置时间
     *
     * @param key   Key值   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     */
    public static void set(String key, Object value, long time) {
        if (time > 0) {
            REDIS_TEMPLATE.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            set(key, value);
        }
    }
    
    /**
     * 递增
     *
     * @param key   Key值   键
     * @param delta 要增加几(大于0)
     * @return 递增后的值
     * @throws RedisOperationException 递增因子必须大于0
     */
    public static Long increment(String key, long delta) {
        if (delta < 0) {
            throw new RedisOperationException("递增因子必须大于0");
        }
        return REDIS_TEMPLATE.opsForValue().increment(key, delta);
    }
    
    /**
     * 递减
     *
     * @param key   Key值   键
     * @param delta 要减少几(小于0)
     * @return 递减后的值
     * @throws RedisOperationException 递减因子必须大于0
     */
    public static Long decrement(String key, long delta) {
        if (delta < 0) {
            throw new RedisOperationException("递减因子必须大于0");
        }
        return REDIS_TEMPLATE.opsForValue().increment(key, -delta);
    }
    
    //! ================================Map=================================
    
    /**
     * HashGet
     *
     * @param key  Key值  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public static Object hashGet(String key, String item) {
        return REDIS_TEMPLATE.opsForHash().get(key, item);
    }
    
    /**
     * 获取hashKey对应的所有键值
     *
     * @param key Key值 键
     * @return 对应的多个键值
     */
    public static Map<Object, Object> hashEntries(String key) {
        return REDIS_TEMPLATE.opsForHash().entries(key);
    }
    
    /**
     * HashSet
     *
     * @param key Key值 键
     * @param map 对应多个键值
     */
    public static void hashSetMap(String key, Map<String, Object> map) {
        REDIS_TEMPLATE.opsForHash().putAll(key, map);
    }
    
    /**
     * HashSet 并设置时间
     *
     * @param key  Key值  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @throws RedisOperationException 过期时间设置失败
     */
    public static void hashSetMap(String key, Map<String, Object> map, long time) {
        hashSetMap(key, map);
        if (time <= 0) {
            log.warn("[RedisUtil.hashSetMap] [The expired time of key: {} <=0, invalid]", key);
            return;
        }
        if (Boolean.FALSE.equals(expire(key, time))) {
            throw new RedisOperationException("10001", new Object[]{key, map, time},
                    "Failed to set cache expiration time for the key: " + key);
        }
    }
    
    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   Key值   键
     * @param item  项
     * @param value 值
     */
    public static void hashSet(String key, String item, Object value) {
        REDIS_TEMPLATE.opsForHash().put(key, item, value);
    }
    
    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   Key值   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @throws RedisOperationException 过期时间设置失败
     */
    public static void hashSet(String key, String item, Object value, long time) {
        hashSet(key, item, value);
        if (time <= 0) {
            log.warn("[RedisUtil.hashSet] [The expired time of key: {} <=0, invalid]", key);
            return;
        }
        if (Boolean.FALSE.equals(expire(key, time))) {
            throw new RedisOperationException("10001", new Object[]{key, item, value, time},
                    "Failed to set cache expiration time for the key: " + key);
        }
    }
    
    /**
     * 删除hash表中的值
     *
     * @param key  Key值  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public static void hashDelete(String key, Object... item) {
        REDIS_TEMPLATE.opsForHash().delete(key, item);
    }
    
    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  Key值  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public static Boolean hashHasKey(String key, String item) {
        return REDIS_TEMPLATE.opsForHash().hasKey(key, item);
    }
    
    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  Key值  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return 新增后的值
     */
    public static double hashIncrement(String key, String item, double by) {
        return REDIS_TEMPLATE.opsForHash().increment(key, item, by);
    }
    
    /**
     * hash递减
     *
     * @param key  Key值  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return 递减后的值
     */
    public static double hashDecrement(String key, String item, double by) {
        return REDIS_TEMPLATE.opsForHash().increment(key, item, -by);
    }
    
    //! ============================set=============================
    
    /**
     * 根据key获取Set中的所有值
     *
     * @param key Key值 键
     * @return 对应Set中的所有值
     */
    public static Set<Object> setGet(String key) {
        return REDIS_TEMPLATE.opsForSet().members(key);
    }
    
    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   Key值   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public static Boolean setHasKey(String key, Object value) {
        return REDIS_TEMPLATE.opsForSet().isMember(key, value);
    }
    
    /**
     * 将数据放入set缓存
     *
     * @param key    Key值    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public static Long setSet(String key, Object... values) {
        return REDIS_TEMPLATE.opsForSet().add(key, values);
    }
    
    /**
     * 将set数据放入缓存
     *
     * @param key    Key值    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     * @throws RedisOperationException 过期时间设置失败
     */
    public static Long setSet(String key, long time, Object... values) {
        var count = Optional.ofNullable(setSet(key, values))
                .orElseThrow(() -> new RedisOperationException("10009", new Object[]{key, values},
                        "Failed to set Set cache with Time for the key: " + key));
        if (count < 0 || Boolean.FALSE.equals(expire(key, time))) {
            throw new RedisOperationException("10009", new Object[]{key, values},
                    "Failed to set Set cache with Time for the key: " + key);
        }
        return count;
    }
    
    /**
     * 获取set缓存的长度
     *
     * @param key Key值 键
     * @return Set的长度
     */
    public static Long setGetSize(String key) {
        return REDIS_TEMPLATE.opsForSet().size(key);
    }
    
    /**
     * 移除值为value的
     *
     * @param key    Key值    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    
    public static Long setRemove(String key, Object... values) {
        return REDIS_TEMPLATE.opsForSet().remove(key, values);
    }
    
    //! ============================Sorted Set=============================
    
    /**
     * 增加有序集合
     *
     * @param key   Key值
     * @param value Value值
     * @param seqNo 序号
     * @return Boolean
     */
    public static Boolean addZset(String key, Object value, double seqNo) {
        return REDIS_TEMPLATE.opsForZSet().add(key, value, seqNo);
    }
    
    /**
     * 获取zset集合数量
     *
     * @param key Key值
     * @return Long
     */
    public static Long countZset(String key) {
        return REDIS_TEMPLATE.opsForZSet().size(key);
    }
    
    /**
     * 获取zset指定范围内的集合
     *
     * @param key   Key值
     * @param start 起始值
     * @param end   结束值
     * @return Set<Object>
     */
    public static Set<Object> rangeZset(String key, long start, long end) {
        try {
            return REDIS_TEMPLATE.opsForZSet().range(key, start, end);
        } catch (Exception e) {
            log.error("[RedisUtils.rangeZset] [key is {},start is {},end is {}]", key, start, end, e);
            return Set.of();
        }
    }
    
    /**
     * 根据key和value移除指定元素
     *
     * @param key   Key值
     * @param value Value值
     * @return Long
     */
    public static Long removeZset(String key, Object value) {
        return REDIS_TEMPLATE.opsForZSet().remove(key, value);
    }
    
    /**
     * 获取对应key和value的score
     *
     * @param key   Key值
     * @param value Value值
     * @return Double
     */
    public static Double score(String key, Object value) {
        return REDIS_TEMPLATE.opsForZSet().score(key, value);
    }
    
    /**
     * 指定范围内元素排序
     *
     * @param key Key值
     * @param v1  起始值
     * @param v2  结束值
     * @return Set<Object>
     */
    public static Set<Object> rangeByScore(String key, double v1, double v2) {
        return REDIS_TEMPLATE.opsForZSet().rangeByScore(key, v1, v2);
    }
    
    /**
     * 指定元素增加指定值
     *
     * @param key   Key值
     * @param obj   Value值
     * @param score 增加值
     * @return Double
     */
    public static Double addScore(String key, Object obj, double score) {
        return REDIS_TEMPLATE.opsForZSet().incrementScore(key, obj, score);
    }
    
    /**
     * 获取指定范围的元素排名(逆序)
     *
     * @param key   Key值
     * @param start 起始值
     * @param end   结束值
     * @return Set<Object>
     */
    public static Set<Object> reverseRange(String key, long start, long end) {
        return REDIS_TEMPLATE.opsForZSet().reverseRange(key, start, end);
    }
    
    /**
     * 获取指定范围的元素排名(逆序)(附有scores)
     *
     * @param key   Key值
     * @param start 起始值
     * @param end   结束值
     * @return Set
     */
    public static Set<ZSetOperations.TypedTuple<Object>> reverseRangeWithScores(String key, long start, long end) {
        return REDIS_TEMPLATE.opsForZSet().reverseRangeWithScores(key, start, end);
    }
    
    /**
     * 获取指定元素排名
     *
     * @param key Key值
     * @param obj Value值
     * @return Long
     */
    public static Long rank(String key, Object obj) {
        return REDIS_TEMPLATE.opsForZSet().rank(key, obj);
    }
    
    /**
     * 获取指定元素排名(逆序)
     *
     * @param key Key值
     * @param obj Value值
     * @return Long
     */
    public static Long reverseRank(String key, Object obj) {
        return REDIS_TEMPLATE.opsForZSet().reverseRank(key, obj);
    }
    
    //! ===============================list=================================
    
    /**
     * 获取list缓存的内容
     *
     * @param key   Key值   键
     * @param start 开始
     * @param end   结束 0 到 -1 代表所有值
     * @return list 所有值
     */
    public static List<Object> listGet(String key, long start, long end) {
        try {
            return REDIS_TEMPLATE.opsForList().range(key, start, end);
        } catch (Exception e) {
            log.error("[RedisUtil.listGet] [key: {},start: {},end: {}]", key, start, end, e);
            return List.of();
        }
    }
    
    /**
     * 获取list缓存的长度
     *
     * @param key Key值 键
     * @return list 长度
     */
    public static Long listGetSize(String key) {
        return REDIS_TEMPLATE.opsForList().size(key);
    }
    
    /**
     * 通过索引 获取list中的值
     *
     * @param key   Key值   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return list 指定索引的值
     */
    public static Object listGetIndex(String key, long index) {
        try {
            return REDIS_TEMPLATE.opsForList().index(key, index);
        } catch (Exception e) {
            log.error("[RedisUtil.listGetIndex] [key: {},index: {}]", key, index, e);
            return null;
        }
    }
    
    /**
     * 将list放入缓存
     *
     * @param key   Key值   键
     * @param value 值
     * @return true插入成功, false插入失败
     */
    public static Boolean listSet(String key, Object value) {
        try {
            REDIS_TEMPLATE.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            log.error("[RedisUtil.listSet] [key: {},value: {}]", key, value, e);
            return false;
        }
    }
    
    /**
     * 将list放入缓存
     *
     * @param key   Key值   键
     * @param value 值
     * @param time  时间(秒)
     * @return true插入成功, false插入失败
     */
    public static Boolean listSet(String key, Object value, long time) {
        try {
            REDIS_TEMPLATE.opsForList().rightPush(key, value);
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
     * @param key   Key值   键
     * @param value 值
     * @return true插入成功, false插入失败
     */
    public static Boolean listSet(String key, List<Object> value) {
        try {
            REDIS_TEMPLATE.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            log.error("[RedisUtil.listSet] [key: {},value: {}]", key, value, e);
            return false;
        }
        
    }
    
    /**
     * 将list放入缓存
     *
     * @param key   Key值   键
     * @param value 值
     * @param time  时间(秒)
     * @return true插入成功, false插入失败
     */
    public static Boolean listSet(String key, List<Object> value, long time) {
        try {
            REDIS_TEMPLATE.opsForList().rightPushAll(key, value);
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
     * @param key   Key值   键
     * @param value 值
     * @return null when used in pipeline / transaction
     */
    public static Long listRightPush(String key, Object value) {
        return REDIS_TEMPLATE.opsForList().rightPush(key, value);
    }
    
    /**
     * 功能描述：在list的右边添加集合元素
     * 如果键不存在，则在执行推送操作之前将其创建为空列表
     *
     * @param key    Key值    键
     * @param values 值
     * @return null when used in pipeline / transaction
     */
    public static Long listRightPushList(String key, List<Object> values) {
        return REDIS_TEMPLATE.opsForList().rightPushAll(key, values);
    }
    
    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   Key值   键
     * @param index 索引
     * @param value 值
     * @return true修改成功, false修改失败
     */
    
    public static Boolean listUpdateIndex(String key, long index, Object value) {
        try {
            REDIS_TEMPLATE.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            log.error("[RedisUtil.listUpdateIndex] [key: {},index: {},value: {}]", key, index, value, e);
            return false;
        }
    }
    
    /**
     * 移除N个值为value
     *
     * @param key   Key值   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    
    public static Long listRemove(String key, long count, Object value) {
        return REDIS_TEMPLATE.opsForList().remove(key, count, value);
    }
    
    //! ===============================stream=================================
    //? 待补充
}
