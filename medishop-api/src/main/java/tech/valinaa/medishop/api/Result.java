package tech.valinaa.medishop.api;

import lombok.Data;
import tech.valinaa.medishop.core.model.enums.ResultCodeEnum;

@Data
public final class Result<T> {
    // 状态码
    private Integer code;
    // 信息
    private String message;
    // 数据
    private T data;
    
    // 构造私有化
    private Result() {
    }
    
    /**
     * @param data    数据
     * @param code    响应状态码
     * @param message 响应消息
     * @param <T>     数据类型
     * @return Result<T>
     */
    public static <T> Result<T> build(T data, Integer code, String message) {
        // 创建Result对象，设置值，返回对象
        var result = new Result<T>();
        // 判断返回结果中是否需要数据
        if (data != null) {
            //设置数据到result对象
            result.setData(data);
        }
        // 设置其他值
        result.setCode(code);
        result.setMessage(message);
        // 返回设置值之后的对象
        return result;
    }
    
    /**
     * @param data           数据
     * @param resultCodeEnum 响应体枚举
     * @param <T>            数据类型
     * @return Result<T>
     */
    public static <T> Result<T> build(T data, ResultCodeEnum resultCodeEnum) {
        // 创建Result对象，设置值，返回对象
        var result = new Result<T>();
        // 判断返回结果中是否需要数据
        if (data != null) {
            // 设置数据到result对象
            result.setData(data);
        }
        // 设置其他值
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        // 返回设置值之后的对象
        return result;
    }
    
    /**
     * 成功响应工具方法
     *
     * @param data 数据
     * @param <T>  数据类型
     * @return Result<T>
     */
    public static <T> Result<T> success(T data) {
        return build(data, ResultCodeEnum.SUCCESS);
    }
    
    /**
     * 失败响应工具方法
     *
     * @param data           数据
     * @param resultCodeEnum 响应体枚举
     * @param <T>            数据类型
     * @return Result<T>
     */
    // 失败的方法
    public static <T> Result<T> failure(T data, ResultCodeEnum resultCodeEnum) {
        return build(data, resultCodeEnum);
    }
    
    /**
     * 响应枚举构建
     *
     * @param resultCodeEnum 响应体枚举
     * @param <T>            数据类型
     * @return Result<T>
     */
    public static <T> Result<T> build(ResultCodeEnum resultCodeEnum) {
        // 创建Result对象，设置值，返回对象
        Result<T> result = new Result<>();
        // 设置其他值
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        result.setData(null);
        // 返回设置值之后的对象
        return result;
    }
    
    /**
     * 无数据成功响应
     *
     * @param <T> 数据类型
     * @return Result<T>
     */
    public static <T> Result<T> success() {
        return build(ResultCodeEnum.SUCCESS);
    }
    
    /**
     * 无数据失败响应
     *
     * @param resultCodeEnum 响应体枚举
     * @param <T>            数据类型
     * @return Result<T>
     */
    public static <T> Result<T> failure(ResultCodeEnum resultCodeEnum) {
        return build(resultCodeEnum);
    }
}
