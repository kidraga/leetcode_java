package leetcode.sol.P0323NumberOfConnectedComponentsInAnUndirectedGraph;

class Solution {
    public int countComponents(int n, int[][] edges) {
        UF uf = new UF(n);
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }
        return uf.count();
    }
}

class UF {
    // 连通分量个数（游离岛个数）
    private int count;
    // 存储每个节点的父节点
    private int[] parent;

    public UF(int n) {
        this.count = n;
        parent = new int[n];
        // 初始所有节点的root都是自己
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    // 连接两个node
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) {
            return;
        }

        parent[rootQ] = rootP;
        count--;
    }

    // 判断两个点是否连接
    public boolean isConnected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }

    // 查找x的root，并压缩路径
    // 压缩后x就是root的child
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public int count() {
        return count;
    }
}
