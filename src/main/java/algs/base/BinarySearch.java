package algs.base;

import algs.std.StdRandom;

import java.util.Arrays;
import java.util.HashSet;

public class BinarySearch
{
    public static int binarySearch(Comparable[] a, Comparable t)
    {
        int lo = 0;
        int hi = a.length - 1;
        int find = -1;
        while (lo <= hi)
        {
            int mid = (lo + hi) / 2;
            final int compareTo = a[mid].compareTo(t);
            if (compareTo == 0)
            {
                find = mid;
                break;
            }
            else if (compareTo == 1)
            {
                hi = mid - 1;
            }
            else
            {
                lo = mid + 1;
            }
        }
        return find;
    }

    public static void main(String[] args)
    {
        int n = 100, R = 200;
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++)
        {
            a[i] = StdRandom.uniform(R);
        }
        Arrays.sort(a);
        int t = StdRandom.uniform(R);
        System.out.println(t);
        System.out.println(binarySearch(a, t) != -1);
        System.out.println(new HashSet<>(Arrays.asList(a)).contains(t));
        System.out.println(Arrays.toString(a));

    }
}
