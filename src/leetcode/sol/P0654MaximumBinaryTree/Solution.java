package leetcode.sol.P0654MaximumBinaryTree;

import leetcode.sol.util.TreeNode;

public class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int lo, int hi) {
        if (lo > hi) return null;

        // 找到数组中的最大值和对应的索引
        int index = -1;
        int maxValue = Integer.MIN_VALUE;
        for (int i = lo; i <= hi; i++) {
            if (maxValue < nums[i]) {
                index = i;
                maxValue = nums[i];
            }
        }

        // 先构造出根节点
        TreeNode root = new TreeNode(maxValue);
        root.left = build(nums, lo, index - 1);
        root.right = build(nums, index + 1, hi);

        return root;
    }
}
