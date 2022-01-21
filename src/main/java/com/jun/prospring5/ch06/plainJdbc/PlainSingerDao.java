package com.jun.prospring5.ch06.plainJdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlainSingerDao implements SingerDao {
    private static Logger logger = LoggerFactory.getLogger(PlainSingerDao.class);

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("데이터베이스 드라이버를 찾을 수 없습니다!", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/MUSICDB?allowPublicKeyRetrieval=true&useSSL=true", "root", "1234");
    }

    private void closeConnection(Connection connection) {
        if (connection == null) {
            return;
        }

        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("데이터베이스 커넥션을 닫을 때 문제가 발생했습니다.", e);
        }
    }

    @Override
    public List<Singer> findAll() {
        List<Singer> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from SINGER");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Singer singer = new Singer();
                singer.setId(resultSet.getLong("ID"));
                singer.setFirstName(resultSet.getString("FIRST_NAME"));
                singer.setLastName(resultSet.getString("LAST_NAME"));
                singer.setBirthDate(resultSet.getDate("BIRTH_DATE"));
                result.add(singer);
            }
            statement.close();
        } catch (SQLException e) {
            logger.error("SELECT 실행 중 문제 발생", e);
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public String findNameById(Long id) {
        return null;
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
}
