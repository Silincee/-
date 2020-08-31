package main.java.com.silince.juc;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @program: 多线程高并发
 * @description: 请举例说明集合类是不安全的 map
 * @author: Silince
 * @create: 2020-08-30 20:48
 **/
public class NotSafeDemo3 {
    public static void main(String[] args) {

//        HashMap<String, String> map = new HashMap<>();
        Map<String, String> map = new ConcurrentHashMap();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // method
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }



    }
}
