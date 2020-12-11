package pers.bingo.datasource.config.druid.stat;

import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import pers.bingo.datasource.config.druid.DruidStatProperties;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * @Author GouBin
 * @Date 2020-11-05
 * @Version 1.0
 * @Modified_By
 */
@ConditionalOnProperty("spring.ds.dynamic.druid.aop-patterns")
public class DruidSpringAopConfiguration {

    @Bean
    public Advice advice() {
        return new DruidStatInterceptor();
    }

    @Bean
    @ConditionalOnBean(Advice.class)
    public Advisor advisor(DruidStatProperties properties, Advice advice) {
        return new RegexpMethodPointcutAdvisor(properties.getAopPatterns(), advice);
    }

    @Bean
    @ConditionalOnProperty(name = "spring.aop.auto",havingValue = "false")
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
}
