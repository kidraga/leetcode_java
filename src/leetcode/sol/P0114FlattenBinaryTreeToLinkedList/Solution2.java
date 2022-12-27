package leetcode.sol.P0114FlattenBinaryTreeToLinkedList;

import leetcode.sol.util.TreeNode;

class Solution2 {
    /**
     * Flatten in-place
     */
    public void flatten(TreeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            return;
        }

        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode last = cur.left;
                while (last.right != null) last = last.right;
                last.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }
}
