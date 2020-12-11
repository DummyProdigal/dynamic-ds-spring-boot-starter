# dynamic-ds-spring-boot-starter

## 配置结构

动态数据源采用starter组件方式构建，这样开发者就不必关心各种依赖的注入、配置的管理，对开发者透明地注入了数据源相关bean。

### 配置文件说明

> 基础默认配置

```yml
spring:
  ds:
    basic:
      druid:
        initialSize: 5
        maxActive: 10
        maxWait: 30000
        minIdle: 5
        time-between-eviction-runs-millis: 600000
        min-evictable-idle-time-millis: 300000
        test-on-borrow: false
        test-on-return: false
        test-while-idle: true
        validationQuery: 'select 1'
        keep-alive: true
        # 长时间不归还连接则强制收回
        # 回收周期随timeBetweenEvictionRunsMillis进行，如果连接为从连接池借出状态，并且未执行任何sql，并且从借出时间起已超过removeAbandonedTimeout时间，则强制归还连接到连接池中
        remove-abandoned: true
        # 80秒后强制回收连接
        remove-abandoned-timeout-millis: 80
        # 关闭abanded连接时输出错误日志，这样出现连接泄露时可以通过错误日志定位忘记关闭连接的位置
        log-abandoned: true
        pool-prepared-statements: false
        max-pool-prepared-statement-per-connection-size: 20
        
```

**默认配置在依赖包中，其中的属性可以被上游配置的属性覆盖。该配置根据后期项目的使用情况来进行优化。**

> 自定义配置

```yml
spring:
  ds:
    dynamic:
      # 数据库加密密钥
      publicKey: miyao
      # 该参数必须，用于指定默认数据源
      primary: datasource1
      # jdbc多数据源配置，可在某个数据源中定义局部属性覆盖全局属性
      dataSources:
        # 数据源名字自定义
        datasource1:
          username: root
          password: POCRqiCurCA=
          url: jdbc:mysql://ip:3306/common?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true
          driver-class-name: com.mysql.jdbc.Driver
          # 数据源类型：Druid
          type: com.alibaba.druid.pool.DruidDataSource
          druid: 
          	maxActive: 50
          publicKey: justbond111
        datasource2:
          username: root
          # 加密后的密文
          password: POCRqiCurCA=
          url: jdbc:mysql://ip:3306/test?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true
          driver-class-name: com.mysql.jdbc.Driver
          # 数据源类型：Druid
          type: com.alibaba.druid.pool.DruidDataSource
      # 全局连接池配置
      druid:
        # 配置Druid的其他参数，以下配置必须增加一个配置文件才能有效
        # 初始化大小，最小，最大
        initialSize: 20
        minIdle: 10
        maxActive: 100
        # 获取连接等待超时的时间
        maxWait: 60000
        # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        timeBetweenEvictionRunsMillis: 60000
        # 配置一个连接在池中最小生存的时间，单位是毫秒
        minEvictableIdleTimeMillis: 300000
        validationQuery: SELECT 1 FROM DUAL
        testWhileIdle: true
        testOnBorrow: false
        testOnReturn: false
        poolPreparedStatements: true
        # 打开PSCache，并且指定每个连接上PSCache的大小
        maxPoolPreparedStatementPerConnectionSize: 20
        # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
        connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
        # 合并多个DruidDataSource的监控数据
        useGlobalDataSourceStat: true
        # web监控页面
        web-stat-filter:
          enabled: false
        stat-view-servlet:
          enabled: false
          login-username: root
          login-password: 123456
        # springboot 1.x需要配置该属性；springboot2.x后就可以不用配置，默认不开启
        # springboot 1.x自动装配不支持null值，会报错
        # 根据需要打开此配置，特别是 stat，会监控所有的sql，并缓存到内存，很消耗内存的。
        filter:
          # 防火墙配置，参照wallConfig配置
          wall:
            enabled: false
          # 监控配置，参照statFilter配置
          stat:
            enabled: false
            slow-sql-millis: 1
          # 日志配置，Slf4jFilter配置
          slf4j:
            enabled: false
```
