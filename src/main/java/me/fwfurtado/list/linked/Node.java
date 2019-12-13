package me.fwfurtado.list.linked;

import static java.util.Objects.nonNull;

import me.fwfurtado.list.linked.simply.SimplyNode;

public interface Node<E extends Comparable<E>> extends Comparable<Node<E>> {
    E getContent();

    Node<E> getNext();

    void setNext(Node<E> next);

    Node<E> copy();

    default boolean hasNext() {
        return nonNull(getNext());
    }

    @Override
    default int compareTo(Node<E> o) {
        return getContent().compareTo(o.getContent());
    }

    default boolean isLessThan(Node<E> other) {
        return compareTo(other) < 0;
    }

    default boolean isEqualTo(Node<E> other) {
        return compareTo(other) == 0;
    }

    default boolean isGreaterThan(Node<E> other) {
        return compareTo(other) > 0;
    }

    static <E extends Comparable<E>> Node<E> unidirectional(E element) {
        return SimplyNode.of(element);
    }

    static <E extends Comparable<E>> Node<E> unidirectionalBefore(E element, Node<E> next) {
        return SimplyNode.beforeOf(element, next);
    }
}
