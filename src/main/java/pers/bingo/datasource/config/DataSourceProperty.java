package pers.bingo.datasource.config;

import pers.bingo.datasource.config.druid.DruidDataSourceConfig;
import pers.bingo.datasource.config.hikari.HikariDataSourceConfig;
import pers.bingo.datasource.util.EncryptUtil;

import javax.sql.DataSource;

/**
 * @Author GouBin
 * @Date 2020-10-22
 * @Version 1.0
 * @Modified_By
 */
public class DataSourceProperty {

    /** 池名称 **/
    private String poolName;
    /** 池类型 **/
    private Class<? extends DataSource> type;
    /** 驱动名称 **/
    private String driverClassName;
    /** URL地址 **/
    private String url;
    /** 用户名 **/
    private String username;
    /** 密码 **/
    private String password;
    /** sql脚本路径，初始化sql模板（表结构等，不包含数据） **/
    private String schema;
    /** sql脚本路径，初始化sql数据 **/
    private String data;
    /** sql执行失败是否继续 默认true **/
    private boolean continueOnError = true;
    /** Druid参数配置 **/
    private DruidDataSourceConfig druid = new DruidDataSourceConfig();
    private HikariDataSourceConfig hikari = new HikariDataSourceConfig();
    /** 解密公钥（为空默认使用全局公钥） **/
    private String publicKey;

    public boolean isContinueOnError() {
        return continueOnError;
    }

    public void setContinueOnError(boolean continueOnError) {
        this.continueOnError = continueOnError;
    }

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    public Class<? extends DataSource> getType() {
        return type;
    }

    public void setType(Class<? extends DataSource> type) {
        this.type = type;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return decrypt(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public DruidDataSourceConfig getDruid() {
        return druid;
    }

    public void setDruid(DruidDataSourceConfig druid) {
        this.druid = druid;
    }

    public HikariDataSourceConfig getHikari() {
        return hikari;
    }

    public void setHikari(HikariDataSourceConfig hikari) {
        this.hikari = hikari;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    private String decrypt(String cipherStr) {
        return EncryptUtil.decrypt(publicKey, cipherStr);
    }
}
