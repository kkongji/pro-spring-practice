package com.jun.prospring5.ch06.springJdbcEmbedded;

import org.springframework.jdbc.core.JdbcTemplate;

public class CleanUp {
    JdbcTemplate jdbcTemplate;

    public CleanUp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private void destroy() {
    }
}
