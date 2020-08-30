package main.java.com.silince.juc;

import java.util.concurrent.TimeUnit;

/**
 * @program: 多线程高并发
 * @description: 什么是线程
 * @author: Silince
 * @create: 2020-08-26 16:52
 **/
public class T01_WhatsThread {
    private static class T1 extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1");
            }
        }
    }

    public static void main(String[] args) {
        // new T1().run
        new T1().start();
        for (int i = 0; i < 3; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main");
        }
    }
}
