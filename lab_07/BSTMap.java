import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Entry root;
    private int size;

    private class Entry {
        private K key;
        private V val;
        Entry left;
        Entry right;

        public Entry(K k, V v) {
            key = k;
            val = v;
            left = null;
            right = null;
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (root == null) {
            return false;
        }
        return get(key) != null;
    }

    private Entry getHelper(Entry parent, K k) {
        if (parent == null) {
            return null;
        }
        int res = parent.key.compareTo(k);
        if (res == 0) {
            return parent;
        } else if (res > 0) {
            return getHelper(parent.left, k);
        } else {
            return getHelper(parent.right, k);
        }
    }

    @Override
    public V get(K key) {
        if (root == null) {
            return null;
        }
        Entry lookup = getHelper(root, key);
        if (lookup == null) {
            return null;
        }
        return lookup.val;
    }

    @Override
    public int size() {
        return size;
    }

    private Entry putHelper(Entry parent, K k, V v) {
        if (parent == null) {
            size++;
            return new Entry(k, v);
        }
        int res = parent.key.compareTo(k);
        if (res == 0) {
            parent.val = v;
        } else if (res > 0) {
            parent.left = putHelper(parent.left, k, v);
        } else {
            parent.right = putHelper(parent.right, k, v);
        }
        return parent;
    }

    @Override
    public void put(K key, V value) {
        root = putHelper(root, key, value);
    }

    private void keySetHelper(Set keySet, Entry parent) {
        if (parent == null) {
            return;
        }
        keySet.add(parent.key);
        keySetHelper(keySet, parent.left);
        keySetHelper(keySet, parent.right);
    }

    @Override
    public Set<K> keySet() {
        Set<K> res = new TreeSet<K>();
        keySetHelper(res, root);
        return res;
    }

    private Entry min(Entry parent) {
        if (parent.left == null) {
            return parent;
        }
        return min(parent.left);
    }

    private Entry deleteMin(Entry parent) {
        if (parent.left == null) {
            return parent.right;
        }
        parent.left = deleteMin(parent.left);
        return parent;
    }

    private Entry removeHelper(Entry parent, K key, V val) {
        if  (parent == null) {
            return null;
        }
        int cmp = parent.key.compareTo(key);
        if (cmp > 0) {
            parent.left = removeHelper(parent.left, key, val);
        } else if (cmp < 0) {
            parent.right = removeHelper(parent.right, key, val);
        } else if (val == null || parent.val.equals(val)){
            if (parent.left == null) {
                return parent.right;
            }
            if (parent.right == null) {
                return parent.left;
            }
            Entry entryToDel = parent;
            parent = min(parent.right);
            parent.right = deleteMin(entryToDel.right);
            parent.left = entryToDel.left;
        } else {
            return null;
        }

        return parent;
    }

    @Override
    public V remove(K key) {
        return remove(key, null);
    }

    @Override
    public V remove(K key, V value) {
        if (root == null) {
            return null;
        }
        V res = get(key);
        if (res == null) {
            return null;
        } else if (value != null && res != value) {
            return null;
        }
        root = removeHelper(root, key, value);
        size--;
        return res;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
