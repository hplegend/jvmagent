package com.hepeng.visitor;

/**
 * @author hp.he
 * @date 2019/8/29 17:08
 */
public class That implements Element {
    @Override
    public void   accept( Visitor v ) {
        v.visit( this );
    }

    public String that() {
        return "that";
    }
}
