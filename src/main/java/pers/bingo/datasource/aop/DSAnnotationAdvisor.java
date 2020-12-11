package pers.bingo.datasource.aop;

import pers.bingo.datasource.annotation.DS;
import pers.bingo.datasource.support.AnnotationMethodPointcut;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.lang.NonNull;

/**
 * @Author GouBin
 * @Date 2020-11-16
 * @Version 1.0
 * @Modified_By
 */
public class DSAnnotationAdvisor extends AbstractPointcutAdvisor implements BeanFactoryAware {

    private final Advice advice;

    private final Pointcut pointcut;

    public DSAnnotationAdvisor(@NonNull Advice advice) {
        this.advice = advice;
        this.pointcut = new AnnotationMethodPointcut(DS.class);
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (advice instanceof BeanFactoryAware) {
            ((BeanFactoryAware) advice).setBeanFactory(beanFactory);
        }
    }
}
