// Name: J1-10
// Date: 12/6/20

//  implements some of the List and LinkedList interfaces: 
//	 	  size(), add(i, o), remove(i);  addFirst(o), addLast(o); 
//  This class also overrides toString().
//  the list is zero-indexed.
//  Uses DLNode.

public class DLL // DoubleLinkedList
{
   private int size;
   private DLNode head = new DLNode(); // dummy node--very useful--simplifies the code

   public int size() {
      return size;
   }

   /*
    * appends obj to end of list; increases size;
    * 
    * @return true
    */
   public boolean add(Object obj) {
      addLast(obj);
      return true;
   }

   /* inserts obj at position index. increments size. */
   public void add(int index, Object obj) throws IndexOutOfBoundsException // this the way the real LinkedList is coded
   {
      if (index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below */
      size++;
      DLNode pointer = head;
      int count = 0;
      while (index != count) {
         pointer = pointer.getNext();
         count ++;
      }
      DLNode newNode = new DLNode(obj, pointer, pointer.getNext());
      pointer.getNext().setPrev(newNode); 
      pointer.setNext(newNode);

   }

   /* return obj at position index. */
   public Object get(int index) 
   {
      if (index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below */
      DLNode pointer = head.getNext();
      int count = 0;
      while (index != count) {
         pointer = pointer.getNext();
         count ++;
      }
      return pointer.getValue();
   }

   /*
    * replaces obj at position index. returns the obj that was replaced
    */
   public Object set(int index, Object obj)
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode pointer = head.getNext();
      int count = 0;
      while (index != count) {
         pointer = pointer.getNext();
         count ++;
      }
      Object s = pointer.getValue();
      pointer.setValue(obj);
      return s;

   }

   /*
    * removes the node from position index (zero-indexed). decrements size.
    * 
    * @return the object of the node that was removed.
    */
   public Object remove(int index) {
      if (index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below */
      size--;
      DLNode pointer = head.getNext();
      int count = 0;
      while (index != count) {
         pointer = pointer.getNext();
         count++;
      }
      Object s = pointer.getValue();
      DLNode temp = pointer.getPrev();
      temp.setNext(pointer.getNext());
      pointer.getNext().setPrev(temp);
      return s;
   }

   /* inserts obj at front of list, increases size */
   public void addFirst(Object obj) {
    DLNode node = new DLNode(obj, head, head.getNext());
    head.setNext(node);
    node.getNext().setPrev(node);
    size ++;
    }

   /* appends obj to end of list, increases size */
   public void addLast(Object obj) {

    DLNode node = new DLNode(obj, head.getPrev(), head);
    head.getPrev().setNext(node);
    head.setPrev(node);
    size++;
   }

   /* returns the first element in this list */
   public Object getFirst() {
      return head.getNext().getValue();
   }

   /* returns the last element in this list */
   public Object getLast() {
      return head.getPrev().getValue();
   }

   /*
    * returns and removes the first element in this list, or returns null if the
    * list is empty
    */
   public Object removeFirst() {
      /*
       * if (head.getNext() != null) { return null; } Object s = getFirst();
       * head.setNext(head.getNext().getNext()); return s;
       */
      if (head.getNext() == null) {
         return null;
      }
      size --;
      Object s = head.getNext().getValue(); 
      DLNode node = head.getNext().getNext();
      node.setPrev(head);
      head.setNext(node);
      return s;
      
   }
   /*
    * returns and removes the last element in this list, or returns null if the
    * list is empty
    */
   public Object removeLast() {
     if (head.getNext() == null) {
         return null;
     } 
     size --;
     Object s = head.getPrev().getValue();
     DLNode node = head.getPrev().getPrev();
     node.setNext(head);
     head.setPrev(node);
     return s;
     
   }

   /*
    * returns a String with the values in the list in a friendly format, for
    * example [Apple, Banana, Cucumber] The values are enclosed in [], separated by
    * one comma and one space.
    */
   public String toString() {
      String s = "";
      DLNode pointer = head.getNext();
      while (pointer != head) {
         s = s + pointer.getValue() + ", ";
         pointer = pointer.getNext();
      }
      return "[" + s.substring(0, s.length() - 2) + "]";
      
   }
}