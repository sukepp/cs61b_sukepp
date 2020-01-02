package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

    private int N;
    private int T;
    private PercolationFactory pf;
    private double[] results;
    private double mean;
    private double stddev;
    private double totalTimes;

    /**
     * perform T independent experiments on an N-by-N grid
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0) {
            throw new IllegalArgumentException("N: " + N + " <= 0");
        }
        if (T <= 0) {
            throw new IllegalArgumentException("T: " + T + " <= 0");
        }
        this.N = N;
        this.T = T;
        this.pf = pf;
        this.results = new double[T];
        this.totalTimes = 0;
        experiment();
        this.mean = -1;
        this.stddev = -1;
    }

    private void experiment() {
        for (int i = 0; i < T; i++) {
            Percolation percolation = pf.make(N);
            while (!percolation.percolates()) {
                int x = StdRandom.uniform(N);
                int y = StdRandom.uniform(N);
                percolation.open(x, y);
                totalTimes += 1;
            }
            results[i] = 1.0 * percolation.numberOfOpenSites() / (N * N);
        }
    }

    /**
     * Sample mean of percolation threshold.
     */
    public double mean() {
        if (mean < 0) {
            this.mean = StdStats.mean(results);
        }
        return this.mean;
    }

    /**
     * sample standard deviation of percolation threshold
     */
    public double stddev() {
        if (T == 1) return Double.NaN;

        if (stddev < 0) {
            this.stddev = StdStats.stddev(results);
        }
        return this.stddev;
    }

    /**
     * low endpoint of 95% confidence interval
     */
    public double confidenceLow() {
        return mean() / T - 1.96 * stddev() / Math.sqrt(T);
    }

    /**
     * high endpoint of 95% confidence interval
     */
    public double confidenceHigh() {
        return mean() / T + 1.96 * stddev() / Math.sqrt(T);
    }

    public double meanTimesPerTrail() {
        return totalTimes / T;
    }

    public void printStats() {
        System.out.println("N: " + N + ", T: " + T);
        System.out.println("Mean: " + mean());
        System.out.println("Mean times per trail: " + meanTimesPerTrail());
    }

    public static void main(String[] args) {
        int N = 100;
        int T = 10;
        Stopwatch timer = new Stopwatch();
        double preTime = 0;
        for (int i = 0; i < 20; i++) {
            Double time1 = timer.elapsedTime();
            PercolationStats stats = new PercolationStats(N, T, new PercolationFactory());
            stats.printStats();
            Double time2 = timer.elapsedTime();

            double curTime = time2 - time1;
            System.out.println("Total time:" + curTime);
            System.out.println("Time ratio:" + curTime / preTime);
            preTime = curTime;

            N *= 2;
            System.out.println("------------------------------");
        }

    }
}