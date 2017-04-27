package com.cmcc.autowire.clz;

/**
 * Created by zmcc on 17/4/26.
 */
public class A {

    @Autowire
    private B b;

    public void aout() {
        System.out.println("a out...");
    }

}
