package leetcode.sol.P0101SymmetricTree;

import leetcode.sol.util.TreeNode;

import java.util.Stack;

public class Solution2 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        if (root.left == null && root.right == null) return true;
        if (root.left == null || root.right == null) return false;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.left);
        stack.push(root.right);

        TreeNode left;
        TreeNode right;
        while (!stack.isEmpty()) {
            right = stack.pop();
            left = stack.pop();

            if (left == null && right == null) continue;
            if (left == null || right == null || left.val != right.val) return false;

            stack.push(left.left);
            stack.push(right.right);
            stack.push(left.right);
            stack.push(right.left);

        }
        return true;
    }
}
