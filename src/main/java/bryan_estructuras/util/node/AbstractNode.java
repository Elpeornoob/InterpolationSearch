package bryan_estructuras.util.node;

public abstract class AbstractNode <E>implements Node<E>, Cloneable{
	protected E element;

	protected AbstractNode() {
		super();
		this.element = null;
	}
	
	protected AbstractNode(E element) {
		super();
		this.element = element;
	}

	@Override
	public void set(E element) {
		this.element = element;
	}

	@Override
	public E get() {
		return element;
	}

	@Override
	public String toString() {
		return element.toString();
	}
	
	

}
