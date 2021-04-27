// Name: J1-10
// Date: 4/15/21

import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 *              Graph1 WarshallDriver,
 *          and Graph2 FloydDriver
 */

interface AdjacencyMatrix {
   void addEdge(int source, int target);

   void removeEdge(int source, int target);

   boolean isEdge(int from, int to);

   String toString(); // returns the grid as a String

   int edgeCount();

   List<Integer> getNeighbors(int source);
   // public List<String> getReachables(String from); //Warshall extension
}

interface Warshall // User-friendly functionality
{
   boolean isEdge(String from, String to); //

   Map<String, Integer> getVertices(); //

   void readNames(String fileName) throws FileNotFoundException; //

   void readGrid(String fileName) throws FileNotFoundException; //

   void displayVertices(); //

   void allPairsReachability(); // Warshall's Algorithm //
}

interface Floyd {
   int getCost(int from, int to);

   int getCost(String from, String to);

   void allPairsWeighted();
}

public class AdjMat implements AdjacencyMatrix// ,Warshall//,Floyd
{
   private int[][] grid = null; // adjacency matrix representation
   private Map<String, Integer> vertices = null;
   // name maps to index (for Warshall & Floyd)
   /* for Warshall's Extension */ ArrayList<String> nameList = null; // reverses the map, index-->name

   /* enter your code here */
   public AdjMat(int size) {
      grid = new int[size][size];
      vertices = new TreeMap<String, Integer>();
   }
   
   public int getCost(String from, String to) {
      return getCost(vertices.get(from), vertices.get(to));
   }

   public int getCost(int from, int to) {
      return grid[from][to];
   }

   public void allPairsWeighted() {
      for (String from : vertices.keySet()) {
         for (String mid : vertices.keySet()) {
            for (String to : vertices.keySet()) {
               /*if (grid[vertices.get(mid)][vertices.get(from)] + grid[vertices.get(from)][vertices.get(to)] < grid[vertices.get(mid)][vertices.get(to)] && grid[vertices.get(mid)][vertices.get(to)] != 0 && grid[vertices.get(from)][vertices.get(to)] != 0 && grid[vertices.get(mid)][vertices.get(to)] != 9999) {
                  grid[vertices.get(mid)][vertices.get(to)] = grid[vertices.get(mid)][vertices.get(from)] + grid[vertices.get(from)][vertices.get(to)];
               }*/
               if (grid[vertices.get(mid)][vertices.get(from)] + grid[vertices.get(from)][vertices.get(to)] < grid[vertices.get(mid)][vertices.get(to)]) {
                  grid[vertices.get(mid)][vertices.get(to)] = grid[vertices.get(mid)][vertices.get(from)] + grid[vertices.get(from)][vertices.get(to)];
               }
            }
         }
      }
   }

   public void readNames(String filename) throws FileNotFoundException {
      File file = new File(filename);
      Scanner scan = new Scanner(file);
      scan.nextLine();
      int count = 0;
      while (scan.hasNextLine()) {
         vertices.put(scan.nextLine(), count);
         count++;
      }
      scan.close();
   }

   public void readGrid(String filename) throws FileNotFoundException {
      File file = new File(filename);
      Scanner scan = new Scanner(file);
      scan.nextLine();
      for (int i = 0; i < grid.length; i++) {
         String[] line = scan.nextLine().split(" ");
         for (int j = 0; j < grid[i].length; j++) {
            grid[i][j] = Integer.parseInt(line[j]);
         }
      }
      scan.close();
   }

   public Map<String, Integer> getVertices() {
      return vertices;
   }

   public void addEdge(int source, int target) {
      grid[source][target] = 1;
   }

   public void removeEdge(int source, int target) {
      grid[source][target] = 0;
   }

   public void displayVertices() {
      int count = 0;
      Set<String> keys = vertices.keySet();
      for (String city : keys) {
         System.out.println(count + "-" + city);
         count ++;
      }
   }

   public boolean isEdge(int from, int to) {
      if (grid[from][to] != 9999 && grid[from][to] != 0) {
         return true;
      }
      return false;
   }

   public boolean isEdge(String from, String to) {
      int fromIndex = vertices.get(from);
      int toIndex = vertices.get(to);
      return isEdge(fromIndex, toIndex);
   }

   public String toString() {
      String toReturn = "";
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            toReturn += grid[i][j];
         }
         toReturn += "\n";
      }
      return toReturn;
   }

   public void allPairsReachability() {
      for (String from : vertices.keySet()) {
         for (String mid : vertices.keySet()) {
            for (String to : vertices.keySet()) {
               if (isEdge(from, mid) && isEdge(mid, to)) {
                  if (!isEdge(from, to))
                     addEdge(vertices.get(from), vertices.get(to));
               }
            }
         }
      }
   }

   public int edgeCount() {
      int count = 0;
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            if (isEdge(i, j)) {
               count++;
            }
         }
      }
      return count;
   }

   public List<Integer> getNeighbors(int source) {
      List<Integer> toReturn = new ArrayList<Integer>();
      for (int i = 0; i < grid[source].length; i++) {
         if (isEdge(source, i)) {
            toReturn.add(i);
         }
      }
      return toReturn;
   }
}
/*
 * each row of a matrix is a different array to iterate through a matrix mat =
 * matrix for (int i = 0; i < mat.length; i ++) {
 * 
 * }
 */