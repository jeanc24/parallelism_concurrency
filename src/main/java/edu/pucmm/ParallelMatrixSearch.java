package edu.pucmm;

/**
 * @author me@fredpena.dev
 * @created 06/06/2024  - 08:46
 */
public class ParallelMatrixSearch {

    private static final int MATRIX_SIZE = 1000;
    private static final int THREAD_COUNT = 4;
    private static final int[][] matrix = new int[MATRIX_SIZE][MATRIX_SIZE];
    private static final int TARGET = 256; // Número a buscar

    public static void main(String[] args) {
        // Inicializar la matriz con valores aleatorios
        //...

        // Medir el tiempo de ejecución de la búsqueda secuencial
        //...
        // System.out.println("Resultado búsqueda secuencial: " + ...);
        // System.out.println("Tiempo búsqueda secuencial: " + (endTime - startTime) + "ms");

        // Medir el tiempo de ejecución de la búsqueda paralela
        //...
        // System.out.println("Resultado búsqueda paralela: " + ...);
        // System.out.println("Tiempo búsqueda paralela: " + (endTime - startTime) + "ms");
    }

    private static void sequentialSearch() {
        //...
    }

    private static void parallelSearch() {
        //...
    }
}
