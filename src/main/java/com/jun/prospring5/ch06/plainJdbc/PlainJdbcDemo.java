package com.jun.prospring5.ch06.plainJdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PlainJdbcDemo {
    private static SingerDao singerDao = new PlainSingerDao();
    private static Logger logger = LoggerFactory.getLogger(PlainJdbcDemo.class);

    public static void main(String[] args) {
        listAllSingers();
    }

    private static void listAllSingers() {
        List<Singer> singers = singerDao.findAll();
        for (Singer s : singers) {
            logger.info(s.toString());
        }
    }
}
