package bryan_estructuras.linkedlist.node.singly;

import bryan_estructuras.util.node.AbstractNode;

public class LinkedNode <E> extends AbstractNode<E>{
	private LinkedNode<E> next;

	public LinkedNode() {
		super();
		this.next = null;
	}

	public LinkedNode(E element){
		set(element);
	}  
	
	public LinkedNode(LinkedNode<E> next) {
		super();
		this.next = null;
	}
	
	public LinkedNode<E> getNext(){
		return next;
	}
	
	public void setNext(LinkedNode<E> next) {
		this.next = next;
	}

}
