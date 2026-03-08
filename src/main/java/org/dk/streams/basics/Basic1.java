package org.dk.streams.basics;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Basic1 {
    public static String mapFunction(Integer integer){

        return switch (integer) {
            case 1 -> "One";
            case 2 -> "Two";
            case 3 -> "Three";
            case 4 -> "Four";
            case 5 -> "Five";
            case 6 -> "Six";
            case 7 -> "Seven";
            case 8 -> "Eight";
            case 9 -> "Nine";
            default -> "";
        };


    }
    public static void main(String[] args) {
        printStreamOfStringsInfinite(); // comment this if you want to run remaining methods

        printStreamOfNumbers();

        filterExample();

        mapExample();


    }

    // Streams are lazy
    // Note: Filter and map are intermediate operations returns stream. Other example is peek()
    // Note: Collect, forEach, count similar terminal operations doesn't return anything and the stream will be processed. you cannot process the stream again after using these methods

    private static void mapExample() {
        Integer[] integerArray = {1,2,3,4,5,6,7,8,9};
        Stream<Integer> integerStream = Arrays.stream(integerArray);

        Stream<Integer> filteredIntegerStream = integerStream.filter(i -> i > 5);
        Stream<String> mappedToString = filteredIntegerStream.map(number -> Basic1.mapFunction(number));

        // Note: collect and foreach are a terminal operation
        mappedToString.collect(Collectors.toList());
//        mappedToString.forEach(System.out::println); // this will print nothing because we have already collected the stream using collect. As a result, the stream is closed and cannot be streamed again
    }




    // Use of Filter
    private static void filterExample() {
        Integer[] integerArray = {1,2,3,4,5,6,7,8,9};

        Predicate<Integer> integerPredicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer > 5; // returns a boolean
            }
        };

        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        };
        // converting array of Integer Objects to Stream of Integer Objects
        Stream<Integer> integerStream = Arrays.stream(integerArray);
        integerStream
                .filter(integerPredicate)
                .forEach(integerConsumer);
    }


    // use of Stream.of()
    private static void printStreamOfNumbers() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        };

        integerStream.forEach(integerConsumer);
    }

    // use of Stream.generate();
    private static void printStreamOfStringsInfinite() {
        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "Hello World!!";
            }
        };
        Stream<String> stringStream = Stream.generate(supplier);
//        we can also use iterate method to generate infinite stream
//        Stream.iterate(0, new UnaryOperator<Integer>() {
//            @Override
//            public Integer apply(Integer integer) {
//                return integer+1;
//            }
//        })
//        .filter(i->i<20) // prints 20 elements but still the program continues executing
//        .foreach(System.out::print);/// infinite print
//        In this case we use limit() which is a short circuit i.e., limit() will cut off execution
//

        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        stringStream.forEach(consumer);
    }
}
