package com.wzinner.algorithms;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int size;
    private int counter;
    private final int[] grid;

    private WeightedQuickUnionUF wqu;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Given n <= 0");
        }

        this.size = n;
        this.grid = new int[n * n + 2];
        this.counter = 0;

        wqu = new WeightedQuickUnionUF(size * size + 2);

        grid[size * size] = 1;
        grid[size * size + 1] = 1;
    }

    public void open(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        int site = getIndex(row, col);
        grid[site] = 1; // open a site
        counter++; // increase count of open sites

        if (row != 1 && isOpen(row - 1, col)) {
            union(getIndex(row - 1, col), site);
        }
        else if (row == 1) {
            union(site, size * size);
        }

        if (row != size && isOpen(row + 1, col)) {
            union(getIndex(row + 1, col), site);
        }
        else if (row == size) {
            union(site, size * size + 1);
        }

        if (col != 1 && isOpen(row, col - 1)) {
            union(getIndex(row, col - 1), site);
        }

        if (col != size && isOpen(row, col + 1)) {
            union(getIndex(row, col + 1), site);
        }

    }  // open site (row, col) if it is not open already

    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        return grid[getIndex(row, col)] == 1;

    } // is site (row, col) open?

    public boolean isFull(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
//        else return isOpen(row, col) && wqu.connected(getIndex(row, col), size * size);
        else return wqu.connected(getIndex(row, col), size * size);

    }  // is site (row, col) full?

    public int numberOfOpenSites() {
        return counter;
    }    // number of open sites

    public boolean percolates() {
        // system percolates if there is a full site in the bottom row
        for (int col = 1; col <= size; col++) {
            if (isFull(size , col)) {
                return true;
            }
        }
        return false;
//        return wqu.connected(size * size,size * size + 1);
   }

    private int getIndex(int row, int col) {
        return (size * (row - 1)) + col - 1;
    }

    private void union(int a, int b) {
        if (!wqu.connected(a, b)) {
            wqu.union(a, b);
        }
    }

    // public static void main(String[] args)   // test client (optional)
}