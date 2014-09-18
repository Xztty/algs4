package algs.chapter1;

import algs.std.StdOut;
import algs.std.StdRandom;
import algs.std.StdStats;

public class PercolationStats
{
    private int T;
    private double[] percolationThreasholds;

    public PercolationStats(int N, int T)    // perform T independent computational experiments on an N-by-N grid
    {
        this.T = T;
        percolationThreasholds = new double[T];

        for (int i = 0; i < T; i++)
        {
            final Percolation percolation = new Percolation(N);
            int open = 0;
            while (!percolation.percolates())
            {
                percolation.open(StdRandom.uniform(N), StdRandom.uniform(N));
                open++;
            }
            percolationThreasholds[i] = open * 1.0 / (N * N);
//            percolation.printBlocks();
        }
    }

    public double mean()                     // sample mean of percolation threshold
    {
        return StdStats.mean(percolationThreasholds);
    }

    public double stddev()                   // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(percolationThreasholds);
    }

    public double confidenceLo()             // returns lower bound of the 95% confidence interval
    {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    public double confidenceHi()             // returns upper bound of the 95% confidence interval
    {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

    public static void main(String[] args) throws InterruptedException   // test client, described below
    {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
//        stat(N, T);

        while (true)
        {
            stat(N, T);
            System.out.println();
            T += T;
            Thread.sleep(1000);
        }
    }

    private static void stat(int n, int t)
    {
        final PercolationStats percolationStats = new PercolationStats(n, t);
        StdOut.printf("%50s = %f\n", "mean", percolationStats.mean());
        StdOut.printf("%50s = %f\n", "stddev", percolationStats.stddev());
        StdOut.printf("%50s = %f, %f\n", "95% confidence interval", percolationStats.confidenceLo(), percolationStats.confidenceHi());
    }
}
