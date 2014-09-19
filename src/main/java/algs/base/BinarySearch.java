package algs.base;

import algs.std.StdRandom;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class BinarySearch {
    public static int binarySearch(Comparable[] a, int lo, int hi, Comparable t) {
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            final int compareTo = a[mid].compareTo(t);
            if (compareTo == 0) {
                return mid;
            } else if (compareTo == 1) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return -1;
    }


    public static int binarySearchDescend(Comparable[] a, int lo, int hi, Comparable t) {
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            final int compareTo = t.compareTo(a[mid]);
            if (compareTo == 0) {
                return mid;
            } else if (compareTo == 1) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            testRandom();
        }
    }

    private static void testRandom() {
        int n = 100, R = 200;
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniform(R);
        }
        Arrays.sort(a);
        int t = StdRandom.uniform(R);
        System.out.println(t);
        System.out.println((binarySearch(a, 0, a.length - 1, t) != -1) == new HashSet<>(Arrays.asList(a)).contains(t));

        Arrays.sort(a, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        final boolean find = binarySearchDescend(a, 0, a.length - 1, t) != -1;
        System.out.println(
                find == new HashSet<>(Arrays.asList(a)).contains(t));
    }
}
