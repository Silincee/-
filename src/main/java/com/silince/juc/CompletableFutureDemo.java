package main.java.com.silince.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @program: 多线程高并发
 * @description: 异步回调
 * @author: Silince
 * @create: 2020-09-01 22:50
 **/
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 异步回调 无返回值
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "没有返回, update mysql ok");
        });
        System.out.println(completableFuture.get());

        // 异步回调 有返回值
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "有返回, insert mysql ok");
//            int age=10/0;
            return 1024;
        });
        completableFuture1.whenComplete((t,u)->{
            System.out.println("***t: "+t);
            System.out.println("***u: "+u);
        }).exceptionally(f->{  //异常分支
            System.out.println("***exception: "+f.getMessage());
            return 4444;
        }).get();
    }
}
