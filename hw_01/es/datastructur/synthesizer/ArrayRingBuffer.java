package es.datastructur.synthesizer;

import org.junit.Test;

import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * Return the size of buffer.
     */
    public int capacity() {
        return rb.length;
    }

    /**
     * Return the number of items in the buffer.
     */
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     * @param x
     */
    @Override
    public void enqueue(T x) throws RuntimeException {
        if (fillCount == rb.length) {
            throw new RuntimeException("Ring Buffer overflow");
        }
        rb[last] = x;
        last = (last + 1) % rb.length;
        fillCount++;
        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() throws RuntimeException {
        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        T res = rb[first];
        first = (first + 1) % rb.length;
        fillCount--;
        return res;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        T res = rb[first];
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ArrayRingBuffer) {
            if (((ArrayRingBuffer) obj).capacity() != this.capacity()) {
                return false;
            }
            for (int i = 0; i < this.capacity(); i++) {
                if (this.rb[i] != ((ArrayRingBuffer) obj).rb[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator {
        private int cur;

        public ArrayRingBufferIterator() {
            cur = first;
        }

        @Override
        public boolean hasNext() {
            return cur != last;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                return null;
            }
            T res = rb[cur];
            cur = (cur + 1) % rb.length;
            return res;
        }
    }
}
