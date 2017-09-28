package de.giorgio_gross.gol;

/**
 * Created by Giorgio on 28.09.17.
 */
public interface EventProvider<E> {

    void register(E eventListener);

}
