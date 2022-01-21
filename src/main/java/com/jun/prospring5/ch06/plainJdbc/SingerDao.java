package com.jun.prospring5.ch06.plainJdbc;

import java.util.List;

public interface SingerDao {
    List<Singer> findAll();

    List<Singer> findByFirstName(String firstName);

    String findNameById(Long id);

    String findLastNameById();

    String findFirstNameById(Long id);

    void insert(Singer singer);

    void update(Singer singer);

    void delete();

    List<Singer> findAllWithAlbums();

    void insertWithAlbum(Singer singer);
}
