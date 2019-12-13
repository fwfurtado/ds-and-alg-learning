package me.fwfurtado.list.linked.simply;

import me.fwfurtado.list.SortAlgorithm;
import me.fwfurtado.list.linked.LinkedList;
import me.fwfurtado.list.linked.Node;

public class InsertionSort implements SortAlgorithm<Integer> {

    public LinkedList<Integer> sort(LinkedList<Integer> original) {

        var current = original.getFirst();

        for (int i = 1; i < original.size() ; i++) {

            var after = original.getFirst();

            for (int j = 1; j < i; j++) {
                shift(current, after);

                after = after.getNext();
            }

            System.out.println(current);
        }


        return null;
    }

    private void shift(Node<Integer> current, Node<Integer> after) {

        if (after.getContent() < current.getContent()) {
            current.setNext(after.getNext());
            after.setNext(current);
        }

    }

}
