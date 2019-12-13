package me.fwfurtado.list;

import static org.junit.jupiter.api.Assertions.*;

import me.fwfurtado.list.linked.simply.InsertionSort;
import me.fwfurtado.list.linked.simply.SimplyLinkedList;
import org.junit.jupiter.api.Test;

class SimplyLinkedListTest {

    @Test
    void shouldCreateALinkedListOf1And2And3() {
        var list = SimplyLinkedList.withFirst(5);

        list.addOnLast(2);
        list.addOnLast(3);
        list.addOnLast(7);
        list.addOnLast(1);
        list.addOnLast(0);

        new InsertionSort().sort(list);

        assertEquals(list.size(), 5);

    }
}