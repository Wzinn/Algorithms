import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node first; // link to least recent added node
    private Node last; // link to recently added node
    private int n; // number of items on deque

    // nested class to define nodes
    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        first = null;
        last = null;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return first == null || last == null;
    }

    // return the number of items on the queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }

        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.previous = oldLast;
        last.next = null;

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        n++;
    }

    // remove and return a random item
    public Item dequeue() {

        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        Node current = first;
        int random = StdRandom.uniform(1, size() + 1);

        if (random == 1) {
            Node oldFirst = first;
            first = oldFirst.next;
            if (first != null) {
                first.previous = null;

            }
            n--;
            return oldFirst.item;
        }

        if (random == size()) {
            Node oldLast = last;
            last = oldLast.previous;
            last.next = null;
            n--;
            return oldLast.item;
        }

        while (random > 1) {
            random--;
            current = current.next;
        }

        current.previous.next = current.next;
        current.next.previous = current.previous;
        n--;
        return current.item;
    }

    // return (but do not remove) a random item
    public Item sample() {
        Item item = null;
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        Node current = first;
        int random = StdRandom.uniform(1, size() + 1);

        while (random > 1) {
            random--;
            current = current.next;
        }
        return current.item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node current = first;
            public boolean hasNext() {
                return current != null;
            }
            @Override
            public void remove() {
                throw new java.lang.UnsupportedOperationException();
            }
            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                Item item = current.item;
                current = current.next;
                return item;
            }
        };
    }

}