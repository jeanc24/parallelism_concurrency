package edu.pucmm;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author me@fredpena.dev
 * @created 02/06/2025  - 20:46
 */
public class ProducerConsumer {
    private static final int QUEUE_CAPACITY = 10;
    private static final int PRODUCER_COUNT = 2;
    private static final int CONSUMER_COUNT = 2;
    private static final int PRODUCE_COUNT = 100;
    private static final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(PRODUCER_COUNT + CONSUMER_COUNT);


        ConcurrentHashMap<String, Integer> consumerStats = new ConcurrentHashMap<>();

        long start = System.nanoTime();

        for (int i = 0; i < PRODUCER_COUNT; i++) {
            executor.execute(new Producer(queue));
        }




    }
/*
Descripción
Implementar el patrón clásico de Productor-Consumidor usando una BlockingQueue en Java.

Requerimientos
Crear una cola bloqueante con capacidad limitada (ArrayBlockingQueue).
Implementar n productores que generen números aleatorios y los coloquen en la cola.
Implementar n consumidores que extraigan y procesen los números de la cola (ej. sumarlos).
Ejecutar el sistema con múltiples productores y consumidores simultáneos.
Medir el tiempo total de procesamiento.
Mostrar cuántos elementos consumió cada hilo consumidor.

Recomendaciones
Utiliza ExecutorService o lanza hilos manualmente.
Documenta tu código con comentarios claros.
Si se desea medir carga por consumidor, puede usarse un ConcurrentHashMap<Thread, Integer>.

 */
    static class Producer implements Runnable {
    private final BlockingQueue<Integer> queue;

    // Generar PRODUCE_COUNT números aleatorios y colocarlos en la cola
    // Sugerencia: usar Thread.sleep(10) para simular tiempo de producción
     public Producer( BlockingQueue<Integer> queue){
         this.queue = queue;

     }
        @Override
        public void run() {
            Random rand = new Random();
            for (int i = 0; i < PRODUCE_COUNT; i++) {
                int value = rand.nextInt(1000);
                try {
                    queue.put(value); // bloquea si la cola está llena
                    Thread.sleep(10); // simula tiempo de producción
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }


    static class Consumer implements Runnable {
        @Override
        public void run() {
            // Extraer elementos de la cola y procesarlos (ej: sumarlos)
            // Sugerencia: llevar la suma total por hilo y reportar al final
        }
    }
}