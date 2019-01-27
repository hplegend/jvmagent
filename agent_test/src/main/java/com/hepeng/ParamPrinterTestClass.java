package com.hepeng;

/**
 * Created with IntelliJ IDEA.
 *
 * @author hp.he
 * @date 2019/1/15 19:29
 */
public class ParamPrinterTestClass {

    public  void print(Integer a) {
        System.out.println("test ~~~~"+a);
    }

    public static void main(String[] args) throws Exception {

        while (true) {
            new ParamPrinterTestClass().print(1);

            Thread.sleep(1000);
        }

    }

}
