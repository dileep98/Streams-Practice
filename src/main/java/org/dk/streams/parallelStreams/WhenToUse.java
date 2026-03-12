package org.dk.streams.parallelStreams;

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
//                .collect(Collectors.toCollection(() -> new ArrayList<Integer>())); // another way of writing Collect(Collectors.toList))

        ArrayList<Integer> list = Stream.iterate(0, n -> n + 1)
                .parallel()
                .limit(20)
                .collect(
                        // 1. Supplier: How to create a new container
                        () -> new ArrayList<Integer>(), // can be written as ArrayList::new
                        // 2. Accumulator(BiConsumer): How to add a single element to a container
                        (leftList, element) -> leftList.add(element), // can be written as ArrayList::add
                        // 3. Combiner(BiConsumer): How to merge two containers (Crucial for .parallel())
                        (list1, list2) -> { // can be written as ArrayList::addAll
                            list1.addAll(list2);
                        });
        System.out.println(list);
        System.out.println(list.size());


    }
}
