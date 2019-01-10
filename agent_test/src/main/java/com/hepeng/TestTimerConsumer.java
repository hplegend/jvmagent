package com.hepeng;

import com.hepeng.timerconsumer.Bazhang;

/**
 * Created with IntelliJ IDEA.
 *
 * @author hp.he
 * @date 2018/12/30 10:53
 */
public class TestTimerConsumer {

    public static void main(String[] args) throws Exception {
        while (true) {
            Bazhang bazhang = new Bazhang();
            bazhang.newFunc1("1111");
            bazhang.newFunc2("2222");
            Thread.sleep(1000);
        }
    }

}
