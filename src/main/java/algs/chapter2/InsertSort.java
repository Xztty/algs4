/**
 * User: ZY
 * Date: 9/1/001
 * Time: 21:12
 */

package algs.chapter2;

import algs.util.RandomUtil;

public class InsertSort
{
    public boolean less(Comparable v1, Comparable v2)
    {
        return v1.compareTo(v2) < 0;
    }

    public void exch(Comparable a[], int i, int j)
    {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public void sort(Comparable[] a, int low, int high)
    {
        for (int i = low + 1; i <= high; i++)
        {
            for (int j = i; j > low && less(a[j], a[j - 1]); j--)
            {
                exch(a, j, j - 1);
            }
        }
    }

    public boolean isSorted(Comparable[] a, int low, int high)
    {
        boolean sorted = true;
        for (int i = low + 1; i < high; i++)
        {
            if (less(a[i], a[i - 1]))
            {
                sorted = false;
                break;
            }
        }
        return sorted;
    }

    public static void main(String[] args)
    {
        final int NUM = 100;
        Integer[] a = new Integer[NUM];
        for (int i = 0; i < NUM; i++)
        {
            a[i] = RandomUtil.random(0, 100);
        }
        final InsertSort sort = new InsertSort();
        sort.sort(a, 0, a.length - 1);
        System.out.println(sort.isSorted(a, 0, a.length - 1));
    }
}
