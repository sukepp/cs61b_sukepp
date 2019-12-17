public class LinkedListDeque <T> {
    private class Node {
        //U  _item;
        T _item;
        Node _next;
        Node _prev;

        public Node(T item, Node next, Node prev) {
            _item = item;
            _next = next;
            _prev = prev;
        }

        public String toString() {
            return _item.toString();
        }

    }

    private Node sentinel;
    private int size = 0;

    public LinkedListDeque() {
        sentinel = new Node(null, sentinel, sentinel);
        sentinel._next = sentinel;
        sentinel._prev = sentinel;
    }

    public LinkedListDeque(LinkedListDeque other) {
        if (other == null || other.isEmpty()) {
            sentinel = new Node(null, sentinel, sentinel);
            sentinel._next = sentinel;
            sentinel._prev = sentinel;
        } else {
            sentinel = new Node(null, sentinel, sentinel);
            sentinel._next = sentinel;
            sentinel._prev = sentinel;
            Node tmp = other.sentinel._next;
            while (tmp != other.sentinel) {
                addLast(tmp._item);
                tmp = tmp._next;
                //System.out.print(tmp._item + " ");
            }
        }
    }

    public void addFirst(T item) {
        sentinel._next._prev = new Node(item, sentinel._next, sentinel);
        sentinel._next = sentinel._next._prev;
        size += 1;
    }

    public void addLast(T item) {
        sentinel._prev._next = new Node(item, sentinel, sentinel._prev);
        sentinel._prev = sentinel._prev._next;
        size += 1;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T res = sentinel._prev._item;
        sentinel._prev = sentinel._prev._prev;
        sentinel._prev._next = sentinel;
        size -= 1;
        return res;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T res = sentinel._next._item;
        sentinel._next = sentinel._next._next;
        sentinel._next._prev = sentinel;
        size -= 1;
        return res;
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
        Node tmp = sentinel._next;
        while (tmp != sentinel) {
            System.out.print(tmp._item + " ");
            tmp = tmp._next;
        }
        System.out.println();
    }

    public T get(int index) {
        if (size == 0 || index > size - 1 || index < 0) {
            return null;
        }
        Node tmp = sentinel._next;
        while (index > 0) {
            tmp = tmp._next;
            index -= 1;
        }
        
        return tmp._item;
    }

    public T getRecursive(int index) {
        if (size == 0 || index > size - 1 || index < 0) {
            return null;
        }
        return getRecursiveHelper(sentinel._next, index);
    }

    public T getRecursiveHelper(Node node, int index) {
        if (index == 0) {
            return node._item;
        } else {
            return getRecursiveHelper(node._next, index - 1);
        }
    }

    public static void main(String[] args) {
        //LinkedListDeque<String> list = new LinkedListDeque<String>();
        LinkedListDeque<Integer> list = new LinkedListDeque<Integer>();
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);
        //System.out.println(list.isEmpty());
        list.printDeque();
        LinkedListDeque<Integer> list2 = new LinkedListDeque<Integer>(list);
        list2.printDeque();
        for (int i = 0; i < 6; i++) {
            System.out.println(list2.get(i));
            System.out.println(list2.getRecursive(i));
        }
    }
}
