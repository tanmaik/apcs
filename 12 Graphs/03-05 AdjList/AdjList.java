// Name: J1-10  
// Date: 4/22/21

import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs3: EdgeList,
 *              Graphs4: DFS-BFS
 *          and Graphs5: EdgeListCities
 */

/* Graphs 3: EdgeList 
 */
interface VertexInterface {
   String toString(); // Don't use commas in the list. Example: "C [C D]"

   String getName();

   ArrayList<Vertex> getAdjacencies();

   void addAdjacent(Vertex v);
}

class Vertex implements VertexInterface {
   private final String name;
   private ArrayList<Vertex> adjacencies;

   /* enter your code here */
   public Vertex(String name) {
      this.name = name;
      adjacencies = new ArrayList<Vertex>();
   }

   public String toString() {
      String adj = "[";
      String toReturn = this.name + " ";
      int count = 0;
      for (Vertex v : adjacencies) {
         adj += v.getName() + " ";
         count++;
      }
      if (count != 0)
         adj = adj.substring(0, adj.length() - 1);
      adj += "]";
      return toReturn + adj;
   }

   public String getName() {
      return this.name;
   }

   public ArrayList<Vertex> getAdjacencies() {
      return adjacencies;
   }

   public void addAdjacent(Vertex v) {
      adjacencies.add(v);
   }
}

interface AdjListInterface {
   List<Vertex> getVertices();

   Vertex getVertex(int i);

   Vertex getVertex(String vertexName);

   Map<String, Integer> getVertexMap();

   void addVertex(String v);

   void addEdge(String source, String target);

   String toString(); // returns all vertices with their edges (omit commas)
}

/*
 * Graphs 4: DFS and BFS
 */
interface DFS_BFS {
   List<Vertex> depthFirstSearch(String name);

   List<Vertex> depthFirstSearch(Vertex v);

   List<Vertex> breadthFirstSearch(String name);

   List<Vertex> breadthFirstSearch(Vertex v);

   /* three extra credit methods */
   List<Vertex> depthFirstRecur(String name);

   List<Vertex> depthFirstRecur(Vertex v);

   void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable);
}

/*
 * Graphs 5: Edgelist with Cities
 */
interface EdgeListWithCities {
   void graphFromEdgeListData(String fileName) throws FileNotFoundException;

   int edgeCount();

   int vertexCount(); // count the vertices in the object

   boolean isReachable(String source, String target);

   boolean isConnected();
}

public class AdjList implements AdjListInterface// , DFS_BFS , EdgeListWithCities
{
   private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
   private Map<String, Integer> nameToIndex = new TreeMap<String, Integer>();
   private int last = vertices.size() - 1;
   
   public List<Vertex> getVertices() {
      return vertices;
   }

   public Vertex getVertex(int i) {
      return vertices.get(i);
   }

   public Vertex getVertex(String vertexName) {
      return getVertex(nameToIndex.get(vertexName));
   }

   public Map<String, Integer> getVertexMap() {
      return nameToIndex;
   }

   public void addVertex(String v) {
      if (nameToIndex.get(v) == null) {
         vertices.add(new Vertex(v));
         nameToIndex.put(v, last + 1);
         last++;
      }
      return;
   }

   public void addEdge(String source, String target) {
      if (nameToIndex.get(source) == null) {
         addVertex(source);
      }
      if (nameToIndex.get(target) == null) {
         addVertex(target);
      }
      getVertex(source).addAdjacent(getVertex(target));
   }

   public String toString() {
      String toReturn = "";
      int s = vertices.size();
      for (Vertex v : vertices) {
         if (vertices.indexOf(v) == (s - 1))
            toReturn += "" + v;
         else
            toReturn += "" + v + "\n";
      }
      return toReturn;
   }

   public List<Vertex> depthFirstSearch(String name) {
      Vertex v = vertices.get(nameToIndex.get(name));
      return depthFirstSearch(v);
   }

   public List<Vertex> depthFirstSearch(Vertex v) {
      List<Vertex> reachables = new ArrayList<Vertex>();
      Stack<Vertex> holder = new Stack<Vertex>();
      holder.push(v);
      while (!holder.empty()) {  
         Vertex temp = holder.pop();
         if (!reachables.contains(temp)) {
            reachables.add(temp);
            for (Vertex edge : temp.getAdjacencies()) {
               if (!holder.contains(edge)) {
                  holder.push(edge);
               }
            }
         }
      }
      return reachables;
   }

   public List<Vertex> breadthFirstSearch(String name) {
      Vertex v = vertices.get(nameToIndex.get(name));
      return breadthFirstSearch(v);
   }

   public List<Vertex> breadthFirstSearch(Vertex v) {
      List<Vertex> reachables = new ArrayList<Vertex>();
      Queue<Vertex> holder = new LinkedList<Vertex>();
      holder.add(v);
      while (!holder.isEmpty()) {  
         Vertex temp = holder.remove();
         if (!reachables.contains(temp)) {
            reachables.add(temp);
            for (Vertex edge : temp.getAdjacencies()) {
               if (!holder.contains(edge)) {
                  holder.add(edge);
               }
            }
         }
      }
      return reachables;
   }

   public void graphFromEdgeListData(String fileName) throws FileNotFoundException {
      File f = new File(fileName);
      Scanner scan = new Scanner(f);
      while (scan.hasNextLine()) {
         String[] vert = scan.nextLine().split(" ");
         if (!nameToIndex.containsKey(vert[0])) {
            addVertex(vert[0]);
         }
         if (!nameToIndex.containsKey(vert[1])) {
            addVertex(vert[1]);
         }
         addEdge(vert[0], vert[1]);
      }
      scan.close();
   }

   public int edgeCount() {
      int count = 0;
      for (Vertex v : vertices) {
         for (Vertex vert : v.getAdjacencies()) {
            count ++;
         }
      }
      return count;
   }

   public int vertexCount() {
      int count = 0;
      for (String key : nameToIndex.keySet()) {
         count ++;
      }
      return count;
   }

   public boolean isReachable(String source, String target) {
      List<Vertex> reachables = depthFirstSearch(source);
      for (Vertex v : reachables) {
         if (v.getName().equals(target)) {
            return true;
         }
      }
      return false;
   }

   public boolean isConnected() {
      Set<String> verts = nameToIndex.keySet();
      for (Vertex v : vertices) {
         for (Vertex vert : v.getAdjacencies()) {
            if (verts.contains(vert.getName())) {
               verts.remove(vert.getName());
            }
         }
      }
      if (verts.isEmpty()) {
         return true;
      }
      return false;
   }


   /* three extra credit methods, recursive version */
   List<Vertex> depthFirstRecur(String name) {
      return null;
   }

   List<Vertex> depthFirstRecur(Vertex v) {
      return null;
   }

   void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable) {

   }

   
}
