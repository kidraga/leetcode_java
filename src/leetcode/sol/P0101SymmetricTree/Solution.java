package leetcode.sol.P0101SymmetricTree;

import leetcode.sol.util.TreeNode;

public class Solution {
    /**
     * Recursive solution
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return recursiveSymmetricCheck(root.left, root.right);
    }

    private boolean recursiveSymmetricCheck(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;

        return left.val == right.val
                && recursiveSymmetricCheck(left.right, right.left)
                && recursiveSymmetricCheck(left.left, right.right);
    }
}
