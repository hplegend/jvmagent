package com.hepeng.visitor;

/**
 * @author hp.he
 * @date 2019/8/29 17:09
 */
public class TheOther implements Element {
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public String theOther() {
        return "the other";
    }
}
