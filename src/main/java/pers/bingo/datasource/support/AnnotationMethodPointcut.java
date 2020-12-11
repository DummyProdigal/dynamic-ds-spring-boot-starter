package pers.bingo.datasource.support;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import java.lang.annotation.Annotation;

/**
 * @Author GouBin
 * @Date 2020-11-16
 * @Version 1.0
 * @Modified_By
 */
public class AnnotationMethodPointcut implements Pointcut {

    private final Class<? extends Annotation> annotationType;

    public AnnotationMethodPointcut(Class<? extends Annotation> annotationType) {
        this.annotationType = annotationType;
    }

    @Override
    public ClassFilter getClassFilter() {
        return ClassFilter.TRUE;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return new AnnotationMethodMatcher(annotationType);
    }
}
