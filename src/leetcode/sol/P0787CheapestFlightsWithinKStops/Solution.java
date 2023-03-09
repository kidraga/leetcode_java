package leetcode.sol.P0787CheapestFlightsWithinKStops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    class State {
        // 节点id
        int id;
        // 从src到当前节点的花费
        int costFromSrc;
        // 从src到当前节点经过的节点个数
        int nodeNumFromSrc;
        public State(int id, int costFromSrc, int nodeNumFromSrc) {
            this.id = id;
            this.costFromSrc = costFromSrc;
            this.nodeNumFromSrc = nodeNumFromSrc;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 建图
        Map<Integer, List<int[]>> graph = buildGraph(flights);
        k++;

        // 从起点src到达节点i的最小权重(最便宜花费)
        int[] priceTo = new int[n];
        // 从起点src到达节点i, 最便宜的路线要经过多少个node
        int[] nodeNumTo = new int[n];
        Arrays.fill(priceTo, Integer.MAX_VALUE);
        Arrays.fill(nodeNumTo, Integer.MAX_VALUE);

        priceTo[src] = 0;
        nodeNumTo[src] = 0;

        // 用priority queue来保持扩展到的nodes, 并且按花费排序
        Queue<State> pq = new PriorityQueue<>((a, b) -> {
            return a.costFromSrc - b.costFromSrc;
        });

        // 从起点开始进行BFS
        pq.offer(new State(src, 0, 0));
        while (!pq.isEmpty()) {
            State currState = pq.poll();
            int currNodeId = currState.id;
            int currCostFromSrc = currState.costFromSrc;
            int currNodeNumFromSrc = currState.nodeNumFromSrc;

            if (currNodeId == dst) {
                // 找到了最便宜路径
                return currCostFromSrc;
            }

            if (currNodeNumFromSrc == k) {
                // 耗尽了中转次数
                // 注意这只是某条路线耗尽了中转次数, 不代表别的路线走完了
                // 有的路线可能更便宜, 但中转次数多, 所以优先排在了队列前面
                // 耗尽了中转次数, 也可以理解为"没有下一个邻居了". 所以应该continue
                continue;
            }

            // 将邻居放入queue中
            for (int[] neighbor : graph.getOrDefault(currNodeId, new ArrayList<>())) { // 为什么getOrDefault? 有些node可能没有邻居
                int nextNodeId = neighbor[0];
                int costToNextNode = currCostFromSrc + neighbor[1];
                int nextNodeNumFromSrc = currNodeNumFromSrc + 1; // 中转次数消耗1

                // 更新price table
                if (priceTo[nextNodeId] > costToNextNode) {
                    // 找到了更便宜的一条路
                    priceTo[nextNodeId] = costToNextNode;
                    // 同时也要更新到达这条路的节点数
                    nodeNumTo[nextNodeId] = nextNodeNumFromSrc;
                }

                // 剪枝, 如果中转次数更多, 花费更大, 必然不会是最便宜路径
                if (costToNextNode > priceTo[nextNodeId]
                        && nextNodeNumFromSrc > nodeNumTo[nextNodeId]) {
                    // 注意, 只有两条都满足才应该跳过
                    // 如果只是花费贵, 但是中转少, 后面有可能再便宜回来, 而且有可能到达. (便宜但中转多的可能到不了)
                    continue;
                }

                pq.offer(new State(nextNodeId, costToNextNode, nextNodeNumFromSrc));
            }
        }
        return -1;
    }

    private Map<Integer, List<int[]>> buildGraph(int[][] flights) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : flights) {
            int from = edge[0];
            int to = edge[1];
            int price = edge[2];
            List<int[]> neighbors = graph.getOrDefault(from, new ArrayList<>());
            neighbors.add(new int[]{to, price});
            graph.put(from, neighbors);
        }
        return graph;
    }
}
