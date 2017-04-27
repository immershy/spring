package com.cmcc.autowire;

import com.cmcc.autowire.clz.A;

/**
 * Created by zmcc on 17/4/26.
 */
public class Main {

    public static void main(String[] args) {
        ClassPathAppContext context = new ClassPathAppContext("test.xml");
        //A a = (A) context.getBean("a");
    }

}
