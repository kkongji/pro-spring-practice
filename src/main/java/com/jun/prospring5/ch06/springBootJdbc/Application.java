package com.jun.prospring5.ch06.springBootJdbc;

import com.jun.prospring5.ch06.plainJdbc.SingerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

        assert (ctx != null);

        SingerDao singerDao = ctx.getBean(SingerDao.class);
        String singerName = singerDao.findNameById(1L);
        logger.info("조회된 가수: " + singerName);

        System.in.read();
        ctx.close();
    }
}
