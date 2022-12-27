package leetcode.sol.P1038BinarySearchTreeToGreaterSumTree;

import leetcode.sol.util.TreeNode;

/**
 * Same as 538
 */
class Solution {
    private int sum = 0;

    public TreeNode bstToGst(TreeNode root) {
        traverse(root);
        return root;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root.right);
        // 维护累加和
        sum += root.val;
        root.val = sum;
        traverse(root.left);
    }
}
