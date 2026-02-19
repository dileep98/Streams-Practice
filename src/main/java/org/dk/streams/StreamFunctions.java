package org.dk.streams;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamFunctions {
    public static void sumOfAllIntegers(List<Integer> list){
        int sum = list.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Sum of all Integers: " + sum);
    }

    public static void productOfAllIntegers(List<Integer> list){
        int sum = list.stream().reduce(1, (a,b) -> a*b);
        System.out.println("product of all Integers: " + sum);
    }

    public static void averageOfAllIntegers(List<Integer> list){
        double sum = list.stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println("average of all Integers: " + sum);
    }

    public static void maxAndMinOfAllIntegers(List<Integer> list){
        int min = list.stream().min(Integer::compare).orElse(0);
        System.out.println("Minimum of all Integers: " + min);
        int max = list.stream().max(Integer::compare).orElse(0);
        System.out.println("Minimum of all Integers: " + max);
    }

    public static void numberOfElements(List<Integer> list){
        long count = list.stream().count();
        System.out.println("Count of all Integers: " + count);
    }

    public static void containsElement(List<Integer> list, int element){
        boolean contains = list.stream().anyMatch(n -> n == element);
        System.out.println("contains "+element+" in all Integers: "+ contains);
    }

    public static void filterEvenAndOddFromList(List<Integer> list){
        System.out.println("Even number from list: "+
                list.stream()
                        .filter(i -> i%2 == 0)
                        .map(String::valueOf)
                        .collect(Collectors.joining(","))
        );

        List<Integer> evenNumbers = list.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Even Numbers: " + evenNumbers);
        List<Integer> evenNumbersList = list.stream()
                .filter(n -> n % 2 == 0)
                .toList();
        System.out.println("Even Numbers: " + evenNumbersList);

        System.out.println("Odd number from list: "+
                list.stream()
                        .filter(i -> i%2 == 1)
                        .map(String::valueOf)
                        .collect(Collectors.joining(","))
        );
    }

    public static void toUpperStringList(List<String> list){
        list.stream().map(String::toUpperCase).forEach(System.out::println);
        System.out.println("ToUpper: "+list.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList())
        );
    }

    public static void squaresOfList(List<Integer> list){
        System.out.println("Squares: "+list.stream()
                .map(i -> i*i)
                .collect(Collectors.toList())
        );
        System.out.println("Squares: "+list.stream()
                .map(i -> i*i)
                .toList()
        );
    }

    public static <T> void findFirstAndLast(List<T> list){
        System.out.println("First: "+ list.stream().findFirst().orElse(null));
        System.out.println("Last: "+ list.stream()
                .reduce((a,b) -> b)
                .orElse(null)
        );
    }

    public static <T> void conditionCheck(List<T> list, Predicate<T> predicate){
        System.out.println("No of Elements passed the condition: "+ list.stream().filter(predicate).count());
        System.out.println("All Elements passed the condition? "+ list.stream().allMatch(predicate));
        System.out.println("At least one Elements passed the condition? "+ list.stream().anyMatch(predicate));
    }

    public static <T> void removeDuplicates(List<T> list){
        System.out.println("Before removing duplicates: " + list);
        System.out.println("After Removing Duplicates: "+ list.stream().distinct().toList());
    }

    public static <T> void sortList(List<T> list, Comparator<? super T> comparator){
        System.out.println("Sorted List: "+ list.stream().sorted(comparator).toList());
    }

    public static void sortStringsByLength(List<String> list){
        System.out.println("Sorted List based on length of the Strings: "+ list.stream()
                .sorted(Comparator.comparing(String::length, Comparator.naturalOrder()))
                .toList());
    }




}
