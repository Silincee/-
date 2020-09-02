package main.java.com.silince.juc;


import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @program: 多线程高并发
 * @description: 信号灯
 * @author: Silince
 * @create: 202009-01 09:53
 **/
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(4); // 模拟资源位，有4个空车位
        for (int i = 0; i < 7; i++) {
            new Thread(() -> {
                // method
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+" 抢到了车位");
                    try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
                    System.out.println(Thread.currentThread().getName()+" 离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}

