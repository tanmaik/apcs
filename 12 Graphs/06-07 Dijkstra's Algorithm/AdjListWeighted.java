// Name:   
// Date:

import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs6: Dijkstra
 *              Graphs7: Dijkstra with Cities
 */

class Edge {
   public final wVertex target; // if it's public, you don't need accessor methods
   public final double weight; // if it's public, you don't need accessor methods

   public Edge(wVertex argTarget, double argWeight) {
      target = argTarget;
      weight = argWeight;
   }
}

interface wVertexInterface {
   String getName();

   double getMinDistance();

   void setMinDistance(double m);

   // wVertex getPrevious(); //for Dijkstra 7
   // void setPrevious(wVertex v); //for Dijkstra 7
   ArrayList<Edge> getAdjacencies();

   void addEdge(wVertex v, double weight);

   int compareTo(wVertex other);
}

class wVertex implements Comparable<wVertex>, wVertexInterface {
   private final String name;
   private ArrayList<Edge> adjacencies;
   private double minDistance = Double.POSITIVE_INFINITY;
   // private wVertex previous; //for building the actual path in Dijkstra 7

   /* enter your code for this class here */
   public wVertex(String argName) {
      this.name = argName;
      adjacencies = new ArrayList<Edge>();
   }

   public String getName() {
      return name;
   }

   public double getMinDistance() {
      return minDistance;
   }

   public void setMinDistance(double newMinDistance) {
      minDistance = newMinDistance;
   }

   public ArrayList<Edge> getAdjacencies() {
      return adjacencies;
   }

   public void addEdge(wVertex v, double weight) {
      adjacencies.add(new Edge(v, weight));
   }

   public int compareTo(wVertex other) {
      return (int) (minDistance - other.minDistance);
   }
}

interface AdjListWeightedInterface {
   List<wVertex> getVertices();

   Map<String, Integer> getNameToIndex();

   wVertex getVertex(int i);

   wVertex getVertex(String vertexName);

   void addVertex(String v);

   void addEdge(String source, String target, double weight);

   void minimumWeightPath(String vertexName); // Dijkstra's
}

/*
 * Interface for Graphs 7: Dijkstra with Cities
 */

interface AdjListWeightedInterfaceWithCities {
   List<String> getShortestPathTo(wVertex v);

   AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException;
}

public class AdjListWeighted implements AdjListWeightedInterface // ,AdjListWeightedInterfaceWithCities
{
   private List<wVertex> vertices = new ArrayList<wVertex>();
   private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();
   // the constructor is a no-arg constructor

   /* enter your code for Graphs 6 */
   public List<wVertex> getVertices() {
      return vertices;
   }

   public wVertex getVertex(int i) {
      return vertices.get(i);
   }

   public Map<String, Integer> getNameToIndex() {
      return nameToIndex;
   }

   public wVertex getVertex(String vertexName) {
      return vertices.get(nameToIndex.get(vertexName));
   }

   public void addVertex(String v) {
      vertices.add(new wVertex(v));
      nameToIndex.put(v, vertices.size() - 1);
   }

   public void addEdge(String source, String target, double weight) {
      boolean sourceExists = false;
      boolean targetExists = false;
      for (wVertex v : vertices) {
         if (v.getName().equals(source)) {
            sourceExists = true;
         }
         if (v.getName().equals(target)) {
            targetExists = true;
         }
      }
      if (!sourceExists) {
         addVertex(source);
      }
      if (!targetExists) {
         addVertex(target);
      }
      vertices.get(nameToIndex.get(source)).addEdge(vertices.get(nameToIndex.get(target)), weight);
   }

   public void minimumWeightPath(String vertexName) {
      minimumWeightPath(vertices.get(nameToIndex.get(vertexName)));
   }

   public void minimumWeightPath(wVertex source) {
      source.setMinDistance(0);
      PriorityQueue<wVertex> pq = new PriorityQueue<>();
      pq.add(source);
      while (!pq.isEmpty()) {
         wVertex current = pq.remove();
         for (Edge neighbor : current.getAdjacencies()) {
            if (neighbor.weight + current.getMinDistance() < neighbor.target.getMinDistance()) {
               pq.remove(neighbor.target);
               neighbor.target.setMinDistance(neighbor.weight + current.getMinDistance());
               pq.add(neighbor.target);
            }
         }
      }
   }
   /* enter your code for two new methods in Graphs 7 */

}
