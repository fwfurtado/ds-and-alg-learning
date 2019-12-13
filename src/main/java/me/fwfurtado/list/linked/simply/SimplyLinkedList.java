package me.fwfurtado.list.linked.simply;

import static java.util.Objects.nonNull;

import java.util.StringJoiner;
import java.util.function.Consumer;
import me.fwfurtado.list.linked.LinkedList;
import me.fwfurtado.list.linked.Node;

public class SimplyLinkedList<T extends Comparable<T>> implements LinkedList<T> {

    private Node<T> first;
    private Node<T> last;

    private long length = 1;

    public static <T extends Comparable<T>> SimplyLinkedList<T> withFirst(T first) {
        return new SimplyLinkedList<>(first);
    }

    public static <T extends Comparable<T>> SimplyLinkedList<T> empty() {
        return new SimplyLinkedList<>();
    }


    public static <T extends Comparable<T>> SimplyLinkedList<T> withHead(Node<T> first) {
        return new SimplyLinkedList<>(first);
    }

    private SimplyLinkedList() {
        length = 0;
    }

    private SimplyLinkedList(Node<T> head) {
        this.first = head;

        traverse(this::setLast);
    }

    private void setLast(Node<T> node) {
        this.last = node;
    }

    private SimplyLinkedList(T first) {
        var firstNode = SimplyNode.of(first);

        this.first = firstNode;
        this.last = firstNode;
    }

    @Override
    public void add(Node<T> element) {
        addTail(element);
    }

    @Override
    public void addOnLast(T element) {
        var newNode = SimplyNode.of(element);

        addTail(newNode);
    }

    private void addTail(Node<T> newSimpleNode) {
        if (length == 0) {
            first = newSimpleNode;
            last = newSimpleNode;

            first.setNext(last);
        } else {
            last.setNext(newSimpleNode);

            last = newSimpleNode;
        }
        length++;
    }

    @Override
    public void addAsFirst(T element) {
        var node = SimplyNode.of(element);

        node.setNext(first);

        first = node;

        length++;
    }

    @Override
    public Node<T> getFirst() {
        return first;
    }

    @Override
    public void setFirst(Node<T> node) {
        this.first = node;
    }

    @Override
    public T removeNode(Node<T> node) {

        var it = first;

        for (int i = 1; i < length; i++) {

            Node<T> next = it.getNext();

            if (nonNull(next) && next.isEqualTo(node)) {
                it.setNext(node.getNext());

                break;
            }

        }

        return node.getContent();
    }

    @Override
    public void advanced(Consumer<T> consumer) {
        traverse(node -> consumer.accept(node.getContent()));
    }



    @Override
    public LinkedList<T> copy() {

        LinkedList<T> copyList = LinkedList.simple(first.getContent());

        traverse(copyList::add);

        return copyList;
    }

    @Override
    public long size() {
        return length;
    }

    @Override
    public boolean contains(T element) {
        return contains(SimplyNode.of(element));
    }

    @Override
    public boolean contains(Node<T> element) {
        var it = first;

        for (int i = 0; i < length; i++){
            if (it.equals(element)) {
                return true;
            }

            it = it.getNext();
        }

        return false;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(" ", "[", "]");

        traverse(node -> joiner.add(node.toString()));

        return joiner.toString();
    }

    private void traverse(Consumer<Node<T>> consumer) {
        var it = first;

        while (it != last) {
            consumer.accept(it);
            it = it.getNext();
        }

        consumer.accept(it);
    }

}
