package com.hepeng.timerconsumer;

/**
 * Created with IntelliJ IDEA.
 *
 * @author hp.he
 * @date 2018/12/30 10:48
 */
public class Bazhang {
    public void newFunc1(String str) {
        System.out.println(str);
        for (int i = 0; i < 100; i++) {
            if (i % 10 == 0) {
                System.out.println(i);
            }
            if (i == 50) {
                return;
            }
        }
    }
    @Cost
    public void newFunc2(String str) {
        System.out.println(str);
        for (int i = 0; i < 100; i++) {
            if (i % 8 == 0) {
                System.out.println(i);
            }
            if (i > 50) {
                return;
            }

        }
    }
}
