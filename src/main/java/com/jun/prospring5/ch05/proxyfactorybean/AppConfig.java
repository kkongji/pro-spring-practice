package com.jun.prospring5.ch05.proxyfactorybean;

import com.jun.prospring5.ch05.isModify.Contact;
import com.jun.prospring5.ch05.isModify.IsModifiedAdvisor;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Contact guitarist() {
        Contact contact = new Contact();
        contact.setName("John Mayer");
        return contact;
    }

    @Bean
    public Advisor advisor() {
        return new IsModifiedAdvisor();
    }

    @Bean
    ProxyFactoryBean bean() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.addAdvisor(advisor());
        proxyFactoryBean.setTarget(guitarist());
        proxyFactoryBean.setProxyTargetClass(true);
        return proxyFactoryBean;
    }
}
