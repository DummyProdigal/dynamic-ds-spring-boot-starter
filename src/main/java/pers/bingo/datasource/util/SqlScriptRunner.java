package pers.bingo.datasource.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * sql脚本执行器
 *
 * @Author GouBin
 * @Date 2020-10-22
 * @Version 1.0
 * @Modified_By
 */
public class SqlScriptRunner {

    private static Logger log = LoggerFactory.getLogger(SqlScriptRunner.class);

    private final boolean continueOnError;

    private final String separator = ";";

    public SqlScriptRunner(boolean continueOnError) {
        this.continueOnError = continueOnError;
    }

    /**
     * 执行脚本
     * @param dataSource
     * @param sqlFile
     */
    public void runScript(DataSource dataSource, String sqlFile) {
        if (StringUtils.hasText(sqlFile)) {
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.setContinueOnError(continueOnError);
            populator.setSeparator(separator);
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            try {
                populator.addScripts(resolver.getResources(sqlFile));
                DatabasePopulatorUtils.execute(populator, dataSource);
            } catch (DataAccessException e) {
                log.warn("执行sql错误: ", e);
            } catch (Exception e1) {
                log.warn("从脚本文件 {} 初始化数据源失败: ", sqlFile, e1);
            }
        }
    }
}
