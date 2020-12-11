package pers.bingo.datasource.provider;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @Author GouBin
 * @Date 2020-10-22
 * @Version 1.0
 * @Modified_By
 */
public interface DynamicDataSourceProvider {

    /**
     * 加载数据源
     * @return
     */
    Map<String, DataSource> loadDataSource();
}
