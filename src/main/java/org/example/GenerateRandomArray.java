package org.example;
import java.util.Random;

public class GenerateRandomArray {
    public static void do_(String[] args) {
        int[] arr = generateRandomArray(1000000000);
        // use the array here
    }

    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(100000) + 1;
        }
        return arr;
    }
}

