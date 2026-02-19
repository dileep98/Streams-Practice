package org.dk.streams;

import org.dk.util.MyLogger;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamFunctionsAdvanced {
    public static <T> void mostFrequentElementInList(List<T> list){
        T t = list.stream()
                .collect(Collectors.groupingBy(
                        i -> i,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

//        String mostFrequent = list.stream() // nlogn
//                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
//                .entrySet().stream()
//                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
//                .map(Map.Entry::getKey)
//                .findFirst()
//                .orElse(null);

//        String mostFrequent = list.stream()  // n^2 because Collections.frequency scans the entire list for every single element in the stream. Never use this for large datasets.
//                .max(Comparator.comparingInt(w -> Collections.frequency(words, w)))
//                .orElse(null);

//        T t1 = list.stream()
//                .collect(Collectors.groupingBy(
//                        i -> i,
//                        Collectors.counting()
//                ))
//                .entrySet()
//                .stream()
//                .max(Map.Entry.comparingByValue())
//                .map(m -> m.getKey())
//                .orElse(null);

        MyLogger.log("Most Frequent Element in the List", list, t);
    }

    public static <T> void leastFrequentInList(List<T> list){
        T leastFrequentElement = list.stream()
                .collect(Collectors.groupingBy(
                        e -> e,
                        Collectors.counting()
                )).entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
        MyLogger.log("Least Frequent Element in the List", list, leastFrequentElement);
    }

    public static void firstNonRepeatingChar(String text){
        Character firstnonrepeatingCharacter = text.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        c -> c,
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet().stream()
//                .filter(i -> i.getValue() == 1)
                .map(i -> i.getKey())
//                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        MyLogger.log("First Non-repeating Character in text", text, firstnonrepeatingCharacter);
    }

    public static void firstRepeatedChar(String text){
        Character firstRepeatedCharacter = text.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        c -> c,
                        LinkedHashMap::new,
                        Collectors.counting()
                )).entrySet().stream()
                .filter(i -> i.getValue() > 1)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(null);
        MyLogger.log("First Repeating Character in text", text, firstRepeatedCharacter);
    }

    public static void checkPalindrome(String text){
        boolean isPalindrome = IntStream.range(0, text.length() / 2)
                .allMatch(n -> text.charAt(n) == text.charAt(text.length() - 1 - n));
        MyLogger.log("Given String", text, (isPalindrome?"is a Palindrome":"is not a Palindrome"));
//        System.out.println("Given String \""+text+"\" is " + (isPalindrome?"a Palindrome":"not a Palindrome"));
    }

    public static void anagrams(List<String> list, String target){
        // split each word into char array then sort them and compare
        int[] sortedTarget = target.chars().sorted().toArray();
        List<String> anagramsList = list.stream()
                .filter(element -> Arrays.equals(element.chars().sorted().toArray(), sortedTarget))
                .collect(Collectors.toList());
        MyLogger.log("Anagrams for target", target, anagramsList);
    }

    public static void fibonacciSequence(int n){
        // Important to dig deep
        List<Integer> fibonacciSequence = Stream.iterate(new int[]{0, 1}, fib -> new int[]{fib[1], fib[0] + fib[1]})
                .limit(n)
                .map(fib -> fib[0])
                .collect(Collectors.toList());
        MyLogger.log("Fibonacci sequence till", n, fibonacciSequence);
    }

    public static void listOfRandomNumbers(int n){
//        List<Double> randomNumbers = IntStream.range(0, n).mapToDouble(i -> Math.random()).boxed().collect(Collectors.toList()); // no random range returns 0.xxx, 0.yyyy random values
        Random random = new Random();
        // To specifically use Streams class
        List<Integer> randomNumbers = Stream.generate(() -> random.nextInt(100))
                .limit(n)
                .collect(Collectors.toList());

        // Recommended use case
//        List<Integer> randomNumbers = new Random()
//                .ints(10, 0, 100) // (count, origin, bound)
//                .boxed()          // Must box to use Collectors.toList()
//                .collect(Collectors.toList());

//        List<Integer> randomNumbers = Stream.generate(() -> new Random().nextInt(100)) // Performance Issue By putting new Random() inside the generate lambda, you are creating a new Random object 10 times
//                .limit(10)
//                .collect(Collectors.toList());
        MyLogger.log("Random Numbers till", n, randomNumbers);
    }

    public static <T> void flattenListOfLists(List<List<T>> listOfLists){
        List<T> flatList = listOfLists.stream()
                .flatMap(l -> l.stream())
                .collect(Collectors.toList());
//        List<T> flatList = listOfLists.stream().flatMap(List::stream) // or Collection::stream
//                .collect(Collectors.toList());
        MyLogger.log("Flat list", listOfLists, flatList);
    }

    public static void sumOfAllInNestedList(List<List<Integer>> listOfLists) {
        int sumOfElements = listOfLists.stream()
                .flatMap(List::stream)
                .mapToInt(Integer::intValue)
                .sum();
        MyLogger.log("Sum of All elements in List", listOfLists, sumOfElements);
    }

    public static void sumOfAllEvenInNestedList(List<List<Integer>> listOfLists){
//        Integer sumOfEven = listOfLists.stream() // slower because every time the reduce operation adds two numbers, it has to: Take the Integer object a. Extract the primitive value (unboxing). Add the value of b. Wrap the result back into a new Integer object (boxing)
//                .flatMap(List::stream)
//                .filter(i -> i % 2 == 0)
//                .reduce(0, (a,b) -> a + b);

        // better way
        int sumOfEven = listOfLists.stream()
                .flatMap(List::stream)
                .filter(i -> i%2 == 0)
                .mapToInt(Integer::intValue) // i -> i.intValue()
                .sum();
        MyLogger.log("Sum of all even numbers", listOfLists, sumOfEven);
    }

    public static void sumOfAllOddInNestedList(List<List<Integer>> listOfLists){
        int sumOfOdd = listOfLists.stream()
                .flatMap(List::stream)
                .filter(i -> i % 2 == 1)
                .mapToInt(Integer::intValue)
                .sum();
        MyLogger.log("Sum of all Odd Numbers", listOfLists, sumOfOdd);
    }

    public static void longestPalindrome(List<String> list){
        String longestPalindrome = list.stream()
                .filter(word -> word.equals(new StringBuffer(word).reverse().toString()))
                .max(Comparator.comparingInt(String::length))
                .orElse("");
        MyLogger.log("Longest Palindrome among the list", list, longestPalindrome);
    }

    public static void shortestPalindrome(List<String> list){
        String shortestPalindrome = list.stream()
                .filter(word -> word.equals(new StringBuilder(word).reverse().toString()))
                .min(Comparator.comparingInt(String::length))
                .orElse("");
        MyLogger.log("Shortest Palindrome among the list", list, shortestPalindrome);
    }

    public static void longestWordInString(String text){
        String longestWordinString = Arrays.stream(text.split(" "))
                .max(Comparator.comparingInt(String::length))
                .orElse("");
        MyLogger.log("Longest word in the given string", text, longestWordinString);
    }

    public static void shortestWordInString(String text){
        String shortestWordInString = Arrays.stream(text.split(" "))
                .min(Comparator.comparingInt(String::length))
                .orElse("");
        MyLogger.log("Shortest word in the given string", text, shortestWordInString);
    }

    public static void noOfWordsInString(String text){
        long noOfWords = Arrays.stream(text.split(" "))
                .count();
        MyLogger.log("No. of words in the given string", text, noOfWords);
    }

    public static void noOfLinesInAFile(Path path) throws IOException {
        long noOfLines = Files.lines(path).count();
        MyLogger.log("No. of lines in given file", path.getFileName(), noOfLines);
    }

    public static void noOfCharsInFile(Path path) throws IOException {
//        long noOfChars = Files.readString(path).chars() // not recommended for large files and also includes the hidden newline characters at the end of each line.
//                .count();
        long count = Files.lines(path) // Pure stream to exclude spaces and count exact characters
                .mapToLong(line -> line.replace(" ", "").length())
                .sum();
        long noOfChars = Files.lines(path).flatMapToInt(String::chars).count(); // excluding new line
        long byteCount = Files.size(path); // This counts bytes. For simple ASCII text, 1 byte = 1 character. For UTF-8 with special characters, it may differ.

        MyLogger.log("No. of chars excluding new line in file", path.getFileName(), noOfChars);
        MyLogger.log("No. of bytes in file", path.getFileName(), byteCount);
        MyLogger.log("No. of chars excluding spaces in file", path.getFileName(), count);
    }

    public static void noOfWordsInFile(Path path) throws IOException {
//        split(" ") will return empty strings ("") in the middle. The count() method will include those as "words."
        long noOfWords = Files.lines(path)
                .flatMap(line -> Arrays.stream(line.split(" "))) // returns stream array of words
                .count();

        // Better way
//        long count = Files.lines(path)
//                .flatMap(line -> Arrays.stream(line.split("\\s+"))) // Splits on any whitespace
//                .filter(word -> !word.isEmpty())                 // Ensures blank entries aren't counted
//                .count();
        // if you want to count distinct just add .distinct() before count()

        //regex alternative
//        For very large files, splitting strings into arrays and then streaming those arrays creates many short-lived objects. A more performance-oriented way is to use Pattern.splitAsStream(). This avoids the intermediate array creation.
//        Pattern spacePattern = Pattern.compile("\\s+");
//        long count = Files.lines(path)
//                .flatMap(spacePattern::splitAsStream)
//                .filter(word -> !word.isEmpty())
//                .count();
        MyLogger.log("No. of words in the file", path.getFileName(), noOfWords);

    }

    public static void csvSalaryAggregate(Path path) throws IOException {
        Map<String, Double> departmentAvgSal = Files.lines(path)
                .skip(1)
                .map(i -> i.split(","))
                .collect(Collectors.groupingBy(
                        fields -> fields[1],
                        Collectors.averagingDouble(fields -> Double.parseDouble(fields[2]))
                ));

        MyLogger.log("Average Salaries grouped by Department", path.getFileName(), departmentAvgSal);

    }

    public static void salaryGreaterThanInDept(List<Employee> employees, int minSalary){
        Map<String, List<String>> salGreaterThanMin = employees.stream()
                .filter(employee -> employee.getSalary() > minSalary)
                .collect(Collectors.groupingBy(
                        employee -> employee.getDepartment(),
                        Collectors.mapping(
                                employee -> employee.getName(),
                                Collectors.toList()
                        )
                ));

        // using method reference
//        Map<String, List<String>> salGreaterThanMin = employees.stream()
//                .filter(employee -> employee.getSalary() > minSalary)
//                .collect(Collectors.groupingBy(
//                        Employee::getDepartment,
//                        Collectors.mapping(
//                                Employee::getName,
//                                Collectors.toList()
//                        )
//                ));
        MyLogger.log("Employee names By department with salary greaterthan", minSalary, salGreaterThanMin);
    }

    public static void ageGreaterThan(List<Employee> employees, int targetAge){
        Map<String, List<String>> ageGreaterThanTarget = employees.stream()
                .filter(e -> e.getAge() > targetAge)
                .collect(Collectors.groupingBy(
                        e -> e.getDepartment(),
                        Collectors.mapping(
                                e -> e.getName(),
                                Collectors.toList()
                        )
                ));
        MyLogger.log("Employees group by department with age greater than", targetAge, ageGreaterThanTarget);
    }

    public static void topNHighestSal(List<Employee> employees, int topN){
        List<String> topNPaidEmployees = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary)
                        .reversed())
                .limit(topN)
                .map(e -> e.getName())
                .collect(Collectors.toList());
        MyLogger.log("Highest Salaried employees top", topN, topNPaidEmployees);
    }

    public static void topNFreqWords(String text, int topN){
        List<Map.Entry<String, Long>> topNFreqWords = Arrays.stream(text.split(" "))
                .collect(Collectors.groupingBy(
                        word -> word,
                        Collectors.counting()
                )).entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()) // important
                .limit(topN)
                .collect(Collectors.toList());
        MyLogger.log("Frequent words top", topN, topNFreqWords);
    }

    public static void removeAllVowels(String text){
        String noVowelString = text.chars()
                .filter(c -> !"aeiou".contains(Character.toString(c)))
                .mapToObj(c -> Character.toString(c))
                .collect(Collectors.joining());

        // to remove all consonants
//        String collect = text.chars()
//                .filter(c -> "aeiou".contains(String.valueOf((char) c))) // another way to convert char int value to String
//                .mapToObj(c -> String.valueOf((char) c))
//                .collect(Collectors.joining());

        MyLogger.log("After removing vowels from the text", text, noVowelString);

    }

    public static void removeAllDigits(String text){
        String noDigits = text.chars()
                .filter(c -> !Character.isDigit((char) c))
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
        MyLogger.log("After removing digits from", text, noDigits);
    }

    public static void removeSpecialChar(String text){
        String noSpecialCharsString = text.chars()
                .filter(c -> Character.isLetterOrDigit((char) c) || Character.isWhitespace((char) c))
                .mapToObj(c -> Character.toString(c))
                .collect(Collectors.joining());
        MyLogger.log("After removing special chars from text", text, noSpecialCharsString);
    }

    public static void extractDigitsSum(String text){
        int sum = text.chars()
                .map(c -> (char) c)
                .filter(Character::isDigit)
                .map(Character::getNumericValue)
                .sum();
        MyLogger.log("Sum of digits from text", text, sum);
    }

    public static void sumOfAllFibonacci(List<Integer> list){
        int sum = list.stream()
                .filter(n -> {
                    int a = 0, b = 1;
                    while (b < n) {
                        int temp = b;
                        b = a + b;
                        a = temp;
                    }
                    return b == n;
                })
                .mapToInt(Integer::intValue)
                .sum();
        MyLogger.log("Sum of fibonacci elements from the list", list, sum);
    }

    public static void combiningTwoAsyncTasks(CompletableFuture<List<Integer>> future1, CompletableFuture<List<Integer>> future2){
        List<Integer> combinedtasks = Stream.of(future1, future2)
                .map(CompletableFuture::join)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        MyLogger.log("Combined Tasks", "-", combinedtasks);
    }

    // Sum of integers till 1000000
    public static void processLargeDatasetInParallel(){
        long sum = IntStream.rangeClosed(1, 1000000)
                .boxed()
                .collect(Collectors.toList()).parallelStream()
                .mapToLong(Integer::intValue)
                .sum();
        MyLogger.log("Sum of integers till", 1000000, sum);
    }

    public static void handleException(List<String> list){
        List<Integer> convertedList = list.stream()
                .flatMap(element -> {
                    try {
                        return Stream.of(Integer.parseInt(element));
                    } catch (Exception e) {
                        return Stream.empty();
                    }
                })
                .collect(Collectors.toList());
        MyLogger.log("Converted the list handling exceptions", list, convertedList);
    }


    public static void customCollector(List<Integer> list){
        DoubleSummaryStatistics stats = list.stream()
                .collect(Collectors.summarizingDouble(Integer::intValue));
        MyLogger.log("Custom collected values of", list, stats);
    }
//    public static <T, R> void printMessage(String label, T input, R result) {
//        System.out.printf("%s \"%s\": %s%n", label, input, result);
//    }

}
