package algs.base;

import algs.std.StdRandom;

public class MergeSort
{
    public void sort(Comparable[] a, int low, int high)
    {
        if (low >= high)
        {
            return;
        }
        else
        {
            int mid = (low + high) / 2;
            sort(a, low, mid);
            sort(a, mid + 1, high);
            merge(a, low, mid, high);
        }
    }

    public boolean less(Comparable a, Comparable b)
    {
        return a.compareTo(b) < 0;
    }

    private void merge(Comparable[] a, int low, int mid, int high)
    {
        Comparable[] aux = new Comparable[a.length];
        System.arraycopy(a, 0, aux, 0, a.length);

        int p = low;
        int q = mid + 1;
        for (int i = low; i <= high; i++)
        {
            if (p > mid)
            {
                a[i] = aux[q++];
            }
            else if (q > high)
            {
                a[i] = aux[p++];
            }
            else if (less(aux[p], aux[q]))
            {
                a[i] = aux[p++];
            }
            else
            {
                a[i] = aux[q++];
            }
        }
    }

    private boolean isSorted(Comparable[] a)
    {
        boolean sorted = true;
        for (int i = 0; i < a.length - 1; i++)
        {
            if (less(a[i + 1], a[i]))
            {
                sorted = false;
                break;
            }
        }
        return sorted;
    }

    public static void main(String[] args)
    {
        int NUM = 100;
        Integer[] a = new Integer[NUM];
        for (int i = 0; i < NUM; i++)
        {
            final int uniform = StdRandom.uniform(1000);
            a[i] = uniform;
        }
        final MergeSort mergeSort = new MergeSort();
        mergeSort.sort(a, 0, a.length - 1);
        System.out.println(mergeSort.isSorted(a));
    }
}
