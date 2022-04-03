//Name: J1-10      
//Date: 1/19/20
import java.util.*;


public class AssemblyLine_Driver {
   static int NDISKS = 50;
   static int MAXRADIUS = 100;

   public static void main(String[] args) {
      AssemblyLine a = new AssemblyLine(NDISKS, MAXRADIUS);
      a.showInput();
      a.process();
      a.showOutput();
   }
}

class AssemblyLine {
   private Queue<Disk> assemblyLineIn = new LinkedList<Disk>();
   private Queue<Pyramid> assemblyLineOut = new LinkedList<Pyramid>();
   private Pyramid robotArm = new Pyramid();

   /**
    * initializes this object so the assemblyLineIn contains nDisks with random
    * radii; assemblyLineOut is initialized to * an empty Queue; robotArm is
    * initialized to an empty Pyramid.
    **/
   // Part A
   public AssemblyLine (int nDisks, int maxRadius) {
      int newRadius = 0;
      for (int i = 0; i < nDisks; i ++) {
        Random rand = new Random();
        Disk d = new Disk(rand.nextInt(maxRadius - 1) + 1);
        assemblyLineIn.add(d);
      }
   }

   /**
    * "flips over" the pyramid in the robotArm and adds it to the assemblyLineOut
    * queue. Precondition: robotArm is not empty and holds an inverted pyramid of
    * disks
    **/
   // Part B
   private void unloadRobot() {
      Pyramid p = new Pyramid();
      while (!robotArm.empty()) {
         p.push(robotArm.pop());
      }
      assemblyLineOut.add(p);
   }

   /**
    * processes all disks from assemblyLineIn; a disk is processed as follows: if
    * robotArm is not empty and the next disk does not fit on top of robotArm
    * (which must be an inverted pyramid) then robotArm is unloaded first; the disk
    * from assemblyLineIn is added to robotArm; when all the disks have been
    * retrieved from assemblyLineIn, robotArm is unloaded. Precondition: robotArm
    * is empty; assemblyLineOut is empty
    **/
   // Part C
   public void process() {
      while (!assemblyLineIn.isEmpty()) {
         Disk d = assemblyLineIn.remove();
         if (robotArm.empty()) {
            robotArm.push(d);
         } else if (d.compareTo(robotArm.peek()) == 0) {
            unloadRobot();
            robotArm.push(d);
         } else  {
            robotArm.push(d);
         }
         
      }
   
      unloadRobot();
   }

   public void showInput() {
      System.out.println(assemblyLineIn);
   }

   public void showOutput() {
      System.out.println(assemblyLineOut);
   }
}

// Disk is standard and straightforward.
class Disk implements Comparable<Disk> {

   private int radius;
   
   public Disk(int radius) {
      this.radius = radius;
   }
   public int compareTo(Disk o) {
      if (o.getRadius() >= radius) {
         return 0;
      } else {
         return 1;
      }
     
   }
   public int getRadius() {
      return radius;
   }
   public void setRadius(int radius) {
      this.radius = radius;
   }
   public String toString() {
      return radius + "";
   }

}

// Pyramid is sly.
class Pyramid extends Stack<Disk> {

}