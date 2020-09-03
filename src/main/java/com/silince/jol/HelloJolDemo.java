package com.silince.jol;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @program: 多线程高并发
 * @description: JOL工具类
 * @author: Silince
 * @create: 2020-09-02 11:59
 **/
public class HelloJolDemo {
    public static void main(String[] args) {
        try {TimeUnit.SECONDS.sleep(6);} catch (InterruptedException e) {e.printStackTrace();}


        Object o = new Object();
        String s = ClassLayout.parseInstance(o).toPrintable();
        System.out.println(s);

        System.out.println("========");


        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
