package leetcode.sol.P0560SubarraySumEqualsK;

import java.util.HashMap;
import java.util.Map;

class Solution2 {
  /**
   * Definition of prefixSum:
   * PrefixSum[x] = sum of subarray(0, x) = nums[0] + nums[1] + ... + nums[x]
   * index:        0 1 2
   * nums:        [1,1,1]
   * prefixSum: [0,1,2,3]
   *
   * Calculate prefixSum:
   * prefixSum[x] = prefixSum[x-1] + nums[x]
   *
   * sum of subarray(i, j) = prefixSum[j] - prefixSum[i-1]
   *
   * The question becomes:
   * Find how many pairs of <i, j>,
   * where i < j, preFixSum[j] - prefixSum[i] == k?
   *
   * This equals:
   * for each j: how many i < j, so that prefixSum[i] = prefixSum[j] - k
   * This is the same as Two sum
   */
  public int subarraySum(int[] nums, int k) {
    int count = 0;
    // Using a HashMap<Integer, Integer> to record:
    // key: prefixSum value
    // value: # of occurrence of the prefixSum value
    Map<Integer, Integer> sumToOccurrence = new HashMap<>();
    sumToOccurrence.put(0, 1);

    int prefixSum = 0;

    for (int num: nums) {
      prefixSum += num;

      if (sumToOccurrence.containsKey(prefixSum - k)) {
        count += sumToOccurrence.get(prefixSum - k);
      }

      if (sumToOccurrence.containsKey(prefixSum)) {
        // update occurrence
        sumToOccurrence.put(prefixSum, sumToOccurrence.get(prefixSum) + 1);
      } else {
        // havent occurred before
        sumToOccurrence.put(prefixSum, 1);
      }
    }
    return count;
  }
}
