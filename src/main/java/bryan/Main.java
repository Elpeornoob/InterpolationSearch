package bryan;

import bryan_estructuras.linkedlist.doubly.LinkedList;

import java.util.Comparator;

import static bryan.interpolationSearch.InterpolationSearch.interpolacionSearch;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        // Agregar elementos a la lista
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        list.add(60);
        list.add(70);
        list.add(80);
        list.add(90);
        list.add(100);

        int x = 70;

        // Usar un comparador para enteros
        Comparator<Integer> comparator = Integer::compareTo;
        int index = interpolacionSearch(list, x, comparator);

        if (index != -1) {
            System.out.println("El elemento se encuentra en el índice: " + index);
        } else {
            System.out.println("El elemento no está presente en la lista.");
        }
    }
}