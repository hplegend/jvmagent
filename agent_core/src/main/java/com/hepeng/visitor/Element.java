package com.hepeng.visitor;

/**
 * @author hp.he
 * @date 2019/8/29 17:04
 */
public interface Element {
    // 1. accept(Visitor) interface
    public void accept( Visitor v ); // first dispatch
}
