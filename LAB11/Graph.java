/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author TGM
 */
public class Graph {
  
    public void addEdge(int[][] matrix,int v1,int v2){
       matrix[v1][v2]=1;
       matrix[v2][v1]=1;
       
       
    }
    public void displayMatrix(int[][] matrix) {
        for (int[] i: matrix) {
            for (int j: i) {
                System.out.print(j);
            }
            System.out.println();
        }
    }
public void ShortestPath(int matrix[][],int start,int destination){
    
    int n=matrix.length;
   int distance[]=new int[n];
   int prev[]=new int[n];
   boolean visited[]=new boolean[n];
   Arrays.fill(prev, -1);
   Arrays.fill(distance, Integer.MAX_VALUE);
   Arrays.fill(visited, false);
   distance[start]=0;
   
   for(int i=0;i<n;i++){
   int u=-1;
   for(int j=0;j<n;j++){
      if(!visited[j] && (u==-1 || distance[j]<distance[u])){
          u=j;
      } 
   }
      visited[u]=true;
   for (int v=0;v<n;v++){
       if(matrix[u][v]!=0 && !visited[v]){
       int alt=distance[u]+matrix[u][v];
       if(alt<distance[v]){
       distance[v]=alt;
       prev[v]=u;
       }
       }
     }
   }
   if(distance[destination]==Integer.MAX_VALUE){
       System.out.println("no path exists");
       return;
   }

List<Integer> pth=new ArrayList<>();
for(int at=destination;at!=-1;at=prev[at]){
    pth.add(at);
}
Collections.reverse(pth);
for(int i=0;i<pth.size();i++){
    System.out.print(pth.get(i)+" ");
    
}
}
   public void allpaths(int matrix[][], int start, int destination) {
        boolean[] visited = new boolean[matrix.length];
        List<Integer> currentPath = new ArrayList<>();
        List<List<Integer>> allPaths = new ArrayList<>();
        dfs(matrix, start, destination, visited, currentPath, allPaths);

        if (allPaths.isEmpty()) {
            System.out.println("No paths found.");
        } else {
            System.out.println("All paths from vertex " + (start + 1) + " to vertex " + (destination + 1) + ":");
            for (List<Integer> path : allPaths) {
                for (int i = 0; i < path.size(); i++) {
                    System.out.print((path.get(i) + 1)); // Convert to 1-based index
                    if (i < path.size() - 1) {
                        System.out.print(" → ");
                    }
                }
                System.out.println();
            }
        }
    }
 private void dfs(int[][] matrix,int current,int destination,boolean[] visited,List<Integer> currentPath,List<List<Integer>> allPaths) {
        visited[current] = true;
        currentPath.add(current);

        if (current == destination) {
            allPaths.add(new ArrayList<>(currentPath)); // Add the current path when destination is reached
        } else {
            // Explore all neighbors
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[current][i] != 0 && !visited[i]) {
                    dfs(matrix, i, destination, visited, currentPath, allPaths);
                }
            }
        }

        // Backtrack:
        visited[current] = false;
        currentPath.remove(currentPath.size() - 1);
    }
    public static void main(String[] args) {
        // TODO code application logic here
        Graph g=new Graph();
         int v = 4; 
        int[][] matrix = new int[v][v];
        g.addEdge(matrix, 0, 1);
        g.addEdge(matrix, 1, 2);
        g.addEdge(matrix, 2, 3);
        g.addEdge(matrix, 3, 0);
       g.displayMatrix(matrix);
        g.ShortestPath(matrix, 0, 3);
    }
    
}
