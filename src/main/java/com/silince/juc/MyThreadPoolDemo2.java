package main.java.com.silince.juc;

import java.util.concurrent.*;

/**
 * @program: 多线程高并发
 * @description: 自定义线程池
 * @author: Silince
 * @create: 2020-09-01 18:16
 **/
public class MyThreadPoolDemo2 {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(2,
                Runtime.getRuntime().availableProcessors()+1,
                2L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3), //阻塞队列长度为3，默认的是Integer.MAX_VALUE
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        try {
            // 最大线程数= maximumPoolSize + capacity
            for (int i = 0; i < 9; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }


    }
}
