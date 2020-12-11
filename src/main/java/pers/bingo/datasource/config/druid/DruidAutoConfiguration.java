package pers.bingo.datasource.config.druid;

import com.alibaba.druid.pool.DruidDataSource;
import pers.bingo.datasource.config.druid.stat.DruidFilterConfiguration;
import pers.bingo.datasource.config.druid.stat.DruidWebStatFilterConfiguration;
import pers.bingo.datasource.config.druid.stat.DruidSpringAopConfiguration;
import pers.bingo.datasource.config.druid.stat.DruidStatViewServletConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author GouBin
 * @Date 2020-11-04
 * @Version 1.0
 * @Modified_By
 */
@Configuration
@ConditionalOnClass(DruidDataSource.class)
@EnableConfigurationProperties(DruidStatProperties.class)
@Import({DruidSpringAopConfiguration.class,
        DruidStatViewServletConfiguration.class,
        DruidWebStatFilterConfiguration.class,
        DruidFilterConfiguration.class})
public class DruidAutoConfiguration {
}
