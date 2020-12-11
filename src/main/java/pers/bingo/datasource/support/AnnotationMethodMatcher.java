package pers.bingo.datasource.support;

import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.StaticMethodMatcher;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author GouBin
 * @Date 2020-11-16
 * @Version 1.0
 * @Modified_By
 */
public class AnnotationMethodMatcher extends StaticMethodMatcher {

    private final Class<? extends Annotation> annotationType;

    public AnnotationMethodMatcher(Class<? extends Annotation> annotationType) {
        this.annotationType = annotationType;
    }

    @Override
    public boolean matches(Method method, Class<?> aClass) {
        boolean matches;
        if (AnnotatedElementUtils.hasAnnotation(method, annotationType)) {
            matches = true;
        } else if (AnnotatedElementUtils.hasAnnotation(aClass, annotationType)){
            matches = true;
        } else if (Proxy.isProxyClass(aClass)) {
            matches = false;
        } else {
            Method specificMethod = AopUtils.getMostSpecificMethod(method, aClass);
            matches = (specificMethod != method && AnnotatedElementUtils.hasAnnotation(specificMethod, annotationType));
        }
        return matches;
    }
}
