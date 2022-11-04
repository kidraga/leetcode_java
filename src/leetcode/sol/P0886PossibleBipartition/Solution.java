package leetcode.sol.P0886PossibleBipartition;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private boolean[] visited;
    private boolean[] color;
    private boolean ok = true;
    public boolean possibleBipartition(int n, int[][] dislikes) {
        visited = new boolean[n];
        color = new boolean[n];
        List<List<Integer>> graph = buildGraph(n, dislikes);

        for (int v = 0; v < n; v++) {
            traverse(graph, v);
        }

        return ok;
    }

    private void traverse(List<List<Integer>> graph, int start) {
        if (!ok) return;
        if (visited[start]) return;
        visited[start] = true;
        for (int next : graph.get(start)) {
            if (!visited[next]) {
                color[next] = !color[start];
                traverse(graph, next);
            } else {
                if (color[start] == color[next]) {
                    ok = false;
                    return;
                }
            }
        }
    }

    private List<List<Integer>> buildGraph(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for (int[] edge : edges) {
            int node1 = edge[0] - 1, node2 = edge[1] - 1;
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }
        return graph;
    }
}
