/**
 * User: ZY
 * Date: 9/14/014
 * Time: 22:33
 */

package algs.problem;

import algs.std.StdRandom;

public class InversionPairNum {

    public boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public boolean isSorted(Comparable[] a) {
        boolean sorted = true;
        for (int i = 0; i < a.length - 1; i++) {
            if (less(a[i + 1], a[i])) {
                sorted = false;
                break;
            }
        }
        return sorted;
    }

    public int sort(Comparable[] a, int low, int high) {
        if (low >= high) {
            return 0;
        } else {
            int mid = (low + high) / 2;
            int sum = 0;
            sum += sort(a, low, mid);
            sum += sort(a, mid + 1, high);
            return sum + merge(a, low, mid, high);
        }
    }

    private int merge(Comparable[] a, int low, int mid, int high) {
        Comparable[] aux = new Comparable[a.length];
        System.arraycopy(a, 0, aux, 0, a.length);

        int p = low;
        int q = mid + 1;
        int num = 0;
        for (int i = low; i <= high; i++) {
            if (p > mid) {
                a[i] = aux[q++];
            } else if (q > high) {
                num += q - mid - 1;
                a[i] = aux[p++];
            } else if (less(aux[q], aux[p])) {
                a[i] = aux[q++];
            } else {
                num += q - mid - 1;
                a[i] = aux[p++];
            }
        }
        return num;
    }

    public static void main(String[] args) {
        int NUM = 5;
        Integer[] a = new Integer[NUM];
        for (int i = 0; i < NUM; i++) {
            final int uniform = StdRandom.uniform(10);
            a[i] = uniform;
            System.out.print(a[i] + " ");
        }
        System.out.println();
        final InversionPairNum mergeSort = new InversionPairNum();
        final int sort = mergeSort.sort(a, 0, a.length - 1);
        System.out.println(sort);
        System.out.println(mergeSort.isSorted(a));
    }
}
