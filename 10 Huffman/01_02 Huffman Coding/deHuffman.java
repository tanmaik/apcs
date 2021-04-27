// Name: J1-10             
//Date: 4/12/20
import java.util.*;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   public static TreeNode huffmanTree(Scanner huffLines)
   { 
     TreeNode root = new TreeNode("", new TreeNode(""), new TreeNode(""));
     TreeNode pointer = root;
     while (huffLines.hasNextLine()) {
         String code = huffLines.nextLine();
         String letter = code.substring(0, 1);
         String pos = code.substring(1);
         for (int i = 0; i < pos.length(); i ++) {
            if (pos.charAt(i) == '0') {
               if (pointer.getLeft() == null) {
                  pointer.setLeft(new TreeNode("", new TreeNode(""), new TreeNode("")));
               }
               pointer = pointer.getLeft();
            } else {
               if (pointer.getRight() == null) {
                  pointer.setRight(new TreeNode("",  new TreeNode(""),  new TreeNode("")));
               }
               pointer = pointer.getRight();
            }
         }
         pointer.setValue(letter);
         pointer = root;
     }
     return root;
   }
   public static String dehuff(String text, TreeNode root)
   {
      String mes = "";
      TreeNode pointer = root;
      for (int i = 0; i < text.length(); i ++) {
         char code = text.charAt(i);
         if (code == '0') {
            pointer = pointer.getLeft();
            if (!pointer.getValue().equals("")) {
               mes += pointer.getValue();
               pointer = root;
            }
         } else if (code == '1') {
            pointer = pointer.getRight();
            if (!pointer.getValue().equals("")) {
               mes += pointer.getValue();
               pointer = root;
            }
         }
      
      }
      return mes;
   }
}

 /* TreeNode class for the AP Exams */
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
