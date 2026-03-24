package org.dk.streams.flatMap;


import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapExample {

    public static void main(String[] args) {
        // map()
        List<Integer> integerList = List.of(1,2,3,4,5,6);
        Stream<Integer> streamOfIntegers = integerList.stream();
        Stream<String> stringStream = streamOfIntegers.map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return "Value is: " + integer;
            }
        });
//        streamOfIntegers.map(integer -> "Value is: "+ integer); can be written like this
        List<String> listOfStrings = stringStream.collect(Collectors.toList());
        System.out.println(listOfStrings);

        // FlatMap()
        List<List<Integer>> listOfIntegerList = List.of(integerList, integerList);
        Stream<List<Integer>> streamOfListOfIntegers = listOfIntegerList.stream();
        Stream<Integer> flatStreamOfIntegerList = streamOfListOfIntegers.flatMap(new Function<List<Integer>, Stream<Integer>>() {
            @Override
            public Stream<Integer> apply(List<Integer> integers) {
                return integers.stream();
            }
        });

//        Stream<Integer> integerStream = streamOfListOfIntegers.flatMap(integers -> integers.stream()); // can be written like this
        List<Integer> flatStreamOfIntegers = flatStreamOfIntegerList.collect(Collectors.toList());
        System.out.println(flatStreamOfIntegers);

        // Exercise:
        // input: ["hello world", "Dileep Kumar", "Java Programming"]
        // output: ["hello", "world", "Dileep", "Kumar", "Java", "Programming"]

        List<String> listOfString = List.of("Hello World", "Dileep Kumar", "Java Programming");
        List<String> listOfWords = listOfString.stream()
                .map(s -> List.of(s.split(" ")))
                .flatMap(stream -> stream.stream())
                .collect(Collectors.toList());
        System.out.println(listOfWords);

    }

}
