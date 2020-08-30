package main.java.com.silince.juc;

/**
 * @program: 多线程高并发
 * @description: lock新特性
 * @author: Silince
 * @create: 2020-08-30 18:06
 **/

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：多线程之间按顺序调用，实现A->B->C。三个线程启动，要求如下：
 *
 * 1. AA打印5次，BB打印10次，CC打印15次
 * 2. 按此顺序打印10轮
 **/
public class ThreadOrderAccess {
    public static void main(String[] args) {

    }
}

class ShareResource{
    private int number=1; //1:A 2:B 3:C
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    

}