/**
 * User: ZY
 * Date: 9/14/014
 * Time: 20:45
 */

package algs.base;

import algs.std.StdRandom;

public class InsertionSort extends AbstractSort {

    @Override
    public void sort(Comparable[] a, int low, int high) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        int NUM = 500;
        Integer[] a = new Integer[NUM];
        for (int i = 0; i < NUM; i++) {
            final int uniform = StdRandom.uniform(1000);
            a[i] = uniform;
        }

        final AbstractSort sort = new InsertionSort();
        sort.sort(a, 0, a.length - 1);
        System.out.println(sort.isSorted(a));
    }
}
