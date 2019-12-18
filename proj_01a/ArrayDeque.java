public class ArrayDeque<T> {
    T[] arr;
    int size;
    int nextFirst = 1;
    int nextLast = 0;

    public ArrayDeque() {
        arr = (T[])new Object[8];
        size = 0;
    }

    public ArrayDeque(ArrayDeque other) {
        arr = (T[])new Object[other.arr.length];
        size = other.size;
        nextLast = other.nextLast;
        nextFirst = other.nextFirst;
        System.arraycopy(other.arr, 0, arr, 0, other.arr.length);
    }

    public void increaseSize() {
        T[] newArr = (T[])new Object[arr.length * 2];
        for (int i = 1; i < size + 1; i++) {
            newArr[i] = arr[(nextLast + i) % arr.length];
        }
        nextLast = 0;
        nextFirst = size + 1;
        arr = newArr;
    }

    public void decreaseSize() {
        T[] newArr = (T[])new Object[arr.length / 2];
        for (int i = 1; i < size + 1; i++) {
            newArr[i] = arr[(nextLast + i) % arr.length];
        }
        nextLast = 0;
        nextFirst = size + 1;
        arr = newArr;
    }

    public void addFirst(T item) {
        if (nextFirst == nextLast) {
            increaseSize();
        }
        arr[nextFirst] = item;
        nextFirst =  (nextFirst + 1) % arr.length;
        size++;
    }

    public void addLast(T item) {
        if (nextFirst == nextLast) {
            increaseSize();
        }
        arr[nextLast] = item;
        nextLast =  (nextLast - 1 + arr.length) % arr.length;
        size++;
    }

    public T removeFirst() {
        if ((arr.length >= 16) && ((float)size / arr.length <= 0.25)) {
            decreaseSize();
        }
        nextFirst =  (nextFirst - 1 + arr.length) % arr.length;
        size--;
        return arr[nextFirst];
    }

    public T removeLast() {
        if ((arr.length >= 16) && ((float)size / arr.length <= 0.25)) {
            decreaseSize();
        }
        nextLast =  (nextLast + 1) % arr.length;
        size--;
        return arr[nextLast];
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size == 0) {
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.print(arr[(nextLast + i + 1) % arr.length] + " ");
        }
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return arr[(nextLast + index + 1) % arr.length];
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> list = new ArrayDeque<Integer>();
        for (int i = 0; i < 100; i++) {
            list.addFirst(i);
            list.addLast(i);
        }
        for (int i = 0; i < 99; i++) {
            list.removeFirst();
            list.removeLast();
        }
        list.printDeque();
    }
}
