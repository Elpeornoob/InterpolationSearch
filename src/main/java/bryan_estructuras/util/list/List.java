package bryan_estructuras.util.list;

import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import bryan_estructuras.util.collection.Collection;

public interface List<E> {
	
	/**
	 * Appends the specified element to the end of this list.
	 * 
	 * @param element The element to be appended to this list.
	 * @return 'true' if the element was added successfully, otherwise 'false'.
	 */
	public boolean add(E element);
	
	/**
	 * Appends the array of element to the end of this list.
	 * 
	 * @param array The array whose elements are to be added to the list.
	 * @return Returns 'true' if the array was added successfully, otherwise 'false'.
	 */
	public boolean add(E[] array);
	
	/**
	 * Appends all of the elements in the specified collection to the end of this list.
	 * 
	 * @param collection The collection whose elements are to be added to the list.
	 * @return 'true' if the element was added successfully, otherwise 'false'.
	 */
	public boolean add(Collection<E> collection);
	
	/**
	 * Appends the specified element at the beginning of this list.
	 * 
	 * @param element The element to be appended to this list.
	 * @return 'true' if the element was added successfully, otherwise 'false'.
	 */
	public boolean addFirst(E element);
	
	/**
	 * Appends all of the elements in the specified array at the beginning of this list, in the order that they are returned by the specified collection's iterator.
	 * 
	 * @param array The element to be appended to this list.
	 * @return 'true' if the collection was added successfully, otherwise 'false'.
	 */
	public boolean addFirst(E[] array);
	
	/**
	 * ppends all of the elements in the specified collection at the beginning of this list, in the order that they are returned by the specified collection's iterator.
	 * 
	 * @param collection The element to be appended to this list.
	 * @return 'true' if the collection was added successfully, otherwise 'false'.
	 */
	public boolean addFirst(Collection<E> collection);
	
	/**
	 * Retrieves, but does not remove, the head (first element) of this list.
	 * 
	 * @return the element in the head of this list, or 'null' if this list is empty.
	 */
	public E peek();
	
	/**
	 * Retrieves, but does not remove, the last element of this list.
	 * 
	 * @return the element in the last of this list, or 'null' if this list is empty.
	 */
	public E peekLast();
	
	/**
	 * Retrieves, but does not remove, the first n elements of the list.
	 * 
	 * @param n The number of elements to get.
	 * @return an array containing the first n elements of the list.
	 */
	public E[] peekArray(int n);
	
	/**
	 * Retrieves, but does not remove, the last n elements of the list.
	 * 
	 * @param n The number of elements to get.
	 * @return an array containing the last n elements of the list.
	 */
	public E[] peekLastArray(int n);
	
	/**
	 * Retrieves, but does not remove, the first n elements of the list.
	 * 
	 * @param n The number of elements to get.
	 * @return a collection containing the first n elements of the list.
	 */
	public List<E> peekCollection(int n);
	
	public List<E> peekLastCollection(int n);
	
	public E poll();
	
	public E pollLast();
	
	public E[] pollArray(int n);
	
	public E[] pollLastArray(int n);
	
	public List<E> pollCollection(int n);
	
	public List<E> pollLastCollection(int n);
	
	public boolean remove(E element);
	
	public boolean remove(E[] array);
	
	public boolean remove(Collection<E> collection);
	
	public boolean remove(Predicate<E> filter);
	
	public boolean replace(E element, E newElement, Predicate<E> comparator);
	
	public boolean replace(E[] array, E[] newArray, Predicate<E> comparator);
	
	public boolean replace(Collection<E> collection, Collection<E> newCollection, Predicate<E> comparator);
	
	public boolean retain(E[] array);
	
	public boolean retain(Collection<E> collection);
	
	public boolean set(E index, E element);
	
	public boolean sort(ToIntFunction<E> toInt);
	
	public List<E> subList(E from, E to);
	
	@SuppressWarnings("hiding")
	public <E> E[] toArray(E[] array);

}
