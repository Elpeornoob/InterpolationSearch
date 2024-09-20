package bryan_estructuras.linkedlist.doubly;

import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import bryan_estructuras.linkedlist.node.doubly.LinkedNode;
import bryan_estructuras.util.iterator.Iterator;
import bryan_estructuras.util.list.AbstractList;
import bryan_estructuras.util.list.List;

public class LinkedList<E> extends AbstractList<E>{
    private LinkedNode<E> head;
	private LinkedNode<E> tail;
	private LinkedNode<E> inode;

    public LinkedList() {
		super();
		head = null;
		tail = null;
		inode = null;
	}
	
	public LinkedList(E element) {
		super();
		add(element);
	}

    @Override
    public boolean add(E element) {
        try {
            LinkedNode<E> node = new LinkedNode<>();
            node.set(element);
            if (isEmpty()) {
                head = node;
                tail = node;
            }else{
                tail.setNext(node);
                node.setPrev(tail);
                tail = node;
            }
            size++;
            return true;
        } catch (Exception e) {
            System.err.println("Error al agregar el elemento " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addFirst(E element) {
        try {
            LinkedNode<E> node = new LinkedNode<>();
            node.set(element);
            if (isEmpty()) {
                head = node;
                tail = node;
            }else{
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
        return head.get();
    }

    @Override
    public E peekLast() {
        return tail.get();
    }

    @Override
    public E poll() {
        if(isEmpty()){
			return null;
		}
		E obtnerE = head.get();
		head = head.getNext();
		size--;
		return obtnerE;
    }

    @Override
    public E pollLast() {
        if(isEmpty()){
			return null;
		}
		E obtenerE = tail.get();
		if(head.equals(tail)){
			head = null;
        	tail = null;
        	size--;
        	return obtenerE;
		}
		LinkedNode<E> current = head;
		while (current != null) {
			if(current.equals(tail)){
				tail = current.getPrev();
				tail.setNext(null);
			}else{
				current = current.getNext();
			}
		}
		return obtenerE;
    }

    @Override
    public boolean remove(E element) {
        LinkedNode<E> current = head;
		if(isEmpty()){
			return false;
		}else if(element.equals(head.get())){
			if(size() == 1){
				head = null;
				tail = null;
			}else {
				head = head.getNext();
			}
			size--;
			return true;
		}else{
			while (current != null) {
				if(current.getNext().get().equals(element)){
					if(current.getNext().get().equals(tail.get())){
						current.setNext(null);
						tail = current;
					}else{
						current.setNext(current.getNext().getNext());
					}
					size--;
					return true;
				}
				current = current.getNext();
			}
		}
		return false;
    }

    @Override
    public boolean remove(Predicate<E> filter) {
        boolean removed = false;
        LinkedNode<E> current = head;
        while (current != null) {
            if(filter.test(current.get())){
                removed = remove(current.get());
            }
            current = current.getNext();
        }
        return removed;
    }

    @Override
    public boolean replace(E element, E newElement, Predicate<E> comparator) {
        boolean replaced = false;
        LinkedNode<E> current = head;
        while (current != null) {
            if(comparator.test(current.get()) && element.equals(current.get())){
                current.set(newElement);
                replaced = true;
            }
            current = current.getNext();
        }
        return replaced;
    }

    @Override
    public boolean set(E index, E element) {
        try {
            if (element == null || isEmpty()) {
                return false;
            }
            LinkedNode<E> current = head;
            while (current != null) {
                if(current.get().equals(index)){
                    current.set(element);
                }
                current = current.getNext();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean sort(ToIntFunction<E> toInt) {
        if (isEmpty() || size() == 1 || toInt == null) {
			return false; 
		}
	
		boolean sorted = false;
		LinkedNode<E> prevNode;
		LinkedNode<E> current = head.getNext();
	
		while (!sorted) {
			sorted = true;
			prevNode = head;
			current = head.getNext();
			while (current != null) {
				if (toInt.applyAsInt(prevNode.get()) > toInt.applyAsInt(current.get())) {
					E temp = prevNode.get();
					prevNode.set(current.get());
					current.set(temp);
					sorted = false;
				}
				prevNode = current;
				current = current.getNext();
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
		while (current != null) {
			if (current.get().equals(element)) {
				return current; 
			}
			current = current.getNext();
		}
		return null;
	}

    @Override
    public boolean clear() {
        head = null;
        tail = null;
        inode = null;
        size = 0;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return head == null && tail == null && inode == null && size() == 0;
    }

    @Override
    public boolean reverse() {
        if (isEmpty() || size() == 1) {
			return false;
		}
		LinkedNode<E> anterior = null;
		LinkedNode<E> current = head;
		LinkedNode<E> siguiente = null;
		tail = head;
		while (current != null) {
			siguiente = current.getNext();
			current.setNext(anterior);
			anterior = current;
			current = siguiente;
		}
		head = anterior;
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
				if(!hasNext()) {
					throw new NoSuchElementException("No more elements in the list.");
				}
				E element = inode.get();
				inode = inode.getNext();
				return element;
			}
			
			
		};
	}
    
}
