package org.dk.parallelStreams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class WhenToUse {
    public static void main(String[] args) {
        long sum = LongStream
                .rangeClosed(0, 10)
                .sum();
        System.out.println(sum);

        // Faster execution
        long sum1 = LongStream
                .rangeClosed(0, 10)
                .parallel().sum();
        System.out.println(sum1);
        // Note: we may see similar time of execution for smaller inputs.
        // But, when it comes to larger data, parallel stream is much faster

        long startTime = System.currentTimeMillis();
        long reduceSeqSum = LongStream
                .rangeClosed(1, 1000000000)
                .reduce(0, Long::sum);
        System.out.println(reduceSeqSum);
        long endTime = System.currentTimeMillis();
        System.out.println("Time for sequential sum: "+ (endTime-startTime));

        startTime = System.currentTimeMillis();
        long reduceParSum = LongStream
                .rangeClosed(1, 1000000000)
                .reduce(0, Long::sum);
        System.out.println(reduceParSum);
        endTime = System.currentTimeMillis();
        System.out.println("Time for Parallel sum: "+ (endTime-startTime));


        ArrayList<Integer> listOfIntegers = new ArrayList<>();
//        List<Integer> list = Stream.iterate(0, no -> no + 1)
//                .parallel()
//                .limit(20)
//                .collect(Collectors.toList());
//        ArrayList<Integer> list = Stream.iterate(0, n -> n + 1)
//                .parallel()
//                .limit(20)
//                .collect(Collectors.toCollection(() -> new ArrayList<Integer>())); // another way of writing Collect(Collectors.toList)

//        ArrayList<Integer> list = Stream.iterate(0, n -> n + 1)
//                .parallel()
//                .limit(20)
//                .collect(Collectors.toCollection(); // Ignore this, I will implement internal working of Collectors.toList() in the future
//        System.out.println(list);
//        System.out.println(list.size());


    }
}
