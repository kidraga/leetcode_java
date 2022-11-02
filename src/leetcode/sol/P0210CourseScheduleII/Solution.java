package leetcode.sol.P0210CourseScheduleII;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    private List<Integer> postorder = new ArrayList<>(); // 记录后序遍历结果
    boolean hasCycle = false;
    boolean[] visited;
    boolean[] onPath;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = buildGraph(numCourses, prerequisites);
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        // 遍历图
        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
        }
        if (hasCycle) {
            return new int[0];
        }
        // 逆后序遍历结果即为拓扑排序结果
        Collections.reverse(postorder);
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = postorder.get(i);
        }
        return res;
    }

    private List<List<Integer>> buildGraph(int numCourses, int[][] prerequisites) {
        // 图中共有numCourses个节点
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<Integer>());
        }

        for (int[] edge : prerequisites) {
            int from = edge[1], to = edge[0];
            // 添加一条从from指向to的有向边
            graph.get(from).add(to);
        }
        return graph;
    }

    private void traverse(List<List<Integer>> graph, int s) {
        if (onPath[s]) {
            // 发现环
            hasCycle = true;
        }
        if (visited[s] || hasCycle) {
            return;
        }
        // 前序遍历位置
        onPath[s] = true;
        visited[s] = true;
        for (int t : graph.get(s)) {
            traverse(graph, t);
        }
        // 后序遍历位置
        postorder.add(s);
        onPath[s] = false;
    }
}
