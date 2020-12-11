package pers.bingo.datasource.creator;

import pers.bingo.datasource.config.DataSourceProperty;
import pers.bingo.datasource.util.SqlScriptRunner;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * @Author GouBin
 * @Date 2020-10-22
 * @Version 1.0
 * @Modified_By
 */
public abstract class AbstractDataSourceCreator implements DataSourceCreator {

    /**
     * 执行脚本，初始化数据源
     * @param dataSource
     * @param property
     */
    protected void runScrip(DataSource dataSource, DataSourceProperty property) {
        String schema = property.getSchema();
        String data = property.getData();
        if (StringUtils.hasText(schema) || StringUtils.hasText(data)) {
            SqlScriptRunner runner = new SqlScriptRunner(property.isContinueOnError());
            if (StringUtils.hasText(schema)) {
                runner.runScript(dataSource, schema);
            }
            if (StringUtils.hasText(data)) {
                runner.runScript(dataSource, data);
            }
        }
    }

}
