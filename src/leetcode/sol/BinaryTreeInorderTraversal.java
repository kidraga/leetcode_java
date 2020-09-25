package leetcode.sol;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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
public class BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        dfs(root, result);
        return result;
    }

    private void dfs(TreeNode root, List<Integer> result) {
        if (root == null) return;

        dfs(root.left, result);
        result.add(root.val);
        dfs(root.right, result);
    }

    public List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Deque<TreeNode> stack = new ArrayDeque<>(); // FIFO queue used as a stack
        TreeNode rootOfSubTree = root;
        while (rootOfSubTree != null || !stack.isEmpty()) {
            while (rootOfSubTree != null) {
                stack.push(rootOfSubTree); // push to last
                rootOfSubTree = rootOfSubTree.left;
            }
            rootOfSubTree = stack.pop(); // pop last
            result.add(rootOfSubTree.val);
            rootOfSubTree = rootOfSubTree.right;
        }
        return result;
    }

   static class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode() {}
       TreeNode(int val) { this.val = val; }
       TreeNode(int val, TreeNode left, TreeNode right) {
           this.val = val;
           this.left = left;
           this.right = right;
       }
   }
}
