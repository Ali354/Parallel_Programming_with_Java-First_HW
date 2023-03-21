package org.example;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinOccurrenceCount {
    public static long countOccurrences(int[] arr, int num){
        long startTime = System.nanoTime();
        ForkJoinPool pool = new ForkJoinPool();
        int count = pool.invoke(new CountTask(arr, num, 0, arr.length));
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        return executionTime;
    }

    private static class CountTask extends RecursiveTask<Integer> {
        private int[] arr;
        private int num;
        private int start;
        private int end;

        public CountTask(int[] arr, int num, int start, int end) {
            this.arr = arr;
            this.num = num;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start <= 1000) {
                int count = 0;
                for (int i = start; i < end; i++) {
                    if (arr[i] == num) {
                        count++;
                    }
                }
                return count;
            } else {
                int mid = (start + end) / 2;
                CountTask leftTask = new CountTask(arr, num, start, mid);
                CountTask rightTask = new CountTask(arr, num, mid, end);
                leftTask.fork();
                int rightCount = rightTask.compute();
                int leftCount = leftTask.join();
                return leftCount + rightCount;
            }
        }
    }
}
