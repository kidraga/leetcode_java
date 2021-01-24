package leetcode.sol.P0096UniqueBinarySearchTrees;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int numTrees(int n) {
        if (n == 1) {
            return 1;
        }
        // use map to save calculated result
        Map<Integer, Integer> solved = new HashMap<Integer, Integer>();
        solved.put(0, 1); // n will not be 0, but need to add this to calculate other n
        solved.put(1, 1);

        return calculateForN(n, solved);
    }

    private int calculateForN(int n, Map<Integer, Integer> solved) {

        if (solved.containsKey(n)) {
            return solved.get(n);
        }

        int totalNumberOfPossibleTree = 0;
        // use each value as root
        // total number of trees are combination of all possible left trees and right trees
        for (int i = 1; i <= n; i++) {
            int totalNumberOfLeftSubtree = calculateForN(i - 1, solved);
            int totalNumberOfRightSubtree = calculateForN(n - i, solved);

            totalNumberOfPossibleTree += totalNumberOfLeftSubtree * totalNumberOfRightSubtree;
        }

        solved.put(n, totalNumberOfPossibleTree);

        return totalNumberOfPossibleTree;
    }
}
