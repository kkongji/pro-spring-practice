package com.jun.prospring5.ch06.plainJdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

public class AnnotationJdbcTest {
    private GenericApplicationContext ctx;
    private SingerDao singerDao;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);
    }

    @Test
    public void testFindAll() {
        List<Singer> singers = singerDao.findAll();
        assertTrue(singers.size() == 3);
        listSingers(singers);
    }

    @Test
    public void testFindByFirstName() {
        List<Singer> singers = singerDao.findByFirstName("John");
        assertTrue(singers.size() == 1);
        listSingers(singers);
    }

    @Test
    public void testSingerUpdate() {
        Singer singer = new Singer();
        singer.setId(1L);
        singer.setFirstName("John Clayton");
        singer.setLastName("Mayer");
        singer.setBirthDate(new Date(new GregorianCalendar(1977, 9, 16).getTime().getTime()));
        singerDao.update(singer);

        List<Singer> singers = singerDao.findAll();
        listSingers(singers);
    }

    @Test
    public void testSingerInsert() {
        Singer singer = new Singer();
        singer.setFirstName("Ed");
        singer.setLastName("Sheeran");
        singer.setBirthDate(new Date(new GregorianCalendar(1991, 1, 17).getTime().getTime()));
        singerDao.insert(singer);

        List<Singer> singers = singerDao.findAll();
        listSingers(singers);
    }

    @Test
    public void testSingerInsertWithAlbum() {
        Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(new Date(new GregorianCalendar(1940, 8, 16).getTime().getTime()));

        Album album = new Album();
        album.setTitle("My Kind of Blues");
        album.setReleaseDate(new Date(new GregorianCalendar(1962, 3, 20).getTime().getTime()));
        singer.addAlbum(album);

        singerDao.insertWithAlbum(singer);

        List<Singer> singers = singerDao.findAllWithAlbums();
        listSingers(singers);
    }

    @Test
    public void testFindFirstNameById() {
        String firstName = singerDao.findFirstNameById(2L);
        assertEquals("Eric", firstName);
        System.out.println("조회된 값: " + firstName);
    }

    @After
    public void tearDown() {
        ctx.close();
    }

    private void listSingers(List<Singer> singers) {
        singers.forEach(singer -> {
            System.out.println(singer);
            if (singer.getAlbums() != null) {
                for (Album album : singer.getAlbums()) {
                    System.out.println("\t--> " + album);
                }
            }
        });
    }

}
