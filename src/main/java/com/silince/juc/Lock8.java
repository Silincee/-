package main.java.com.silince.juc;

import java.util.concurrent.TimeUnit;

/**
 * @program: 多线程高并发
 * @description: 多线程8锁
 * @author: Silince
 * @create: 2020-08-30 19:42
 **/

/**
 * 1 标准访问，先打印短信还是邮件
 * 2 停4秒在短信方法内，先打印短信还是邮件
 * 3 普通的hello方法，是先打短信还是hello
 * 4 现在有两部手机，先打印短信还是邮件
 * 5 两个静态同步方法，1部手机，先打印短信还是邮件
 * 6 两个静态同步方法，2部手机，先打印短信还是邮件
 * 7 1个静态同步方法，1个普通同步方法，1部手机，先打印短信还是邮件
 * 8 1个静态同步方法，1个普通同步方法，2部手机，先打印短信还是邮件
 *
 * 运行答案：1、短信2、短信
 * 3、Hello
 * 4、邮件
 * 5、短信6、短信7、邮件
 * 8、邮件
 * */
public class Lock8 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            // method
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            // method
            try {
               phone2.sendSMS();
//                phone.hello();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}

class Phone {
    public synchronized static void sendEmail() throws Exception {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendEmail...");
    }

    public synchronized static void sendSMS() throws Exception {
        System.out.println("sendSMS...");
    }
    public  void hello() throws Exception {
        System.out.println("hello...");
    }

}
