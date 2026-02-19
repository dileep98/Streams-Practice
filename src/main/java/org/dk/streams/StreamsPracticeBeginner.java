package org.dk.streams;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class StreamsPracticeBeginner {
    public static void main(String[] args){
        List<Integer> list = List.of(1,2,3,4,5);
        List<Integer> duplicateIntList = List.of(1,1,2,3,3,4,5);
        StreamFunctions.sumOfAllIntegers(list);
        StreamFunctions.productOfAllIntegers(list);
        StreamFunctions.averageOfAllIntegers(list);
        StreamFunctions.maxAndMinOfAllIntegers(list);
        StreamFunctions.numberOfElements(list);
        StreamFunctions.containsElement(list, 5);
        StreamFunctions.filterEvenAndOddFromList(list);
        StreamFunctions.squaresOfList(list);
        StreamFunctions.findFirstAndLast(list);
        StreamFunctions.conditionCheck(list, (integer -> integer>0));
        StreamFunctions.removeDuplicates(duplicateIntList);

        List<String> stringlist = List.of("Apple", "Ball", "Cat", "Dog");
        StreamFunctions.toUpperStringList(stringlist);
        StreamFunctions.findFirstAndLast(stringlist);
        StreamFunctions.conditionCheck(stringlist, (s -> s.startsWith("A")));
        List<String> duplicateStringlist = List.of("Apple", "Ball", "Cat", "Dog", "Apple", "Ball");
        StreamFunctions.removeDuplicates(duplicateStringlist);
        StreamFunctions.sortList(duplicateStringlist, Comparator.reverseOrder());
        StreamFunctions.sortList(duplicateStringlist, Comparator.<String>naturalOrder().reversed());
        StreamFunctions.sortStringsByLength(duplicateStringlist);

    }







}
