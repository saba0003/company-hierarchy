package utils;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class LinkedList<T> implements Iterable<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList() {
    }

    public LinkedList(T head) {
        Node<T> node = new Node<>(head);
        this.head = node;
        this.tail = node;
    }

    @SafeVarargs
    public final LinkedList<T> of(T... elements) {
        LinkedList<T> linkedList = new LinkedList<>();
        addAll(linkedList, elements);
        return linkedList;
    }

    public T getHead() {
        return head.data;
    }

    public void setHead(T head) {
        this.head = new Node<>(head);
    }

    public T getTail() {
        return tail.data;
    }

    public void setTail(T tail) {
        this.tail = new Node<>(tail);
    }

    /** Collection methods */
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        for (T e : this)
            if (Objects.equals(e, o))
                return true;
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        Node<T> current = head;
        while (current != null) {
            action.accept(current.data);
            current = current.next;
        }
    }

    @Override
    public Spliterator<T> spliterator() {
        return Spliterators.spliterator(iterator(), size, Spliterator.ORDERED);
    }

    public boolean add(T element) {
        Node<T> node = new Node<>(element);
        if (head == null)
            head = node;
        else
            tail.next = node;
        tail = node;
        increaseSize();
        return true;
    }

    @SafeVarargs
    public final boolean addAll(T... elements) {
        for (T element : elements)
            add(element);
        return true;
    }

    @SafeVarargs
    public static <T> boolean addAll(LinkedList<T> linkedList, T... elements) {
        for (T element : elements)
            linkedList.add(element);
        return true;
    }

    public boolean remove(T element) {
        Node<T> prev = null;
        Node<T> current = head;
        while (current != null) {
            if (Objects.equals(current.data, element)) {
                if (prev == null) {
                    head = current.next;
                    if (head == null)
                        tail = null;
                } else {
                    prev.next = current.next;
                    if (current == tail)
                        tail = prev;
                }
                decreaseSize();
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    /** Stream Methods */
    public Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    public Stream<T> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof LinkedList<?> that)) return false;
        return Objects.equals(head, that.head) && Objects.equals(tail, that.tail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, tail);
    }

    @Override
    public String toString() {
        return "LinkedList{" + "head=" + head + ", tail=" + tail + '}';
    }

    private void increaseSize() {
        ++size;
    }

    private void decreaseSize() {
        --size;
    }

    private static class Node<T> {

        private final T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }
}
