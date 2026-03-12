package org.dk.streams.boxing;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Boxing {
    public static void main(String[] args) {
        IntStream intStream = IntStream.range(0, 10); // 0 to 9
        IntStream intStream1 = IntStream.rangeClosed(0, 10);// 0 to 10

        // converting int straem to straem of Integers
        // convert each number to Hashcode. Hint: we need objects to generate hashcode
//        intStream1.map(no -> {
//            return no.hashCode();
//        }).forEach(System.out::println); // returns numbers instead of hashcode
//        Solution:
        intStream1
                .boxed()
                .map(Object::hashCode)
                .forEach(System.out::println);

        Integer[] integers = new Integer[]{1,2,3,4,5};
        Stream<Integer> stream = Arrays.stream(integers); // returns stream of Integers
        int[] ints = new int[]{1,2,3,4,5};
        IntStream stream1 = Arrays.stream(ints); // returns intStraem

    }
}
