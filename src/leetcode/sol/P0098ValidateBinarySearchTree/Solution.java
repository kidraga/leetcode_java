package leetcode.sol.P0098ValidateBinarySearchTree;

import leetcode.sol.util.TreeNode;

class Solution {

    public boolean isValidBST(TreeNode root) {

        return isValidBSTWithBoundaryCheck(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBSTWithBoundaryCheck(TreeNode root, long minValue, long maxValue) {

        if (root == null) {
            return true;
        }

        if (root.val <= minValue) return false;
        if (root.val >= maxValue) return false;

        return isValidBSTWithBoundaryCheck(root.left, minValue, root.val)
                && isValidBSTWithBoundaryCheck(root.right, root.val, maxValue);
    }
}
