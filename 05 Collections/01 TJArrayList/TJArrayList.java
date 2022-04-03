// Name: J1-10
// Date: 12/08/20

/**
 * Implements the cheat sheet's List interface.  Implements generics.
 * The backing array is an array of (E[]) new Object[10];
 * @override toString()
 */ 
public class TJArrayList<E>
{
   private int size;							//stores the number of objects
   private E[] myArray;
   public TJArrayList()                //default constructor makes 10 cells
   {
      myArray = (E[]) new Object[10];
      size = 0;
   }
   public int size()
   {
      return size;
   }
 	/* appends obj to end of list; increases size;
      must be an O(1) operation when size < array.length, 
         and O(n) when it doubles the length of the array.
	  @return true  */
   public boolean add(E obj)
   {
      
      if (size < myArray.length) {
        myArray[size] = obj;
      } 
      else {
        E[] temp = (E[]) new Object[myArray.length*2];
        for (int i = 0; i < myArray.length; i ++) {
            temp[i] = myArray[i];
        }
        temp[size] = obj;
        myArray = temp;
      }
      size++;
      return true;
   }
   /* inserts obj at position index.  increments size. 
		*/
   public E[] removeNull () {
     E[] mod = myArray;
     int nullIndex = mod.length-1;
     for (int i = 0; i < mod.length; i ++) {
        if (mod[i] == null) {
            nullIndex = i;
            i = i + 100 + mod.length;
        }
     }
     E[] temp = (E[]) new Object[nullIndex];
     for (int i = 0; i < temp.length; i ++) {
        temp[i] = mod[i];
     }
     mod=temp;
     return mod;
   }
   public void add(int index, E obj) throws IndexOutOfBoundsException  //this the way the real ArrayList is coded
   {
      if(index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      if (size >= myArray.length) {
      E[] temp = (E[]) new Object[myArray.length+1];
      for (int i = 0; i < myArray.length; i ++) {
         temp[i] = myArray[i];
      }
      myArray = temp; 
      }
      for (int i = size - 1; i >= index; i --) {
         myArray[i+1] = myArray[i];
      }
      myArray[index] = obj;
      size ++;
      
      /*
         //change the i + 1 to i and then make it so that the index specified is changed to obj
         //make sure u check where the null is
         int nullIndex = myArray.length;
         E temp = "";
         for (int i = 0; i < myArray.length; i ++) {
            if (myArray[i] == null) {
               nullIndex = i;
               i = myArray.length + 100;
            }
         }
         //this doesn't work because it keeps replacing itself
         temp = myArray[index + 1];
         myArray[index+1] = myArray[index]; 
         for (int i = index+1; i < nullIndex; i ++) {
               te
               myArray[i + 1] = myArray[i];
            
            
               myArray[i+1] = myArray[i];
               i = myArray.length+100;
            
         }
         myArray[index] = obj;
         size ++;*/
   }

   /* return obj at position index.  
		*/
   public E get(int index) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      
      return myArray[index];
   }
   /**
    * Replaces obj at position index. 
    * @return the object is being replaced.
    */  
   public E set(int index, E obj) throws IndexOutOfBoundsException  
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      
      E data = myArray[index];
      myArray[index] = obj;
      return data;
   
   }
 /*  removes the node from position index. shifts elements 
     to the left.   Decrements size.
	  @return the object at position index.
	 */
   public E remove(int index) throws IndexOutOfBoundsException  
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      E object_replaced = myArray[index];
      for (int i = index; i < myArray.length-1; i ++) {
         myArray[i] = myArray[i+1];
      }
      size --;
      E[] temp = (E[]) new Object[myArray.length-1];
      for (int i = 0; i < temp.length; i ++) {
         temp[i] = myArray[i];
      }
      myArray = temp;
      return object_replaced;
      
      /*
      for (int i = index + 1; i < myArray.length; i ++) {
         myArray[index - 1] = myArray[index];
      }
      E[] temp = (E[]) new Object[myArray.length-1];
      for (int i = 0; i < temp.length; i ++) {
         temp[i] = myArray[i];
      }
      myArray = temp;
      return myArray[index];*/ 
      //doesn't work either just like add method do reverse instead of i-1 = i i = i +1 
      
   }
	   /*
		   This method compares objects.
         Must use .equals(), not ==
     	*/
   public boolean contains(E obj)
   {
      for (int i = 0; i < myArray.length; i ++) {
         if (obj.equals(myArray[i])) {
            return true;
         }
      }
      return false;
   }
	 /*returns a String of E objects in the array with 
       square brackets and commas.
     	*/
   public String toString()
   {
      E[] mod = removeNull();
      String s = "[";
      for (int i = 0; i < mod.length; i ++) {
         if (i == mod.length - 1) {
            s = s + mod[i] + "]";
         } else {
            s = s + mod[i] + ", ";
         }
         
      }
      return s;
   }
}