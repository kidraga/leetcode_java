package leetcode.sol.P0114FlattenBinaryTreeToLinkedList;

import leetcode.sol.util.TreeNode;

public class Solution3 {
    public void flatten(TreeNode root) {
        if (root == null) return;

        flatten(root.left);
        flatten(root.right);

        TreeNode left = root.left;
        root.left = null;
        if (left != null) {
            TreeNode node = left;
            while ( node.right != null) {
                node = node.right;
            }
            node.right = root.right;
            root.right = left;
        }
    }
}
