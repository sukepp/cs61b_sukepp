import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class WeightedQuickUnionTest {

    @Test
    public void testSimple() {
        int n = 5;
        WeightedQuickUnion unionFind = new WeightedQuickUnion(n);
        assertFalse(unionFind.connected(0, 2));
        assertFalse(unionFind.connected(3, 4));
        assertFalse(unionFind.connected(4, 1));

        unionFind.union(0, 1);
        unionFind.union(1, 0);
        unionFind.union(1, 2);
        unionFind.union(3, 4);
        unionFind.union(4, 2);

        assertTrue(unionFind.connected(0, 1));
        assertTrue(unionFind.connected(2, 0));
        assertTrue(unionFind.connected(3, 1));
        assertTrue(unionFind.connected(4, 2));
        assertTrue(unionFind.connected(4, 0));
        assertTrue(unionFind.connected(4, 3));
        assertTrue(unionFind.connected(2, 4));

        for (int i = 0; i < n; i++) {
            assertEquals(5, unionFind.sizeOf(i));
        }
    }

    @Test
    public void testBig() {
        int n = 1000000;
        WeightedQuickUnion unionFind = new WeightedQuickUnion(n);
        for (int i = 0; i < n * 2; i++) {
            int v1 = (int) (Math.random() * n);
            int v2 = (int) (Math.random() * n);
            unionFind.union(v1, v2);
        }

    }

}