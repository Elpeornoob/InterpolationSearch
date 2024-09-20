package bryan_estructuras.util.iterable;

import java.util.function.Function;

import bryan_estructuras.util.iterator.Iterator;

public interface Iterable<E> {
  
    /**
     * For each element in the iterator, executes the specified action.
     * 
     * @param action The action to be executed on each element.
     */
    public void forEach(Function<E, Void> action);

    /**
     * Gets an iterator over the elements in the iterator.
     * 
     * @return an iterator over the elements in the iterator.
     */
    public Iterator<E> iterator();
  }