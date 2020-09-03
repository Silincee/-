package com.silince.interview;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: 多线程高并发
 * @description: 线程交替输出 ReentrantLock
 * 用两个线程，一个输出字母，一个输出数字，交替输出1A2B3C...26Z
 * @author: Silince
 * @create: 2020-09-03 10:15
 **/
public class OutputByTurns3 {
    public static void main(String[] args) {
        int a = (int) 'a';
        Lock lock = new ReentrantLock();
        CountDownLatch countDownLatch = new CountDownLatch(1); // 控制执行顺序
        Condition conditionNumber = lock.newCondition(); // 数字条件队列
        Condition conditionLetter = lock.newCondition(); // 字母条件队列

        new Thread(() -> {
            // method
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.lock();
            try {
                // ... method body
                for (int i = 0; i < 26; i++) {
                    System.out.println((char) (a + i));
                    conditionNumber.signal();
                    conditionLetter.await();
                }
                conditionNumber.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "字母").start();

        new Thread(() -> {
            // method
            countDownLatch.countDown();

            lock.lock();
            try {
                // ... method body
                for (int i = 0; i < 26; i++) {
                    System.out.println(i + 1);
                    conditionLetter.signal();
                    conditionNumber.await();
                }
                conditionLetter.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "数字").start();
    }
}