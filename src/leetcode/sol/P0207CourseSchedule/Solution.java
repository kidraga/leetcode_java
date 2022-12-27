package leetcode.sol.P0207CourseSchedule;

import java.util.ArrayList;
import java.util.List;

class Solution {
    private boolean[] visited; // 防止重复遍历同一个节点
    private boolean[] onPath;
    private boolean hasCycle = false;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = buildGraph(numCourses, prerequisites);

        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
        }

        return !hasCycle;
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
            // 边的方向是“被依赖”关系，即修完课程from才能修课程to
            graph.get(from).add(to);
        }
        return graph;
    }

    private void traverse(List<List<Integer>> graph, int s) {
        if (onPath[s]) {
            // 在当前path上出现重复的节点了，说明有环
            hasCycle = true;
        }
        if (visited[s]) {
            return;
        }
        
        // 将当前节点标记为已遍历
        visited[s] = true;
        onPath[s] = true;
        for (int t : graph.get(s)) {
            traverse(graph, t);
        }
        onPath[s] = false;
    }
}
