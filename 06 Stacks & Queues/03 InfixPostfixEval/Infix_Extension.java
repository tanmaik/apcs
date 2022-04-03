// Name:  J1-10
// Date:  1/18/20
//uses PostfixEval

import java.util.*;
public class Infix_Extension
{
   public static final String LEFT  = "([{<";
   public static final String RIGHT = ")]}>";
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
      /*build your list of Infix expressions here  */
      ArrayList<String> infixExp = new ArrayList<>();
      infixExp.add("3 + 4 * 5");
      infixExp.add("3 * 4 + 5");
      infixExp.add("1.3 + 2.7 + -6 * 6");
      infixExp.add("( 33 + -43 ) * ( -55 + 65 )");
      infixExp.add("3 * 4 + 5 / 2 - 5");
      infixExp.add("8 + 1 * 2 - 9 / 3");
      infixExp.add("3 * ( 4 * 5 + 6 )");
      infixExp.add("3 + ( 4 - 5 - 6 * 2 )");
      infixExp.add("2 + 7 % 3");
      infixExp.add("( 2 + 7 ) % 3");
      
      for( String infix : infixExp )
      {
         String pf = infixToPostfix(infix);  //get the conversion to work first
         System.out.println(infix + "\t\t\t" + pf );  
         //System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + PostfixEval.eval(pf));  //PostfixEval must work!
      }
   }
   
   public static String infixToPostfix(String infix)
   {
       String opsxspaces = "+-*/%^!";
      String nonmath = "1 2 3 4 5 6 7 8 9 . ( ) " + opsxspaces;

      if (!ParenMatch.checkParen(infix)) {
          return "ERROR";
      }
      for (Character str : infix.toCharArray()) {
        if (!nonmath.contains(Character.toString(str))) {
            return "ERROR";
        }
      }
      List<String> nums = new ArrayList<String>(Arrays.asList(infix.split(" ")));
            /* enter your code here  */
      String result = "";
      Stack<String> ops = new Stack<>();
      String [] chars = infix.split(" ");
    
      for (String str : chars) {
         if (LEFT.contains(str)) {
            ops.push(str);
         } else if (RIGHT.contains(str)) {
            while (!LEFT.contains(ops.peek())) {
               result = result + ops.pop() + " ";
            }
            // result += ops.pop();
            ops.pop();
         }
         else if (!opsxspaces.contains(str)) {
           result = result + str + " "; 
         } else if (opsxspaces.contains(str)) {
             if (ops.empty()) {
                ops.push(str);
          } else {
               while (!ops.empty() && isLower(str.charAt(0), ops.peek().charAt(0))) {
                  result = result + ops.pop() + " ";
               }
               ops.push(str);
            }
         }
      }
      if (ops.empty()) {
         return result;
      } else {
         while (!ops.empty()) {
            result = result + ops.pop() + " ";
         }
         return result;
      }

   }
   
   //returns true if c1 has strictly lower precedence than c2
   public static boolean isLower(char c1, char c2)
   {
      if (c1 == '+') {
         if (c2 == '*' || c2 == '/' || c2 == '-' || c2 == '!' || c2 == '^') {
            return true;
         }
      }
      if (c1 == '-') {
         if (c2 == '*' || c2 == '/'|| c2 == '+'|| c2 == '!' || c2 == '^') {
            return true;
         }
      }
      if (c1 == '*') {
        if (c2 == '/'|| c2 == '!' || c2 == '^') {
           return true;
        }
     }
     if (c1 == '/') {
        if (c2 == '*'|| c2 == '!' || c2 == '^') {
           return true;
        }
     }
      return false;
   }
}


/*
 Infix  -->   Postfix    -->  Evaluate
( 3.0 + -1.0 ) ^ 3.0    3.0 -1.0 + 3.0 ^     8.0
2 ^ 3 + 3     2 3 ^ 3 +   11.0
3 * 2 ^ 3     3 2 3 ^ *   24.0
( 1 + 3 ) !      1 3 + !  24.0
1 + 3 !    1 3 ! +  7.0
1 * 3 !    1 3 ! *  6.0
3 ? 2      ERROR
3 @ 2      ERROR
( 3 + 2    ERROR
3 + 2 ]    ERROR
( 3 + 2 ]     ERROR

 */