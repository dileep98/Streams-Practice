package org.dk.streams.basics;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Basic2 {

    public static void main(String[] args) {
        sortIntegers();

        takeWhileExample();

//        Consumer<List<Integer>> consumerLogic = null;

        biFunctionExample();

        boolean b = biPredicateExample();


    }

    private static boolean biPredicateExample() {
        BiPredicate<Integer, Integer> biPredicate = Object::equals;
        return biPredicate.test(1,1);
    }

    private static void biFunctionExample() {
        BiFunction<Integer, Integer, Integer> biFunctionSum = Integer::sum;
        Integer applied = biFunctionSum.apply(1, 2);
    }

    private static void takeWhileExample() {
        Stream.of(1,2,3,4,5,6,7,8,9)
                .takeWhile(i->i<7) // filter will check all elements whereas takeWhile terminates the operation on failure
                .map(i->i*i)
                .forEach(i->System.out.print(" "+i));
    }

    private static void sortIntegers() {
        Stream<Integer> integerStream = Stream.of(1, 3, 5, 2, 4, 9, 6, 3, 3, 7);

        // sorted is a stateful, intermediate operation
        // Stateful means the sorted method will store all teh elements in the stream unlike filter and map which takes one element at a time. It returns a sorted stream
        integerStream.sorted().forEach(System.out::print);
    }
}
