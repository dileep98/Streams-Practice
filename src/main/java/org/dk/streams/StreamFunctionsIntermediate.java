package org.dk.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamFunctionsIntermediate {

    public static void sumOfDigits(int  number){
        int sum = String.valueOf(number).chars()
                .map(Character::getNumericValue)
                .sum();
        System.out.println("Sum of digits of "+number+" : "+ sum);
    }

    public static void factorial(int number){
        int reduce = IntStream.rangeClosed(1, number)
                .reduce(1, (a, b) -> a * b);
        System.out.println("Factorial of digits of "+number+" : "+ reduce);
    }

    public static void secondLargestInList(List<Integer> list){
        int secondLargest = list.stream()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst().orElse(0);
        System.out.println("Second largest from the list "+ list+" is: "+ secondLargest);
    }

    public static void secondSmallestInList(List<Integer> list){
        int secondSmallest = list.stream()
                .sorted()
                .skip(1)
                .findFirst().orElseGet(() -> {return 0;});
        System.out.println("Second largest from the list "+ list+" is: "+ secondSmallest);
    }

    public static void largestStringInList(List<String> list){
        String largestString = list.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse("");
//        list.stream()
//                .sorted(Comparator.comparingInt(String::length).reversed()
//                    .thenComparing(Comparator.naturalOrder()))
//                .findFirst().orElse("");
//        list.stream()
//                .max(Comparator.comparingInt(String::length)
//                        .thenComparing(Comparator.naturalOrder())) // Tie-breaker: A-Z
//                .orElse("");
        System.out.println("Largest from the list "+ list+" is: "+ largestString);
    }

    public static void smallestStringInList(List<String> list){
        String smallestString = list.stream()
                .min(Comparator.comparingInt(String::length))
                .orElse("");
//        list.stream().sorted(Comparator.comparingInt(String::length).reversed()
//                .thenComparing(Comparator.naturalOrder()))
//                        .findFirst().orElse("");
//        list.stream().min(Comparator.comparingInt(String::length)
//                .thenComparing(Comparator.naturalOrder()))
//                        .orElse("");


        System.out.println("Smallest String from the list "+ list+" : "+ smallestString);
    }

    public static void groupStringsByLength(List<String> list){
        Map<Integer, List<String>> lengthMap = list.stream()
                .collect(Collectors.groupingBy(String::length)); // TODO Important to remember
        System.out.println("With Delimiter "+list.stream().collect(Collectors.joining("|")));
        System.out.println("Strings grouped by Length: "+ lengthMap);
    }

    public static void startsWithAlphabet(List<String> list){
        Map<Character, List<String>> mapByAlphabet = list.stream()
                .filter(Objects::nonNull)
                .filter(i-> !i.isEmpty())
                .collect(Collectors.groupingBy((i) -> i.charAt(0)));
        System.out.println("Map By alphabet: " + mapByAlphabet);
    }

    public static void groupByAttribute(List<Person> list){
//        Map<Integer, List<Person>> groupByAge = list.stream().collect(Collectors.groupingBy(i -> i.age));
//        Map<Integer, List<Person>> groupByAge = list.stream().collect(Collectors.groupingBy(Person::getAge));
        // TODO Important to refer
        Map<Integer, List<String>> groupByAge = list.stream()
                .collect(Collectors.groupingBy(
                        Person::getAge,
                        Collectors.mapping(
                                Person::getName,
                                Collectors.toList()
                        )
                ));
        System.out.println("Group By Age: "+ groupByAge);
    }

    public static void partitionByEvenOdd(List<Integer> list){
        Map<Boolean, List<Integer>> groupByEvenOdd = list.stream()
                .collect(Collectors.groupingBy(i->i%2 == 0));
        System.out.println("Partitioned by Even Odd: "+ groupByEvenOdd);
    }

    public static void mergeTwoLists(List<Integer> list1, List<Integer> list2){
        List<Integer> mergedList = Stream.concat(list1.stream(), list2.stream())
                .collect(Collectors.toList()); // TODO check differences between this and below line
//        List<Integer> mergedList = Stream.concat(list1.stream(), list2.stream()).toList(); // usually ArrayList
        System.out.println("Merged List: "+ mergedList);
    }

    public static void intersectionOfTwoLists(List<Integer> list1, List<Integer> list2){
        List<Integer> intersectList = list1.stream()
                .filter(list2::contains)
                .collect(Collectors.toList());

        System.out.println("Intersection between two lists: " + intersectList);
    }

    public static void unionOfLists(List<Integer> list1, List<Integer> list2){
        List<Integer> unionList = Stream.concat(list1.stream(), list2.stream())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Union of List: "+ unionList);
    }

    public static void differenceInLists(List<Integer> list1, List<Integer> list2){
        // list1 - list2
        List<Integer> differenceList1 = list1.stream()
                .filter(n -> !list2.contains(n))
                .collect(Collectors.toList());

        // list2 - list1
        List<Integer> differenceList2 = list2.stream()
                .filter(n-> !list1.contains(n))
                .collect(Collectors.toList());

        System.out.println("Difference in Lists (List1 (minus) List2): " + differenceList1);
        System.out.println("Difference in Lists (List2 (minus) List1): " + differenceList2);
    }

    public static <T> void eachOccurrencesInList(List<T> list){
        // TODO Important
        System.out.println("Each occurrences count: "+list.stream()
                .collect(Collectors.groupingBy(
                        s -> s,
                        Collectors.counting()
                ))
        );
    }

    public static void eachOccurrencesOfCharacter(String word){
        // TODO Important to convert chars() intStream to char obj
        Map<Character, Long> charOccurrences = word.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        c -> c,
                        Collectors.counting()
                ));
        System.out.println("occurrences of each character in String \""+word+"\" : " + charOccurrences);
    }

    public static void eachOccurrencesWordsInString(String text){
        Map<String, Long> occurancesOfWordsInStringMap = Arrays.stream(text.split(" "))
                .collect(Collectors.groupingBy(
                        i -> i,
                        Collectors.counting()
                ));

        System.out.println("Occurrences of each word in the given string \""+text+"\" :"+occurancesOfWordsInStringMap);
    }

    public static void occurrencesOfEachVowel(String text){
        // TODO convert char stream to char object and convert char to string while comparing
        Map<Character, Long> occurVowelsMap = text.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> "aeiou".contains(String.valueOf(c)))
                .collect(Collectors.groupingBy(
                        c -> c,
                        Collectors.counting()
                ));

        Map<String, Long> collect = text.chars()
                .mapToObj(Character::toString)
                .filter("aeiou"::contains)
                .collect(Collectors.groupingBy(
                        i -> i,
                        Collectors.counting()
                ));
        System.out.println("Occurrences of vowels in the given string \""+text+"\" :" + occurVowelsMap+" - "+ collect);
    }

    public static void occurrencesOfDigits(String text){
        Map<Character, Long> countOfNumbers = text.chars()
                .mapToObj(c -> (char) c)
                .filter(Character::isDigit)
                .collect(Collectors.groupingBy(
                        c -> c,
                        Collectors.counting()
                ));
        System.out.println("Occurrences of numbers in the given text \""+text+"\" :"+ countOfNumbers);
    }

    public static <T> void reverseList(List<T> list){

        List<T> reversedList = list.stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), l -> {
                    Collections.reverse(l);
                    return l;
                }));
        System.out.println("Reversed List: "+reversedList);
    }

    public static void reverseString(String text){
        String reversedString = text.chars()
                .mapToObj(Character::toString)
                .reduce("", (a, b) -> b + a);
        System.out.println("Reversed String: "+ reversedString);
    }



}
