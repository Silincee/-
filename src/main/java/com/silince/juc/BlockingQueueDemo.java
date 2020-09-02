package main.java.com.silince.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @program: 多线程高并发
 * @description: 阻塞队列
 * @author: Silince
 * @create: 2020-09-01 13:09
 **/
public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        //IllegalStateException:Queue full
/*      System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        System.out.println(blockingQueue.add("d")); */


        // java.util.NoSuchElementException
/*      System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove()); */

        blockingQueue.add("a");
        blockingQueue.add("b");
        System.out.println(blockingQueue.element()); // 查看队首元素

        // 插入方法，成功ture失败false
        System.out.println(blockingQueue.offer("d")); // true
        System.out.println(blockingQueue.offer("x")); // false

        // 移除方法，成功返回出队列的元素，队列里没有就返回null
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }
}
