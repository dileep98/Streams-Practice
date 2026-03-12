package org.dk.streams.methodReference;

public class UpperCaseTest {
    public static void main(String[] args) {

        // when the method is not static
        IUpperCase iUpperCase = UpperCaseTest::convertStringToUpperCase; // creates an arbitrary object
        System.out.println(iUpperCase.convertStringToUpperCase(new UpperCaseTest(), "Dileep"));



    }

    String convertStringToUpperCase(String s){
        return s.toUpperCase();
    }
}
