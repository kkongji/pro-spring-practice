package com.jun.prospring5.ch04.initializingBeanDestroy;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.File;
import java.io.IOException;

public class DestructiveBean {
    private File file;
    private String filePath;

    public void afterPropertiesSet() throws IOException {
        System.out.println("파일을 초기화합니다.");

        if (filePath == null) {
            throw new IllegalArgumentException(DestructiveBean.class + "에 filePath 프로퍼티를 지정해야 합니다");
        }

        this.file = new File(filePath);
        this.file.createNewFile();
        System.out.println("파일 존재여부: " + file.exists());
    }

    public void destroy() {
        System.out.println("빈을 소멸합니다.");

        if (!file.delete()) {
            System.out.println("에러: 파일 삭제에 실패했습니다.");
        }

        System.out.println("파일 존재여부: " + file.exists());
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch04/initializingBeanDestroy/app-context-xml.xml");
        ctx.refresh();

        DestructiveBean bean = ctx.getBean("destructiveBean", DestructiveBean.class);

        System.out.println("destroy() 호출 시작");
        ctx.destroy();
        System.out.println("destroy() 호출 종료");
    }
}
