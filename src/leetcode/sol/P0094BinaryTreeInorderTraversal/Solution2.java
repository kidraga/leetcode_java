package leetcode.sol.P0094BinaryTreeInorderTraversal;

import leetcode.sol.util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution2 {

    /**
     * Iterative solution
     */
    public List<Integer> inorderTraversal(TreeNode root) {
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
}
