package com.silince.interview;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @program: 多线程高并发
 * @description: 线程交替输出 wait notify + CountDownLatch
 * 用两个线程，一个输出字母，一个输出数字，交替输出1A2B3C...26Z
 * @author: Silince
 * @create: 2020-09-03 10:15
 **/
public class OutputByTurns2 {
    public static void main(String[] args) throws Exception {
        final Object o = new Object();
        int a = (int) 'a';
        CountDownLatch countDownLatch = new CountDownLatch(1); // 控制执行顺序


        new Thread(() -> {
            // method
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o) {
                for (int i = 0; i < 26; i++) {

                    System.out.println((char) (a + i));
                    try {
                        o.notify();
                        o.wait();// 让出锁 持有该对象锁的线程去wait()
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }, "字母").start();

        new Thread(() -> {
            // method
            countDownLatch.countDown();
            synchronized (o) {
                for (int i = 0; i < 26; i++) {
                    System.out.println(i + 1);
                    try {
                        o.notify(); // 随机唤醒一个 wait 线程
                        o.wait();// 让出锁 持有该对象锁的线程去wait()
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify(); // ⚠️ 不然程序停止不了，一直互相通知
            }
        }, "数字").start();
    }
}
