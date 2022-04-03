// Name: J1-10
// Date: 3.23.21

import java.util.*;
import java.lang.Math;

public class Polynomial_Driver {
   public static void main(String[] args) {
      Polynomial poly = new Polynomial(); // 2x^3 + -4x + 2
      poly.makeTerm(1, -4);
      poly.makeTerm(3, 2);
      poly.makeTerm(0, 2);
      System.out.println("Map:  " + poly.getMap());
      System.out.println("String:  " + poly.toString());
      double evaluateAt = 2.0;
      System.out.println("Evaluated at " + evaluateAt + ": " + poly.evaluateAt(evaluateAt));

      System.out.println("-----------");
      Polynomial poly2 = new Polynomial(); // 2x^4 + x^2 + -5x + -3
      poly2.makeTerm(1, -5);
      poly2.makeTerm(4, 2);
      poly2.makeTerm(0, -3);
      poly2.makeTerm(2, 1);
      System.out.println("Map:  " + poly2.getMap());
      System.out.println("String:  " + poly2.toString());
      evaluateAt = -10.5;
      System.out.println("Evaluated at " + evaluateAt + ": " + poly2.evaluateAt(evaluateAt));

      System.out.println("-----------");
      System.out.println("Sum: " + poly.add(poly2));
      System.out.println("Product:  " + (poly.multiply(poly2)));

      /* Another case: (x+1)(x-1) --> x^2 + -1 */
      // System.out.println("===========================");
      // Polynomial poly3 = new Polynomial(); // (x+1)
      // poly3.makeTerm(1, 1);
      // poly3.makeTerm(0, 1);
      // System.out.println("Map: " + poly3.getMap());
      // System.out.println("String: " + poly3.toString());
      //
      // Polynomial poly4 = new Polynomial(); // (x-1)
      // poly4.makeTerm(1, 1);
      // poly4.makeTerm(0, -1);
      // System.out.println("Map: " + poly4.getMap());
      // System.out.println("String: " + poly4.toString());
      // System.out.println("Product: " + poly4.multiply(poly3)); // x^2 + -1
      //
      // /* testing the one-arg constructor */
      // System.out.println("===========================");
      // Polynomial poly5 = new Polynomial("2x^3 + 4x^2 + 6x^1 + -3");
      // System.out.println("Map: " + poly5.getMap());
      // System.out.println(poly5);

   }
}

interface PolynomialInterface {
   public void makeTerm(Integer exp, Integer coef);

   public Map<Integer, Integer> getMap();

   public double evaluateAt(double x);

   public Polynomial add(Polynomial other);

   public Polynomial multiply(Polynomial other);

   public String toString();
}

class Polynomial implements PolynomialInterface {
   private Map<Integer, Integer> p = new TreeMap<Integer, Integer>();

   public Polynomial(String str) {
      String[] terms = str.split(" ");
      for (int i = 0; i < terms.length; i += 2) {
         int endChar = terms[i].length();
         for (int t = 0; t < terms[i].length(); t++) {
            if (terms[i].charAt(t) == 'x') {
               endChar = t;
               t = terms[i].length() + 10;
            }
         }
         if (endChar != terms[i].length()) {
            makeTerm(Integer.valueOf(terms[i].substring(endChar + 2)), Integer.valueOf(terms[i].substring(0, endChar)));
         } else {
            makeTerm(Integer.valueOf(0), Integer.valueOf(terms[i]));
         }
      }
   }

   public Polynomial() {

   }

   public void makeTerm(Integer exp, Integer coef) {

      if (coef != 0) {
         if (p.containsKey(exp)) {
            int added = 0;
            added = p.get(exp);
            coef += added;
         }
         p.put(exp, coef);
      }
   }

   public Map<Integer, Integer> getMap() {
      return p;
   }

   public double evaluateAt(double x) {
      double ans = 0.00;
      for (int exp : p.keySet()) {
         double e = Math.pow(x, exp);
         ans += e * p.get(exp);
      }
      return ans;
   }

   public Polynomial add(Polynomial other) {
      Map<Integer, Integer> a = other.getMap();
      Polynomial sum = new Polynomial();
      for (Integer i : p.keySet()) {
         sum.makeTerm(i, p.get(i));
      }
      for (Integer i : a.keySet()) {
         sum.makeTerm(i, a.get(i));
      }
      return sum;

   }

   public Polynomial multiply(Polynomial other) {
      Polynomial product = new Polynomial();
      Map<Integer, Integer> a = other.getMap();
      for (Integer i : p.keySet()) {
         for (Integer q : a.keySet()) {
            product.makeTerm(i + q, p.get(i) * a.get(q));
            System.out.println((product.getMap()));
         }
      }
      return product;
   }

   public String toString() {
      String s = "";
      for (Integer i : p.keySet()) {
         if (p.get(i) == 1) {
            if (i == 1) {
               s = "x" + s;
            } else if (i == 0) {
               s = "1" + s;
            } else {
               s = "x^" + Integer.toString(i) + s;
            }
         } else if (p.get(i) == -1) {
            if (i == 1) {
               s = "-x" + s;
            } else if (i == 0) {
               s = "-1" + s;
            } else {
               s = "-x^" + Integer.toString(i) + s;
            }
         } else {
            if (i == 1) {
               s = p.get(i) + "x" + s;
            } else if (i == 0) {
               s = p.get(i) + s;
            } else {
               s = p.get(i) + "x^" + Integer.toString(i) + s;
            }
         }
         s = " + " + s;
      }
      return s.substring(3);
   }
}

/***************************************
 * ----jGRASP exec: java Polynomial_teacher Map: {0=2, 1=-4, 3=2} String: 2x^3 +
 * -4x + 2 Evaluated at 2.0: 10.0 ----------- Map: {0=-3, 1=-5, 2=1, 4=2}
 * String: 2x^4 + x^2 + -5x + -3 Evaluated at -10.5: 24469.875 ----------- Sum:
 * 2x^4 + 2x^3 + x^2 + -9x + -1 Product: 4x^7 + -6x^5 + -6x^4 + -10x^3 + 22x^2 +
 * 2x + -6
 * 
 * ----jGRASP: operation complete.
 ********************************************/