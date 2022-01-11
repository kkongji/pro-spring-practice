package com.jun.prospring5.ch05.security;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class SecurityAdvice implements MethodBeforeAdvice {
    private SecurityManager securityManager;

    public SecurityAdvice() {
        this.securityManager = new SecurityManager();
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        UserInfo user = securityManager.getLoggedOnUser();

        if (user == null) {
            System.out.println("인증되지 않은 사용자 입니다");
            throw new SecurityException("메서드를 호출하려면 로그인이 필요합니다." + method.getName());
        } else if ("John".equals(user.getUserName())) {
            System.out.println("John 사용자로 로그인 했습니다 -OKAY!");
        } else {
            System.out.println(user.getUserName() + "사용자로 로그인 하였습니다." + "- NOT GOOD :(");
            throw new SecurityException(user.getUserName() + "사용자는 메서드에 대한 권한이 없습니다." + method.getName());
        }
    }
}
