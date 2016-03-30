package com.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by YanJun on 2016/3/30.
 */
public class DataAttribute {
    @Retention(RetentionPolicy.RUNTIME)

/**
 * @Target
 * 指示注释类型所适用的程序元素的种类，如果注释类型声明中不存在 Target 元注释，
 * 则声明的类型可以用在任一程序元素上。
 * ElementType.ANNOTATION_TYPE：注释类型声明
 * ElementType.CONSTRUCTOR：构造方法声明
 * ElementType.FILED：字段声明
 * ElementType.LOCAL_VARIABLE：局部变量声明
 * ElementType.METHOD：方法声明
 * ElementType.PACKAGE：包声明
 * ElementType.PARAMETER：参数声明
 * ElementType.TYPE：类、借口或枚举声明
 */
    @Target(ElementType.FIELD)
    public @interface NeedDataFormatter {
        boolean value() default false; //默认值
    }
}
