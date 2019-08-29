package com.hepeng.visitor;

/**
 * @author hp.he
 * @date 2019/8/29 17:06
 */
public interface Visitor {

    public void visit( This e ); // second dispatch
    public void visit( That e );
    public void visit( TheOther e );

}
