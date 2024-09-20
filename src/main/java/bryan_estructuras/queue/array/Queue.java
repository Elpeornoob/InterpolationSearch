package bryan_estructuras.queue.array;

import bryan_estructuras.array.Array;
import bryan_estructuras.util.collection.Collection;
import bryan_estructuras.util.iterator.Iterator;
import bryan_estructuras.util.queue.AbstractQueue;
import java.util.function.Function;

public class Queue<E> extends AbstractQueue<E>{
    private Array<E> elementos;

    public Queue(){
        this.elementos = new Array<>();
    }

    public Queue(int length){
        this.elementos = new Array<>(length);
    }

    public Queue(E[] elements){
        this.elementos = new Array<>(elements);
    }
    
    @Override
    public boolean clear() {
        return elementos.clear();
    }

    @Override
    public boolean contains(E element) {
        return elementos.contains(element);
    }

    @Override
    public boolean contains(E[] array) {
        return elementos.contains(array);
    }

    @Override
    public boolean contains(Collection<E> collection) {
        return elementos.contains(collection);
    }

    @Override
    public boolean isEmpty() {
        return elementos.isEmpty();
    }

    @Override
    public boolean reverse() {
        return elementos.reverse();
    }

    @Override
    public int size() {
        return elementos.size();
    }

    @Override
    public void forEach(Function<E, Void> action) {
        elementos.forEach(action);
    }

    @Override
    public Iterator<E> iterator() {
        return elementos.iterator();
    }

    @Override
    public E peek() {
        return elementos.get(0);
    }

    @Override
    public E extract() {
        E elemento = peek();
        elementos.remove(0);
        return elemento;
    }

    @Override
    public boolean insert(E element) {
        return elementos.add(element);
    }
    
}
