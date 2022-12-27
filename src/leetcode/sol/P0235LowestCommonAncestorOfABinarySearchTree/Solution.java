package leetcode.sol.P0235LowestCommonAncestorOfABinarySearchTree;

import leetcode.sol.util.TreeNode;

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int val1 = Math.min(p.val, q.val);
        int val2 = Math.max(p.val, q.val);
        return find(root, val1, val2);
    }

    private TreeNode find(TreeNode root, int val1, int val2) {
        if (root == null) return null;

        if (root.val > val2) {
            // val1 < val2 < root
            // 当前节点太大，去左子树找
            return find(root.left, val1, val2);
        }
        if (root.val < val1) {
            // root < val1 < val2
            // 当前节点太小，去右子树
            return find(root.right, val1, val2);
        }
        // val1 <= root <= val2
        // 当前就是公共祖先
        return root;
    }
}
