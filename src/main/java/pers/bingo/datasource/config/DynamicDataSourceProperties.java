package pers.bingo.datasource.config;

import pers.bingo.datasource.config.hikari.HikariDataSourceConfig;
import pers.bingo.datasource.constant.BasicConstants;
import pers.bingo.datasource.config.druid.DruidDataSourceConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.Map;

/**
 * @Author GouBin
 * @Date 2020-10-21
 * @Version 1.0
 * @Modified_By
 */
@ConfigurationProperties(BasicConstants.PREFIX)
public class DynamicDataSourceProperties {

    /**
     * 默认库， 默认master
     */
    private String primary = "master";
    /**
     * 所有数据源
     */
    private Map<String, DataSourceProperty> dataSources;
    /** 全局Druid参数配置 **/
    @NestedConfigurationProperty
    private DruidDataSourceConfig druid = new DruidDataSourceConfig();
    /** 全局Hikari参数配置 **/
    @NestedConfigurationProperty
    private HikariDataSourceConfig hikari = new HikariDataSourceConfig();
    /** 全局解密公钥 **/
    private String publicKey = "justbond";

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public Map<String, DataSourceProperty> getDataSources() {
        return dataSources;
    }

    public void setDataSources(Map<String, DataSourceProperty> dataSources) {
        this.dataSources = dataSources;
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
}
