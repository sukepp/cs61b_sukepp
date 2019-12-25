public class WeightedQuickUnion {

    int[] arr;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public WeightedQuickUnion(int n) {
        if (n < 0) {
            return;
        }
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) throws IllegalArgumentException {
        if (vertex < 0 || vertex > arr.length) {
            throw new IllegalArgumentException();
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        return Math.abs(arr[find(v1)]);
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return arr[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);

        if (connected(v1, v2)) {
            return;
        }

        int size_v1 = sizeOf(v1);
        int size_v2 = sizeOf(v2);
        if (size_v1 > size_v2) {
            arr[find(v1)] = -(size_v1 + size_v2);
            arr[find(v2)] = find(v1);
        } else {
            arr[find(v2)] = -(size_v1 + size_v2);
            arr[find(v1)] = find(v2);
        }
    }

    private void pathCompression(int vertex, int root) {
        if (arr[vertex] < 0) {
            return;
        }
        pathCompression(arr[vertex], root);
        arr[vertex] = root;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        int x = vertex;
        while (arr[x] >= 0) {
            x = arr[x];
        }
        pathCompression(vertex, x);
        return x;
    }
    public static void main(String[] args) {
        WeightedQuickUnion qu = new WeightedQuickUnion(8);

        System.out.println(qu.connected(1, 2));
        qu.union(1, 2);
        qu.union(3, 4);
        qu.union(5, 4);
        qu.union(1, 3);
        System.out.println(qu.connected(5, 2));
        System.out.println(qu.connected(4, 3));
        System.out.println(qu.connected(1, 4));
    }
}
