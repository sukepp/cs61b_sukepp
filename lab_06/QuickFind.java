public class QuickFind {
    int[] arr;
    
    public QuickFind(int size) {
        if (size < 0) {
            return;
        }
        arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
    }

    public boolean isConnected(int x, int y) {
        if (x < 0 || y < 0 || x >= arr.length || y > arr.length) {
            return false;
        }
        return arr[x] == arr[y];
    }

    public void union(int x, int y) {
        if (x < 0 || y < 0 || x >= arr.length || y > arr.length) {
            return;
        }
        if (isConnected(x, y) == true) {
            return;
        }
        int oldGroup = arr[y];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == oldGroup) {
                arr[i] = arr[x];
            }
        }
    }
    
    public static void main(String[] args) {
        QuickFind qf = new QuickFind(8);

        System.out.println(qf.isConnected(1, 2));
        qf.union(1, 2);
        System.out.println(qf.isConnected(1, 2));
    }
}
