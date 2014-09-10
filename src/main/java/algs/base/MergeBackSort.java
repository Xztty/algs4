package algs.base;

import algs.std.StdRandom;

public class MergeBackSort extends AbstractSort
{
    @Override
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
            mergeBack(a, low, mid, high);
        }
    }

    private void mergeBack(Comparable[] a, int low, int mid, int high)
    {
        Comparable[] aux = new Comparable[a.length];
        for (int i = low; i <= mid; i++)
        {
            aux[i] = a[i];
        }
        for (int i = high, j = mid + 1; i > mid; i--, j++)
        {
            aux[i] = a[j];
        }
        int p = low;
        int q = high;
        int index = low;
        while (p <= q)
        {
            if (less(aux[p], aux[q]))
            {
                a[index] = aux[p++];
            }
            else
            {
                a[index] = aux[q--];
            }
            index++;
        }
    }


    public static void main(String[] args)
    {
        int NUM = 500;
        Integer[] a = new Integer[NUM];
        for (int i = 0; i < NUM; i++)
        {
            final int uniform = StdRandom.uniform(1000000);
            a[i] = uniform;
        }

        final MergeBackSort mergeSort = new MergeBackSort();
        mergeSort.sort(a, 0, a.length - 1);
        System.out.println(mergeSort.isSorted(a));
    }
}
