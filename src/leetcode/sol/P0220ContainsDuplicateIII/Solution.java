package leetcode.sol.P0220ContainsDuplicateIII;

import java.util.TreeSet;

/**
 * Time: O(n * log(k)), k = indexDiff
 * Space: O(k)
 * TreeSet或者说TreeMap实现原理：底层是红黑树（自平衡二叉树）。
 * 自平衡二叉树会让左右子树节点数尽量差不多，不会出现一边一堆节点一边没有的情况，这样搜索效率会提高
 * 每次插入会通过旋转操作保持平衡
 */
public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            // find the successor of current element
            Integer s = set.ceiling(nums[i]); // smallest number that is larger than nums[i]
            if (s != null && (long) s <= nums[i] + valueDiff) return true;

            // find the predecessor of current element
            Integer g = set.floor(nums[i]); // largest number that is smaller than nums[i]
            if (g != null && nums[i] <= (long) g + valueDiff) return true;

            set.add(nums[i]);
            if (set.size() > indexDiff) set.remove(nums[i - indexDiff]);
        }
        return false;
    }
}
