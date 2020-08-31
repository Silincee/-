package main.java.com.silince.juc;



import com.sun.tools.javac.util.StringUtils;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @program: 多线程高并发
 * @description: 请举例说明集合类是不安全的 list
 * @author: Silince
 * @create: 2020-08-30 20:48
 **/
public class NotSafeDemo {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>(); //ArrayList线程不安全
//        List<String> list = new Vector<>(); //Vector 线程安全
//        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                // method
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }


    }
}
