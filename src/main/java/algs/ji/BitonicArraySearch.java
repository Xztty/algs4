package algs.ji;

import algs.std.StdRandom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BitonicArraySearch
{
    public static int bitonicSearch(Comparable[] a, int lo, int hi)
    {
        while (lo < hi)
        {
        }
        return 0;
    }

    public static int bitonicMax(Comparable[] a)
    {
        int lo = 0;
        int hi = a.length - 1;
        while (lo < hi)
        {
            int mid = (lo + hi) / 2;
            final int compareTo = a[mid].compareTo(a[mid + 1]);
            if (compareTo == 1)
            {
                hi = mid;
            }
            else
            {
                lo = mid + 1;
            }
        }
        return lo;
    }

    public static void main(String[] args)
    {
        for (int i = 0; i < 100; i++)
        {
            testRandom();
        }

        testSpecify();
    }

    private static void testSpecify()
    {
        int[] a = {1, 2};
        testInts(a);
        int[] b = {1, 0};
        testInts(b);
        int[] c = {0, 1, 0};
        testInts(c);
        int[] d = {1, 3, 5, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1};
        testInts(d);
    }

    private static void testInts(int[] a)
    {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < a.length; i++)
        {
            list.add(a[i]);
        }
        final int maxIndex = bitonicMax(list.toArray(new Integer[0]));
        System.out.println(list.get(maxIndex));
    }

    private static void testRandom()
    {
        List<Integer> bitonicList = getRandomBitonicList();
        final int bitonicMax = bitonicMax(bitonicList.toArray(new Integer[0]));

        final Integer max = bitonicList.get(bitonicMax);

        List<Integer> sorted = new ArrayList<>(bitonicList);
        Collections.sort(sorted);
        final boolean result = max == sorted.get(sorted.size() - 1);
        if (!result)
        {
            throw new RuntimeException("result error!");
        }
    }

    public static List<Integer> getRandomBitonicList()
    {
        List<Integer> bitonicList = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        int n = 5000, r = 10000;
        while (list.size() < n)
        {
            final int uniform = StdRandom.uniform(r);
            if (!set.contains(uniform))
            {
                set.add(uniform);
                list.add(uniform);
            }
        }
        checkNoRepeat(list);
        Collections.sort(list);
        for (Integer v : list)
        {
            if (StdRandom.bernoulli())
            {
                bitonicList.add(v);
            }
        }
        checkNoRepeat(bitonicList);
        int ascend = bitonicList.size() - 1;
        int descent = list.size() - 1;
        if (ascend > 0)
        {
            descent = list.indexOf(bitonicList.get(ascend));
        }

        for (int i = descent - 1; i >= 0; i--)
        {
            if (StdRandom.bernoulli())
            {
                bitonicList.add(list.get(i));
            }
        }
        return bitonicList;
    }

    public static void checkNoRepeat(Collection<?> collection)
    {
        if (new HashSet<>(collection).size() != collection.size())
        {
            throw new RuntimeException();
        }
    }
}
