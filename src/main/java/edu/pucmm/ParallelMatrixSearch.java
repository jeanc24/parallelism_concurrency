package edu.pucmm;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author me@fredpena.dev
 * @created 02/06/2025  - 20:46
 */
public class ParallelMatrixSearch {

    private static final int MATRIX_SIZE = 1000;
    private static final int THREAD_COUNT = 4;
    private static final int[][] matrix = new int[MATRIX_SIZE][MATRIX_SIZE];
    private static final int TARGET = 256; // Número a buscar

    public static void main(String[] args) {
        // Inicializar la matriz con valores aleatorios
        fillMatrixRandom();
        //Medir el tiempo de ejecución de la búsqueda secuencial
        long startTime = System.nanoTime();
        sequentialSearch();
        long endTime = System.nanoTime();
        System.out.println("Tiempo búsqueda secuencial: " + ((endTime - startTime) / 1_000_000) + "ms");

        // Medir el tiempo de ejecución de la búsqueda paralela
        startTime = System.nanoTime();
        parallelSearch();
        endTime = System.nanoTime();
        System.out.println("Tiempo búsqueda paralela: " + ((endTime - startTime) / 1_000_000) + "ms");
    }
    private static void printMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void sequentialSearch() {
        // Implementar búsqueda secuencial
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                if (matrix[i][j] == TARGET) {
                    return;
                }
            }
        }
    }

    private static void parallelSearch() {
        // Implementar búsqueda paralela
        AtomicBoolean found = new AtomicBoolean(false);
        Thread[] hilos = new Thread[MATRIX_SIZE];

        for (int i = 0; i < MATRIX_SIZE; i++) {
            final int row = i;
            hilos[i] = new Thread(() -> {
                for (int j = 0; j < MATRIX_SIZE && !found.get(); j++) {
                    if (matrix[row][j] == TARGET) {
                        found.set(true);
                        break;
                    }
                }
            });
            hilos[i].start();
        }

        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private static void fillMatrixRandom() {
        Random rand = new Random();
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                matrix[i][j] = rand.nextInt(1000); // Rango arbitrario
            }
        }
    }
}