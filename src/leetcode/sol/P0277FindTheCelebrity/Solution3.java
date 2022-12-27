package leetcode.sol.P0277FindTheCelebrity;

import leetcode.sol.util.Relation;

/**
 * Time O(n)
 * Space O(1)
 */
class Solution3 extends Relation {

    public int findCelebrity(int n) {
        // 先假设cand是名人
        int cand = 0;
        for (int other = 1; other < n; other++) {
            if (!knows(other, cand) || knows(cand, other)) {
                // cand不可能是名人，排除
                // 假设other是名人
                cand = other;
            } else {
                // other不可能是名人，排除
                // 什么都不用做，继续假设cand是名人
            }
        }

        // 现在的cand是排除后的最后结果，但不能保证一定是名人
        for (int other = 0; other < n; other++) {
            if (cand == other) continue;
            // 需要保证其他人都认识cand，且cand不认识任何其他人
            if (!knows(other, cand) || knows(cand, other)) {
                return -1;
            }
        }
        return cand;
    }
}
