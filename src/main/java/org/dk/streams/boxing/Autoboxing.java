package org.dk.streams.boxing;

import java.util.ArrayList;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Autoboxing {
    public static void main(String[] args) {
        int i = 10;
        // boxing
        Integer integer = Integer.valueOf(i);
        // Autoboxing
        Integer integer1 = i;

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1); // Autoboxing internally the compiler will convert this to Integer.valueOf(i)

        // Auto unboxing
        int i1 = list.get(0); // returns Integer but we are assigning it to primitive int
        // Unboxing
        Integer integer2 = new Integer(10);
        int i2 = integer2.intValue();
        int i3 = integer2; // auto Unboxing

        // performance issue since java handling the boxing
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4);
        integerStream
                .forEach(System.out::println); // converts primitive to Integer Objects(Stream of Integers)
        // because of this we have an interface which handles Ints is IntStream

        IntStream.of(1,2,3,4)
                .forEach(System.out::println); // returns primitive less memory 4 bites for each int

        // COnvert Stream of Integers to IntStream
        Stream<Integer> integerStream1 = Stream.of(1, 2, 3, 4);
        IntStream intStream = integerStream1.mapToInt(new ToIntFunction<Integer>() {
            @Override
            public int applyAsInt(Integer value) {
                return value; // Auto unboxing we can also ise Integer.valueof(value) for manual unboxing
            }
        });
        // we can also write it like this:
//        integerStream1.mapToInt(value->value.intValue()); // Integer::intValue

    }
}
