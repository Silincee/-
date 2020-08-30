package main.java.com.silince.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: 多线程高并发
 * @description: 买票例子  三个售票员卖出30张票
 * 多线程变成的企业级套路 + 模版
 * 1 在高内聚低耦合的前提下  线程  操作(对外暴露的调用方法)  资源类
 * @author: Silince
 * @create: 2020-08-30 10:43
 **/
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        // 售票员1
        new Thread(() -> {
            for (int i = 0; i <= 40; i++) {
                ticket.saleTicket();
            }
        }, "A").start();


        // 售票员2
        new Thread(() -> {
            for (int i = 0; i <= 40; i++) {
                ticket.saleTicket();
            }
        }, "B").start();

        // 售票员3
        new Thread(() -> {
            for (int i = 0; i <= 40; i++) {
                ticket.saleTicket();
            }
        }, "C").start();
    }
}

// 资源类
class Ticket {
    private int number = 30;
    private Lock lock = new ReentrantLock();

    public void saleTicket() {
        lock.lock();
        try {
            // ... method body
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "\t卖出第: " + (number--) + "\t还剩下" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
}
