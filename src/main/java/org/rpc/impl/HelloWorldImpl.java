package org.rpc.impl;

import java.util.Random;

/**
 * @author jiankang Li
 * @ClassName HelloWorldImpl.java
 * @Description TODO
 * @createTime 2021年02月08日 11:16:00
 */
public class HelloWorldImpl implements Helloworld{
    @Override
    public String hi() {
        return "ok";
    }

    @Override
    public int add(int a, int b) {
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(new Random().nextInt(10000));
            // 故意添加了耗时操作，以便于模拟真实的调用操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int c = a + b;
        System.out.println(Thread.currentThread().getName() + " 耗时:" + (System.currentTimeMillis() - start));
        return c;
    }
}
