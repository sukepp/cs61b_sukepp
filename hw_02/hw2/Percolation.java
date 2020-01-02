package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    private boolean[][] grid;
    private WeightedQuickUnionUF unionFind;
    /**
     * Back up unionFind, ONLY connected to the virtual TOP but not BOTTOM.
     */
    //private WeightedQuickUnionUF unionFindTopOnly;
    private int N;
    private int openCount;
    private int TOP;
    private int BOTTOM;

    /**
     * Create N-by-N grid, with all sites initially blocked
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N: " + N + " <= 0");
        }

        this.unionFind = new WeightedQuickUnionUF(N * N + 2);
        //this.unionFindTopOnly = new WeightedQuickUnionUF(N * N + 2);
        this.grid = new boolean[N][N];
        this.openCount = 0;
        this.N = N;
        this.TOP = 0;
        this.BOTTOM = 1;
    }

    private boolean isOutOfBound(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= N;
    }

    private int ufIndex(int row, int col) {
        return row * N + col + 2;
    }

    private void unionAround(int row, int col) {
        int x, y;
        for (int[] direction : directions) {
            x = direction[0] + row;
            y = direction[1] + col;
            if (!isOutOfBound(x, y) && isOpen(x, y)) {
                unionFind.union(ufIndex(row, col), ufIndex(x, y));
                //unionFindTopOnly.union(ufIndex(row, col), ufIndex(x, y));
            }
        }
        if (row == 0) {
            unionFind.union(TOP, ufIndex(row, col));
            //unionFindTopOnly.union(TOP, ufIndex(row, col));
        }
        if (row == N - 1) unionFind.union(BOTTOM, ufIndex(row, col));
    }

    /**
     * Open the site (row, col) if it is not open already.
     */
    public void open(int row, int col) {
        if (isOutOfBound(row, col)) {
            throw new IllegalArgumentException("(" + row + ", " + col + ") out of bound");
        }
        if (grid[row][col]) return;

        grid[row][col] = true;
        unionAround(row, col);
        openCount += 1;
    }

    /**
     * Is the site (row, col) open?
     */
    public boolean isOpen(int row, int col) {
        if (isOutOfBound(row, col)) {
            throw new IllegalArgumentException("(" + row + ", " + col + ") out of bound");
        }

        return grid[row][col];
    }

    /**
     * Is the site (row, col) full?
     * Complexity: O(log(N))
     */
    public boolean isFull(int row, int col) {
        if (isOutOfBound(row, col)) {
            throw new IllegalArgumentException("(" + row + ", " + col + ") out of bound");
        }

        if (!isOpen(row, col)) return false;
        //return unionFind.connected(TOP, ufIndex(row, col))
        //        && unionFindTopOnly.connected(TOP, ufIndex(row, col));
        return unionFind.connected(TOP, ufIndex(row, col));
    }

    /**
     * Number of open sites
     */
    public int numberOfOpenSites() {
        return openCount;
    }

    /**
     * Does the system percolate?
     * Complexity: O(log(N))
     */
    public boolean percolates() {
        return unionFind.connected(BOTTOM, TOP);
    }
}