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
class ShareData {
    private int flag = 1; // 0:打印字母 1:打印数字
    private Lock lock = new ReentrantLock();
    private Condition conditionNumber = lock.newCondition();
    private Condition conditionLetter = lock.newCondition();

    public void printNumber(int number) {
        lock.lock();
        try {
            // 1 判断
            while (flag != 1) {
                conditionNumber.await();
            }
            // 2 干活
            System.out.println(number);
            // 3 通知
            flag = 0;
            conditionLetter.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printLetter(char ch) {
        lock.lock();
        try {
            // 1 判断
            while (flag != 0) {
                conditionLetter.await();
            }
            // 2 干活
            System.out.println(ch);
            // 3 通知
            flag = 1;
            conditionNumber.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class OutputByTurns3_2 {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            // method
            for (int i = 0; i < 26; i++) {
                shareData.printLetter((char)('a'+i));
            }
        }, "打印字母").start();


        new Thread(() -> {
            // method
            for (int i = 0; i < 26; i++) {
                shareData.printNumber(i+1);
            }
        }, "打印数字").start();
    }

}