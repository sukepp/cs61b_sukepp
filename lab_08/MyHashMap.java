import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private static final int defaultCapacity = 16;
    private static final double defaultLoadFactor = 0.75;
    private final int initialCapacity;
    /**
     * loadFactor = size / buckets.length
     */
    private final double loadFactor;

    private class Entry {
        K key;
        V val;

        /**
         * Represents one key-value pair in MyHashMap that stores the key-value pairs
         */
        Entry(K k, V v) {
            key = k;
            val = v;
        }
    }
    private List<Entry>[] buckets;
    private Set<K> keySet;

    public MyHashMap(int initialCapacity, double loadFactor) {
        this.initialCapacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.buckets = createBuckets(initialCapacity);
        this.keySet = new HashSet<>();
    }

    public MyHashMap() {
        this(defaultCapacity, defaultLoadFactor);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, defaultLoadFactor);
    }

    private List[] createBuckets(int capacity) {
        List[] result = new List[capacity];
        for (int i = 0; i < result.length; i++) {
            result[i] = new ArrayList<>();
        }
        return result;
    }

    private void resize(int newCapacity) {
        List<Entry>[] backUp = this.buckets;
        this.buckets = createBuckets(newCapacity);
        for (List<Entry> entryList : backUp) {
            for (Entry entry : entryList) {
                put(entry);
            }
        }
    }

    @Override
    public void clear() {
        this.buckets = createBuckets(initialCapacity);
        this.keySet = new HashSet<>();
    }

    private int calcIndex(K key) {
        return Math.floorMod(key.hashCode(), buckets.length);
    }

    @Override
    public boolean containsKey(K key) {
        return keySet.contains(key);
    }

    @Override
    public V get(K key) {
        if (containsKey(key)) {
            for (Entry entry : buckets[calcIndex(key)]) {
                if (entry.key.equals(key)) {
                    return entry.val;
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return keySet.size();
    }

    private void put(Entry newEntry) {
        if (1.0 * size() / buckets.length > loadFactor) {
            resize(buckets.length * 2);
        }
        K newKey = newEntry.key;
        int index = calcIndex(newKey);
        for (Entry entry : buckets[index]) {
            if (entry.key.equals(newKey)) {
                entry.val = newEntry.val;
                return;
            }
        }
        buckets[index].add(newEntry);
        keySet.add(newEntry.key);
    }

    @Override
    public void put(K key, V value) {
        put(new Entry(key, value));
    }

    @Override
    public Set<K> keySet() {
        return this.keySet;
    }

    @Override
    public V remove(K key) {
        return remove(key, null);
    }

    @Override
    public V remove(K key, V value) {
        int index = calcIndex(key);
        for (Entry entry : buckets[index]) {
            if (entry.key.equals(key) && (value == null || entry.val.equals(value))) {
                buckets[index].remove(entry);
                keySet.remove(key);
                return entry.val;
            }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet.iterator();
    }
}
