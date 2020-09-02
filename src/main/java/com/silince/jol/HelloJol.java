package com.silince.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @program: 多线程高并发
 * @description: JOL工具类
 * @author: Silince
 * @create: 2020-09-02 11:59
 **/
public class HelloJol {
    public static void main(String[] args) {
        Object o = new Object();
        String s = ClassLayout.parseInstance(o).toPrintable();
        System.out.println(s);
    }
}
