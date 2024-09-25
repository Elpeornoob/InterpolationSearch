package bryan.interpolationSearch.executiontime;

import javax.swing.*;

import bryan.interpolationSearch.InterpolationSearch;
import bryan.interpolationSearch.component.*;
import bryan.interpolationSearch.testclass.Avion;
import java.util.Comparator;
import bryan_estructuras.linkedlist.doubly.LinkedList;
import bryan_estructuras.util.iterator.Iterator;


public class ExecutionTime<E> {

    public static void complejidadInterpolationSearch() {

        MostrarTablas ventana = new MostrarTablas();
        ventana.setTitulo("Tiempos de Ejecución del Algoritmo MergeSort");

        ventana.getTablaNoPrimitiva().eliminarDatos();
        ventana.getTablaPrimitiva().eliminarDatos();

        int numeroPruebas = 1000;

        realizarPruebas(numeroPruebas,ventana.getTablaPrimitiva(), ventana.getTablaNoPrimitiva(), 10, 20);
        realizarPruebas(numeroPruebas,ventana.getTablaPrimitiva(), ventana.getTablaNoPrimitiva(), 100, 1000);
        realizarPruebas(numeroPruebas,ventana.getTablaPrimitiva(), ventana.getTablaNoPrimitiva(), 1000, 5000);

        // Mostrar la ventana con los resultados
        SwingUtilities.invokeLater(() -> ventana.setVisible(true));
    }
    private static void realizarPruebas(int numeroPruebas,Table tablaNumerica, Table tablaAvion, int minSize, int maxSize) {
        for (int i = 0; i < numeroPruebas; i++) {

            int size = (int) (Math.random() * (maxSize - minSize + 1)) + minSize;

            LinkedList<Integer> listaPrimitiva = new LinkedList<>();
            LinkedList<Avion> listaAvion = new LinkedList<>();


            llenarListaNumerica(listaPrimitiva, size);
            llenarListaAvion(listaAvion, size);

            long tiempoNumerico = -1;
                tiempoNumerico = medirTiempoDeEjecucionInterpolationSearch(listaPrimitiva, Comparator.naturalOrder());

            tablaNumerica.añadirFila(new String[]{String.valueOf(size),String.valueOf(tiempoNumerico)});

            long tiempoAvion = -1;
            tiempoAvion = medirTiempoDeEjecucionInterpolationSearch(listaAvion, Comparator.comparing(Avion::getYear));


            tablaAvion.añadirFila(new String[]{String.valueOf(size),String.valueOf(tiempoAvion)});
        }
    }

    private static void llenarListaNumerica(LinkedList<Integer> lista, int size) {
        for (int i = 0; i < size; i++) {
            lista.add((int) (Math.random() * 10000000));
        }
        lista.sort(Integer::intValue);
    }

    private static void llenarListaAvion(LinkedList<Avion> lista, int size) {
        for (int i = 0; i < size; i++) {
            lista.add(new Avion("Texto" + (int) (Math.random() * 10000), (int) (Math.random() * 100000)));
        }
        lista.sort(Avion::getYear);
    }
    private static <E> long medirTiempoDeEjecucionInterpolationSearch(LinkedList<E> lista, Comparator<E> comparator) {
        int i = (int) (Math.random() * lista.size());
        Iterator<E> it = lista.iterator();
        E element = null;
        for(int j = 0; j <= i; j++) {
            element = it.next();
        }
        long startTime = System.nanoTime();
        InterpolationSearch.interpolacionSearch(lista,element,comparator);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}
