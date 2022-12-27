package leetcode.sol.P0207CourseSchedule;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * BFS环检测,需要借助入度.
 * 对于每一个节点,将指向它的边一个一个的删除(入度-1)
 * 当入度减为0的时候,就作为起点放入queue中.
 * 有点像捋线头,先找到一个头(入度为0的node),然后把指向的所有点的入度都减一
 * 就会有一些node的入度变为了0(成为新的头),然后重复上面的过程.
 * 如果没有环,这样迟早会过一遍所有的node
 *
 * queue中的节点每次拿出来的时候counter就++,即记录全程一共放入过queue中有多少个节点
 * 如果没有环,则所有节点都会进入queue一次.
 *
 * 如果有环,则环的交点入度不会减到0,所以进不了queue.
 * 如果是孤岛圆环(没有交点),则环上所有点都不是起点,所以也进不了queue
 *
 * https://labuladong.github.io/algo/2/22/51/
 */
class Solution2 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 建图,有向边代表"被依赖关系"
        List<List<Integer>> graph = buildGraph(numCourses, prerequisites);
        // 构建入度数组
        int[] indegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            int from = edge[1], to = edge[0];
            indegree[to]++;
        }

        // 根据入度初始化队列中的节点
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                // 节点i的入度为0,说明没有依赖
                // 可以作为拓扑排序的起点,加入队列
                queue.offer(i);
            }
        }

        // 记录遍历的节点个数
        int count = 0;
        // 开始执行BFS
        while (!queue.isEmpty()) {
            // 弹出节点curr，并将它指向的节点的入度减一
            int curr = queue.poll();
            count++;
            for (int neighbour : graph.get(curr)) {
                indegree[neighbour]--;
                if (indegree[neighbour] == 0) {
                    // 如果入度为0,说明所有neighbour的依赖节点都已经被遍历了
                    queue.offer(neighbour);
                }
            }

        }

        // 如果所有节点都被遍历了,说明不成环
        return count == numCourses;
    }

    private List<List<Integer>> buildGraph(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<Integer>());
        }

        for (int[] edge : prerequisites) {
            int from = edge[1], to = edge[0];
            graph.get(from).add(to);
        }
        return graph;
    }
}
