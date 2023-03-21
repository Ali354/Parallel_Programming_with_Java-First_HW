package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelArraySearch {
    public static void do_search(int[] arr, int num) {
        int numThreads = Runtime.getRuntime().availableProcessors();
        int chunkSize = arr.length / numThreads;

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        List<Integer> futures = new ArrayList<>();
        int totalCount = 0;

        for (int i = 0; i < numThreads; i++) {
            int startIndex = i * chunkSize;
            int endIndex = (i == numThreads - 1) ? arr.length : (i + 1) * chunkSize;
            int[] subArray = new int[endIndex - startIndex];
            System.arraycopy(arr, startIndex, subArray, 0, subArray.length);

            executor.submit(() -> {
                int count = 0;
                for (int j = 0; j < subArray.length; j++) {
                    if (subArray[j] == num) {
                        count++;
                    }
                }
                System.out.println("The count is : "+count);
                futures.add(count);
                return count;
            });
        }

        executor.shutdown();

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int future : futures) {
            totalCount += future;
        }

        System.out.println("Total occurrences of " + num + " in array: " + totalCount);
    }
}