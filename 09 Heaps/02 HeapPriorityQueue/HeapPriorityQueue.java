//Name:   J1-10
//Date:   Apr 18 2020

import java.util.*;

/* implement the API for java.util.PriorityQueue
 * test this class with HeapPriorityQueue_Driver.java.
 * test this class with LunchRoom.java.
 * add(E) and remove()  must work in O(log n) time
 */
public class HeapPriorityQueue<E extends Comparable<E>> {
   private ArrayList<E> myHeap;

   public HeapPriorityQueue() {
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }

   public boolean add(E obj) {
      if (myHeap.size() >= 3) {
         myHeap.add(obj);
         heapUp(myHeap.size() - 1);
         return true;
      } else {
         myHeap.add(obj);
         return true;
      }
   }

   public E remove() {
      E removing = myHeap.get(1);
      swap(1, myHeap.size() - 1);
      myHeap.remove(myHeap.size() - 1);
      heapDown(1, myHeap.size());
      return removing;
   }

   public E peek() {
      if (myHeap.isEmpty()) {
         // return -1;
         return myHeap.get(0);
      }
      return myHeap.get(1);
   }

   public boolean isEmpty() {
      if (myHeap.size() <= 1) {
         return true;
      }
      return false;
   }

   private void heapUp(int k) {
      int parent = k / 2;
      while (myHeap.get(parent).compareTo(myHeap.get(k)) < 0 && parent > 0 && k > 0) {
         swap(parent, k);
         k /= 2;
         parent = k / 2;
      }
   }

   private void swap(int a, int b) {
      E temp = myHeap.get(a);
      myHeap.set(a, myHeap.get(b));
      myHeap.set(b, temp);
   }

   private void heapDown(int k, int size) {
      if (k * 2 >= size || k >= size) {
         return;
      }
      int maxChild = 0;
      if (myHeap.get(k * 2).compareTo(myHeap.get(k * 2 + 1)) >= 0) {
         maxChild = k * 2;
      } else {
         maxChild = k * 2 + 1;
      }
      if (myHeap.get(k).compareTo(myHeap.get(maxChild)) < 0) {
         swap(k, maxChild);
         heapDown(maxChild, size);
      }
   }

   public String toString() {
      return myHeap.toString();
   }
}
