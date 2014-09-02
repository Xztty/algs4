package algs.base;

public abstract class AbstractSort
{
    public abstract void sort(Comparable[] a, int low, int high);

    public boolean less(Comparable a, Comparable b)
    {
        return a.compareTo(b) < 0;
    }

    public boolean isSorted(Comparable[] a)
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
}
