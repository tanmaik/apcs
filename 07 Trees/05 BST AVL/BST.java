// Name: J1-10 & J2-21
// Date: 02/23/202

interface BSTinterface
{
   public int size();
   public boolean contains(String obj);
   public void add(String obj);   //does not balance
   public void addBalanced(String obj);
   public void remove(String obj);
   public String min();
   public String max();
   public String display();
   public String toString();
}

public class BST implements BSTinterface
{
   /*  copy your BST code  here  */
   private TreeNode root;
   private int size;
   public String x;

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
      // if(root==null) {
         // root = new TreeNode(s);
         // size++;
      //} else {
      add(root, s);
      size++;
      //}
   }
   private TreeNode add(TreeNode t, String s) //recursive helper method
   {      
    if(t == null) {
         t = new TreeNode(s);
         return t;
      }else if((s.compareTo((String)t.getValue()) <= 0))
         t.setLeft(add(t.getLeft(), s));
      else if((s.compareTo((String)t.getValue()) > 0))
         t.setRight(add(t.getRight(), s));
      return t;
   }
   public static int height(TreeNode t)
   {
      if(t == null)
         return -1;
      int lh = height(t.getLeft());
      int rh = height(t.getRight());
      if(rh > lh)
         return rh +1;
      else
         return lh +1;
   }

   
   public String display()
   {
      return display(root, 0);
   }
   private String display(TreeNode t, int level) //recursive helper method
   {
      String toReturn = ""; //start with empty string
      if(t == null)
         return "";
      toReturn += display(t.getRight(), level + 1);
             
      //display(t.getRight(), level + 1); //recurse right
     
      for(int k = 0; k < level; k++)
         toReturn += "\t";
      toReturn += t.getValue() + "\n";
      toReturn += display(t.getLeft(), level + 1);
      return toReturn;
      //return t.getValue() + "";
     
   }
   
   public boolean contains( String obj)
   {
      return contains(root, obj);
   }
   private boolean contains(TreeNode t, String x) //recursive helper method
   {
      if(t == null)
         return false;
      Comparable cont = (Comparable) x;
      Comparable comp = (Comparable) t.getValue();
      if(x.compareTo((String)t.getValue()) < 0) //or cont.compareTo(comp) i found using the string is easier idk
         return contains(t.getLeft(), x);
      if(x.compareTo((String)t.getValue()) > 0) //no equal sign if its equal then duh it is true
         return contains(t.getRight(), x);
      return true;
   }
   
   public String min()
   {
      return min(root);
   }
   private String min(TreeNode t)  //use iteration
   {
      if(t == null)
         return null;
      while(t.getLeft() != null)
         t = t.getLeft();
      return t.getValue() + "";
   }
   
   public String max()
   {
      return max(root);
   }
   private String max(TreeNode t)  //recursive helper method
   {
      if(t == null)
         return null;
      if(t.getRight() == null)
         return t.getValue() + "";
         // t = t.getRight();          
      return max(t.getRight());
      ///return t.getValue() + "";
   }
   
   public String toString()
   {
      return toString(root);  
   }
   private String toString(TreeNode t)  //an in-order traversal.  Use recursion.
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += toString(t.getLeft());   //recurse left
      toReturn += t.getValue() + " "; //inorder visit
      toReturn += toString(t.getRight());  //recurse right
      return toReturn;
   }
   public void remove(String target)
   {
      root = remove(root, target);
      size--;
   }
   private TreeNode remove(TreeNode current, String target)
   {
      TreeNode parent = null;
      if (current == null)
         return current;
      if (target.compareTo(current.getValue().toString()) < 0) {
         if(current.getLeft() != null) {
            current.setLeft(remove(current.getLeft(), target));
         }
      } else if (target.compareTo(current.getValue().toString()) > 0) {
         if(current.getRight() != null)
            current.setRight(remove(current.getRight(), target));
      } else {
         if(target.compareTo(current.getValue().toString()) == 0) {
            if (current.getLeft() == null && current.getRight() == null) {
               return parent;
            }else if (current.getLeft() == null && current.getRight() != null) {
               parent = current.getRight();
               current = null;
               return parent;
            } else if (current.getRight() == null && current.getLeft() != null) {
               parent = current.getLeft();
               current = null;
               return parent;
            }
            current.setValue(max(current.getLeft())); //at first i put min of right which messed everything
            current.setLeft(remove(current.getLeft(), current.getValue().toString()));
         }
     
      }
      return current;
   }




   /* start the addBalanced methods */
   public void addBalanced(String value) {
      size++;
      root = add(root, value);
      balanceTree(null, root, true); // for an AVL tree. You may change this line.
   }

   private int balance(TreeNode x) {
      if (x == null)
         return 0;
      return height(x.getLeft()) - height(x.getRight());
   }

   private TreeNode balanceTree(TreeNode t, TreeNode x, boolean z) {
      if (balance(x) == 2) {
         if (balance(x.getLeft()) < 0)
            x.setLeft(rotateLeft(x.getLeft()));
         x = rotateRight(x);
      } else if (balance(x) == -2) {
         if (balance(x.getRight()) > 0)
            x.setRight(rotateRight(x.getRight()));
         x = rotateLeft(x);
      }
      return x;
   }

   private TreeNode rotateLeft(TreeNode x) {
      TreeNode newChild =null;
      TreeNode rand = root;
      root = root.getRight();
      rand.setRight(null);
      if (root.getLeft() != null) {
       newChild = root.getLeft();
      
      rand.setRight(newChild);
      }
      root.setLeft(rand);
      return root;
   }

   private TreeNode rotateRight(TreeNode x) {
       TreeNode newChild =null;
      TreeNode rand = root;
      root = root.getLeft();
      rand.setLeft(null);
      if (root.getRight() != null) {
       newChild = root.getRight();
      
      rand.setLeft(newChild);
      }
      root.setRight(rand);
      return root;   }
}
