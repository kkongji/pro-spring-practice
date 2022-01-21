package com.jun.prospring5.ch06.springJdbcEmbedded;

import com.jun.prospring5.ch06.dao.SingerDao;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class JdbcSingerDao implements SingerDao, InitializingBean {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        MySQLErrorCodesTranslator errorTranslator = new MySQLErrorCodesTranslator();
        errorTranslator.setDataSource(dataSource);
        jdbcTemplate.setExceptionTranslator(errorTranslator);
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public String findNameById(Long id) {
        String sql = "select first_name || ' ' || last_name from singer where id = :singerId";
        Map<String, Object> namedParameter = new HashMap<>();
        namedParameter.put("singerId", id);
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameter, String.class);
        //return jdbcTemplate.queryForObject("select first_name || ' ' || last_name from singer where id = ?", new Object[]{id}, String.class);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        throw new BeanCreationException("SingerDao의 NamedParameterJdbcTemplate가 null 입니다.");
    }
}
