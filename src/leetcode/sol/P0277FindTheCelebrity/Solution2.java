package leetcode.sol.P0277FindTheCelebrity;

import leetcode.sol.util.Relation;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Time O(n)
 * Space O(n)
 * 根据名人的定义：
 * 1. 所有其他人都认识名人
 * 2. 名人不认识任何人
 * 保证了最多只能有一个名人
 * 判断是名人不太容易，但判断不是名人很容易
 * if (knows(cand, other) || !knows(other, cand)) {
 *     // cand 不可能是名人
 * } else {
 *     // other 不可能是名人
 * }
 * 我们可以不断从候选人中选两个出来，然后排除掉一个，直到最后只剩下一个候选人，这时候再使用一个 for 循环判断这个候选人是否是货真价实的「名人」
 */
public class Solution2 extends Relation {

    public int findCelebrity(int n) {
        if (n == 1) return 0;
        // 将所有候选人装进队列
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            q.offer(i);
        }
        // 一直排除，直到只剩下一个候选人停止循环
        while (q.size() > 1) {
            // 每次取出两个候选人，排除一个
            int cand = q.poll();
            int other = q.poll();
            if (knows(cand, other) || !knows(other, cand)) {
                // cand不可能是名人，排除，让other回到queue里
                q.offer(other);
            } else {
                // other 不可能是名人，排除，让cand回到queue里
                q.offer(cand);
            }
        }

        // 现在排除的只剩一个候选人，判断他是否真的是名人
        int cand = q.poll();
        for (int other = 0; other < n; other++) {
            if (other == cand) {
                continue;
            }
            // 保证其他人都认识cand，且cand不认识其他人
            if (!knows(other, cand) || knows(cand, other)) {
                return -1;
            }
        }
        // cand是名人
        return cand;
    }
}
