package leetcode.sol.P1570DotProductofTwoSparseVectors;

import java.util.HashMap;
import java.util.Map;

/** can improve the design, but works
 */
class SparseVector {

    Map<Integer, Integer> indexToValue = new HashMap<>();

    public SparseVector(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                indexToValue.put(i, nums[i]);
            }
        }
    }

    public int dotProduct(SparseVector vec) {
        // need to check null in real world

        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : indexToValue.entrySet()) {
            int index = entry.getKey();
            int value = entry.getValue();
            if (vec.hasValueAtIndex(index)) {
                ans += value * vec.getValueAtIndex(index);
            }
        }
        return ans;
    }

    public boolean hasValueAtIndex(int index) {
        return indexToValue.containsKey(index);
    }

    public int getValueAtIndex(int index) {
        return indexToValue.get(index);
    }
}
