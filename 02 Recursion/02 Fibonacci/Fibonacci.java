// Name: J1-10
// Date: 10/10/20

import java.util.*;

public class Fibonacci {
    public static void main(String[] args) {
        long start, end, fib; // why long?
        int lastFibNumber = 43;
        int[] fibNumber = { 1 };
        System.out.println("\tFibonacci\tBy Iteration\tTime\tby Recursion\t Time");
        for (int n = fibNumber[0]; n <= lastFibNumber; n++) {
            start = System.nanoTime();
            fib = fibIterate(n);
            end = System.nanoTime();
            System.out.print("\t\t" + n + "\t\t" + fib + "\t" + (end - start) / 1000.);

            start = System.nanoTime();
            fib = fibRecur(n);
            end = System.nanoTime();
            System.out.println("\t" + fib + "\t\t" + (end - start) / 1000.);

        }
    }

    /**
     * Calculates the nth Fibonacci number by interation
     * 
     * @param n A variable of type int representing which Fibonacci number to
     *          retrieve
     * @returns A long data type representing the Fibonacci number
     */
    public static long fibIterate(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 1;
        }
        int end = 1;
        int next = 1;
        int temp = 0;
        for (int i = 3; i <= n; i++) {
            temp = next; // testing: temp = 1 - temp = 2
            next += end; // next = 2 - next = 3
            end = temp; // end = 1 - end = 2
        }
        return next;
    }

    /**
     * Calculates the nth Fibonacci number by recursion
     * 
     * @param n A variable of type int representing which Fibonacci number to
     *          retrieve
     * @returns A long data type representing the Fibonacci number
     */
    public static long fibRecur(int n) {
        if (n == 0) {// bsae case
            return 0;
        } else if (n == 1) {// base case
            return 1;
        }
        return fibRecur(n - 2) + fibRecur(n - 1);// actual recursion

    }
}