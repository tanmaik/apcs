// Name: J1-10
// Date: 1/10/21

import java.util.*;

public class ParenMatch {
   public static final String LEFT = "([{<";
   public static final String RIGHT = ")]}>";

   public static void main(String[] args) {
      System.out.println("Parentheses Match");
      ArrayList<String> parenExp = new ArrayList<String>();
      /* enter test cases here */
      parenExp.add("5 + 7");
      parenExp.add("( 15 + -7 )");
      parenExp.add(") 5 + 7 (");
      parenExp.add("( ( 5.0 - 7.3 ) * 3.5 )");
      parenExp.add("< { 5 + 7 } * 3 >");
      parenExp.add("[ ( 5 + 7 ) * ] 3");
      parenExp.add("( 5 + 7 ) * 3");
      parenExp.add("5 + ( 7 * 3 )");
      parenExp.add("( ( 5 + 7 ) * 3");
      parenExp.add("[ ( 5 + 7 ] * 3 )");
      parenExp.add("[ ( 5 + 7 ) * 3 ] )");
      parenExp.add("( [ ( 5 + 7 ) * 3 ]");
      parenExp.add("( ( ( ) $ ) )");
      parenExp.add("( ) [ ]");

      for (String s : parenExp) {
         boolean good = checkParen(s);
         if (good)
            System.out.println(s + "\t good!");
         else
            System.out.println(s + "\t BAD");
      }
   }

   // returns the index of the left parentheses or -1 if is not
   public static int isLeftParen(String p) {
      return LEFT.indexOf(p);
   }

   // returns the index of the right parentheses or -1 if is not
   public static int isRightParen(String p) {
      return RIGHT.indexOf(p);
   }

   public static boolean checkParen(String exp) {
      int length = exp.length();

      Stack<String> check = new Stack<String>();
      for (int i = 0; i < length; i++) {

         if (isRightParen(Character.toString(exp.charAt(i))) != -1) {
            if (check.empty()) {
               return false;
            } else if (isLeftParen(check.peek()) == isRightParen((Character.toString(exp.charAt(i))))) {
               check.pop();
            } else {
               return false;
            }

         } else {
            if (isLeftParen(Character.toString(exp.charAt(i))) != -1) {
               check.push(Character.toString(exp.charAt(i)));
            }
         }
      }
      if (check.empty()) {
         return true;
      } else {
         return false;
      }
   }
}

/*****************************************

Parentheses Match
5 + 7		 good!
( 15 + -7 )		 good!
) 5 + 7 (		 BAD
( ( 5.0 - 7.3 ) * 3.5 )		 good!
< { 5 + 7 } * 3 >		 good!
[ ( 5 + 7 ) * ] 3		 good!
( 5 + 7 ) * 3		 good!
5 + ( 7 * 3 )		 good!
( ( 5 + 7 ) * 3		 BAD
[ ( 5 + 7 ] * 3 )		 BAD
[ ( 5 + 7 ) * 3 ] )		 BAD
( [ ( 5 + 7 ) * 3 ]		 BAD
( ( ( ) $ ) )		 good!
( ) [ ]		 good!
 
 *******************************************/
