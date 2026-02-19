package org.dk.util;

import jdk.jfr.StackTrace;

public class MyLogger {
    public static <T, R> void log(String label, T input, R result){
        StackWalker stackWalker = StackWalker.getInstance();
        String callerInfo = stackWalker.walk(frames -> frames
                .skip(1)
                .findFirst()
                .map(f -> f.getClassName().replaceAll(".*\\.", "") + "." + f.getMethodName())
//                .map(f -> f.getClassName() + "." + f.getMethodName())
                .orElse("Unknown Method")
        );
        System.out.printf("%s :: %s \"%s\" : %s\n", callerInfo, label, input, result);
    }
}
