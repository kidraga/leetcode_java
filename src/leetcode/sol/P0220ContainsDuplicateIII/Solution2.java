package leetcode.sol.P0220ContainsDuplicateIII;

import java.util.HashMap;
import java.util.Map;

class Solution2 {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        if (valueDiff < 0) return false;
        Map<Long, Long> bucketIdToValueDiff = new HashMap<>();
        long w = (long) valueDiff + 1;
        for (int i = 0; i < nums.length; i++) {
            long m = getID(nums[i], w);
            // check if bucket m is empty, each bucket may contain at most one element
            if (bucketIdToValueDiff.containsKey(m)) return true;
            // check the neighbor buckets for almost duplicate
            if (bucketIdToValueDiff.containsKey(m - 1) && Math.abs(nums[i] - bucketIdToValueDiff.get(m - 1)) < w) return true;
            if (bucketIdToValueDiff.containsKey(m + 1) && Math.abs(nums[i] - bucketIdToValueDiff.get(m + 1)) < w) return true;
            // now bucket m is empty and no almost duplicate in neighbor buckets
            bucketIdToValueDiff.put(m, (long) nums[i]);
            if (i >= indexDiff) bucketIdToValueDiff.remove(getID(nums[i - indexDiff], w));
        }
        return false;
    }

    // In java, (-3) / 5 = 0, but we need (-3) / 5 = -1
    // get the id of the bucket
    private long getID(long x, long w) {
        return x < 0 ? (x + 1) / w - 1 : x / w;
    }
}
