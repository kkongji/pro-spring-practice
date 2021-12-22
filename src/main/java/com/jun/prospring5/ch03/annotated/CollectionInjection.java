package com.jun.prospring5.ch03.annotated;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Service("injectCollection")
public class CollectionInjection {

    @Resource(name="map")
    private Map<String, Object> map;

    @Resource(name = "props")
    private Properties props;

    @Resource(name = "set")
    private Set set;

    @Resource(name = "list")
    private List list;

    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch03/annotated/app-context-annotation.xml");
        ctx.refresh();

        CollectionInjection instance = (CollectionInjection) ctx.getBean("injectCollection");
        instance.displayInfo();

        ctx.close();
    }

    public void displayInfo() {
        System.out.println("Map 내용:\n");
        map.entrySet().stream().forEach(e -> System.out.println("키: " + e.getKey() + " - 값: " + e.getValue()));

        System.out.println("\nProperties 내용:\n");
        props.entrySet().stream().forEach(e -> System.out.println("키: " + e.getKey() + " - 값: " + e.getValue()));

        System.out.println("\nSet 내용:\n");
        set.forEach(obj -> System.out.println("값: " + obj));

        System.out.println("\nList 내용:\n");
        list.forEach(obj -> System.out.println("값:  " + obj));
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public void setList(List list) {
        this.list = list;
    }
}
