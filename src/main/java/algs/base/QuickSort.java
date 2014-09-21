package algs.base;

import algs.std.StdRandom;

public class QuickSort extends AbstractSort {
    @Override
    public void sort(Comparable[] a, int low, int high) {
        if (low >= high) {
            return;
        }
        int partition = partition(a, low, high);
        sort(a, low, partition - 1);
        sort(a, partition + 1, high);
    }

    public int partition(Comparable[] a, int low, int high) {
        int i = low;
        int j = high + 1;
        Comparable v = a[i];
        while (true) {
            while (less(a[++i], v)) {
                if (i == high) {
                    break;
                }
            }
            while (less(v, a[--j])) {
                if (j == low) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, low, j);
        return j;
    }

    public static void main(String[] args)
    {
        int NUM = 500;
        Integer[] a = new Integer[NUM];
        for (int i = 0; i < NUM; i++) {
            final int uniform = StdRandom.uniform(1000000);
            a[i] = uniform;
        }

        final QuickSort sort = new QuickSort();
        sort.sort(a, 0, a.length - 1);
        System.out.println(sort.isSorted(a));
    }
}
