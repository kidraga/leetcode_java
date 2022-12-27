package leetcode.sol.P0538ConvertBSTToGreaterTree;

import leetcode.sol.util.TreeNode;

class Solution {
    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
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
