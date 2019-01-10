package com.hepeng.asm;

/**
 * Created with IntelliJ IDEA.
 *
 * @author hp.he
 * @date 2018/12/27 14:24
 */
public class ByteCodeDemo {
    private static final String name = "xiaoming";

    private int age;

    public ByteCodeDemo(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
    public void setAge(){
        this.age = age;
    }
    public static void main(String[] args) {
        ByteCodeDemo byteCodeDeomo = new ByteCodeDemo(12);
        System.out.println("name:" + name + "age:" + byteCodeDeomo.getAge());
    }
}
