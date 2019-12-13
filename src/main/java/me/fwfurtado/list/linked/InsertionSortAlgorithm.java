package me.fwfurtado.list.linked;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import me.fwfurtado.list.linked.simply.SimplyLinkedList;

public class InsertionSortAlgorithm<T extends Comparable<T>>  {

    public LinkedList<T> sort(LinkedList<T> original) {
        var copy = original.copy();

        var it = copy.getFirst();
        Node<T> sorted = null;

        for (int i = 1; i < original.size(); i++) {

            var next = it.getNext();

            if (nonNull(next) && it.isGreaterThan(next)) {
                copy.removeNode(next);

                if (isNull(sorted)) {
                    sorted = next.copy();
                } else {

                    if (sorted.isGreaterThan(next)) {
                        next.setNext(sorted);
                        sorted = next;
                    }else {
                        sorted.setNext(next);
                    }

                    it = next;
                }
            }

        }

        return SimplyLinkedList.withHead(sorted);
    }



}
