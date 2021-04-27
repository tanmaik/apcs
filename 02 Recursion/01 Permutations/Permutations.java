// Name: J1-10
// Date: 10/1/2020

import java.util.*;
// import java.lang.Math;

public class Permutations {
   public static int count = 0;

   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      System.out.print("\nHow many digits? ");
      int n = sc.nextInt();
      // leftRight("", n);

      // oddDigits("", n);
      
      // superprime(n);
      // if(count==0)
      // System.out.println("no superprimes");
      // else
      // System.out.println("Count is "+count);
   }

   /**
    * Builds all the permutations of a string of length n containing Ls and Rs
    * 
    * @param s A string
    * @param n An postive int representing the length of the string
    */
   public static void leftRight(String s, int n) {
      if (s.length() == n) {
         System.out.println(s);

      } else {
         leftRight(s + "L", n);
         leftRight(s + "R", n);
      }

   }

   /**
    * Builds all the permutations of a string of length n containing odd digits
    * 
    * @param s A string
    * @param n A postive int representing the length of the string
    */
   public static void oddDigits(String s, int n) {
      /*
       * code doesn't work int counter = 1; while (s.length() < n) { if
       * (s.length()<n){ oddDigits("" + s + counter, n); } counter += 2; }
       * System.out.println(s);
       */
      if (s.length() == n) {
         System.out.println(s);
         return;
      }
      for (int i = 1; i <= 9; i = i + 2) {
         oddDigits("" + s + i, n);

      }
   }

   /**
    * Builds all combinations of a n-digit number whose value is a superprime
    * 
    * @param n A positive int representing the desired length of superprimes
    */
   public static void superprime(int n) {

      recur(2, n); // try leading 2, 3, 5, 7, i.e. all the single-digit primes
      recur(3, n);
      recur(5, n);
      recur(7, n);
   }

   /**
    * Recursive helper method for superprime
    * 
    * @param k The possible superprime
    * @param n A positive int representing the desired length of superprimes
    */
   private static void recur(int k, int n) {
      /*
       * first add all the odd numbers you can to the string then test if that number
       * is prime if the number is prime then divide by 10 and check again if the
       * number is prime keep doing this while the number is greater than (not equal
       * to) 0 if it is prime all the way thru, print the number
       */
      String s = Integer.toString(k);
      int f;
      boolean printed = false;
      if (s.length() == n) {
         f = Integer.valueOf(s);
         while (f > 0 && isPrime(f) && !printed) {
            f = f / 10;
            if (f < 10) {
               System.out.println(s);
               printed = true;
               count ++;
            }
         }
         return;
      }

      for (int i = 1; i <= 9; i = i + 2) {
         recur(Integer.valueOf(s + Integer.toString(i)), n);
      }

   }

   /**
    * Determines if the parameter is a prime number.
    * 
    * @param n An int.
    * @return true if prime, false otherwise.
    */
   public static boolean isPrime(int n) {
      if (n <= 0) {
         return false;
      }

      for (int i = 2; i < n; i++) {
         if (n % i == 0) {
            return false;
         }
      }
      return true;

   }
}
