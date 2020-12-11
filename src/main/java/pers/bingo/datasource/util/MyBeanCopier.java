package pers.bingo.datasource.util;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

public class MyBeanCopier extends BeanCopier {
    @Override
    public void copy(Object o, Object o1, Converter converter) {
        System.out.println(o + "-----------" + o1);
        BeanCopier.Generator.getCurrent();
    }
}
