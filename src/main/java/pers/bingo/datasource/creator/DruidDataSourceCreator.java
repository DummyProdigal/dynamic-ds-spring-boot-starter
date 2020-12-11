package pers.bingo.datasource.creator;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import pers.bingo.datasource.config.DataSourceProperty;
import pers.bingo.datasource.config.DynamicDataSourceProperties;
import pers.bingo.datasource.config.druid.DruidDataSourceConfig;
import pers.bingo.datasource.constant.BasicConstants;
import pers.bingo.datasource.util.SpringContextUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author GouBin
 * @Date 2020-10-22
 * @Version 1.0
 * @Modified_By
 */
public class DruidDataSourceCreator extends AbstractDataSourceCreator {
    /**
     * 用户全局配置
     */
    private DruidDataSourceConfig globalDruid;

    public DruidDataSourceCreator(DruidDataSourceConfig globalDruid) {
        this.globalDruid = globalDruid;
    }

    @Override
    public DataSource createDataSource(DataSourceProperty property) {
        DataSource dataSource = createDruidDataSource(property);
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
    private DataSource createDruidDataSource(DataSourceProperty property) {
        DruidDataSource dataSource = new DruidDataSource();
        // 基础全局配置,可被用户全局配置覆盖
        DynamicDataSourceProperties globalBasicProperties = SpringContextUtils.getBean(BasicConstants.BASIC_BEAN_NAME);
        DruidDataSourceConfig globalBasicDruid = globalBasicProperties.getDruid();
        BeanUtil.copyProperties(globalBasicDruid, dataSource, CopyOptions.create().setIgnoreNullValue(true));
        // 全局配置
        BeanUtil.copyProperties(globalDruid, dataSource, CopyOptions.create().setIgnoreNullValue(true));
        // 将Druid局部配置覆盖全局配置（为null的字段不复制）
        DruidDataSourceConfig local = property.getDruid();
        BeanUtil.copyProperties(local, dataSource, CopyOptions.create().setIgnoreNullValue(true));
        dataSource.setName(property.getPoolName());
        String driverClassName = property.getDriverClassName();
        if (!StringUtils.isEmpty(driverClassName)) {
            dataSource.setDriverClassName(driverClassName);
        }
        dataSource.setUrl(property.getUrl());
        dataSource.setUsername(property.getUsername());
        dataSource.setPassword(property.getPassword());
        List<Filter> filters = SpringContextUtils.getBean(BasicConstants.FILTERS_BEAN_NAME);
        dataSource.setProxyFilters(filters);
        try {
            dataSource.init();
        } catch (SQLException e) {
            throw new RuntimeException("初始化数据源错误", e);
        }
        return dataSource;
    }
}
