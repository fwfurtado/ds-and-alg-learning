package me.fwfurtado.hash;

import static java.util.Objects.isNull;

import java.lang.reflect.Array;
import me.fwfurtado.list.linked.LinkedList;

public class HashTable<T extends Comparable<T>> implements Hash<T> {

    private static final int INITIAL_SIZE = 11;

    private int size;
    LinkedList<T>[] table;

    @SuppressWarnings("unchecked")
    public HashTable() {
        table = (LinkedList<T>[]) Array.newInstance(LinkedList.class, INITIAL_SIZE);
        size = INITIAL_SIZE;
    }

    @Override
    public boolean contains(T element) {
        int index = hashing(element);
        var bucket = table[index];

        if (isNull(bucket)) {
            return false;
        }

        return bucket.contains(element);
    }

    @Override
    public boolean add(T element) {
        int index = hashing(element);

        if (isNull(table[index])) {
            table[index] = LinkedList.simple();
        }

        LinkedList<T> bucket = table[index];

        if (bucket.contains(element)) {
            return false;
        }

        bucket.addOnLast(element);

        return true;
    }

    private int hashing(T element) {
        var key = element.hashCode();

        int absoluteKey = key & 0x7FFFFFFF;

        return absoluteKey % size;
    }
}
