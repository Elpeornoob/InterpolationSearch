package bryan_estructuras.array;

import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

import bryan_estructuras.util.array.AbstractArray;
import bryan_estructuras.util.collection.Collection;
import bryan_estructuras.util.iterator.Iterator;

public class Array<E> extends AbstractArray<E>{

private E[] elementos;
private int lenght;

@SuppressWarnings("unchecked")
public Array() {
        elementos = (E[]) new Object[100];
        lenght = 100;
}

@SuppressWarnings("unchecked")
public Array(int lenght){
        elementos = (E[]) new Object[lenght];
        this.lenght = lenght;
}

public Array(E[] elementos){
        this.elementos = elementos;  
        this.lenght = elementos.length;  
}

@Override
public void forEach(Function<E, Void> action) {
        for (E element : elementos) {
        action.apply(element);
        }
}

        @Override
        public Iterator<E> iterator() {
                return new Iterator<E>() {
                        int iArray = 0;

                        @Override
                        public boolean hasNext() {
                                return iArray < getLenght();
                        }

                        @Override
                        public E next() {
                                if(!hasNext()) {
                                        throw new NoSuchElementException("No more elements in the list.");
                                }
                                E element = elementos[iArray];
                                iArray++;
                                return element;
                        }

                };
        }

        @Override
        public boolean clear() {
                for (int ii = 0; ii < getLenght(); ii++) {
                        elementos[ii] = null;
                }
                return true;
        }

        @Override
        public boolean contains(E element) {
                for (E e : elementos) {
                        if(e != null && e.equals(element)) {
                                return true;
                        }
                }
                return false;
        }

        @Override
        public boolean contains(E[] array) {
                for(E element : array){
                        boolean contiene = false;
                        for(E elementoContains : elementos){
                                if(element != null && element.equals(elementoContains)){
                                        contiene = true;
                                        break;
                                }else if(element == null){
                                        break;
                                }
                        }
                        if(contiene == false){
                                return false;
                        }
                }
                return true;

        }

        @Override
        public boolean contains(Collection<E> collection) {
                if(collection == null || collection.isEmpty()){
                        return false;
                }

                Iterator<E> it = collection.iterator();
                while(it.hasNext()){
                        E element = it.next();
                        boolean contiene = false;
                        for(E elementoContains : elementos){
                                if(element != null && element.equals(elementoContains)){
                                        contiene = true;
                                        break;
                                }else if(element == null){
                                        break;
                                }
                        }
                        if(contiene == false){
                                return false;
                        }
                }
                return true;
        }

        @Override
        public boolean isEmpty() {
                int elementosExis = 0;
                for (int ii = 0; ii < lenght; ii++) {
                        if(elementos[ii] != null) {
                                elementosExis++;
                        }
                }
                if(elementosExis > 0) {
                        return false;
                }else {
                        return true;
                }
        }

        @Override
        public boolean reverse() {
                int posIzquierda = 0;
                int posDerecha = size() - 1;
                while(posIzquierda < posDerecha) {
                        E elementTemp = elementos[posIzquierda];
                        elementos[posIzquierda] = elementos[posDerecha];
                        elementos[posDerecha] = elementTemp;

                        posIzquierda++;
                        posDerecha--;
                }
                return true;
        }

        @Override
        public int size() {
                return getLenght();
        }

        @Override
        public boolean add(E element) {
                for (int ii = 0; ii < getLenght(); ii++) {
                        if(elementos[ii] == null) {
                                elementos[ii] = element;
                                return true;
                        }
                }
                return false;
        }

        @Override
        public boolean add(int index, E[] array) {
                if (index < 0 || index > getLenght()) {
                        return false;
                }
                int contador = 0;
                for (int ii = index; ii < getLenght(); ii++) {
                        if(contador < getLenght()){
                                elementos[ii] = array[contador];
                                contador++;
                        }
                }
                return true;
        }

        @Override
        public boolean add(int index, Collection<E> collection) {
                if(index < 0 || index > getLenght()){
                        return false;
                }

                int contador = index;
                Iterator<E> it = collection.iterator();
                while (it.hasNext()) {
                        elementos[contador] = it.next();
                        contador++;
                        if(contador == getLenght()){
                                return true;
                        }
                }
                return false;
        }

        @Override
        public void defragment() {
        int contador = 0;
        @SuppressWarnings("unchecked")
        E[] newElementos = (E[]) new Object[lenght];
        for (int ii = 0; ii < lenght; ii++) {
                if(elementos[ii] != null){
                newElementos[contador++] = elementos[ii];
                }
        }
        clear();
        for (int ii = 0; ii < lenght; ii++) {
                elementos[ii] = newElementos[ii];
        }
        }

        @Override
        public E get(int index) {
                return elementos[index];
        }

        @Override
        public int indexOf(E element) {
                for (int ii = 0; ii < getLenght(); ii++) {
                        if(elementos[ii].equals(element)){
                                return ii;
                        }
                }
                return -1;
        }

        @Override
        public int lastIndex(E element) {
                int contador = getLenght() - 1;
                while (contador >= 0) {
                        if(elementos[contador] == null){
                                contador--;
                                continue;
                        }
                        if(elementos[contador].equals(element)){
                                return contador;
                        }
                        contador--;
                }
                return -1;
        }

        @Override
        public boolean remove(int index) {
                try {
                        elementos[index] = null;
                        defragment();
                        return true;
                } catch (IndexOutOfBoundsException e) {
                // Imprimir información sobre la excepción
                e.printStackTrace();
                return false;
                }
        }

        @Override
        public boolean remove(Predicate<E> filter) {
                boolean removed = false;
        for (int ii = 0; ii < elementos.length; ii++) {
                if (filter.test(elementos[ii])) {
                        remove(ii); 
                removed = true; 
                }
        }
        return removed;
        }

        @Override
        public boolean remove(int from, int to) {
                if(from < 0 || to > getLenght()){
                        return false;
                }
                for (int ii = from; ii <= to; ii++) {
                        elementos[ii] = null;
                }
                defragment();
                return true;
        }

        @Override
        public boolean dimension(int newDimension) {
                if(newDimension < 0){
                        return false;
                }else if (newDimension == getLenght()) {
                        return true;
                }else if(newDimension > getLenght()){
                        @SuppressWarnings("unchecked")
                        E[] nuevoArray = (E[]) new Object[newDimension];
                        for (int ii = 0; ii < getLenght(); ii++) {
                                nuevoArray[ii] = elementos[ii];
                        }
                        elementos = nuevoArray;
                        setLenght(newDimension);
                }
                return true;
        }

        @Override
        public boolean set(int index, E element) {
                if(index < 0 || index > getLenght()){
                        return false;
                }
                elementos[index] = element;
                return true;
        }

        private int getLenght() {
                return lenght;
        }

        private void setLenght(int lenght) {
                this.lenght = lenght;
        }



}
