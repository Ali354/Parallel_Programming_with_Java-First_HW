package org.example;
import java.util.Arrays;

public class ParallelOccurrenceCount {
    public static int countOccurrences(int[] arr, int num) {
        return Arrays.stream(arr)
                .parallel()
                .map(x -> x == num ? 1 : 0)
                .sum();
    }
}

