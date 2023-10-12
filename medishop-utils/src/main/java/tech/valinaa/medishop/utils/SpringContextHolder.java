package tech.valinaa.medishop.utils;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Valinaa
 * @Date 2023/10/12 10:52
 * @Description SpringContextHolder
 */
@Component
@SuppressWarnings("unused")
public class SpringContextHolder implements ApplicationContextAware {
    
    private static ApplicationContext applicationContext;
    
    /**
     * 取得存储在静态变量中的ApplicationContext.
     *
     * @return {@link ApplicationContext}
     */
    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }
    
    /**
     * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量
     *
     * @param applicationContext {@link ApplicationContext}
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextHolder.applicationContext = applicationContext;
    }
    
    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     *
     * @param name Bean名称
     * @param <T>  Bean类型
     * @return Bean对象
     */
    public static <T> T getBean(String name) {
        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }
    
    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.<br/>
     * 如果有多个Bean符合Class, 取出第一个.
     *
     * @param clazz Bean Class
     * @param <T>   Bean类型
     * @return Bean对象
     */
    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        var beanMaps = applicationContext.getBeansOfType(clazz);
        if (beanMaps.isEmpty()) {
            throw new NoSuchBeanDefinitionException(clazz.getName());
        }
        return beanMaps.values().iterator().next();
    }
    
    /**
     * 判断ApplicationContext是否为空.
     */
    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicationContext未注入!");
        }
    }
    
}
