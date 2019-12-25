public class QuickUnion {
    int[] arr;

    public QuickUnion(int size) {
        if (size < 0) {
            return;
        }
        arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = -1;
        }
    }

    private int findRoot(int x) {
        while (arr[x] != -1) {
            x = arr[x];
        }
        return x;
    }

    public boolean isConnected(int x, int y) {
        if (x < 0 || y < 0 || x >= arr.length || y > arr.length) {
            return false;
        }
        return findRoot(x) == findRoot(y);
    }

    public void union(int x, int y) {
        if (x < 0 || y < 0 || x >= arr.length || y > arr.length) {
            return;
        }
        arr[findRoot(y)] = findRoot(x);
    }

    public static void main(String[] args) {
        QuickUnion qu = new QuickUnion(8);

        System.out.println(qu.isConnected(1, 2));
        qu.union(1, 2);
        System.out.println(qu.isConnected(1, 2));
    }
}