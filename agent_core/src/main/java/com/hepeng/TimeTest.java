package com.hepeng;

/**
 * Created with IntelliJ IDEA.
 *
 * @author hp.he
 * @date 2018/12/25 20:15
 */
public class TimeTest {

    public static void sayHello() {
        try {
            Thread.sleep(2000);
            System.out.println("hello world!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sayHello2(String hello) {
        try {
            Thread.sleep(1000);
            System.out.println(hello);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
