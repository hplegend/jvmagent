package com.hepeng.visitor;

/**
 * @author hp.he
 * @date 2019/8/29 17:13
 */
public class VisitorDemo {
    public static Element[] list = { new This(), new That(), new TheOther() };

    public static void main( String[] args ) {
        UpVisitor    up   = new UpVisitor();
        DownVisitor  down = new DownVisitor();
        for (int i=0; i < list.length; i++) {
            list[i].accept( up );
        }
        for (int i=0; i < list.length; i++) {
            list[i].accept( down );
        }
    }
}
