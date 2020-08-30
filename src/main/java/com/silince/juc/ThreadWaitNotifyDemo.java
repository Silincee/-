package main.java.com.silince.juc;

/**
 * @program: 多线程高并发
 * @description: 面试题
 * 现在4个线程，可以操作初始值为0的一个变量。实现2个线程对该变量加1，2个线程对该变量减1，实现交替10轮。
 * @author: Silince
 * @create: 2020-08-30 16:12
 **/
public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
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

class ShareData {
    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        //1 判断
        while (number != 0) {
            this.wait();
        }
        //2 干活
        number++;
        System.out.println(Thread.currentThread().getName() + ": " + number);
        //3 通知
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        //1 判断
        while (number == 0) {
            this.wait();
        }
        //2 干活
        number--;
        System.out.println(Thread.currentThread().getName() + ": " + number);
        //3 通知
        this.notifyAll();
    }
}
