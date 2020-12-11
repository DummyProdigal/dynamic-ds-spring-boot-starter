package pers.bingo.datasource.config;

import com.alibaba.druid.filter.Filter;
import pers.bingo.datasource.constant.BasicConstants;
import pers.bingo.datasource.provider.DynamicDataSourceProvider;
import pers.bingo.datasource.provider.DynamicYmlDataSourceProvider;
import pers.bingo.datasource.util.SpringContextUtils;
import pers.bingo.datasource.config.druid.DruidAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

/**
 * <p>数据源自动化配置</p>
 * <p>@ConditionalOnExpression 存在数据源配置,则注入</p>
 *
 * @Author GouBin
 * @Date 2020-10-19
 * @Version 1.0
 * @Modified_By
 */
@Configuration
@Import(DruidAutoConfiguration.class)
@EnableConfigurationProperties(DynamicDataSourceProperties.class)
@ConditionalOnProperty(prefix = BasicConstants.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@AutoConfigureBefore({DataSourceAutoConfiguration.class})
@ConditionalOnMissingBean(DataSource.class)
public class DynamicDataSourceAutoConfiguration implements ApplicationContextAware {

    private static Logger log = LoggerFactory.getLogger(DynamicDataSourceAutoConfiguration.class);

    private final DynamicDataSourceProperties properties;

    public DynamicDataSourceAutoConfiguration(
            @Qualifier("spring.ds.dynamic-pers.bingo.datasource.config.DynamicDataSourceProperties")
            DynamicDataSourceProperties properties) {
        log.info("初始化动态数据源全局配置");
        this.properties = properties;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("初始化工具类：SpringContextUtils");
        SpringContextUtils.setApplicationContext(applicationContext);
    }

    @ConfigurationProperties(BasicConstants.BASIC_PREFIX)
    @Bean(BasicConstants.BASIC_BEAN_NAME)
    public DynamicDataSourceProperties BasicDataSourceProperties() {
        log.info("初始化动态数据源基础全局配置");
        return new DynamicDataSourceProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public DynamicDataSourceProvider dynamicYmlDataSourceProvider() {
        log.info("从yml配置中加载配置");
        return new DynamicYmlDataSourceProvider(properties);
    }

    @Bean(BasicConstants.FILTERS_BEAN_NAME)
    @ConditionalOnMissingBean
    public List<Filter> filters(@Autowired(required = false) List<Filter> filters) {
        log.info("加载用户配置的filter列表");
        return CollectionUtils.isEmpty(filters) ? Collections.emptyList() : filters;
    }

    @Bean
    @ConditionalOnBean(value = {DynamicDataSourceProvider.class}, name = {"filters"})
    @ConditionalOnMissingBean
    public DataSource dataSource(DynamicDataSourceProvider provider) {
        log.info("初始化所有数据源");
        return new DynamicRoutingDataSource(properties.getPrimary(), provider);
    }
}
