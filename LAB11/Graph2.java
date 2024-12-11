/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package graph2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TGM
 */
public class Graph2 {
// Method to find and print all connected components
   
     // Method to find and print all connected components
    public void detectConnectedComponents(int[][] matrix) {
        int n = matrix.length;
        boolean[] visited = new boolean[n];  // Track visited vertices
        List<List<Integer>> connectedComponents = new ArrayList<>();

        // Explore all vertices
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                // Start a new component from this unvisited vertex
                List<Integer> component = new ArrayList<>();
                dfs(matrix, i, visited, component);
                connectedComponents.add(component);
            }
        }

        // Print all connected components
        System.out.println("Connected Components:");
        for (int i = 0; i < connectedComponents.size(); i++) {
            System.out.print("Component " + (i + 1) + ": ");
            System.out.println(connectedComponents.get(i));
            
        }
    }

    // DFS function to explore all nodes in the connected component
    private void dfs(int[][] matrix, int node, boolean[] visited, List<Integer> component) {
        visited[node] = true;
        component.add(node + 1);  // Add node to the component (1-based index)

        // Explore all the neighbors of the current node
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[node][i] == 1 && !visited[i]) {
                dfs(matrix, i, visited, component);
            }
        }
    }
    public static void main(String[] args) {
           Graph2 graph=new Graph2();
      int[][] matrix = {
            {0, 1, 1, 0, 0},  // Vertex 1
            {1, 0, 1, 0, 0},  // Vertex 2
            {1, 1, 0, 0, 0},  // Vertex 3
            {0, 0, 0, 0, 1},  // Vertex 4
            {0, 0, 0, 1, 0}   // Vertex 5
        };

        // Detect and print connected components
        graph.detectConnectedComponents(matrix);
    }
    
}
