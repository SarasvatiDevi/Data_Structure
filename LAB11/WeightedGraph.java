/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package weightedgraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author TGM
 */
public class WeightedGraph {

    public void addEdge(int[][] matrix, int v1, int v2, int weight) {
        matrix[v1][v2] = weight;
        matrix[v2][v1] = weight; // For undirected graph
    }

    // Display the adjacency matrix with weights
    public void display(int matrix[][]) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Method to find the shortest path using Dijkstra's algorithm (for weighted graph)
   public void shortestPath(int matrix[][], int start, int destination) {
    int n = matrix.length;
    int[] distance = new int[n];
    int[] prev = new int[n];
    boolean[] visited = new boolean[n];

    Arrays.fill(prev, -1);
    Arrays.fill(distance, Integer.MAX_VALUE);
    Arrays.fill(visited, false);
    distance[start] = 0;

    for (int i = 0; i < n; i++) {
        int u = -1;
        for (int j = 0; j < n; j++) {
            if (!visited[j] && (u == -1 || distance[j] < distance[u])) {
                u = j;
            }
        }
        visited[u] = true;

        for (int v = 0; v < n; v++) {
            if (matrix[u][v] != 0 && !visited[v]) { // If there's an edge
                int alt = distance[u] + matrix[u][v];
                if (alt < distance[v]) {
                    distance[v] = alt;
                    prev[v] = u;
                }
            }
        }
    }

    // If no path exists
    if (distance[destination] == Integer.MAX_VALUE) {
        System.out.println("No path exists");
        return;
    }

    List<Integer> path = new ArrayList<>();
    for (int at = destination; at != -1; at = prev[at]) {
        path.add(at);
    }
    Collections.reverse(path);

    // Display the path (convert to 1-based indexing for output)
    System.out.print("Shortest Path from vertex " + (start + 1) + " to vertex " + (destination + 1) + ": ");
    for (int i = 0; i < path.size(); i++) {
        System.out.print((path.get(i) + 1)); // Convert to 1-based index for display
        if (i < path.size() - 1) {
            System.out.print(" > ");
        }
    }
    System.out.println();
}

    // Method to find all paths from start to destination using DFS
    public void allPaths(int matrix[][], int start, int destination) {
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
                        System.out.print(" > ");
                    }
                }
                System.out.println();
            }
        }
    }

    // DFS helper method to find all paths
    private void dfs(int[][] matrix, int current, int destination, boolean[] visited, List<Integer> currentPath, List<List<Integer>> allPaths) {
        visited[current] = true;
        currentPath.add(current);

        if (current == destination) {
            allPaths.add(new ArrayList<>(currentPath)); // Add the current path when destination is reached
        } else {
            // Explore all neighbors
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[current][i] != 0 && !visited[i]) { // If there's an edge
                    dfs(matrix, i, destination, visited, currentPath, allPaths);
                }
            }
        }

        // Backtrack: unmark the current node as visited
        visited[current] = false;
        currentPath.remove(currentPath.size() - 1);
    }

    public static void main(String[] args) {
        int vertices = 4;
        WeightedGraph graph = new WeightedGraph();
        int matrix[][] = new int[vertices][vertices];

        // Adding weighted edges
        graph.addEdge(matrix, 0, 1, 10); // Edge from vertex 1 to 2 with weight 10
        graph.addEdge(matrix, 1, 2, 5);  // Edge from vertex 2 to 3 with weight 5
        graph.addEdge(matrix, 2, 3, 1);  // Edge from vertex 3 to 4 with weight 1
        graph.addEdge(matrix, 3, 0, 7);  // Edge from vertex 4 to 1 with weight 7

        // Display the adjacency matrix
        System.out.println("Adjacency Matrix (Weighted):");
        graph.display(matrix);

        // Find the shortest path from vertex 1 to vertex 4
        System.out.println("\nShortest Path from vertex 1 to vertex 4:");
        graph.shortestPath(matrix, 0, 3);  // 0 and 3 are 0-based indices

        // Find all paths from vertex 1 to vertex 4
        System.out.println("\nAll paths from vertex 1 to vertex 4:");
        graph.allPaths(matrix, 0, 3);  // 0 and 3 are 0-based indices
    }
    
}
