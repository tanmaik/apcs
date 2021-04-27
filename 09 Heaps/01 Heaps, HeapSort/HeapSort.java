import java.text.DecimalFormat;

// Name: J1-10
// Date: 4/3/2020

public class HeapSort
{
   public static int SIZE;  //9 or 100
	
   public static void main(String[] args)
   {
      //Part 1: Given a heap, sort it. Do this part first. 
      SIZE = 9;  
      double heap[] = {-1,3.4,7.2,6.4,9.9};
      
      display(heap);
      sort(heap);
      display(heap);
      System.out.println(isSorted(heap));
      
      //Part 2:  Generate 100 random numbers, make a heap, sort it.
      // SIZE = 100;
      // double[] heap = new double[SIZE + 1];
      // heap = createRandom(heap);
      // display(heap);
      // makeHeap(heap, SIZE);
      // display(heap); 
      // sort(heap);
      // display(heap);
      // System.out.println(isSorted(heap));
   }
   
	//******* Part 1 ******************************************
   public static void display(double[] array)
   {
      for(int k = 1; k < array.length; k++)
         System.out.print(array[k] + "    ");
      System.out.println("\n");	
   }
   
   public static void sort(double[] array)
   {
      if(isSorted(array)) {
         return;
      }
      int s = array.length - 1;
      int s1 = array.length - 1;
      if (s1 == 0 || s1 == 1) {
         return;
      }
      while (s != 1) {
         swap(array, 1, s);
         s --;
         heapDown(array, 1, s);
      }
      if (array[3] > array[4] && s1 >= 4) {
         swap (array, 3, 4);
      }
      
      if(array[1] > array[2] && s1 > 2)   //just an extra swap, if needed.
         swap(array, 1, 2);
   }
  
   public static void swap(double[] array, int a, int b)
   {
      double temp = array[a];
      array[a] = array[b];
      array[b] = temp;  
   }
   
   public static void heapDown(double[] array, int k, int size)
   {
      if (k*2 >= size || k >= size) {
         return;
      }
      int maxChild = 0;
      if (array[k*2] >= array[k*2+1]) {
         maxChild = k*2;
      } else {
         maxChild = k*2+1;
      }
      if (array[k] < array[maxChild]) {
         swap(array, k, maxChild);
         heapDown(array, maxChild, size);
      }
   }
   
   public static boolean isSorted(double[] arr)
   {
      for (int i = 2; i < arr.length; i ++) {
         if (arr[i-1] > arr[i]) {
            return false;
         }
      }
      return true;
   }
   
   //****** Part 2 *******************************************

   //Generate 100 random numbers (between 1 and 100, formatted to 2 decimal places) 
   public static double[] createRandom(double[] array)
   {  
      array[0] = -1;   //because it will become a heap
      DecimalFormat df = new DecimalFormat("0.00");
      for (int i = 1; i <= 100; i ++) {
         array[i] = Double.parseDouble(df.format(Math.random()*100));
      }
      return array;
   }
   
   //turn the random array into a heap
   public static void makeHeap(double[] array, int size)
   {
      for (int i = size/2; i >= 1; i --) {
         heapDown(array, i, size);
      }
   }
}

