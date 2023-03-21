package org.example;

import java.util.*;
import java.util.concurrent.*;



public class Main {

public static void main(String[] args) {
    // Generate a random array of 1 billion integers
    int[] arr = GenerateRandomArray.generateRandomArray(1000000);

    // Serial count
    long startSerial = System.currentTimeMillis();
    int serialCount = OccurrenceCount.countOccurrences(arr, 5);
    long endSerial = System.currentTimeMillis();
    System.out.println("Serial count: " + serialCount);
    System.out.println("Serial execution time: " + (endSerial - startSerial) + " milliseconds");

    // Parallel count with threads
    long startThreads = System.currentTimeMillis();
    int threadsCount = ParallelOccurrenceCount.countOccurrences(arr, 5);
    long endThreads = System.currentTimeMillis();
    System.out.println("Parallel count with threads: " + threadsCount);
    System.out.println("Threads execution time: " + (endThreads - startThreads) + " milliseconds");

    // Parallel count with fork/join
    long startForkJoin = System.currentTimeMillis();
    long forkJoinCount = ForkJoinOccurrenceCount.countOccurrences(arr, 5);
    long endForkJoin = System.currentTimeMillis();
    System.out.println("Parallel count with fork/join: " + forkJoinCount);
    System.out.println("Fork/join execution time: " + (endForkJoin - startForkJoin) + " milliseconds");

    // Fibonacci
    int n = 5;

    long start = System.currentTimeMillis();
    FibonacciParallel FP = new FibonacciParallel();
    long result = FP.fib(n);
    long end = System.currentTimeMillis();

    System.out.println("Fibonacci(" + n + ") = " + result);
    System.out.println("Execution time: " + (end - start) + " milliseconds");
}
}