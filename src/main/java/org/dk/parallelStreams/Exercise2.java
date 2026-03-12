package org.dk.parallelStreams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Exercise2 {
    public static void main(String[] args) {
        List<Integer> listOfNumbers = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        Stream<Integer> parallel = listOfNumbers.stream().parallel();
        System.out.println(parallel.isParallel());
        parallel.forEach(System.out::println);
        // the stream can be either parallel or sequential
        // forEachOrdered() can be used to print in order. Still the stream is parallel even though we are making it in order. the rest of the operations will be parallel like map and filter
        // if we want to sort the elements:
        listOfNumbers.parallelStream()
                .sorted() // subsequent 'forEach()' operation doesn't depend on the sort order for parallel streams
                .forEach(System.out::println); // we should use forEachOrdered() to sort a parallel stream

        ArrayList<Integer> integerList = new ArrayList<>(); // Collections.synchronizedList(integerList); use this for synchronized list


        // this may not result in consistent result because the arraylist is not thread safe
        for(int i = 1; i<=100; i++){
            integerList.clear();
            Stream.iterate(1, no->no<=100, no->no+1)
                    .parallel()
                    .forEach(no -> integerList.add(no));
            System.out.println(integerList);
            System.out.println(integerList.size());
        }
    }
}
