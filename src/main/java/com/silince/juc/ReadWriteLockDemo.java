package main.java.com.silince.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: 多线程高并发
 * @description: 读写锁
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。但是如果有一个线程想去写共享资源，就不应该再有其它线程可以对该资源进行读或写。
 * 总结：
 * - 读-读能共存；读-写不能共存；写-写不能共存。
 * - 写时加锁，读操作共享不加锁。应用场景：缓存
 * @author: Silince
 * @create: 2020-09-01 10:22
 **/

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++) {
            final int tempInt=i;
            new Thread(() -> {
                // method
                myCache.put(tempInt+"",tempInt+"");
            }, "写线程"+i).start();
        }

        for (int i = 0; i < 5; i++) {
            final int tempInt=i;
            new Thread(() -> {
                // method
                myCache.get(tempInt+"");
            }, "读线程"+i).start();
        }

    }
}

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public  void put(String key, Object value) {
        // 写锁
        readWriteLock.writeLock().lock();
        try {
            // ... method body
            System.out.println(Thread.currentThread().getName() + " ---开始写入数据"+key);
            // 暂停一会线程
            try {
                TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " ---写入完成");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }




    }

    public  void get(String key) {
        readWriteLock.readLock().lock();
        try {
            // ... method body
            System.out.println(Thread.currentThread().getName() + " 开始读取数据");
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + " 读取完成: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
        }

    }

