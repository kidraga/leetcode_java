package leetcode.sol.P0145BinaryTreePostorderTraversal;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import leetcode.sol.util.TreeNode;

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>(); // 必须用linkedlist
        if (root == null) return result;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode currNode = stack.pop();
            // pop出来的加到答案的前面
            // 越靠后出来的在答案里越靠前
            result.addFirst(currNode.val); // 这里使用了linkedlist的addfirst

            // 先左再右, 从stack里出来的时候就是先右再左了
            if (currNode.left != null) stack.push(currNode.left);
            if (currNode.right != null) stack.push(currNode.right);
        }
        return result;
    }
}
