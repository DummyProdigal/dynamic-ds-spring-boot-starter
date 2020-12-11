package pers.bingo.datasource.aop;

import pers.bingo.datasource.annotation.DS;
import pers.bingo.datasource.util.DynamicDataSourceContextHolder;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.MethodClassKey;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.util.ClassUtils;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author GouBin
 * @Date 2020-10-26
 * @Version 1.0
 * @Modified_By
 */
public class DynamicDataSourceInterceptor implements MethodInterceptor {

    private final Map<Object, String> dsCache = new ConcurrentHashMap<>();

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        try{

            String ds = getDataSource(methodInvocation);
            DynamicDataSourceContextHolder.push(ds);
            return methodInvocation.proceed();
        } finally {
            DynamicDataSourceContextHolder.poll();
        }
    }

    /**
     * 根据类或方法上的DS注解查找数据源
     */
    private String getDataSource(MethodInvocation methodInvocation) {
        Class<?> targetClass = methodInvocation.getThis().getClass();
        Method method = methodInvocation.getMethod();

        Object cacheKey = new MethodClassKey(method, targetClass);
        String ds = dsCache.get(cacheKey);
        if (ds == null) {
            ds = findDataSource(method, targetClass);
            if (ds != null) {
                dsCache.put(cacheKey, ds);
            }
        }
        return ds;
    }

    /**
     * 分别从 当前方法、当前方法申明的类、桥接方法、桥接方法申明的类 中查找
     *
     * @param method      方法
     * @param targetClass
     *
     * @return
     */
    private String findDataSource(Method method, Class<?> targetClass) {
        Class<?> userClass = ClassUtils.getUserClass(targetClass);
        // JDK代理时，获取实现类的方法
        Method specificMethod = ClassUtils.getMostSpecificMethod(method, userClass);
        // 找到原始方法
        specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);
        // 从当前方法中查找
        String ds = getDsAnnotationValue(specificMethod);
        if (ds != null) {
            return ds;
        }
        // 从当前方法申明的类查找
        ds = getDsAnnotationValue(specificMethod.getDeclaringClass());
        // 确定method是否用户申明的，并且ds不为空
        if (ClassUtils.isUserLevelMethod(method) && ds != null) {
            return ds;
        }
        // 如果specificMethod ！= method，则method为桥接方法
        if (specificMethod != method) {
            ds = getDsAnnotationValue(method);
            if (ds != null) {
                return ds;
            }
            // 从桥接方法申明的类查找
            ds = getDsAnnotationValue(method.getDeclaringClass());
            // 确定method是否用户申明的，并且ds不为空
            if (ClassUtils.isUserLevelMethod(method) && ds != null) {
                return ds;
            }
        }
        return ds;
    }

    /**
     * 获取注解中的数据源名称
     *
     * @param ae
     *
     * @return
     */
    private String getDsAnnotationValue(AnnotatedElement ae) {
        AnnotationAttributes attributes = AnnotatedElementUtils.getMergedAnnotationAttributes(ae, DS.class);
        return attributes == null ? null : attributes.getString("value");
    }
}
