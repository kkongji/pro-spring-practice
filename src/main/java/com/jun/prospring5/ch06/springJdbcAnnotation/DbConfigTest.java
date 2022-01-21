package com.jun.prospring5.ch06.springJdbcAnnotation;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DbConfigTest {
    private static Logger logger = LoggerFactory.getLogger(DbConfigTest.class);

    @Test
    public void testOne() throws SQLException {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch06/springJdbcAnnotations/dirvermanager-cfg-01.xml");
        ctx.refresh();

        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);
        testDataSource(dataSource);

        ctx.close();
    }

    @Test
    public void testTwo() throws SQLException {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(DbConfig.class);

        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);
        testDataSource(dataSource);

        ctx.close();
    }

    private void testDataSource(DataSource dataSource) throws SQLException {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT 1");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int mockVal = resultSet.getInt("1");
                assertTrue(mockVal == 1);
                logger.info("성공");
            }
            statement.close();
        } catch (SQLException e) {
            logger.error("예상치 못한 에러 발생", e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
