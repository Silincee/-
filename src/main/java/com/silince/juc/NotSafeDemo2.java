package main.java.com.silince.juc;


import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @program: 多线程高并发
 * @description: 请举例说明集合类是不安全的 set
 * @author: Silince
 * @create: 2020-08-30 20:48
 **/
public class NotSafeDemo2 {
    public static void main(String[] args) {

        Set<Object> set = new CopyOnWriteArraySet<>();//Collections.synchronizedSet(new HashSet<>());

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                // method
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }


    }
}
