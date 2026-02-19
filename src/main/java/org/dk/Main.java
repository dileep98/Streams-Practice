package org.dk;

import org.dk.streams.StreamsPractice;
import org.dk.streams.StreamsPracticeAdvanced;
import org.dk.streams.StreamsPracticeBeginner;
import org.dk.streams.StreamsPracticeIntermediate;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {
        System.out.println("===== StreamsPracticeBeginner =====");
        StreamsPractice beginner = new StreamsPracticeBeginner();
        beginner.runMethods();
        System.out.println("===================================\n");

        System.out.println("===== StreamsPracticeIntermediate =====");
        StreamsPractice intermediate = new StreamsPracticeIntermediate();
        intermediate.runMethods();
        System.out.println("=======================================\n");

        System.out.println("===== StreamsPracticeAdvanced =====");
        StreamsPractice advanced = new StreamsPracticeAdvanced();
        advanced.runMethods();
        System.out.println("===================================\n");
    }
}