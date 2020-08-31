package main.java.com.silince.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @program: 多线程高并发
 * @description: 减少计数
 * @author: Silince
 * @create: 2020-08-31 22:45
 **/

// 6个同学各上各的自习，中间没有交互。都走完了班长才能关门
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                // method
                System.out.println(Thread.currentThread().getName() + ":\t离开教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();

        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t班长关门走人");
    }
}


