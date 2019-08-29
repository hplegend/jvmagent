package com.hepeng.visitor;

/**
 * @author hp.he
 * @date 2019/8/29 17:06
 */
public class This implements Element {
    @Override
    public void   accept( Visitor v ) {
        v.visit( this );
    }

    public String thiss() {
        return "this";
    }
}
