package me.fwfurtado.list;

import me.fwfurtado.list.linked.LinkedList;

public interface List<T> {

     void add(T element);

    long size();

    boolean contains(T element);
}
