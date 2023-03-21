package org.example;

import java.util.*;
import java.util.concurrent.*;



public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world _ 2!");

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int num = 5;
        ParallelArraySearch PAS = new ParallelArraySearch();
        PAS.do_search(arr,num);

        int n = 50;
        ForkJoinPool pool = new ForkJoinPool();
        long result = pool.invoke(new FibonacciCalculator(n));
        System.out.println("Fibonacci(" + n + ") = " + result);
    }
}