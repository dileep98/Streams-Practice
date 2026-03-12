package org.dk.streams.parallelStreams;

import java.util.stream.Stream;

public class Exercise1 {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());

        long startTime, endTime;
        startTime = System.currentTimeMillis();
        Stream.of(1,2,3,4,5)
                        .forEach(i -> {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println(i + " working with Thread: " + Thread.currentThread().getName());
                        });
        endTime = System.currentTimeMillis();
        System.out.println("Time taken by Stream is: "+ (endTime-startTime));

        // using parallelStream()
        startTime = System.currentTimeMillis();
        Stream.of(1,2,3,4,5)
                .parallel()
                .forEach(i -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(i + " working with Thread: " + Thread.currentThread().getName());
                });
        endTime = System.currentTimeMillis();
        System.out.println("Time taken by parallelStream is: "+ (endTime-startTime));

        // Important note:
        // If we use filter and map like these methods, they will also be assigned to different threads may not the same thread as the element has been printing with


    }
}
