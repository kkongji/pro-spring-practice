package com.jun.prospring5.ch06.dao;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;

import javax.sql.DataSource;

public class JdbcSingerDao implements SingerDao, InitializingBean {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource == null) {
            throw new BeanCreationException("SingerDao에 dataSource를 구성해야 합니다.");
        }
    }

    @Override
    public String findNameById(Long id) {
        return null;
    }
}
