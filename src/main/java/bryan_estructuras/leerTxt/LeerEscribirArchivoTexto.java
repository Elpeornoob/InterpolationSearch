package bryan_estructuras.leerTxt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LeerEscribirArchivoTexto {
    public static void main(String[] args) {
        // Lectura de archivo
        String nombreArchivoEntrada = "archivo.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivoEntrada))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Separar por comas
                String[] datos = linea.split(",");
                for (String dato : datos) {
                    System.out.print(dato.trim() + " ");
                }
                System.out.println(); // Salto de línea entre cada línea del archivo
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        // Escritura en archivo
        String nombreArchivoSalida = "salida.txt"; // Extensión .csv para archivos CSV
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivoSalida))) {
            bw.write("Nombre,Edad,Ciudad\n"); // Encabezado del archivo CSV
            bw.write("Juan,25,Lima\n");
            bw.write("María,30,Arequipa\n");
            bw.write("Carlos,22,Trujillo\n");
            System.out.println("Archivo CSV creado exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
