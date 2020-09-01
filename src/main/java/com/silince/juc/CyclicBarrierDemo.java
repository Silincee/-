package main.java.com.silince.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.FutureTask;

/**
 * @program: 多线程高并发
 * @description: 循环栅栏
 * @author: Silince
 * @create: 2020-09-01 09:28
 **/
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        // public CyclicBarrier(int parties,Runnable barrierAction)
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙 ... !");
        });

        for (int i = 0; i < 7; i++) {
            final int tempInt = i;
            new Thread(() -> {
                // method
                System.out.println(Thread.currentThread().getName()+"\t收集到第："+tempInt+"颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"号龙珠已被使用");
            }, String.valueOf(i)).start();
        }


    }
}
