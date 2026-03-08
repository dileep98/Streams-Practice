package org.dk.streams.methodReference;

public class Test1 {

    public static void main(String[] args) {
        IAddition addition = Integer::sum;
        addition.sum(1,2);
    }
}
