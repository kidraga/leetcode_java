package leetcode.sol;

import leetcode.sol.util.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class FlattenBinaryTreeToLinkedList {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    public void flatten(TreeNode root) { // Time: O(n). Space: O(n)
        if (root == null) return;

        // traverse and put all node to a FIFO queue in pre-order
        Deque<TreeNode> queue = new ArrayDeque<>();
        dfs(root, queue);

        // go through each node, override its left and right node.
        TreeNode curr = queue.pollFirst();
        while (!queue.isEmpty()) {
            TreeNode nxt = queue.pollFirst();
            curr.left = null;
            curr.right = nxt;
            curr = nxt;
        }
    }

    private void dfs(TreeNode root, Deque<TreeNode> queue) {
        if (root == null) return;
        queue.addLast(root);
        dfs(root.left, queue);
        dfs(root.right, queue);
    }

    public void flattenInPlace(TreeNode root) {
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
