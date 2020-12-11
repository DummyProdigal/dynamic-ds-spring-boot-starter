package pers.bingo.datasource.creator;

import pers.bingo.datasource.config.DataSourceProperty;

import javax.sql.DataSource;

/**
 * @Author GouBin
 * @Date 2020-10-22
 * @Version 1.0
 * @Modified_By
 */
public interface DataSourceCreator {

    /**
     * 根据数据源配置创建数据源实例
     *
     * @param property
     *
     * @return
     */
    DataSource createDataSource(DataSourceProperty property);
}
