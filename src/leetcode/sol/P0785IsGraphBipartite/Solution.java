package leetcode.sol.P0785IsGraphBipartite;

class Solution {
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
        // 因为图不一定是联通的,可能存在多个子图
        // 所以要把每个节点都作为起点进行一次遍历
        // 如果发现任何一个子图不是二分图,整幅图都不算二分图
        for (int v = 0; v < n; v++) {
            if (!visited[v]) {
                traverse(graph, v);
            }
        }
        return ok;
    }

    private void traverse(int[][] graph, int v) {
        // 如果已经确定不是二分图了,就不用浪费时间再递归遍历了
        if (!ok) return;

        visited[v] = true;
        for (int w : graph[v]) {
            if (!visited[w]) {
                // 相邻节点w没有被访问过
                // 那么应该给w涂上和v不同的颜色,即!color[v]
                color[w] = !color[v];
                // 继续遍历w
                traverse(graph, w);
            } else {
                // 如果已经visited
                // 那么需要确保v和w不是相同的颜色,否则就不是二分图
                if (color[w] == color[v]) {
                    ok = false;
                    return;
                }
                // 注意这里是已经visited的情况了
                // 所以无论如何都不需要继续遍历了traverse(graph, w)
            }
        }
    }
}
