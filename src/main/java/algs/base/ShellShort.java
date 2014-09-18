/**
 * User: ZY
 * Date: 9/14/014
 * Time: 20:54
 */

package algs.base;

import algs.std.StdRandom;

public class ShellShort extends AbstractSort {
    @Override
    public void sort(Comparable[] a, int low, int high) {
        int h = getShellSortH(a);
        while (h > 0) {
            for (int i = h; i < a.length; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            if (!isHsorted(a, h)) throw new RuntimeException("not h sorted");
            h /= 3;
        }
    }

    // is the array h-sorted?
    private boolean isHsorted(Comparable[] a, int h) {
        for (int i = h; i < a.length; i++)
            if (less(a[i], a[i - h]))
                return false;
        return true;
    }

    private int getShellSortH(Comparable[] a) {
        int h = 0;
        while ((h = 3 * h + 1) < a.length) ;
        return h;
    }

    public static void main(String[] args) {
        int NUM = 500;
        Integer[] a = new Integer[NUM];
        for (int i = 0; i < NUM; i++) {
            final int uniform = StdRandom.uniform(1100);
            a[i] = uniform;
        }

        a = new Integer[10];
        int[] aa = {24, 42, 53, 44, 29, 71, 67, 57, 43, 78};
        for (int i = 0; i < 10; i++) {
            a[i] = aa[i];
        }

        final AbstractSort sort = new ShellShort();
        sort.sort(a, 0, a.length - 1);
        System.out.println(sort.isSorted(a));
    }
}
