package com.hepeng.visitor;

// 访问者模式

// 考虑两个对象，它们的类不同；一个称为元素（Element），另一个称为访问者（Visitor）。元素有一个accept方法，该方法接收访问者作为参数；accept()方法调用访问者的visit()方法，并且将元素自身作为参数传递给访问者。