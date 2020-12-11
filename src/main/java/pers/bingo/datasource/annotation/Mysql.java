package pers.bingo.datasource.annotation;

import pers.bingo.datasource.constant.DsConstants;

import java.lang.annotation.*;

/**
 * 从节点
 *
 * @Author GouBin
 * @Date 2020-10-20
 * @Version 1.0
 * @Modified_By
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@DS(DsConstants.MYSQL)
public @interface Mysql {
}
