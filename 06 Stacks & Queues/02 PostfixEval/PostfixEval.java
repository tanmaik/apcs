// Name: J1-10
// Date: 1/10/21

import java.util.*;
import java.lang.Math;

public class PostfixEval
{
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postfixExp = new ArrayList<String>();
      /*  build your list of expressions here  */
      postfixExp.add("3 4 5 * +");
      postfixExp.add("3 4 * 5 +");
      postfixExp.add("10 20 + -6 6 * +");
      postfixExp.add("3 4 + 5 6 + *");
      postfixExp.add("3 4 5 + * 2 - 5 /");
      postfixExp.add("8 1 2 * + 9 3 / -");
      postfixExp.add("2 3 ^");
      postfixExp.add("20 3 %");
      postfixExp.add("21 3 %");
      postfixExp.add("22 3 %");
      postfixExp.add("23 3 %");
      postfixExp.add("5 !");
      postfixExp.add("1 1 1 1 1 + + + + !");
   
      for( String pf : postfixExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   
   public static double eval(String pf)
   {
      List<String> postfixParts = new ArrayList<String>(Arrays.asList(pf.split(" ")));
      /*  enter your code here  */
      Stack<Double> c = new Stack<Double>();
      for (String str : postfixParts) {
         try {
            c.push(Double.parseDouble(str));
         }
         catch (Exception e)  {
            if (str.equals("!")) {
               double fact = c.peek();
               for (double i = c.pop() - 1; i >= 1; i--) {
                  fact = i*fact;
               }
               c.push(fact);
            } else {
               double b = c.pop();
               double a = c.pop();
               c.push(eval(a, b, str));
            }
         }
      }
      return c.pop();
   }
   
   public static double eval(double a, double b, String ch)
   {
      if (isOperator(ch)) {
         if (ch.equals(Character.toString(operators.charAt(0)))) {
            return a + b;
         }
         else if (ch.equals(Character.toString(operators.charAt(2)))) {
            return a - b;
         }
         else if (ch.equals(Character.toString(operators.charAt(4)))) {
            return a * b;
         }
         else if (ch.equals(Character.toString(operators.charAt(6)))) {
            return a / b;
         }
         else if (ch.equals(Character.toString(operators.charAt(8)))) {
            return a % b;
         }
         else if (ch.equals(Character.toString(operators.charAt(10)))) {
            return Math.pow(a, b);
         }
      }
      return 0.0;
   }
   
   public static boolean isOperator(String op)
   {
      if (operators.contains(op)) {
         return true;
      }
      return false;
   }
}

/**********************************************
Postfix  -->  Evaluate
 3 4 5 * +		23
 3 4 * 5 +		17
 10 20 + -6 6 * +		-6
 3 4 + 5 6 + *		77
 3 4 5 + * 2 - 5 /		5
 8 1 2 * + 9 3 / -		7
 2 3 ^		8
 20 3 %		2
 21 3 %		0
 22 3 %		1
 23 3 %		2
 5 !		120
 1 1 1 1 1 + + + + !		120
 
 
 *************************************/