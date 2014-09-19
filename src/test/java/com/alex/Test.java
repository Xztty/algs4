package com.alex;

import java.lang.reflect.Array;

public class Test
{
    public static void main(String[] args)
    {
        new Test().test();
    }

    public void test()
    {
        final Test[] a = new Test[10];
        final Test[] clone = a.clone();
        System.out.println(a.length);
        System.out.println(clone.length);

        Object o = new Test();
        for (int i = 0; i < 1000000; i++)
        {
            o = Array.newInstance(o.getClass(), 0);
            System.out.println(i + ", " + o.getClass());
        }
    }
}
