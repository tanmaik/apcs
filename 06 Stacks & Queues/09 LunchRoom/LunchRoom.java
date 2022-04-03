// Name: J1-10
// Date: 1/31/20

import java.util.*;
import java.io.*;

public class LunchRoom {
   public static final int TIME = 1080; // 18 hrs * 60 min
   public static double CHANCE_OF_CUSTOMER = .2;
   public static PrintWriter outfile = null; // file to write your results to

   public static int timeToOrderAndBeServed() {
      return (int) (Math.random() * 6 + 2);
   }

   public static void displayTimeAndQueue(Queue<Customer> q, int min) {
      System.out.println(min + ": " + q);
      outfile.println(min + ": " + q);
   }

   public static int getRandGrade() {
      double rand = Math.random();
      if (rand < 0.25) {
         return 9;
      } else if (rand >= 0.25 && rand < 0.5) {
         return 10;
      } else if (rand >= 0.5 && rand < 0.75) {
         return 11;
      } else {
         return 12;
      }
   }

   static class Customer implements Comparable<Customer> {
      /*****************
       * Complete the Customer class. Fields, constructor, accessor methods,
       * compareTo, toString.
       ******************/
      private int waitTime = timeToOrderAndBeServed();
      private int grade;
      private int minuteArrived;

      public Customer(int grade, int minuteArrived) {
         this.grade = grade;
         this.setMinuteArrived(minuteArrived);
      }

      public int getMinuteArrived() {
         return minuteArrived;
      }

      public void setMinuteArrived(int minuteArrived) {
         this.minuteArrived = minuteArrived;
      }

      public int getWaitTime() {
         return waitTime;
      }

      public void setWaitTime(int waitTime) {
         this.waitTime = waitTime;
      }

      public int getGrade() {
         return grade;
      }

      public void setGrade(int grade) {
         this.grade = grade;
      }

      public int compareTo(Customer c) {
         if (this.grade == c.getGrade()) {
            if (minuteArrived > c.getMinuteArrived()) {
               return -1;
            } else {
               return 1;
            }
         } else if (this.grade > c.getGrade()) {
            return -1;
         } else {
            return 1;
         }

      }
      public String getGradeName() {
         String gradeName = "";
         if (grade == 9) {
            gradeName = "Fr";
         }
         if (grade == 10) {
            gradeName = "So";
         }
         if (grade == 11) {
            gradeName = "Ju";
         } else {
            gradeName = "Se";
         }
         return gradeName;
      }

      public String toString() {
         String gradeName = "";
         if (grade == 9) {
            gradeName = "Fr";
         }
         if (grade == 10) {
            gradeName = "So";
         }
         if (grade == 11) {
            gradeName = "Ju";
         } else if (grade == 12) {
            gradeName = "Se";
         }
         return minuteArrived + ":" + gradeName;
      }

   }

   public static void main(String[] args) {
      // set up file
      try {
         outfile = new PrintWriter(new FileWriter("LunchRoom Seniors first.txt"));
      } catch (IOException e) {
         System.out.println("File not created");
         System.exit(0);
      }
      LunchRoom();
      outfile.close();
   }

   public static void LunchRoom() {
      /***********************
       * 
       * Enter your code for the simulation here.
       * 
       *************************/

      String[] classes = new String[] {"Senior", "Junior", "Sophomore", "Freshman"};
      int[] served = new int[] {0, 0, 0, 0};
      int[] lWait = new int[] {0, 0, 0, 0};
      double[] aWait = new double[] {0, 0, 0, 0};

      PriorityQueue<Customer> lr = new PriorityQueue<Customer>();
      Stack<Customer> current = new Stack<Customer>();
      Customer temp;
      Customer temp1;
      
      for (int i = 0; i <= 1000; i++) {
         if (Math.random() < CHANCE_OF_CUSTOMER) {
            temp1 = new Customer(getRandGrade(), i);
            lr.add(temp1);
            System.out.println("new customer " + i + ":" + temp1.getGradeName());
            outfile.println("new customer " + i + ":" + temp1.getGradeName());
         }

         if (current.empty() || current.peek().getWaitTime() == 1) {
            if (!current.empty()) {
               temp = current.pop();
               int tWait = i - temp.getMinuteArrived();
               System.out.println("Customer# " + temp + " leaves and their total wait time is " + tWait);
               outfile.println("Customer# " + temp + " leaves and their total wait time is " + tWait);
               if (temp.getGrade() == 12) {
                  served[0]++;
                  if (tWait > lWait[0]) {
                     lWait[0] = tWait;
                  }
                  aWait[0] += tWait;
               }
               else if (temp.getGrade() == 11) {
                  served[1]++;
                  if (tWait > lWait[1]) {
                     lWait[1] = tWait;
                  }
                  aWait[1] += tWait;
               }
               else if (temp.getGrade() == 10) {
                  served[2]++;
                  if (tWait > lWait[2]) {
                     lWait[2] = tWait;
                  }
                  aWait[2] += tWait;
                  
               } else {
                  served[3]++;
                  if (tWait > lWait[3]) {
                     lWait[3] = tWait;
                  }
                  aWait[3] += tWait;
               }
            }
            if (!lr.isEmpty()) {
               current.push(lr.poll());
            }
         } else {
            current.peek().setWaitTime(current.peek().getWaitTime() - 1);
         }
         
         displayTimeAndQueue(lr, i);
         
         if (!current.empty()) {
            System.out.println("\tServiceArea#1 " + current.peek() + ":" + current.peek().getWaitTime());
            outfile.println("\tServiceArea#1 " + current.peek() + ":" + current.peek().getWaitTime());
         } else {
            System.out.println("\tServiceArea#1");
            outfile.println("\tServiceArea#1");
         }
         System.out.println();
         outfile.println();
         
      }

      /* report the results */
      System.out.println("Customer\t\tTotal\t\tLongest\t\tAverage Wait");
      outfile.println("Customer\t\tTotal\t\tLongest\t\tAverage Wait");
      for (int x = 0; x < 4; x++) {
         System.out.println(
               classes[x] + "\t\t\t" + served[x] + "\t\t\t" + lWait[x] + "\t\t\t" + ((double) aWait[x] / served[x]));
         outfile.println(
               classes[x] + "\t\t\t" + served[x] + "\t\t\t" + lWait[x] + "\t\t\t" + ((double) aWait[x] / served[x]));
      }
      
   }
}
