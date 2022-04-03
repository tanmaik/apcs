// Name:  J1-10
// Date:  2/21

interface BSTinterface
{
   public int size();
   public boolean contains(String obj);
   public void add(String obj);
   //public void addBalanced(String obj);
   public void remove(String obj);
   public String min();
   public String max();
   public String display();
   public String toString();
}

/*******************
Copy your BST code.  Implement the remove() method.
Test it with BST_Delete.java
**********************/
public class BST implements BSTinterface
{
   private TreeNode root;
   private int size;
   public BST()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
     return size;
   }
   public TreeNode getRoot()   //for Grade-It
   {
      return root;  
   }
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   public void add(String s) 
   {
      add(root, s);
      size++;
   }
   private TreeNode add(TreeNode t, String s) //recursive helper method
   {      
      if (root == null) {
         root = new TreeNode(s, null, null);
         return t;
      }
      if (t == null) {
         t = new TreeNode(s, null, null);
         return t;
      }
      if (s.compareTo((String) t.getValue()) < 0) {
         t.setLeft(add(t.getLeft(), s));
      } else {
         t.setRight(add(t.getRight(), s));
      }

      return t;
   }
   
   public String display()
   {
      return display(root, 0);
   }
   private String display(TreeNode t, int level) //recursive helper method
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
   
   public boolean contains(String obj)
   {
      return contains(root, obj);
   }
   private boolean contains(TreeNode t, String x) //recursive helper method
   {
      if (t == null) {
         return false;
      }
      Comparable contain = (Comparable) x;
      Comparable compared = (Comparable) t.getValue();
      if (compared.compareTo(contain) > 0) {
         return contains(t.getLeft(), x);
      }
      if (contain.compareTo(compared) > 0) {
         return contains(t.getRight(), x);
      }
      return true;
   }
   
   public String min()
   {
      return min(root);
   }
   private String min(TreeNode t)  //use iteration
   {
      if (t == null) {
         return null;
      }
      while(t.getLeft() != null) {
         t = t.getLeft();
      }
      return "" + t.getValue();
   }
   
   public String max()
   {
      return max(root);  
   }
   private String max(TreeNode t)  //recursive helper method
   {
      if (t.getRight() == null) {
         return ""+ t.getValue();
      } else {
         return max(t.getRight());
      }
   }
   
   public String toString()
   {
      return toString(root);
   }
   private String toString(TreeNode t)  //an in-order traversal.  Use recursion.
   {
      String toReturn = "";
      if (t == null) {
         return "";
      }
      toReturn += toString(t.getLeft());
      toReturn += t.getValue() + " ";
      toReturn += toString(t.getRight());
      return toReturn;
   }
   
   public void remove(String target)
   {
      root = remove(root, target);
      size--;
   }
   private TreeNode remove(TreeNode current, String target)
   { 
      if (current == null) {
         return current;
      }
      if (target.compareTo((String) current.getValue()) < 0) {
         current.setLeft(remove(current.getLeft(), target));
      } else if (((String) current.getValue()).compareTo(target) < 0) {
         current.setRight(remove(current.getRight(), target));
      } else {
         if (current.getLeft() == null && current.getRight() == null) {
            return null;
         } else if (current.getLeft() != null && current.getRight() == null)  {
            return current.getLeft();
         } else if (current.getLeft() == null && current.getRight() != null) {
            return current.getRight();
         }
         current.setValue(min(current.getRight()));
         current.setRight(remove(current.getRight(), (String) current.getValue()));

      }
      return current;
      
         }
}