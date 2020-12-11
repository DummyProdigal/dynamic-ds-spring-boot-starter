package pers.bingo.datasource.constant;

/**
 * @Author GouBin
 * @Date 2020-10-22
 * @Version 1.0
 * @Modified_By
 */
public interface DsConstants {

    /** 主库 **/
    String MASTER = "master";
    /** 从库 **/
    String SLAVE = "slave";
    /** Oracle **/
    String ORACLE = "Oracle";
    /** Mysql **/
    String MYSQL = "Mysql";

    /** Druid **/
    String DRUID_DATASOURCE = "com.alibaba.druid.pool.DruidDataSource";
    /** Hikari **/
    String HIKARI_DATASOURCE = "com.zaxxer.hikari.HikariDataSource";
}
