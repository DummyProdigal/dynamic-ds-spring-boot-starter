package pers.bingo.datasource.annotation;

import java.lang.annotation.*;

/**
 * @Author GouBin
 * @Date 2020-10-19
 * @Version 1.0
 * @Modified_By
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DS {

    /**
     * 数据源名称
     * @return
     */
    String value();
}
