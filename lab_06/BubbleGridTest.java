import static org.junit.Assert.*;
import org.junit.Test;

public class BubbleGridTest {
    @Test
    public void testSimple() {
        int[][] grid1 = {{1, 1, 0},
                {1, 0, 0},
                {1, 1, 0},
                {1, 1, 1}};
        BubbleGrid bubbleGrid1 = new BubbleGrid(grid1);
        int[][] darts1 = {{2, 2},
                {2, 0}};
        int[] expected1 = {0, 4};
        assertArrayEquals(expected1, bubbleGrid1.popBubbles(darts1));

        int[][] grid2 = {{1, 1, 0},
                {1, 0, 0},
                {1, 1, 0},
                {1, 1, 1}};
        BubbleGrid bubbleGrid2 = new BubbleGrid(grid2);
        int[][] darts2 = {{1, 0}};
        int[] expected2 = {5};
        assertArrayEquals(expected2, bubbleGrid2.popBubbles(darts2));

        int[][] grid3 = {{1, 0, 0, 0},
                {1, 1, 1, 0}};
        BubbleGrid bubbleGrid3 = new BubbleGrid(grid3);
        int[][] darts3 = {{1, 0}};
        int[] expected3 = {2};
        assertArrayEquals(expected3, bubbleGrid3.popBubbles(darts3));

        int[][] grid4 = {{1, 0, 1},
                {1, 1, 1}};
        BubbleGrid bubbleGrid4 = new BubbleGrid(grid4);
        int[][] darts4 = {{0, 0}, {0, 2}, {1, 1}};
        int[] expected4 = {0, 3, 0};
        assertArrayEquals(expected4, bubbleGrid4.popBubbles(darts4));
    }

}