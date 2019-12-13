package me.fwfurtado.list.linked;

import java.util.function.Consumer;
import me.fwfurtado.list.List;
import me.fwfurtado.list.linked.simply.SimplyLinkedList;

public interface LinkedList<T extends Comparable<T>> extends List<Node<T>> {

    void addOnLast(T element);

    void addAsFirst(T element);

    boolean contains(T element);

    Node<T> getFirst();

    void setFirst(Node<T> node);

    T removeNode(Node<T> node);

    void advanced(Consumer<T> consumer);

    LinkedList<T> copy();

    static <T extends Comparable<T>> LinkedList<T> simple(T first) {
        return SimplyLinkedList.withFirst(first);
    }

    static <T extends Comparable<T>> LinkedList<T> simple() {
        return SimplyLinkedList.empty();
    }


}
