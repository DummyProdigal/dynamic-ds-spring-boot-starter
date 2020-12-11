package pers.bingo.datasource.config.druid;

import java.util.Properties;

/**
 * Druid参数配置
 *
 * @Author GouBin
 * @Date 2020-10-23
 * @Version 1.0
 * @Modified_By
 */
public class DruidDataSourceConfig {

    /** 初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时 **/
    private Integer initialSize;
    /** 最大连接池数量 **/
    private Integer maxActive;
    /** 获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。 **/
    private Integer maxWait;
    /**
     * <p>1. Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接</p>
     * <p>2. testWhileIdle的判断依据，详细看testWhileIdle属性的说明</p>
     **/
    private Integer minIdle;
    private Long timeBetweenEvictionRunsMillis;
    /** 配置监控统计日志的输出间隔，单位毫秒，每次输出所有统计数据会重置，酌情开启 **/
    private Long timeBetweenLogStatsMillis;
    /**  **/
    private Integer statSqlMaxSize;
    /** 连接保持空闲而不被驱逐的最小时间 **/
    private Long minEvictableIdleTimeMillis;
    /**  **/
    private Long maxEvictableIdleTimeMillis;
    /**  **/
    private String defaultCatalog;
    /**  **/
    private Boolean defaultAutoCommit;
    /**  **/
    private Boolean defaultReadOnly;
    /**  **/
    private Integer defaultTransactionIsolation;
    /** 设置从连接池获取连接时是否检查连接有效性，true时，如果连接空闲时间超过minEvictableIdleTimeMillis进行检查，否则不检查;false时，不检查  **/
    private Boolean testWhileIdle;
    /** 设置从连接池获取连接时是否检查连接有效性，true时，每次都检查;false时，不检查 **/
    private Boolean testOnBorrow;
    /** 设置往连接池归还连接时是否检查连接有效性，true时，每次都检查;false时，不检查 **/
    private Boolean testOnReturn;
    /** 检验连接是否有效的查询语句。如果数据库Driver支持ping()方法，则优先使用ping()方法进行检查，否则使用validationQuery查询进行检查。 **/
    private String validationQuery;
    /** 单位：秒，检测连接是否有效的超时时间。底层调用jdbc Statement对象的void setQueryTimeout(int seconds)方法 **/
    private Integer validationQueryTimeout;
    /**  **/
    private Boolean useGlobalDataSourceStat;
    /**  **/
    private Boolean clearFiltersEnable;
    /**  **/
    private Boolean resetStatEnable;
    /**  **/
    private Integer notFullTimeoutRetryCount;
    /**  **/
    private Integer maxWaitThreadCount;
    /**  **/
    private Boolean failFast;
    /**  **/
    private Long phyTimeoutMillis;
    /** 打开后，增强timeBetweenEvictionRunsMillis的周期性连接检查，minIdle内的空闲连接，每次检查强制验证连接有效性.  **/
    private Boolean keepAlive;
    /** 打开PSCache，并且指定每个连接上PSCache的大小，Oracle等支持游标的数据库，打开此开关，会以数量级提升性能 **/
    private Boolean poolPreparedStatements;
    /**  **/
    private Boolean initVariants;
    /**  **/
    private Boolean initGlobalVariants;
    /**  **/
    private Boolean useUnfairLock;
    /**  **/
    private Boolean killWhenSocketReadTimeout;
    /**  **/
    private Properties connectionProperties;
    /**  **/
    private Integer maxPoolPreparedStatementPerConnectionSize;
    /**  **/
    private String initConnectionSqls;
    /**  **/
    private Boolean sharePreparedStatements;
    /**  **/
    private Integer connectionErrorRetryAttempts;
    /**  **/
    private Boolean breakAfterAcquireFailure;
    /** 接泄露检查，打开removeAbandoned功能 , 连接从连接池借出后，长时间不归还，将触发强制回连接。
     * 回收周期随timeBetweenEvictionRunsMillis进行，如果连接为从连接池借出状态，并且未执行任何sql。
     * 从借出时间起已超过removeAbandonedTimeout时间，则强制归还连接到连接池中。
     * **/
    private Boolean removeAbandoned;
    /**  **/
    private Integer removeAbandonedTimeoutMillis;
    /** 关闭abanded连接时输出错误日志，这样出现连接泄露时可以通过错误日志定位忘记关闭连接的位置  **/
    private Boolean logAbandoned;
    /**  **/
    private Integer queryTimeout;
    /**  **/
    private Integer transactionQueryTimeout;

    public Integer getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(Integer initialSize) {
        this.initialSize = initialSize;
    }

    public Integer getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public Integer getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(Long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public Long getTimeBetweenLogStatsMillis() {
        return timeBetweenLogStatsMillis;
    }

    public void setTimeBetweenLogStatsMillis(Long timeBetweenLogStatsMillis) {
        this.timeBetweenLogStatsMillis = timeBetweenLogStatsMillis;
    }

    public Integer getStatSqlMaxSize() {
        return statSqlMaxSize;
    }

    public void setStatSqlMaxSize(Integer statSqlMaxSize) {
        this.statSqlMaxSize = statSqlMaxSize;
    }

    public Long getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(Long minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public Long getMaxEvictableIdleTimeMillis() {
        return maxEvictableIdleTimeMillis;
    }

    public void setMaxEvictableIdleTimeMillis(Long maxEvictableIdleTimeMillis) {
        this.maxEvictableIdleTimeMillis = maxEvictableIdleTimeMillis;
    }

    public String getDefaultCatalog() {
        return defaultCatalog;
    }

    public void setDefaultCatalog(String defaultCatalog) {
        this.defaultCatalog = defaultCatalog;
    }

    public Boolean getDefaultAutoCommit() {
        return defaultAutoCommit;
    }

    public void setDefaultAutoCommit(Boolean defaultAutoCommit) {
        this.defaultAutoCommit = defaultAutoCommit;
    }

    public Boolean getDefaultReadOnly() {
        return defaultReadOnly;
    }

    public void setDefaultReadOnly(Boolean defaultReadOnly) {
        this.defaultReadOnly = defaultReadOnly;
    }

    public Integer getDefaultTransactionIsolation() {
        return defaultTransactionIsolation;
    }

    public void setDefaultTransactionIsolation(Integer defaultTransactionIsolation) {
        this.defaultTransactionIsolation = defaultTransactionIsolation;
    }

    public Boolean getTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(Boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public Boolean getTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(Boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public Integer getValidationQueryTimeout() {
        return validationQueryTimeout;
    }

    public void setValidationQueryTimeout(Integer validationQueryTimeout) {
        this.validationQueryTimeout = validationQueryTimeout;
    }

    public Boolean getUseGlobalDataSourceStat() {
        return useGlobalDataSourceStat;
    }

    public void setUseGlobalDataSourceStat(Boolean useGlobalDataSourceStat) {
        this.useGlobalDataSourceStat = useGlobalDataSourceStat;
    }

    public Boolean getClearFiltersEnable() {
        return clearFiltersEnable;
    }

    public void setClearFiltersEnable(Boolean clearFiltersEnable) {
        this.clearFiltersEnable = clearFiltersEnable;
    }

    public Boolean getResetStatEnable() {
        return resetStatEnable;
    }

    public void setResetStatEnable(Boolean resetStatEnable) {
        this.resetStatEnable = resetStatEnable;
    }

    public Integer getNotFullTimeoutRetryCount() {
        return notFullTimeoutRetryCount;
    }

    public void setNotFullTimeoutRetryCount(Integer notFullTimeoutRetryCount) {
        this.notFullTimeoutRetryCount = notFullTimeoutRetryCount;
    }

    public Integer getMaxWaitThreadCount() {
        return maxWaitThreadCount;
    }

    public void setMaxWaitThreadCount(Integer maxWaitThreadCount) {
        this.maxWaitThreadCount = maxWaitThreadCount;
    }

    public Boolean getFailFast() {
        return failFast;
    }

    public void setFailFast(Boolean failFast) {
        this.failFast = failFast;
    }

    public Long getPhyTimeoutMillis() {
        return phyTimeoutMillis;
    }

    public void setPhyTimeoutMillis(Long phyTimeoutMillis) {
        this.phyTimeoutMillis = phyTimeoutMillis;
    }

    public Boolean getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Boolean getPoolPreparedStatements() {
        return poolPreparedStatements;
    }

    public void setPoolPreparedStatements(Boolean poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
    }

    public Boolean getInitVariants() {
        return initVariants;
    }

    public void setInitVariants(Boolean initVariants) {
        this.initVariants = initVariants;
    }

    public Boolean getInitGlobalVariants() {
        return initGlobalVariants;
    }

    public void setInitGlobalVariants(Boolean initGlobalVariants) {
        this.initGlobalVariants = initGlobalVariants;
    }

    public Boolean getUseUnfairLock() {
        return useUnfairLock;
    }

    public void setUseUnfairLock(Boolean useUnfairLock) {
        this.useUnfairLock = useUnfairLock;
    }

    public Boolean getKillWhenSocketReadTimeout() {
        return killWhenSocketReadTimeout;
    }

    public void setKillWhenSocketReadTimeout(Boolean killWhenSocketReadTimeout) {
        this.killWhenSocketReadTimeout = killWhenSocketReadTimeout;
    }

    public Properties getConnectionProperties() {
        return connectionProperties;
    }

    public void setConnectionProperties(Properties connectionProperties) {
        this.connectionProperties = connectionProperties;
    }

    public Integer getMaxPoolPreparedStatementPerConnectionSize() {
        return maxPoolPreparedStatementPerConnectionSize;
    }

    public void setMaxPoolPreparedStatementPerConnectionSize(Integer maxPoolPreparedStatementPerConnectionSize) {
        this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
    }

    public String getInitConnectionSqls() {
        return initConnectionSqls;
    }

    public void setInitConnectionSqls(String initConnectionSqls) {
        this.initConnectionSqls = initConnectionSqls;
    }

    public Boolean getSharePreparedStatements() {
        return sharePreparedStatements;
    }

    public void setSharePreparedStatements(Boolean sharePreparedStatements) {
        this.sharePreparedStatements = sharePreparedStatements;
    }

    public Integer getConnectionErrorRetryAttempts() {
        return connectionErrorRetryAttempts;
    }

    public void setConnectionErrorRetryAttempts(Integer connectionErrorRetryAttempts) {
        this.connectionErrorRetryAttempts = connectionErrorRetryAttempts;
    }

    public Boolean getBreakAfterAcquireFailure() {
        return breakAfterAcquireFailure;
    }

    public void setBreakAfterAcquireFailure(Boolean breakAfterAcquireFailure) {
        this.breakAfterAcquireFailure = breakAfterAcquireFailure;
    }

    public Boolean getRemoveAbandoned() {
        return removeAbandoned;
    }

    public void setRemoveAbandoned(Boolean removeAbandoned) {
        this.removeAbandoned = removeAbandoned;
    }

    public Integer getRemoveAbandonedTimeoutMillis() {
        return removeAbandonedTimeoutMillis;
    }

    public void setRemoveAbandonedTimeoutMillis(Integer removeAbandonedTimeoutMillis) {
        this.removeAbandonedTimeoutMillis = removeAbandonedTimeoutMillis;
    }

    public Boolean getLogAbandoned() {
        return logAbandoned;
    }

    public void setLogAbandoned(Boolean logAbandoned) {
        this.logAbandoned = logAbandoned;
    }

    public Integer getQueryTimeout() {
        return queryTimeout;
    }

    public void setQueryTimeout(Integer queryTimeout) {
        this.queryTimeout = queryTimeout;
    }

    public Integer getTransactionQueryTimeout() {
        return transactionQueryTimeout;
    }

    public void setTransactionQueryTimeout(Integer transactionQueryTimeout) {
        this.transactionQueryTimeout = transactionQueryTimeout;
    }
}
