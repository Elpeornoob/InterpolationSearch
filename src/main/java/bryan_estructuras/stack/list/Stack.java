package bryan_estructuras.stack.list;

import java.util.function.Function;

import bryan_estructuras.linkedlist.singly.LinkedList;
import bryan_estructuras.util.collection.Collection;
import bryan_estructuras.util.iterator.Iterator;
import bryan_estructuras.util.stack.AbstractStack;

public class Stack<E> extends AbstractStack<E>{
    private LinkedList<E> elements;

    public Stack(){
        this.elements = new LinkedList<>();
    }
    @Override
    public E peek() {
        return elements.peekLast();
    }

    @Override
    public E pop() {
        return elements.pollLast();
    }

    @Override
    public boolean push(E element) {
        try {
            elements.add(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public boolean clear() {
        return elements.clear();
    }
    @Override
    public boolean contains(E element) {
        
        return elements.contains(element);
    }
    @Override
    public boolean contains(E[] array) {
        
        return elements.contains(array);
    }
    @Override
    public boolean contains(Collection<E> collection) {
        
        return elements.contains(collection);
    }
    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }
    @Override
    public boolean reverse() {
        return elements.reverse();
        
    }
    @Override
    public int size() {
        return elements.size();
    }
    @Override
    public void forEach(Function<E, Void> action) {
        elements.forEach(action);
    }
    @Override
    public Iterator<E> iterator() {
        return elements.iterator();
    }
    
}
