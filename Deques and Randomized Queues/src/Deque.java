import java.util.Iterator;

/***
 * A double-ended queue or deque is a generalization of a stack and a queue that supports
 * adding and removing items from either the front or the back of the data structure.
 ***/

public class Deque<Item> implements Iterable<Item> {

    private Node first; // link to least recent added node
    private Node last; //link to recently added node
    private int N; // number of items on deque

    //nested class to define nodes
    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null || last == null;
    }

    // return the number of items on the deque
    public int size() {
        return N;
    }

    // add the item to the front
    public void addFirst(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.previous = null;

        if (isEmpty()) {
            last =  first;
        } else {
            oldFirst.next = last;
            oldFirst.previous = first;
        }
        N++;
    }

    // add the item to the end
    public void addLast(Item item) {
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
        N++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item item = first.item;
        first = first.next;
        first.previous = null;

        if (isEmpty()) {
            last = null;
        }
        N--;
        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        Item item = last.item;
        last = last.previous;
        last.next = null;

        if (isEmpty()) {
            first = null;
        }
        N--;
        return item;
    }

    // return an iterator over items in order from front to
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (optional)
    public static void main(String[] args) {

    }
}