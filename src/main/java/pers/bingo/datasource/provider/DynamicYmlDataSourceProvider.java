package pers.bingo.datasource.provider;

import pers.bingo.datasource.config.DynamicDataSourceProperties;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 从yml配置文件中加载配置
 *
 * @Author GouBin
 * @Date 2020-10-22
 * @Version 1.0
 * @Modified_By
 */
public class DynamicYmlDataSourceProvider extends AbstractDynamicDataSourceProvider {

    private final DynamicDataSourceProperties properties;

    public DynamicYmlDataSourceProvider(DynamicDataSourceProperties properties) {
        this.properties = properties;
    }

    @Override
    public Map<String, DataSource> loadDataSource() {
        return createDataSource(properties);
    }
}
