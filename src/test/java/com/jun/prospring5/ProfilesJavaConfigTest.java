package com.jun.prospring5;

import com.jun.prospring5.ch04.profile.FoodProviderService;
import com.jun.prospring5.ch04.profile.HighschoolConfig;
import com.jun.prospring5.ch04.profile2.KindergartenConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={KindergartenConfig.class, HighschoolConfig.class})
@ActiveProfiles("kindergarten")
public class ProfilesJavaConfigTest {
    @Autowired
    FoodProviderService foodProviderService;

    @Test
    public void testProvider() {
        System.out.println("테스트 시작");
        assertTrue(foodProviderService.provideLunchSet() != null);
        assertFalse(foodProviderService.provideLunchSet().isEmpty());
        assertEquals(3, foodProviderService.provideLunchSet().size());
        System.out.println("테스트 종료");
    }
}
