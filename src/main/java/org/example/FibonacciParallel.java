package org.example;

import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibonacciParallel {

    private static final HashMap<Integer, Long> cache = new HashMap<>();

    public static long fib(int n) {
        if (n < 2) {
            return n;
        }

        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        RecursiveTask<Long> task = new FibonacciTask(n);
        ForkJoinPool pool = ForkJoinPool.commonPool();
        long result = pool.invoke(task);

        cache.put(n, result);
        return result;
    }

    private static class FibonacciTask extends RecursiveTask<Long> {
        private final int n;

        public FibonacciTask(int n) {
            this.n = n;
        }

        @Override
        protected Long compute() {
            if (n < 2) {
                return (long) n;
            }

            FibonacciTask left = new FibonacciTask(n - 1);
            left.fork();

            FibonacciTask right = new FibonacciTask(n - 2);
            right.fork();

            return left.join() + right.join();
        }
    }
}
