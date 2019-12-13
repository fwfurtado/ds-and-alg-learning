package me.fwfurtado.hash;

public interface Hash<E extends Comparable<E>> {
    boolean contains(E element);

    boolean add(E element);

}
