package com.jun.prospring5.ch06.springBootJdbc;

import com.jun.prospring5.ch06.plainJdbc.Singer;
import com.jun.prospring5.ch06.plainJdbc.SingerDao;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcSingerDao implements SingerDao, InitializingBean {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Singer> findAll() {
        return null;
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public String findNameById(Long id) {
        return jdbcTemplate.queryForObject("SELECT first_name || '' || last_name FROM SINGER WHERE id = ?", new Object[]{id}, String.class);
    }

    @Override
    public String findLastNameById() {
        return null;
    }

    @Override
    public String findFirstNameById(Long id) {
        return null;
    }

    @Override
    public void insert(Singer singer) {

    }

    @Override
    public void update(Singer singer) {

    }

    @Override
    public void delete() {

    }

    @Override
    public List<Singer> findAllWithAlbums() {
        return null;
    }

    @Override
    public void insertWithAlbum(Singer singer) {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
