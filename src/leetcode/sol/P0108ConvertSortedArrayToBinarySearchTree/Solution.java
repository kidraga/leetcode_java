package leetcode.sol.P0108ConvertSortedArrayToBinarySearchTree;

import leetcode.sol.util.TreeNode;

public class Solution {

    public TreeNode sortedArrayToBST(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    private TreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) return null;

        int mid = (end + start) / 2; // might overflow. Better use start + (end - start) / 2
        TreeNode node = new TreeNode(nums[mid]);

        node.left = buildTree(nums, start, mid - 1);
        node.right = buildTree(nums, mid + 1, end);

        return node;
    }
}
