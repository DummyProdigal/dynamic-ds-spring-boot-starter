package pers.bingo.datasource.util;

import org.springframework.context.ApplicationContext;

/**
 * @Author GouBin
 * @Date 2020-10-30
 * @Version 1.0
 * @Modified_By
 */
public class SpringContextUtils {

    private static ApplicationContext applicationContext = null;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtils.applicationContext = applicationContext;
    }

    /**
     * 通过beanId获取bean
     * @param beanId
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanId) {
        return (T) getApplicationContext().getBean(beanId);
    }

    /**
     * 通过类型获取bean
     * @param requiredType
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> requiredType) {
        return (T) getApplicationContext().getBean(requiredType);
    }

}
