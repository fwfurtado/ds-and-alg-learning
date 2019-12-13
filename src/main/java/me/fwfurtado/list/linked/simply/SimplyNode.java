package me.fwfurtado.list.linked.simply;

import java.util.Objects;
import java.util.StringJoiner;
import me.fwfurtado.list.linked.Node;

public class SimplyNode<T extends Comparable<T>> implements Node<T> {

    private final T data;
    private Node<T> next;

    private SimplyNode(T element) {
        this.data = element;
    }

    private SimplyNode(T element, Node<T> next) {
        this.data = element;
        this.next = next;
    }

    public static <T extends Comparable<T>> SimplyNode<T> of(T element) {
        return new SimplyNode<>(element);
    }

    public static <T extends Comparable<T>> SimplyNode<T> beforeOf(T element, Node<T> next) {
        return new SimplyNode<>(element, next);
    }

    @Override
    public T getContent() {
        return data;
    }

    @Override
    public Node<T> getNext() {
        return next;
    }

    @Override
    public void setNext(Node<T> next) {
        this.next = next;
    }

    @Override
    public Node<T> copy() {
        return new SimplyNode<>(data    );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimplyNode<?> that = (SimplyNode<?>) o;
        return data.equals(that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(" ")
            .add(data.toString());

        if (hasNext() && !next.equals(this)) {
            joiner.add("->");
        }

        return joiner.toString();
    }
}
