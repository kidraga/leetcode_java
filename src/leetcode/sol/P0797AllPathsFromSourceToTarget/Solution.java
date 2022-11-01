package leetcode.sol.P0797AllPathsFromSourceToTarget;

import java.util.LinkedList;
import java.util.List;

public class Solution {

    private List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        // 维护递归过程中经过的路径
        LinkedList<Integer> path = new LinkedList<>();
        traverse(graph, 0, path);
        return res;
    }

    private void traverse(int[][] graph, int s, LinkedList<Integer> path) {
        path.addLast(s);

        int n = graph.length;
        if (s == n - 1) {
            // 到达终点
            res.add(new LinkedList<>(path));
            // 可以在这里直接return，但要removeLast正确维护path
            // path.removeLast();
            // return;
            // 不return也可以，因为图中不包含环，不会出现无限递归
        }

        // 递归每个相邻节点
        for (int v : graph[s]) {
            traverse(graph, v, path);
        }

        // 从路径移出节点s
        path.removeLast();
    }
}
