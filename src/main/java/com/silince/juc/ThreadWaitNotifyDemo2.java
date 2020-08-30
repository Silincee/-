package main.java.com.silince.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: 多线程高并发
 * @description: 面试题  Lock版
 * 现在4个线程，可以操作初始值为0的一个变量。实现2个线程对该变量加1，2个线程对该变量减1，实现交替10轮。
 * @author: Silince
 * @create: 2020-08-30 16:12
 **/
public class ThreadWaitNotifyDemo2 {
    public static void main(String[] args) {
        ShareData2 shareData = new ShareData2();
        new Thread(() -> {
            // method
            for (int i = 0; i < 10; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            // method
            for (int i = 0; i < 10; i++) {
                try {
                    shareData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            // method
            for (int i = 0; i < 10; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            // method
            for (int i = 0; i < 10; i++) {
                try {
                    shareData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }

}

class ShareData2 {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            // ... method body
            //1 判断
            while (number != 0) {
                condition.await();
            }
            //2 干活
            number++;
            System.out.println(Thread.currentThread().getName() + ": " + number);
            //3 通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            // ... method body
            //1 判断
            while (number == 0) {
                condition.await();
            }
            //2 干活
            number--;
            System.out.println(Thread.currentThread().getName() + ": " + number);
            //3 通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

