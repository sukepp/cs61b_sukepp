public class BubbleGrid {
    int[][] grid;
    private int WIDTH;
    private int HEIGHT;
    private WeightedQuickUnion quickUnion;

    public BubbleGrid(int[][] grid) {
        this.grid = grid;
        this.WIDTH = grid[0].length;
        this.HEIGHT = grid.length;
        this.quickUnion = new WeightedQuickUnion(HEIGHT * WIDTH + 1);
    }

    private int getUnionIndex(int x, int y) {
        return x * WIDTH + y + 1;
    }

    private void unionRound(int x, int y) {
        /* Top */
        if (x > 0 && grid[x - 1][y] == 1) {
            quickUnion.union(getUnionIndex(x, y), getUnionIndex(x - 1, y));
        }
        /* Down */
        if (x < HEIGHT - 1 && grid[x + 1][y] == 1) {
            quickUnion.union(getUnionIndex(x, y), getUnionIndex(x + 1, y));
        }
        /* Left */
        if (y > 0 && grid[x][y - 1] == 1) {
            quickUnion.union(getUnionIndex(x, y), getUnionIndex(x , y - 1));
        }
        /* Right */
        if (y < WIDTH - 1 && grid[x][y + 1] == 1) {
            quickUnion.union(getUnionIndex(x, y), getUnionIndex(x , y + 1));
        }
        /* The top row */
        if (x == 0) {
            quickUnion.union(getUnionIndex(x, y), 0);
        }
    }

    /**
     * popBubbles
     * @param darts An array of 2-element arrays representing the grid positions (in [row, column] format) at which darts are thrown in sequence.
     * @return An array where the i-th element is the number of bubbles that fall after the i-th dart is thrown (popped bubbles do not fall!).
     */
    public int[] popBubbles(int[][] darts) {
        // First hit the entire grid. subtract every item by 1 even if there is no bubble.
        // After this:
        //      1 stands for that there is a bubble
        //      0 stands for that there was a bubble
        //      -1 stands for that is empty
        for (int[] dart : darts) {
            int x = dart[0];
            int y = dart[1];
            grid[x][y] -= 1;
        }

        // Now we iterate through the whole grid. Specially, one the top row.
        for (int x = 0; x < HEIGHT; x++) {
            for (int y = 0; y < WIDTH; y++) {
                if (grid[x][y] == 1) {
                    unionRound(x, y);
                }
            }
        }

        // Get the number of bubbles left for the last grid
        int lastNumber = quickUnion.sizeOf(0);

        // Reverse the hitting process backwards
        int[] results = new int[darts.length];
        for (int i = darts.length - 1; i >= 0; i--) {
            int x = darts[i][0];
            int y = darts[i][1];
            if (grid[x][y] == 0) {
                unionRound(x, y);
                int preNumber = quickUnion.sizeOf(0);
                results[i] = Math.max(0, preNumber - lastNumber - 1);
                lastNumber = preNumber;
            }
        }

        return results;
    }
}
