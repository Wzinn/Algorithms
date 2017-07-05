package com.wzinner.algorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] fractions;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Given n <= 0 || trials <= 0");
        }

        fractions = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation pr = new Percolation(n);
            while (!pr.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                if (!pr.isOpen(row, col)) {
                    pr.open(row, col);
                }
            }
            fractions[i] = (double) pr.numberOfOpenSites() / (n * n);
        }
    }    // perform trials independent experiments on an n-by-n grid

    public double mean() {
        return StdStats.mean(fractions);
    }           // sample mean of percolation threshold

    public double stddev() {
        return StdStats.stddev(fractions);
    }                // sample standard deviation of percolation threshold

    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(fractions.length));
    }              // low  endpoint of 95% confidence interval

    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(fractions.length));

    }               // high endpoint of 95% confidence interval

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);

        String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }
}