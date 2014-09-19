package com.alex;

public class TestEulerNumber
{
    public static void main(String[] args)
    {
        final double e = Math.E;
        for (int i = 1; i < 1; i++)
        {
            final double dis = e - Math.pow(1 + 1.0 / i, i);
            final double abs = Math.abs(dis);
            System.out.println(dis);
        }

        for (int n = 1; n <= 100000; n++)
        {
            int t = 0;
            for (int i = 1; i <= n; i++)
            {
                t += (n - 1) / i + 1;
            }
            final double v = n * Math.log(n);
            System.out.println(n + ":" + t + ", " + v);
        }
    }
}
