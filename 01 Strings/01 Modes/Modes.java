// Name: Tanmai Kalisipudi
// Date: 09/08/20

import java.util.*;
import java.io.*;

public class Modes {
    public static void main(String[] args) {
        int[] tally = { 0, 0, 10, 5, 10, 0, 7, 1, 0, 6, 0, 10, 3, 0, 0, 1 };
        display(tally);
        int[] modes = calculateModes(tally);
        display(modes);
        int sum = 0;
        for (int k = 0; k < tally.length; k++)
            sum += tally[k];
        System.out.println("kth \tindex");
        for (int k = 1; k <= sum; k++)
            System.out.println(k + "\t\t" + kthDataValue(tally, k));
    }

    /**
     * precondition: tally.length > 0 postcondition: returns an int array that
     * contains the modes(s); the array's length equals the number of modes.
     */
    public static int[] addNum(int oldArray[], int size, int newNum) {
        int[] newArray = new int[size + 1];
        for (int i = 0; i < oldArray.length; i++)
            newArray[i] = oldArray[i];
        newArray[size] = newNum;
        return newArray;
    }

    public static int[] calculateModes(int[] tally) {
        /*
         * Planning: First check if all the elements in the array are equal by comparing
         * one element to the next Find the max of the array Create a for loop that
         * checks for that max number in each location of the array When the condition
         * matches, add the index of the location that the max integer was found in to
         * an array Repeat process until all the way through the loop
         */
        // boolean equal = false;
        int[] modeArray = new int[0];
        boolean equal = true;
        // for (int i = 0; i < tally.length; i ++) {
        // for (int i = 0; i < tally.length -1; i ++) {
        // if (tally[i] == tally[i+1]) {
        // equal = false;
        // }
        // else {
        // equal = false;
        // }
        // }
        for (int i = 0; i < tally.length - 1; i++) {
            if (tally[i] != tally[i + 1]) {
                equal = false;
            }
        }
        if (equal == true) {
            return modeArray;
        }
        int max = findMax(tally);
        // int[] modeArray = new int[0];
        int sizeArray = 0;
        for (int i = 0; i < tally.length; i++) {
            if (tally[i] == max) {
                modeArray = addNum(modeArray, sizeArray, i);
                sizeArray += 1;
            }

        }
        return modeArray;

    }

    /**
     * precondition: tally.length > 0 0 < k <= total number of values in data
     * collection postcondition: returns the kth value in the data collection
     * represented by tally
     */
    public static int kthDataValue(int[] tally, int k) {
        // Your code goes here.
        /*
         * Planning: Create a variable that will count the number of how much the value
         * at a certain location in the tally If the count number is greater than the
         * value of the given variable k then return the index of the tally array
         *///
        // int sum = 0;
        // for (int i = 0; i < tally.length) {
        // sum = sum + tally[i];
        // }
        // int [] realArray = new int [sum];
        // for (int i = 0; i < realArray.length; i++) {
        //
        // }
        // return -1;
        // int x
        int x = 0;
        // int kth = 0;
        for (int i = 0; i < tally.length; i++) {
            x += tally[i];
            if (x >= k) {
                // return tally[i];
                return i;
            }

        }
        return -1;
    }

    /**
     * precondition: nums.length > 0 postcondition: returns the maximal value in
     * nums
     */
    public static int findMax(int[] nums) {
        int pos = 0;
        for (int k = 1; k < nums.length; k++)
            if (nums[k] > nums[pos])
                pos = k;
        return nums[pos];
    }

    public static void display(int[] args) {
        for (int k = 0; k < args.length; k++)
            System.out.print(args[k] + " ");
        System.out.println();
        System.out.println();
    }
}
