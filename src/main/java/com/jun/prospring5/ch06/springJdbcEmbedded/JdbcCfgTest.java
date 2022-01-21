package com.jun.prospring5.ch06.springJdbcEmbedded;

import com.jun.prospring5.ch06.dao.SingerDao;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class JdbcCfgTest {

    @Test
    public void testH2DB() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch06/springJdbcEmbedded/embedded-h2-cfg.xml");
        ctx.refresh();
        testDao(ctx.getBean(SingerDao.class));
        ctx.close();
    }

    private void testDao(SingerDao singerDao) {
        assertNotNull(singerDao);
        String singerName = singerDao.findNameById(1L);
        assertTrue("John Mayer".equals(singerName));
    }
}
