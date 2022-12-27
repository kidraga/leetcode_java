package leetcode.sol.P0210CourseScheduleII;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Iterative遍历
 * 利用入度控制遍历的顺序
 * 入queue的顺序直接就是topo sort的结果
 */
class Solution2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 建图
        List<List<Integer>> graph = buildGraph(numCourses, prerequisites);
        // 计算入度
        int[] indegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            int from = edge[1], to = edge[0];
            indegree[to]++;
        }

        // 根据入度初始化queue中的节点
        // 把入度为0的放入queue中
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 记录拓扑排序结果
        int[] res = new int[numCourses];
        // 记录遍历节点的顺序(索引)
        int count = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            // 弹出的节点的顺序就是拓扑排序结果
            // 为什么?
            // 还没有进queue的都还有入度,即都还有依赖
            // 优先入度变为0的node就应该排前面
            res[count] = curr;
            count++;
            for (int next : graph.get(curr)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        if (count != numCourses) {
            // 存在环
            return new int[0];
        }
        return res;
    }

    private List<List<Integer>> buildGraph(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for (int[] edge : edges) {
            int from = edge[1], to = edge[0];
            graph.get(from).add(to);
        }
        return graph;
    }
}
