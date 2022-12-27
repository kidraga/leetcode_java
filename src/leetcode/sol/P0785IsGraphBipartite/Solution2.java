package leetcode.sol.P0785IsGraphBipartite;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution2 {
    // 记录图是否符合二分图性质
    private boolean ok = true;
    // 记录图中节点的颜色, false和true代表两种不同的颜色
    private boolean[] color;
    // 记录节点是否被访问过
    private boolean[] visited;
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        color = new boolean[n];
        visited = new boolean[n];

        for (int v = 0; v < n; v++) {
            if (!visited[n]) {
                bfs(graph, v);
            }
        }
        return ok;
    }

    private void bfs(int[][] graph, int start) {
        Deque<Integer> queue = new ArrayDeque<>();
        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int v = queue.poll();
            // 从节点v向所有相邻节点扩散
            for (int w : graph[v]) {
                if (!visited[w]) {
                    // 相邻节点w没有被访问过
                    // 那么应该给节点w涂上和v不同的颜色
                    color[w] = !color[v];
                    // 标记w，并放入队列
                    visited[w] = true;
                    queue.offer(w);
                } else {
                    // 相邻节点已经被访问过
                    // 根据v和w颜色判断是否是二分图
                    if (color[v] == color[w]) {
                        ok = false;
                        return;
                    }
                }
            }
        }
    }
}
