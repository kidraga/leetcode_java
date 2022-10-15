package leetcode.sol.P0380InsertDeleteGetRandomO1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomizedSet {
    List<Integer> nums = new ArrayList<>();
    Map<Integer, Integer> valToIndex = new HashMap<>();
    Random rand = new Random();

    public RandomizedSet() {
    }

    public boolean insert(int val) {
        if (valToIndex.containsKey(val)) {
            return false;
        }
        // 如果val不存在，插入到nums尾部，
        // 并记录val对应的index
        valToIndex.put(val, nums.size());
        nums.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!valToIndex.containsKey(val)) {
            return false;
        }
        // 先拿到val的index
        int index = valToIndex.get(val);
        // 将数组最后一个元素的索引修改为index
        int lastValue = nums.get(nums.size() - 1);
        valToIndex.put(lastValue, index);
        // 交换val和最后一个元素
        nums.set(index, lastValue);
        nums.set(nums.size() - 1, val);
        nums.remove(nums.size() - 1);
        valToIndex.remove(val);
        return true;
    }

    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }
}
