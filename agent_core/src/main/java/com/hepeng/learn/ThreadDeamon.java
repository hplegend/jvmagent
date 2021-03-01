package com.hepeng.learn;

/**
 * @author hp.he
 * @date 2019/9/19 20:38
 */
public class ThreadDeamon {


    static class MyThread implements Runnable {

        @Override
        public void run() {

            for (int i = 0; i < 1000000; ++i) {
                System.out.println(Thread.currentThread().isDaemon() + "," + i);
            }

            try {
                Thread.sleep(1000000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {

        Thread deamo = new Thread(new MyThread());
        deamo.setDaemon(true);
        deamo.start();


        Thread common = new Thread(new MyThread());
        common.start();


        while (true) {

        }
    }


}
