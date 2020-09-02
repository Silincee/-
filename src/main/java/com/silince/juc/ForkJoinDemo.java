package main.java.com.silince.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @program: 多线程高并发
 * @description: 分支合并框架
 * ForkJoinPool
 * ForkJoinTask
 * RecursiveTask
 * @author: Silince
 * @create: 2020-09-01 22:28
 **/
public class ForkJoinDemo {
    public static void main(String[] args) throws Exception {
        MyTask myTask = new MyTask(0, 100);
        ForkJoinPool threadPool = new ForkJoinPool();

        // 向池子递交任务
        ForkJoinTask<Integer> forkJoinTask = threadPool.submit(myTask);
        System.out.println(forkJoinTask.get());

        threadPool.shutdown();
    }
}

class MyTask extends RecursiveTask<Integer> {
    private static final Integer ADJUST_VALUE = 10;
    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        // 计算小时不触发分治
        if ((end - begin) <= ADJUST_VALUE) {
            for (int i = begin; i <= end; i++) {
                result = result + i;
            }
        } else {
            int middle = (end + begin) / 2;
            MyTask task01 = new MyTask(begin, middle);
            MyTask task02 = new MyTask(middle + 1, end);
            task01.fork(); // 调完fork还是回来调用compute()
            task02.fork();
            result = task01.join() + task02.join();
        }
        return result;
    }
}