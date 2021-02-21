package leetcode.sol.P0104MaximumDepthOfBinaryTree;

import javafx.util.Pair;
import leetcode.sol.util.TreeNode;

import java.util.Stack;

public class Solution2 {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(root, 1));

        int maxDepth = 0;
        int currDepth;
        TreeNode currNode;
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> currPair = stack.pop();
            currNode = currPair.getKey();
            currDepth = currPair.getValue();

            if (currNode != null) {
                maxDepth = Math.max(maxDepth, currDepth);
                stack.push(new Pair<>(currNode.left, currDepth + 1));
                stack.push(new Pair<>(currNode.right, currDepth + 1));
            }
        }
        return maxDepth;
    }
}
