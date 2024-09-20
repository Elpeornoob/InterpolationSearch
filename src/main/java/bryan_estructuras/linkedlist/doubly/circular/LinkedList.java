package bryan_estructuras.linkedlist.doubly.circular;

import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import bryan_estructuras.linkedlist.node.doubly.LinkedNode;
import bryan_estructuras.util.iterator.Iterator;
import bryan_estructuras.util.list.AbstractList;
import bryan_estructuras.util.list.List;

public class LinkedList<E> extends AbstractList<E>{
    private LinkedNode<E> head;
    private LinkedNode<E> inode;

    @Override
    public boolean add(E element) {
        try {
            LinkedNode<E> node = new LinkedNode<>();
            node.set(element);
            if(isEmpty()){
                head = node;
                node.setNext(head);
                head.setPrev(node);
                
            }else{
                LinkedNode<E> current = head.getPrev();
                node.setPrev(current);
                node.setNext(head);
                current.setNext(node);
                head.setPrev(node);
            }
            size++;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addFirst(E element) {
        try {
            LinkedNode<E> antes;
            LinkedNode<E> node = new LinkedNode<>();
            node.set(element);
            if(isEmpty()){
                head = node;
                node.setNext(head);
                head.setPrev(node);
            }else{
                antes = head.getPrev();
                antes.setNext(node);
                node.setPrev(antes);
                node.setNext(head);
                head.setPrev(node);
                head = node;
            }
            size++;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public E peek() {
        if(isEmpty()){
            return null;
        }
        return head.get();
    }

    @Override
    public E peekLast() {
        if(isEmpty()){
            return null;
        }
        return head.getPrev().get();
    }

    @Override
    public E poll() {
        if(isEmpty()){
            return null;
        }
        E obtenerE = head.get();
        if(size == 1){
            head = null;
            inode = null;
        }else{
        LinkedNode<E> tail = head.getPrev();
        head = head.getNext();
        tail.setNext(head);
        head.setPrev(tail);
        }
        size--;
        return obtenerE;
    }

    @Override
    public E pollLast() {
        if(isEmpty()){
            return null;
        }
        E element = peekLast();
        remove(element);
        return element;
    }

    @Override
    public boolean remove(E element) {
        if (isEmpty() || element == null) {
            return false;
        }
        LinkedNode<E> current = head;
        do {
            if (element.equals(current.get())) {
                if (size() == 1) { 
                    head = null;
                } else { 
                    LinkedNode<E> prevNode = current.getPrev();
                    LinkedNode<E> nextNode = current.getNext();
                    prevNode.setNext(nextNode);
                    nextNode.setPrev(prevNode);
                    if (current == head) {  
                        head = nextNode;
                    }
                }
                size--;
                return true;
            }
            current = current.getNext();
        } while (current != head);
        return false;
    }

    @Override
    public boolean remove(Predicate<E> filter) {
        if (isEmpty() || filter == null) {
            return false;
        }
        LinkedNode<E> current = head;
        boolean removed = false;
        do {
            if (filter.test(current.get())) {
                if (size() == 1) { 
                    head = null;
                } else { 
                    LinkedNode<E> prevNode = current.getPrev();
                    LinkedNode<E> nextNode = current.getNext();
                    prevNode.setNext(nextNode);
                    nextNode.setPrev(prevNode);
                    if (current == head) {  
                        head = nextNode;
                    }
                }
                size--;
                removed = true;
            }
            current = current.getNext();
        } while (current != head);
        return removed;
    }

    @Override
    public boolean replace(E element, E newElement, Predicate<E> comparator) {
        LinkedNode<E> current = head;
        boolean replaced = false;
        if(comparator.test(head.get()) && head.get().equals(element)){
            current.set(newElement);
            replaced = true;
        }
        current = current.getNext();
        while (current != head) {
            if (comparator.test(current.get()) && current.get().equals(element)) {
                current.set(newElement);
                replaced = true;
            }
            current = current.getNext();
        }
        return replaced;
    }

    @Override
    public boolean set(E index, E element) {
        if(isEmpty()){
            return false;
        }
        LinkedNode<E> current = head;
        if(head.get().equals(index)){
            head.set(element);
            return true;
        }
        do {
            current = current.getNext();
            if(current.get().equals(index)){
                current.set(element);
                return true;
            }
        } while (current != head);
        return false;
    }

    @Override
    public boolean sort(ToIntFunction<E> toInt) {
        if (isEmpty() || size() == 1 || toInt == null) {
            return false; 
        }
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            LinkedNode<E> current = head;
            while (current.getNext() != head) {
                LinkedNode<E> nextNode = current.getNext();
                if (toInt.applyAsInt(current.get()) > toInt.applyAsInt(nextNode.get())) {
                    E temp = current.get();
                    current.set(nextNode.get());
                    nextNode.set(temp);
                    sorted = false;
                }
    
                current = nextNode;
            }
        }
        return true;
    }

    @Override
    public List<E> subList(E from, E to) {
        if (isEmpty() || from == null || to == null) {
			return null; 
		}else if(from.equals(to)){
			LinkedList<E> lista = new LinkedList<>();
			lista.add(from);
			return lista;
		}
		LinkedNode<E> desde = findNode(from);
		LinkedNode<E> hasta = findNode(to);
	
		if (desde == null || hasta == null) {
			return null; 
		}
		LinkedList<E> sublist = new LinkedList<>();
		LinkedNode<E> current = desde;
		while (current != null) {
			sublist.add(current.get());
			current = current.getNext();
			if(current.equals(hasta)){
				break;
			}
		}
		sublist.add(hasta.get());
		return sublist;
    }

    private LinkedNode<E> findNode(E element) {
		LinkedNode<E> current = head;
        boolean primeraIte = true;
		while (current != head || primeraIte) {
			if (current.get().equals(element)) {
				return current; 
			}
			current = current.getNext();
            primeraIte = false;
		}
		return null;
	}

    @Override
    public boolean clear() {
        head = null;
        inode = null;
        size = 0;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return head == null && inode == null && size() == 0;
    }

    @Override
    public boolean reverse() {
        if (isEmpty() || size() == 1) {
            return false;
        }
        LinkedNode<E> prev = null;
        LinkedNode<E> current = head;
        LinkedNode<E> next;
        do {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        } while (current != head);
        head = prev;
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        inode = head;
        return new Iterator<E>() {

            @Override
            public boolean hasNext() {
                return inode != null;
            }
    
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E element = inode.get();
                inode = inode.getNext();
                if (inode == head) {
                    inode = null;
                }
                return element;
            }
        };
    }
    
}
