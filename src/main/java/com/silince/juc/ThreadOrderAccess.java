package main.java.com.silince.juc;

/**
 * @program: 多线程高并发
 * @description: lock新特性:精准通知 精准唤醒
 * @author: Silince
 * @create: 2020-08-30 18:06
 **/

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：多线程之间按顺序调用，实现A->B->C。三个线程启动，要求如下：
 * <p>
 * 1. AA打印5次，BB打印10次，CC打印15次
 * 2. 按此顺序打印10轮
 **/
public class ThreadOrderAccess {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            // method
            for (int i = 0; i < 10; i++) {
                shareResource.print5();
            }
        }, "A").start();
        new Thread(() -> {
            // method
            for (int i = 0; i < 10; i++) {
                shareResource.print10();
            }
        }, "B").start();
        new Thread(() -> {
            // method
            for (int i = 0; i < 10; i++) {
                shareResource.print15();
            }
        }, "C").start();

    }
}

class ShareResource {
    private int number = 1; //1:A 2:B 3:C
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            // 1 判断
            while (number != 1) {
                condition1.await();
            }
            //2 干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
            //3 通知
            number = 2;
            condition2.signal(); // 只唤醒condition2上的一个线程
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            // 1 判断
            while (number != 2) {
                condition2.await();
            }
            //2 干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
            //3 通知
            number = 3;
            condition3.signal(); // 只唤醒condition3上的一个线程
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            // 1 判断
            while (number != 3) {
                condition3.await();
            }
            //2 干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
            //3 通知
            number = 1;
            condition1.signal(); // 只唤醒condition1上的一个线程
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
