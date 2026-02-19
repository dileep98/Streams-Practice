package org.dk.streams;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class StreamsPracticeAdvanced implements StreamsPractice {

    public void runMethods() throws URISyntaxException, IOException {
        List<String> words = List.of("apple", "banana", "kiwi", "apple", "banana", "");
        StreamFunctionsAdvanced.mostFrequentElementInList(words);
        StreamFunctionsAdvanced.leastFrequentInList(words);
        String text = "dsniadbiawuf$%^&*ojasoda wcsnanewicni;awnecon;aewn";
        StreamFunctionsAdvanced.firstNonRepeatingChar(text);
        StreamFunctionsAdvanced.firstRepeatedChar(text);
        StreamFunctionsAdvanced.checkPalindrome("madams");
        StreamFunctionsAdvanced.anagrams(List.of("listen", "silent", "enlist", "google", "inlets"), "silent");
        StreamFunctionsAdvanced.fibonacciSequence(10);
        StreamFunctionsAdvanced.listOfRandomNumbers(10);
        List<List<Integer>> listOfLists = List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6),
                List.of(7, 8, 9)
        );
        StreamFunctionsAdvanced.flattenListOfLists(listOfLists);
        List<List<String>> listOfListsOfStrings = List.of(
                List.of("text1", "text2", "text3"),
                List.of("text8", "text9", "text4"),
                List.of("text7", "text6", "text5")
        );
        StreamFunctionsAdvanced.flattenListOfLists(listOfListsOfStrings);
        StreamFunctionsAdvanced.sumOfAllInNestedList(listOfLists);
        StreamFunctionsAdvanced.sumOfAllEvenInNestedList(listOfLists);
        StreamFunctionsAdvanced.sumOfAllOddInNestedList(listOfLists);

        List<String> somePalindromeList = List.of("madam", "racecar", "apple", "banana", "level");
        StreamFunctionsAdvanced.longestPalindrome(somePalindromeList);
        StreamFunctionsAdvanced.shortestPalindrome(somePalindromeList);

        String lineText = "The quick brown fox jumps over the lazy dog, and then the lazy brown fox jumps back";
        StreamFunctionsAdvanced.longestWordInString(lineText);
        StreamFunctionsAdvanced.shortestWordInString(lineText);
        StreamFunctionsAdvanced.noOfWordsInString(lineText);
        StreamFunctionsAdvanced.topNFreqWords(lineText, 3);
        StreamFunctionsAdvanced.removeAllVowels(lineText);
        StreamFunctionsAdvanced.removeSpecialChar(text);
        String textWithNumbers = "hjdfgi67 w3tr 29h3ibu shdfo87w3 gyigfo86ew gfuksd jgf7ow 83w4gfy uv387pwf";
        StreamFunctionsAdvanced.removeAllDigits(textWithNumbers);
        StreamFunctionsAdvanced.extractDigitsSum(textWithNumbers);
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        StreamFunctionsAdvanced.sumOfAllFibonacci(numbers);
        Path sampleTxtPath = Paths.get(ClassLoader.getSystemResource("sample.txt").toURI());
        StreamFunctionsAdvanced.noOfLinesInAFile(sampleTxtPath);
        StreamFunctionsAdvanced.noOfCharsInFile(sampleTxtPath);
        StreamFunctionsAdvanced.noOfWordsInFile(sampleTxtPath);
        Path sampleCsvPath = Paths.get(ClassLoader.getSystemResource("data.csv").toURI());
        StreamFunctionsAdvanced.csvSalaryAggregate(sampleCsvPath);

        // In main method, update Employee instantiation

        List<Employee> employees = List.of(
                new Employee("Alice", "Engineering", 95000, 30),
                new Employee("Bob", "Engineering", 45000, 28),
                new Employee("Charlie", "HR", 60000, 35),
                new Employee("David", "HR", 48000, 40),
                new Employee("Eve", "Marketing", 75000, 32),
                new Employee("Frank", "Engineering", 110000, 45)
        );

        StreamFunctionsAdvanced.salaryGreaterThanInDept(employees, 50000);
        StreamFunctionsAdvanced.ageGreaterThan(employees, 30);
        StreamFunctionsAdvanced.topNHighestSal(employees, 2);

        CompletableFuture<List<Integer>> future1 = CompletableFuture.supplyAsync(() -> List.of(1, 2, 3));
        CompletableFuture<List<Integer>> future2 = CompletableFuture.supplyAsync(() -> List.of(4, 5, 6));
        StreamFunctionsAdvanced.combiningTwoAsyncTasks(future1, future2);
        StreamFunctionsAdvanced.processLargeDatasetInParallel();
        List<String> stringList = List.of("1", "2", "Three", "4");
        StreamFunctionsAdvanced.handleException(stringList);
        StreamFunctionsAdvanced.customCollector(numbers);



    }



}


class Employee {
    private String name;
    private String department;
    private double salary;
    private int age;

    public Employee(String name, String department, double salary, int age) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return Double.compare(employee.salary, salary) == 0 && age == employee.age && name.equals(employee.name) && department.equals(employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, department, salary, age);
    }
}