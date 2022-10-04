package leetcode.sol.P0144BinaryTreePreorderTraversal;

import leetcode.sol.util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode currNode = stack.pop();
            TreeNode leftChild = currNode.left;
            TreeNode rightChild = currNode.right;

            // preorder. we put root to result first
            result.add(currNode.val);

            // then we put right node to stack, then left node.
            // Since it's stack, the left node will be taken out first
            if (rightChild != null) {
                stack.push(rightChild);
            }
            if (leftChild != null) {
                stack.push(leftChild);
            }
        }
        return result;
    }
}
