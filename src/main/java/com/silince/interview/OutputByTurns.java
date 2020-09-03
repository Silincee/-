package com.silince.interview;

import java.util.concurrent.locks.LockSupport;

/**
 * @program: 多线程高并发
 * @description: 线程交替输出 用两个线程，一个输出字母，一个输出数字，交替输出1A2B3C...26Z
 * @author: Silince
 * @create: 2020-09-03 10:15
 **/
public class OutputByTurns {
    static Thread t1,t2 = null;

    public static void main(String[] args) {
        int a  = (int) 'a';

        t1 = new Thread(() -> {
            // method
            for (int i=0;i<26;i++) {
                System.out.println(i+1);
                LockSupport.unpark(t2); // 叫醒t2 ⚠️ 可以先唤醒，那么下次的park()就不阻塞；notify()则不行。
                LockSupport.park(); // t1 阻塞
            }
        }, "t1");

        t2 = new Thread(() -> {
            // method
            for (int i=0;i<26;i++) {
                LockSupport.park(); // t2 阻塞
                System.out.println((char) (a+i));
                LockSupport.unpark(t1); // 叫醒t1

            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
