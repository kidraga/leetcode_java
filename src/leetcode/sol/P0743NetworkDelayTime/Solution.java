package leetcode.sol.P0743NetworkDelayTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class State {
    // 图节点的id
    int id;
    // 从start节点到当前节点的距离
    int distFromStart;

    State(int id, int distFromStart) {
        this.id = id;
        this.distFromStart = distFromStart;
    }
}

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // 节点编号是从1开始的，所以要一个大小为n+1的邻接表
        List<List<int[]>> graph = new ArrayList<>(); // 3D array
        for (int i = 1; i <= n; i++) {
            graph.add(new ArrayList<int[]>());
        }
        // build graph
        for (int[] edge : times){
            int from = edge[0] - 1;
            int to = edge[1] - 1;
            int weight = edge[2];
            // from -> [to, weight]
            // 邻接表存储图结构，同时存储权重信息
            graph.get(from).add(new int[]{to, weight});
        }

        // 启动dijkstra,计算节点k为起点到其他节点的最短路径
        int[] distTo = dijkstra(k - 1, graph);

        // 找到最长的那一条最短路径
        int res = 0;
        for (int i = 0; i < distTo.length; i++) {
            if (distTo[i] == Integer.MAX_VALUE) {
                // 有节点不可达，返回-1
                return -1;
            }
            res = Math.max(res, distTo[i]);
        }
        return res;
    }

    private int[] dijkstra(int start, List<List<int[]>> graph) {
        // 定义：distTo[i]的值就是起点start到达节点i的最短路径权重
        int[] distTo = new int[graph.size()];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        // base case, start到start的最短距离就是0
        distTo[start] = 0;

        Queue<State> pq = new PriorityQueue<>(
                (a, b) -> a.distFromStart - b.distFromStart
        );
        pq.offer(new State(start, 0));
        while (!pq.isEmpty()) {
            State currState = pq.poll();
            int curNodeId = currState.id;
            int curDistFromStart = currState.distFromStart;

            if (curDistFromStart > distTo[curNodeId]) {
                continue;
            }

            // 将currNode的相邻节点装入队列
            for (int[] neighbor : graph.get(curNodeId)) {
                int nextNodeId = neighbor[0];
                int distToNextNode = distTo[curNodeId] + neighbor[1];
                // 更新dp table
                if (distTo[nextNodeId] > distToNextNode) {
                    distTo[nextNodeId] = distToNextNode;
                    pq.offer(new State(nextNodeId, distToNextNode));
                }
            }
        }
        return distTo;
    }
}
