package pers.bingo.datasource.config;

import pers.bingo.datasource.provider.DynamicDataSourceProvider;
import pers.bingo.datasource.util.DynamicDataSourceContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author GouBin
 * @Date 2020-10-13
 * @Version 1.0
 * @Modified_By
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    private static Logger log = LoggerFactory.getLogger(DynamicRoutingDataSource.class);

    private String primary = "master";

    private DynamicDataSourceProvider provider;

    public DynamicRoutingDataSource(String primary, DynamicDataSourceProvider provider) {
        this.primary = primary;
        this.provider = provider;
    }

    /**
     * 存储所有数据源
     */
    private Map<String, DataSource> dataSources = new LinkedHashMap<>();

    /**
     * @return
     */
    @Override
    public DataSource determineTargetDataSource() {
        return getDataSource(DynamicDataSourceContextHolder.peek());
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.peek();
    }

    private DataSource determinePrimaryDataSource() {
        return dataSources.get(primary);
    }

    /**
     * 通过名称获取数据源
     *
     * @param ds
     *
     * @return
     */
    private DataSource getDataSource(String ds) {
        if (StringUtils.isEmpty(ds)) {
            return determinePrimaryDataSource();
        } else if (dataSources.containsKey(ds)) {
            return dataSources.get(ds);
        }
        return determinePrimaryDataSource();
    }

    @Override
    public void afterPropertiesSet() {
        Map<String, DataSource> dataSourceMap = provider.loadDataSource();
        dataSourceMap.forEach(this::addDataSource);
        if (dataSources.containsKey(primary)) {
            log.info("默认数据源为: [{}]", primary);
        } else {
            throw new RuntimeException("请设置默认数据源");
        }
    }

    /**
     * 添加数据源
     * @param name
     * @param dataSource
     */
    private synchronized void addDataSource(String name, DataSource dataSource) {
        if (!dataSources.containsKey(name)) {
            dataSources.put(name, dataSource);
            log.info("成功加载数据源 [{}]", name);
        } else {
            log.info("数据源 [{}] 已存在", name);
        }
    }

    /**
     * 获取当前数据源
     *
     * @return
     */
    public DataSource getCurrentDataSource() {
        return this.determineTargetDataSource();
    }

    /**
     * 获取当前所有数据源
     *
     * @return
     */
    public Map<String, DataSource> getAllDataSource() {
        return dataSources;
    }

}
