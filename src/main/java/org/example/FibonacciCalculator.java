package org.example;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibonacciCalculator extends RecursiveTask<Long> {
    private static final long serialVersionUID = 1L;

    private static HashMap<Integer, Long> memo = new HashMap<>();

    private int n;

    public FibonacciCalculator(int n) {
        this.n = n;
    }

    @Override
    protected Long compute() {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        if (n <= 1) {
            return (long) n;
        }
        FibonacciCalculator f1 = new FibonacciCalculator(n - 1);
        f1.fork();
        FibonacciCalculator f2 = new FibonacciCalculator(n - 2);
        long result = f2.compute() + f1.join();
        memo.put(n, result);
        return result;
    }
}