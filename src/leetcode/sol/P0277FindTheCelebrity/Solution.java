package leetcode.sol.P0277FindTheCelebrity;


import leetcode.sol.util.Relation;
/**
 * knows(a, b) -> whether a knows b
 */

/**
 * Brute force
 */
class Solution extends Relation {
    public int findCelebrity(int n) {
        for (int cand = 0; cand < n; cand++) {
            int other;
            for (other = 0; other < n; other++) {
                if (cand == other) continue;
                // 其他人都认识cand，且cand不认识其他人
                if (knows(cand, other) || !knows(other, cand)) break;
            }
            if (other == n) {
                // found celebrity
                return cand;
            }
        }
        // 没有名人
        return -1;
    }
}
