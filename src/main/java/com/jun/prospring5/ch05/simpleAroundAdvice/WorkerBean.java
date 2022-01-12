package com.jun.prospring5.ch05.simpleAroundAdvice;

public class WorkerBean {
    public void doSOmeWork(int noOfTimes) {
        for (int i = 0; i < noOfTimes; i++) {
            work();
        }
    }

    private void work() {
        System.out.print("");
    }
}
