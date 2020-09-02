package main.java.com.silince.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: 多线程高并发
 * @description: 线程池
 * @author: Silince
 * @create: 2020-09-01 14:44
 **/
public class MyThreadPoolDemo {
    public static void main(String[] args) {

        // 一池五个工作线程，类似一个银行有5个受理窗口
        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();// 一个任务一个任务的执行，一池一线程
        ExecutorService threadPool3 = Executors.newCachedThreadPool();

        try {
            // 模拟有10个顾客来银行办理业务，目前池子里面有5个工作人员提供服务
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
                threadPool1.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool1.shutdown();
        }
    }
}
