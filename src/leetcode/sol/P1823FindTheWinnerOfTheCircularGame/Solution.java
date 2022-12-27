package leetcode.sol.P1823FindTheWinnerOfTheCircularGame;

import java.util.LinkedList;
import java.util.List;

/**
 * Brute force
 * Time O(n^2). Both ArrayList or LinkedList will result n^2 time
 * Space O(n)
 */
class Solution {
    public int findTheWinner(int n, int k) {
        List<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }
        int index = 0;
        while (queue.size() > 1) {
            index = (index + k - 1) % (queue.size());
            queue.remove(index);
        }
        return queue.get(0);
    }
}
