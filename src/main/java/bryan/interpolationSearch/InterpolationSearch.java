package bryan.interpolationSearch;

import bryan_estructuras.linkedlist.doubly.LinkedList;
import bryan_estructuras.util.iterator.Iterator;

import java.util.Comparator;

public class InterpolationSearch<E> {

    public static <E> int interpolacionSearch(LinkedList<E> lista, E element, Comparator<E> comparator){
        int low = 0;
        int high = lista.size() - 1;

        while (low <= high && comparator.compare(element, lista.peek()) >= 0 && comparator.compare(element, lista.peekLast()) <= 0) {
            // Si el elemento está en el límite inferior
            if (low == high) {
                if (lista.peek().equals(element)) {
                    return low;
                }
                return -1;
            }

            // Estimación de la posición utilizando la fórmula de interpolación
            int pos = low + ((comparator.compare(element, lista.peek()) * (high - low)) /
                    (comparator.compare(lista.peekLast(), lista.peek())));

            // Comprobar límites de posición
            if (pos < low || pos > high) {
                return -1; // Fuera de los límites, el elemento no está presente
            }

            // Usar el iterador para acceder a la posición
            Iterator<E> it = lista.iterator();
            E currentElement = null;
            for (int i = 0; i <= pos; i++) {
                if (it.hasNext()) {
                    currentElement = it.next();
                } else {
                    break; // Salir si no hay más elementos
                }
            }

            // Si se encuentra el valor
            if (currentElement != null && currentElement.equals(element)) {
                return pos;
            }

            // Si el valor es mayor, se ajusta el límite inferior
            if (comparator.compare(currentElement, element) < 0) {
                low = pos + 1;
            }
            // Si el valor es menor, se ajusta el límite superior
            else {
                high = pos - 1;
            }
        }

        // Si no se encuentra el valor, retornar -1
        return -1;
    }
}
