package tech.valinaa.medishop.utils;

import lombok.experimental.UtilityClass;

import java.util.UUID;

/**
 * @author Valinaa
 * @Date 2023/10/12 10:38
 * @Description 常量类
 */
@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
@UtilityClass
public class Constants {
    public static final String KEY_ID = UUID.randomUUID().toString().replaceAll("-", "");
}
