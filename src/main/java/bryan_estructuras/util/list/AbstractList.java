package bryan_estructuras.util.list;

import java.util.function.Function;
import java.util.function.Predicate;

import bryan_estructuras.array.Array;
import bryan_estructuras.linkedlist.singly.LinkedList;
import bryan_estructuras.util.collection.AbstractCollection;
import bryan_estructuras.util.collection.Collection;
import bryan_estructuras.util.iterator.Iterator;

public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {

	protected int size;

	@Override
    public boolean add(E[] array) {
		try {
			for (E e : array) {
				add(e);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
        
    }

	@Override
	public boolean add(Collection<E> collection){
		try {
			Iterator<E> it = collection.iterator();
			while (it.hasNext()) {
				add(it.next());
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addFirst(E[] array){
		try {
			for (E e : array) {
				addFirst(e);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addFirst(Collection<E> collection){
		try {
			Iterator<E> it = collection.iterator();
			while (it.hasNext()) {
				addFirst(it.next());
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public E[] peekArray(int n) {
		if(isEmpty() || n < 0){
			return null;
		}
		@SuppressWarnings("unchecked")
		E[] elements = (E[]) new Object[size()];
		if(elements.length <= n){
			return elements;
		}else{
			@SuppressWarnings("unchecked")
			E[] peekElementos =  (E[]) new Object[n];
			for (int ii = 0; ii < peekElementos.length; ii++) {
				peekElementos[ii] = elements[ii];
			}
			return peekElementos;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public E[] peekLastArray(int n) {
		if (isEmpty() || n <= 0) {
			return (E[]) new Object[0]; // Retorna un array vacío en lugar de null para manejar el caso de error.
		}
		
		// Crear un array del tamaño necesario.
		E[] elements = (E[]) new Object[size()];
		
		// Llenar el array usando el método toArray.
		toArray(elements);
		
		// Verificar si el tamaño de la lista es menor que el número solicitado.
		if (size() <= n) {
			return elements;
		} else {
			// Crear un nuevo array para los últimos n elementos.
			E[] peekElementos = (E[]) new Object[n];
			
			// Copiar los últimos n elementos del array original al nuevo array.
			int start = size() - n;
			System.arraycopy(elements, start, peekElementos, 0, n);
			
			return peekElementos;
		}
	}

	@Override
	public List<E> peekCollection(int n) {
		if(isEmpty() || n < 0){
			return null;
		}
		LinkedList<E> listaElementos = new LinkedList<>();
		Iterator<E> it = iterator();
		int contador = 0;
		while (it.hasNext()) {
			E element = it.next();
			if(contador < n){
				listaElementos.add(element);
			}
			contador++;
		}
		return listaElementos;
	}

	@Override
	public List<E> peekLastCollection(int n) {
		if(isEmpty() || n < 0){
			return null;
		}
		LinkedList<E> listaElementos = new LinkedList<>();
		Iterator<E> it = iterator();
		int indicador = size() - n;
		int contador = 0;
		if (indicador > size()) {
			while (it.hasNext()) {
				listaElementos.add(it.next());
			}
		}else{
			while (it.hasNext()) {
				E element = it.next();
				if(contador >= indicador){
					listaElementos.add(element);
				}
				contador++;
			}
		}
		return listaElementos;
	}

	@Override
	public E[] pollArray(int n){
		if(n <= 0 || isEmpty()){
			return null;
		}else if(n > size()){
			n = size();
		}
		E[] elementos = peekArray(n);
		while (n > 0) {
			poll();
			n--;
		}
		if(n >= size()){
			clear();
		}
		return elementos;
	}

	@Override
	public E[] pollLastArray(int n) {
		if(n <= 0 || isEmpty()){
			return null;
		}
		E[] elementos = peekLastArray(n);
		if(n > size()){
			n = size();
		}
		while (n > 0) {
			pollLast();
			n--;
		}
		if(n >= size()){
			clear();
		}
		return elementos;
			
	}

	@Override
	public List<E> pollCollection(int n) {
		if(isEmpty() || n < 0){
			return null;
		}
		List<E> listaElementos = peekCollection(n);
		while (n > 0) {
			poll();
			n--;
		}
		return listaElementos;
	}

	@Override
	public List<E> pollLastCollection(int n) {
		if(isEmpty() || n < 0){
			return null;
		}
		List<E> listaElementos = peekLastCollection(n);
		while (n > 0) {
			poll();
			n--;
		}
		return listaElementos;
	}

	@Override
	public boolean remove(E[] array){
		if(array.length == 0){
			return false;
		}
		boolean removed = true;
		for (E e : array) {
			if(!remove(e)){
				removed = false;
			}
		}
		
		return removed;
	}

	@Override
	public boolean remove(Collection<E> collection){
		if(collection.size() == 0){
			return false;
		}
		boolean removed = true;
		Iterator<E> it = collection.iterator();
		while (it.hasNext()) {
			E element = it.next();
			if(!remove(element)){
				removed = false;
			}
		}
		return removed;
	}

	@Override
	public boolean replace(E[] array, E[] newArray, Predicate<E> comparator) {
		if(array.length != newArray.length){
			return false;
		}
		boolean replaced = false;
		for (int ii = 0; ii < newArray.length; ii++) {
			E elemento = array[ii];
			E elementoNuevo = newArray[ii];
			replaced |= replace(elemento, elementoNuevo, comparator);
		}
		return replaced;
	}

	@Override
	public boolean replace(Collection<E> collection, Collection<E> newCollection, Predicate<E> comparator) {
		boolean replaced = false;
		Iterator<E> collectionIterator = collection.iterator();
		Iterator<E> newCollectionIterator = newCollection.iterator();
		while (collectionIterator.hasNext()) {
			E elemento = collectionIterator.next();
			E elementoNuevo = newCollectionIterator.next();
			replaced |= replace(elemento, elementoNuevo, comparator);
		}
		return replaced;
	}

	@Override
	public boolean contains(E element) {
		Iterator<E> it  = iterator();
		while (it.hasNext()) {
			if(it.next().equals(element)){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean contains(E[] array) {
		if(array == null){
			return false;
		}
		boolean contained = false;
		for (int ii = 0; ii < array.length; ii++) {
			contained |= contains(array[ii]);
		}
		return contained;
	}

	@Override
	public boolean contains(Collection<E> collection) {
            if(collection == null){
                return false;
            }
            boolean contained = false;
            Iterator<E> it = collection.iterator();
            while (it.hasNext()) {
                E next = it.next();
                contained |= contains(next);
            }
            return contained;
	}

	@Override	
	public boolean retain(E[] array) {
		if(array == null || array.length == 0){
			return false;
		}
		Array<E> arrayRetain = new Array<>(array);
		boolean retained = false;
		boolean contained = false;
		LinkedList<E> elementosAEliminar = new LinkedList<>();
		Iterator<E> it = iterator();
		while (it.hasNext()) {
			E element = it.next();
			if(arrayRetain.contains(element)){
				contained = true;
			}else{
				elementosAEliminar.add(element);
				retained = true;
			}
		}
		remove(elementosAEliminar);
		return retained && contained;
	}

	@Override
	public boolean retain(Collection<E> collection) {
		if (collection == null || collection.isEmpty()) {
			return false;
		}
		boolean contained = false;
		LinkedList<E> elementsToRemove = new LinkedList<>();
		Iterator<E> it = iterator();
		while (it.hasNext()) {
			E element = it.next();
			if (collection.contains(element)) {
				contained = true;
			}else{
				elementsToRemove.add(element);
			}
		}
		remove(elementsToRemove);
		return !elementsToRemove.isEmpty() && contained;
	}

	@SuppressWarnings("hiding")
	public <E> E[] toArray(E[] array) {
		// Si el array proporcionado es más grande que el tamaño de la lista,
		// se usa el array proporcionado, de lo contrario, se crea uno nuevo.
		int size = size();
		if (array.length < size) {
			@SuppressWarnings("unchecked")
			E[] newArray = (E[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), size);
			array = newArray;
		}
	
		// Usar el iterador para llenar el array.
		@SuppressWarnings("unchecked")
		Iterator<E> it = (Iterator<E>) iterator();
		int index = 0;
		while (it.hasNext()) {
			array[index++] = (E) it.next();
		}
	
		// Asegurarse de que el array proporcionado sea del tamaño correcto.
		if (array.length > size) {
			array[size] = null; // Nullify the element after the last element if the array is larger.
		}
	
		return array;
	}

	@Override
	public void forEach(Function<E, Void> action) {
		Iterator<E> it =  iterator();
		while (it.hasNext()) {
			action.apply(it.next());
		}
	}
	
	@Override
	public int size(){
		return size;
	}
	
}
