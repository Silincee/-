package main.java.com.silince.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @program: 多线程高并发
 * @description: 获得多线程的第三种方式
 * @author: Silince
 * @create: 2020-08-31 21:44
 **/
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask<>(new Mythread());
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();

        System.out.println(Thread.currentThread().getName()+": 计算完成");
        System.out.println(futureTask.get());

    }
}

class Mythread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("***come in here");
        return 1024;
    }
}