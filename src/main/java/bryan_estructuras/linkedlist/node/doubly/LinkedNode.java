package bryan_estructuras.linkedlist.node.doubly;

import bryan_estructuras.util.node.AbstractNode;

public class LinkedNode<E> extends AbstractNode<E> {
    private LinkedNode<E> next;
    private LinkedNode<E> prev;

    public LinkedNode() {
        this.next = null;
        this.prev = null;
    }
    
    public LinkedNode(E element){
        set(element);
        next = null;
        prev = null;
    }
    
    public LinkedNode<E> getPrev(){
        return prev;
    }

    public void setPrev(LinkedNode<E> prev) {
        this.prev = prev;
    }

    public LinkedNode<E> getNext() {
        return next;
    }

    public void setNext(LinkedNode<E> next) {
        this.next = next;
    }
    
}
