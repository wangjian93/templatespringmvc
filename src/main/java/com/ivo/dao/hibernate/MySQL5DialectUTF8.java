package com.ivo.dao.hibernate;

import org.hibernate.dialect.MySQL5Dialect;

/**
 * hibernate自建表默认采用的是latin，保存中文会乱码
 * 自定义扩展MySQL5Dialect，重写getTableTypeString方法，设为UTF-8编码
 * @author wangjian
 * @date 2018/11/9
 */
public class MySQL5DialectUTF8 extends MySQL5Dialect {

    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
