package tech.valinaa.medishop.utils.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author Valinaa
 * @Date 2023/10/3 13:42
 * @Description Jackson 工具类
 */
@Slf4j
@UtilityClass
@SuppressWarnings({"checkstyle:HideUtilityClassConstructor", "unused"})
public class JacksonUtil {
    
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    // 时间日期格式
    private static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    //以静态代码块初始化
    static {
        //对象的所有字段全部列入序列化
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //取消默认转换timestamps形式
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //忽略空Bean转json的错误
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //所有的日期格式都统一为以下的格式，即yyyy-MM-dd HH:mm:ss
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat(STANDARD_FORMAT));
        //忽略 在json字符串中存在，但在java对象中不存在对应属性的情况。防止错误
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    
    
    /*
    ===========================以下是从JSON中获取对象====================================
    */
    
    /**
     * 将json字符串转为自定义对象
     *
     * @param jsonString json字符串
     * @param object     自定义对象类
     * @param <T>        自定义对象类型
     * @return 自定义对象
     */
    public static <T> T parseObject(String jsonString, Class<T> object) {
        try {
            return OBJECT_MAPPER.readValue(jsonString, object);
        } catch (JsonProcessingException e) {
            log.error("JsonString转为自定义对象失败：{}", e.getMessage());
            return null;
        }
    }
    
    /**
     * 读取file文件中的json字符串转为自定义对象
     *
     * @param file   文件
     * @param object 自定义对象类
     * @param <T>    自定义对象类型
     * @return 自定义对象
     */
    public static <T> T parseObject(File file, Class<T> object) {
        try {
            return OBJECT_MAPPER.readValue(file, object);
        } catch (IOException e) {
            log.error("从文件中读取json字符串转为自定义对象失败：{}", e.getMessage());
            return null;
        }
    }
    
    /**
     * 将json数组字符串转为指定对象List列表或者Map集合
     *
     * @param jsonArray json数组字符串
     * @param reference 指定对象List列表或者Map集合的类型
     * @param <T>       指定对象List列表或者Map集合的类型
     * @return 指定对象List列表或者Map集合
     */
    public static <T> T parseJSONArray(String jsonArray, TypeReference<T> reference) {
        try {
            return OBJECT_MAPPER.readValue(jsonArray, reference);
        } catch (JsonProcessingException e) {
            log.error("JSONArray转为List列表或者Map集合失败：{}", e.getMessage());
            return null;
        }
    }
    
    /**
     * 将json字符串转为指定对象List列表或者Map集合
     *
     * @param jsonString json字符串
     * @param reference  指定对象List列表或者Map集合的类型
     * @param <T>        指定对象List列表或者Map集合的类型
     * @return 指定对象List列表或者Map集合
     */
    public static <T> T parseObject(String jsonString, TypeReference<T> reference) {
        try {
            return OBJECT_MAPPER.readValue(jsonString, reference);
        } catch (JsonProcessingException e) {
            log.error("JSONArray转为List列表或者Map集合失败：{}", e.getMessage());
            return null;
        }
    }
    
    /*
    =================================以下是将对象转为JSON=====================================
    */
    
    /**
     * 将对象转为json字符串
     *
     * @param object 对象
     * @return json字符串
     */
    public static String toJSONString(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Object转JSONString失败：{}", e.getMessage());
            return null;
        }
    }
    
    /**
     * 将对象转为字节数组
     *
     * @param object 对象
     * @return 字节数组
     */
    public static byte[] toByteArray(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            log.error("Object转ByteArray失败：{}", e.getMessage());
            return null;
        }
    }
    
    /**
     * 将对象写入文件
     *
     * @param file   文件
     * @param object 对象
     */
    public static void objectToFile(File file, Object object) {
        try {
            OBJECT_MAPPER.writeValue(file, object);
        } catch (JsonProcessingException e) {
            log.error("Object写入文件失败：{}", e.getMessage(), e);
        } catch (IOException e) {
            log.error("IO异常：{}", e.getMessage(), e);
        }
    }
    /*
    =============================以下是与JsonNode相关的=======================================
    JsonNode和JSONObject一样，都是JSON树形模型，只不过在jackson中，存在的是JsonNode
    */
    
    /**
     * 将json字符串转为JsonNode对象
     *
     * @param jsonString json字符串
     * @return JsonNode对象
     */
    public static JsonNode parseJSONObject(String jsonString) {
        try {
            return OBJECT_MAPPER.readTree(jsonString);
        } catch (JsonProcessingException e) {
            log.error("JSONString转为JsonNode失败：{}", e.getMessage(), e);
            return null;
        }
    }
    
    /**
     * 将对象转为JsonNode对象
     *
     * @param object 对象
     * @return JsonNode对象
     */
    public static JsonNode parseJSONObject(Object object) {
        return OBJECT_MAPPER.valueToTree(object);
    }
    
    /**
     * 将JsonNode对象转为字符串
     *
     * @param jsonNode JsonNode对象
     * @return 字符串
     */
    public static String toJSONString(JsonNode jsonNode) {
        try {
            return OBJECT_MAPPER.writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            log.error("JsonNode转JSONString失败：{}", e.getMessage(), e);
            return null;
        }
    }
    
    /**
     * JsonNode是一个抽象类，不能实例化<br/>
     * 创建JSON树形模型，得用JsonNode的子类ObjectNode，用法和JSONObject大同小异
     *
     * @return ObjectNode对象
     */
    public static ObjectNode newJSONObject() {
        return OBJECT_MAPPER.createObjectNode();
    }
    
    /**
     * 创建JSON数组对象，就像JSONArray一样用
     *
     * @return ArrayNode对象
     */
    public static ArrayNode newJSONArray() {
        return OBJECT_MAPPER.createArrayNode();
    }
    
    /*
    ===========以下是从JsonNode对象中获取key值的方法，个人觉得有点多余，直接用JsonNode自带的取值方法会好点，出于纠结症，还是补充进来了
    */
    
    /**
     * 从JsonNode对象中获取key值为key的字符串
     *
     * @param jsonObject JsonNode对象
     * @param key        key值
     * @return key值为key的字符串
     */
    public static String getString(JsonNode jsonObject, String key) {
        return jsonObject.get(key).asText();
    }
    
    /**
     * 从JsonNode对象中获取key值为key的整数
     *
     * @param jsonObject JsonNode对象
     * @param key        key值
     * @return key值为key的整数
     */
    public static Integer getInteger(JsonNode jsonObject, String key) {
        return jsonObject.get(key).asInt();
    }
    
    /**
     * 从JsonNode对象中获取key值为key的boolean值
     *
     * @param jsonObject JsonNode对象
     * @param key        key值
     * @return key值为key的boolean值
     */
    public static Boolean getBoolean(JsonNode jsonObject, String key) {
        return jsonObject.get(key).asBoolean();
    }
    
    /**
     * 从JsonNode对象中获取key值为key的JsonNode对象
     *
     * @param jsonObject JsonNode对象
     * @param key        key值
     * @return key值为key的JsonNode对象
     */
    public static JsonNode getJSONObject(JsonNode jsonObject, String key) {
        return jsonObject.get(key);
    }
}
