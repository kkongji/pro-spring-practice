package com.jun.prospring5.ch06.plainJdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class JdbcSingerDao implements SingerDao, InitializingBean {
    private static Logger logger = LoggerFactory.getLogger(JdbcSingerDao.class);
    private DataSource dataSource;
    private SelectAllSingers selectAllSingers;
    private SelectSingerByFirstName selectSingerByFirstName;
    private UpdateSinger updateSinger;
    private InsertSinger insertSinger;
    private InsertSingerAlbum insertSingerAlbum;
    private StoredFunctionFirstNameById storedFunctionFirstNameById;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.selectAllSingers = new SelectAllSingers(dataSource);
        this.selectSingerByFirstName = new SelectSingerByFirstName(dataSource);
        this.updateSinger = new UpdateSinger(dataSource);
        this.insertSinger = new InsertSinger(dataSource);
        this.insertSingerAlbum = new InsertSingerAlbum(dataSource);
        this.storedFunctionFirstNameById = new StoredFunctionFirstNameById(dataSource);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource == null) {
            throw new BeanCreationException("SingerDao에 dataSource를 구성해야 합니다.");
        }
    }

    @Override
    public List<Singer> findAll() {
        return selectAllSingers.execute();
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("first_name", firstName);
        return selectSingerByFirstName.executeByNamedParam(paramMap);
    }

    @Override
    public String findFirstNameById(Long id) {
        List<String> result = storedFunctionFirstNameById.execute(id);
        return result.get(0);
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
    public void insert(Singer singer) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("first_name", singer.getFirstName());
        paramMap.put("last_name", singer.getLastName());
        paramMap.put("birth_date", singer.getBirthDate());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertSinger.updateByNamedParam(paramMap, keyHolder);
        singer.setId(keyHolder.getKey().longValue());
        logger.info("새 가수 등록. id: " + singer.getId());
    }

    @Override
    public void update(Singer singer) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("first_name", singer.getFirstName());
        paramMap.put("last_name", singer.getLastName());
        paramMap.put("birth_date", singer.getBirthDate());
        paramMap.put("id", singer.getId());
        updateSinger.updateByNamedParam(paramMap);
        logger.info("기존 가수정보 수정. id: " + singer.getId());
    }

    @Override
    public void insertWithAlbum(Singer singer) {
        insertSingerAlbum = new InsertSingerAlbum(getDataSource());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("first_name", singer.getFirstName());
        paramMap.put("last_name", singer.getLastName());
        paramMap.put("birth_date", singer.getBirthDate());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertSinger.updateByNamedParam(paramMap, keyHolder);
        singer.setId(keyHolder.getKey().longValue());
        List<Album> albums = singer.getAlbums();
        if (albums != null) {
            for (Album album : albums) {
                paramMap = new HashMap<>();
                paramMap.put("singer_id", singer.getId());
                paramMap.put("title", album.getTitle());
                paramMap.put("release_date", album.getReleaseDate());
                insertSingerAlbum.updateByNamedParam(paramMap);
            }
        }
        insertSingerAlbum.flush();
    }

    @Override
    public void delete() {

    }

    @Override
    public List<Singer> findAllWithAlbums() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "SELECT S.id,\n" +
                "       S.first_name,\n" +
                "       S.last_name,\n" +
                "       S.birth_date,\n" +
                "       A.id AS album_id,\n" +
                "       A.title,\n" +
                "       A.release_date\n" +
                "FROM SINGER S\n" +
                "         LEFT JOIN ALBUM A ON S.id = A.singer_id";
        //return jdbcTemplate.query(sql, new SingerWithAlbumExtractor());
        return jdbcTemplate.query(sql, rs -> {
            Map<Long, Singer> map = new HashMap<>();
            Singer singer;
            while (rs.next()) {
                Long id = rs.getLong("id");
                singer = map.get(id);
                if (singer == null) {
                    singer = new Singer();
                    singer.setId(id);
                    singer.setFirstName(rs.getString("first_name"));
                    singer.setLastName(rs.getString("last_name"));
                    singer.setBirthDate(rs.getDate("birth_date"));
                    singer.setAlbums(new ArrayList<>());
                    map.put(id, singer);
                }
                Long albumId = rs.getLong("album_id");
                if (albumId > 0) {
                    Album album = new Album();
                    album.setId(albumId);
                    album.setSingerId(id);
                    album.setTitle(rs.getString("title"));
                    album.setReleaseDate(rs.getDate("release_date"));
                    singer.getAlbums().add(album);
                }
            }
            return new ArrayList<>(map.values());
        });
    }

    private class SingerWithAlbumExtractor implements ResultSetExtractor<List<Singer>> {
        @Override
        public List<Singer> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<Long, Singer> map = new HashMap<>();
            Singer singer;
            while (rs.next()) {
                Long id = rs.getLong("id");
                singer = map.get(id);
                if (singer == null) {
                    singer = new Singer();
                    singer.setId(id);
                    singer.setFirstName(rs.getString("first_name"));
                    singer.setLastName(rs.getString("last_name"));
                    singer.setBirthDate(rs.getDate("birth_date"));
                    singer.setAlbums(new ArrayList<>());
                    map.put(id, singer);
                }
                Long albumId = rs.getLong("album_id");
                if (albumId > 0) {
                    Album album = new Album();
                    album.setId(albumId);
                    album.setSingerId(id);
                    album.setTitle(rs.getString("title"));
                    album.setReleaseDate(rs.getDate("release_date"));
                    singer.getAlbums().add(album);
                }
            }
            return new ArrayList<>(map.values());
        }
    }
}
