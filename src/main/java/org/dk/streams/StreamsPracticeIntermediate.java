package org.dk.streams;

import java.util.List;

public class StreamsPracticeIntermediate implements StreamsPractice {

    public void runMethods() {
        StreamFunctionsIntermediate.sumOfDigits(12345);
        StreamFunctionsIntermediate.factorial(4);
        List<Integer> numbersList = List.of(5,8,1,2,4,6,7,9);
        StreamFunctionsIntermediate.secondLargestInList(numbersList);
        StreamFunctionsIntermediate.secondSmallestInList(numbersList);
        List<String> words = List.of("apple", "banana", "kiwi", "apple", "banana", "");
        StreamFunctionsIntermediate.largestStringInList(words);
        StreamFunctionsIntermediate.smallestStringInList(words);
        StreamFunctionsIntermediate.groupStringsByLength(words);
        StreamFunctionsIntermediate.startsWithAlphabet(words);
        StreamFunctionsIntermediate.partitionByEvenOdd(numbersList);
        List<Integer> numbersList2 = List.of(10,11,4,33,-1,-5);
        StreamFunctionsIntermediate.mergeTwoLists(numbersList, numbersList2);
        StreamFunctionsIntermediate.intersectionOfTwoLists(numbersList, numbersList2);
        StreamFunctionsIntermediate.unionOfLists(numbersList, numbersList2);
        StreamFunctionsIntermediate.differenceInLists(numbersList, numbersList2);
        StreamFunctionsIntermediate.eachOccurrencesInList(numbersList);
        StreamFunctionsIntermediate.eachOccurrencesInList(words);
        StreamFunctionsIntermediate.eachOccurrencesOfCharacter("aabstuvbababcchsstg");
        String lineText = "The quick brown fox jumps over the lazy dog, and then the lazy brown fox jumps back";
        StreamFunctionsIntermediate.eachOccurrencesWordsInString(lineText);
        StreamFunctionsIntermediate.occurrencesOfEachVowel(lineText);
        String textWithNumbers = "hjdfgi67 w3tr 29h3ibu shdfo87w3 gyigfo86ew gfuksd jgf7ow 83w4gfy uv387pwf";
        StreamFunctionsIntermediate.occurrencesOfDigits(textWithNumbers);
        StreamFunctionsIntermediate.reverseList(words);
        StreamFunctionsIntermediate.reverseString(lineText);

        List<Person> people = List.of(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 25)
        );
        StreamFunctionsIntermediate.groupByAttribute(people);


    }
}

class Person {
    String name;
    int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


}
