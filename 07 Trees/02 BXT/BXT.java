// Name: J1-10 
// Date: 2/15/20
/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings. 
 */
 
import java.util.*;

public class BXT
{
   private TreeNode root;   
   
   public BXT()
   {
      root = null;
   }
  public TreeNode getRoot()   //for Grade-It
  {
      return root;
  }
    
   public void buildTree(String str)
   {
     	String[] s = str.split(" ");
      Stack<TreeNode> stk = new Stack<>();
      for (String item : s) {
         if(isOperator(item)) {
            TreeNode b = (TreeNode) stk.pop();
            TreeNode a = (TreeNode) stk.pop();
            stk.push(new TreeNode(item, a, b));
         } else {
            stk.push(new TreeNode(item));
         }
      }
      TreeNode temp = stk.pop();
      root = temp;
   }
   
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode t)  //recursive
   {
      String tValue = (String) t.getValue();
    
      if(isOperator(tValue)){
   
		return computeTerm(tValue, evaluateNode(t.getLeft()), evaluateNode(t.getRight()));
      } else {
         String str = (String) t.getValue();
         return Double.parseDouble(str);
      } 
   }
   
   private double computeTerm(String s, double a, double b)
   {
      if (s.equals("+")) {
         return a+b;
      } else if (s.equals("-")) {
         return a-b;
      } else if (s.equals("*")) {
         return a*b;
      } else if (s.equals("/")) {
         return a/b;
      } else if (s.equals("^")) {
         return Math.pow(a, b);
      } 
      else {
         return 0.0;
      }
   }
   
   private boolean isOperator(String s)
   {
      String ops = "+-*/!^";
      if (ops.contains(s)) {
         return true;
      } 
         return false;
   }
   
   public String display()
   {
      return display(root, 0);
   }
   
   private String display(TreeNode t, int level)
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
    
   public String inorderTraverse()
   {
      return inorderTraverse(root);
   }
   
   private String inorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if (t == null) {
         return "";
      }
      toReturn += inorderTraverse(t.getLeft());
      toReturn += t.getValue() + " ";
      toReturn += inorderTraverse(t.getRight());
      return toReturn;
   }
   
   public String preorderTraverse()
   {
      return preorderTraverse(root);
   }
   
   private String preorderTraverse(TreeNode root)
   {
      String toReturn = "";
      if (root == null) {
         return "";
      }
      toReturn += root.getValue() + " ";
      toReturn += preorderTraverse(root.getLeft());
      toReturn += preorderTraverse(root.getRight());
      return toReturn;

   }
   
  /* extension */
   // public String inorderTraverseWithParentheses()
   // {
      // return inorderTraverseWithParentheses(root);
   // }
//    
   // private String inorderTraverseWithParentheses(TreeNode t)
   // {
      // return "";
   // }
}