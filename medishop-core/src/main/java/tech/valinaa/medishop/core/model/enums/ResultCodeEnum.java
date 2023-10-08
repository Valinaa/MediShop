package tech.valinaa.medishop.core.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Valinaa
 * @Date 2023/10/1 21:24
 * @Description 统一返回结果状态信息类
 */
@Getter
@AllArgsConstructor
public enum ResultCodeEnum {
    
    SUCCESS(200, "成功"),
    
    FAIL(5000, "失败"),
    REGISTER_FAILED(5001, "注册失败！请联系管理员！"),
    SERVICE_ERROR(5002, "服务异常"),
    DATA_ERROR(5003, "数据异常"),
    NO_SUCH_RECORD(5004, "没有这个记录"),
    NOT_LOGIN(5005, "未登陆"),
    LOGIN_ERROR(5006, "登陆失败"),
    REPEAT_SUBMIT(5007, "重复提交"),
    GET_FAILED(5008, "信息获取失败"),
    URL_ENCODE_ERROR(5009, "URL编码失败"),
    FETCH_ACCESS_TOKEN_FAILED(5010, "获取accessToken失败"),
    FETCH_USERINFO_ERROR(5011, "获取用户信息失败"),
    DATABASE_OPERATION_FAILED(5012, "数据库操作失败"),
    
    ORDER_PRICE_ERROR(5201, "订单商品价格变化"),
    ORDER_STOCK_FALL(5202, "订单库存锁定失败"),
    CREATE_ORDER_FAIL(5203, "创建订单失败"),
    
    DUPLICATE_USERNAME(4002, "用户名已存在！"),
    FORBIDDEN(4003, "没有权限"),
    ILLEGAL_CALLBACK_REQUEST_ERROR(4004, "非法回调请求"),
    ILLEGAL_REQUEST(4005, "非法请求");
    
    private final Integer code;
    private final String message;
    
}
