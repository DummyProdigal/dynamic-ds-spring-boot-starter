package pers.bingo.datasource.creator;

import com.alibaba.druid.pool.DruidDataSource;
import pers.bingo.datasource.config.hikari.HikariDataSourceConfig;
import pers.bingo.datasource.config.DataSourceProperty;

import javax.sql.DataSource;

/**
 * @Author GouBin
 * @Date 2020-10-22
 * @Version 1.0
 * @Modified_By
 */
public class HikariDataSourceCreator extends AbstractDataSourceCreator {
    private HikariDataSourceConfig globalHikari;

    public HikariDataSourceCreator(HikariDataSourceConfig globalHikari) {
        this.globalHikari = globalHikari;
    }

    @Override
    public DataSource createDataSource(DataSourceProperty property) {
        DataSource dataSource = createHikariCPDataSource(property);
        runScrip(dataSource, property);
        return dataSource;
    }

    /**
     * 创建Druid数据源
     *
     * @param property
     *
     * @return
     */
    private DataSource createHikariCPDataSource(DataSourceProperty property) {
        DruidDataSource dataSource = new DruidDataSource();
        // TODO hikari配置项
        return dataSource;
    }


}
