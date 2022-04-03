// Name: J1 - 10
// Date: 11/11/20

import java.util.*;
import java.io.*;

public class MergeSort_Driver
{
   public static void main(String[] args) throws Exception
   {
      //Part 1, for doubles
      double[] array = {3,1,4,1,5,9,2,6};    // small example array from the MergeSort handout
      //int n = (int)(Math.random()*50+10);
      // double[] array = new double[n];
      // for(int k = 0; k < array.length; k++)
         // array[k] = Math.random();
         	
      MergeSort.sort(array);
   
      print(array);
      if( isAscending(array) )
         System.out.println("In order!");
      else
         System.out.println("oops!");
         
      //Part 2, for Comparables
      int size = 100;
      Scanner sc = new Scanner(new File("declaration.txt"));
      Comparable[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();	
   
      MergeSort.sort(arrayStr);
      print(arrayStr);
      System.out.println();
      if( isAscending(arrayStr) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
   }

   
   public static void print(double[] a)   
   {                             
      for(double number : a)    
         System.out.print(number+" ");
      System.out.println();
   }
  
   public static boolean isAscending(double[] a)
   {
   for (int i = 0; i < a.length - 2; i++) {
         if (a[i] > a[i + 1]) {
            return false;
         }
      }
      return true;
   }
  
   public static void print(Object[] peach)
   {
      for (Object abc : peach) {
         System.out.print(abc + " ");
      }
      System.out.println();
   }
   
   @SuppressWarnings("unchecked")
   public static boolean isAscending(Comparable[] a)
   {
      for (int i = 0; i < a.length - 2; i++) {
         if (a[i].compareTo(a[i + 1]) > 0) {
            return false;
         }
      }
      return true;

   }
}
/////////////////////////////////////////////
// 15 Oct 07
// MergeSort Code Handout
// from Lambert & Osborne, p. 482 - 485

class MergeSort
{
   private static final int CUTOFF = 10; // for small lists, recursion isn't worth it
  
   public static void sort(double[] array)
   { 
      double[] copyBuffer = new double[array.length];
      mergeSortHelper(array, copyBuffer, 0, array.length - 1);
   }
   
   /*  array			array that is being sorted
       copyBuffer		temp space needed during the merge process
       low, high		bounds of subarray
       middle			midpoint of subarray   
   */
   private static void mergeSortHelper(double[] array, double[] copyBuffer,
                                                      int low, int high)
   {  
       if ( high - low  < CUTOFF )                  //switch to selection sort  when
          SelectionLowHigh.sort(array, low, high);        //the list gets small enough 
      else
      if (low < high)
      {
         int middle = (low + high) / 2;
         mergeSortHelper(array, copyBuffer, low, middle);
         mergeSortHelper(array, copyBuffer, middle + 1, high);
         merge(array, copyBuffer, low, middle, high);
      }
   }
   
   /* array				array that is being sorted
      copyBuffer		temp space needed during the merge process
      low				beginning of first sorted subarray
      middle			end of first sorted subarray
      middle + 1		beginning of second sorted subarray
      high				end of second sorted subarray   
   */
   public static void merge(double[] array, double[] copyBuffer,
                                   int low, int middle, int high)
   {
      // Initialize i1 and i2 to the first items in each subarray  
      // Interleave items from the subarrays into the copyBuffer in such 
      // a way that order is maintained.
      int secondStart = middle + 1;
      int start = low;
      for (int i = low; i <= high; i ++) {
         if (start > middle) {
            copyBuffer[i] = array[secondStart];
            secondStart ++;
         } else if (secondStart > high) {
            copyBuffer[i] = array[start];
            start ++;
         } else if (array[start] < array[secondStart]) {
            copyBuffer[i] = array[start];
            start ++;
         } else {
            copyBuffer[i] = array[secondStart];
            secondStart ++;
         }
      
      }
      
      for (int i = 0; i < copyBuffer.length; i ++) {
         array[i] = copyBuffer[i];
      }        
      

      
      
      //then copy the just-sorted values (from low to high)
      // from the copyBuffer back to the array.
   	
   
      
   }	
      
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static void sort(Comparable[] array)
   {  
      Comparable[] copyBuffer = new Comparable[array.length];
      mergeSortHelper(array, copyBuffer, 0, array.length - 1);

   }

   /* array				array that is being sorted
      copyBuffer		temp space needed during the merge process
      low, high		bounds of subarray
      middle			midpoint of subarray  */
   @SuppressWarnings("unchecked")
   private static void mergeSortHelper(Comparable[] array, Comparable[] copyBuffer, int low, int high)
   {
     if (low < high)
      {
         int middle = (low + high) / 2;
         mergeSortHelper(array, copyBuffer, low, middle);
         mergeSortHelper(array, copyBuffer, middle + 1, high);
         merge(array, copyBuffer, low, middle, high);
      }

   }
   
   /* array				array that is being sorted
      copyBuffer		temp space needed during the merge process
      low				beginning of first sorted subarray
      middle			end of first sorted subarray
      middle + 1		beginning of second sorted subarray
      high				end of second sorted subarray   */
   @SuppressWarnings("unchecked")
   public static void merge(Comparable[] array, Comparable[] copyBuffer,
                                   int low, int middle, int high)
   {
      int secondStart = middle + 1;
      int start = low;
      for (int i = low; i <= high; i ++) {
         if (start > middle) {
            copyBuffer[i] = array[secondStart];
            secondStart ++;
         } else if (secondStart > high) {
            copyBuffer[i] = array[start];
            start ++;
         } else if (array[start].compareTo(array[secondStart]) < 0) {
            copyBuffer[i] = array[start];
            start ++;
         } else {
            copyBuffer[i] = array[secondStart];
            secondStart ++;
         }
      
      }
      
      for (int i = low; i < high; i ++) {
         array[i] = copyBuffer[i];
      }        
      
   }    	
}

/***************************************************
This is the extension.  You will have to uncomment Lines 89-90 above.
***************************************************/

class SelectionLowHigh
{
   public static void sort(double[] array, int low, int high)
   {  
   for (int i = high; i >= low; i --) {
         swap(array, findMax(array, low, i), i);
      }

   }
   private static int findMax(double[] array, int low, int upper)
   {
      int max = low;
      for (int i = low; i <= upper; i ++) {
         if (array[i] > array[max]) {
            max = i;
         }
      }
      return max;
   }
   private static void swap(double[] array, int a, int b)
   {
      double temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   } 
}
