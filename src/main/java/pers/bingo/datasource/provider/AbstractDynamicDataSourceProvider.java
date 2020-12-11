package pers.bingo.datasource.provider;

import pers.bingo.datasource.constant.DsConstants;
import pers.bingo.datasource.creator.DataSourceCreator;
import pers.bingo.datasource.creator.DruidDataSourceCreator;
import pers.bingo.datasource.creator.HikariDataSourceCreator;
import pers.bingo.datasource.config.DataSourceProperty;
import pers.bingo.datasource.config.DynamicDataSourceProperties;
import pers.bingo.datasource.config.druid.DruidDataSourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author GouBin
 * @Date 2020-10-22
 * @Version 1.0
 * @Modified_By
 */
public abstract class AbstractDynamicDataSourceProvider implements DynamicDataSourceProvider {

    private static Logger log = LoggerFactory.getLogger(AbstractDynamicDataSourceProvider.class);

    protected Map<String, DataSource> createDataSource(DynamicDataSourceProperties properties) {
        Map<String, DataSourceProperty> dataSourcePropertyMap = properties.getDataSources();
        Map<String, DataSource> dataSourceMap = new HashMap<>(dataSourcePropertyMap.size() * 2);
        dataSourcePropertyMap.forEach((k, v) -> {
            String poolName = v.getPoolName();
            if (StringUtils.isEmpty(poolName)) {
                poolName = k;
            }
            v.setPoolName(poolName);
            dataSourceMap.put(poolName, createDataSource(v, properties));
        });
        return dataSourceMap;
    }

    /**
     * 选择连接池类型
     * @param property
     * @param properties
     * @return
     */
    private DataSource createDataSource(DataSourceProperty property, DynamicDataSourceProperties properties) {
        DataSourceCreator dataSourceCreator;
        Class<? extends DataSource> type = property.getType();
        DruidDataSourceConfig dataSourceConfig = properties.getDruid();
        if (StringUtils.isEmpty(property.getPublicKey())) {
            property.setPublicKey(properties.getPublicKey());
        }
        if (type == null) {
            dataSourceCreator = new DruidDataSourceCreator(dataSourceConfig);
        } else {
            String name = type.getName();
            try {
                Class.forName(name);
                if (DsConstants.DRUID_DATASOURCE.equals(name)) {
                    dataSourceCreator = new DruidDataSourceCreator(dataSourceConfig);
                } else {
                    dataSourceCreator = new HikariDataSourceCreator(properties.getHikari());
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("该数据源连接池类型不存在: [" + name + "]", e);
            }
        }
        return dataSourceCreator.createDataSource(property);
    }
}
