package com.jinkme.framtest.common.entity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @ClassName Header
 * @Description TODO
 * @Author zhouhui
 * @Date 2019/7/1 10:15
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Header {
    String chieseName();
}
