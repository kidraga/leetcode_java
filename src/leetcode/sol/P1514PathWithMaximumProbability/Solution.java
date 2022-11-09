package leetcode.sol.P1514PathWithMaximumProbability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class State {
    int id;
    double probFromStart;
    State(int id, double probFromStart) {
        this.id = id;
        this.probFromStart = probFromStart;
    }
}

public class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<List<double[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<double[]>());
        }
        // 构造邻接表
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            double weight = succProb[i];
            // 无向图就是双向图；先把int统一转换成double，一会再转回来
            graph.get(from).add(new double[]{(double) to, weight});
            graph.get(to).add(new double[]{(double) from, weight});
        }
        return dijkstra(start, end, graph);
    }

    private double dijkstra(int start, int end, List<List<double[]>> graph) {
        double[] probTo = new double[graph.size()];
        // dp table 初始化为一个取不到的最小值
        Arrays.fill(probTo, -1);
        // base case, start到start的概率是1
        probTo[start] = 1;

        // 优先级队列，probFromStart交大的排在前面
        Queue<State> pq = new PriorityQueue<>((a, b) -> {
            return Double.compare(b.probFromStart, a.probFromStart);
        });

        // 从起点start开始进行BFS
        pq.offer(new State(start, 1));

        while (!pq.isEmpty()) {
            State curState = pq.poll();
            int curNodeId = curState.id;
            double curProbFromStart = curState.probFromStart;

            // 遇到终点提前返回
            if (curNodeId == end) {
                return curProbFromStart;
            }

            if (curProbFromStart < probTo[curNodeId]) {
                // 有一条更大概率到达curNode的路径了
                continue;
            }

            for (double[] neighbor : graph.get(curNodeId)) {
                int nextNodeId = (int) neighbor[0];
                // 看看从curNode达到nextNode的概率是否会更大
                double probToNextNode = probTo[curNodeId] * neighbor[1];
                if (probTo[nextNodeId] < probToNextNode) {
                    probTo[nextNodeId] = probToNextNode;
                    pq.offer(new State(nextNodeId, probToNextNode));
                }
            }
        }
        // 如果到达这里，说明从start开始无法到达end
        return 0.0;
    }

}
