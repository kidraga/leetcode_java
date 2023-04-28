package leetcode.sol.P0715RangeModule;

import java.util.Map;
import java.util.TreeMap;

/**
 * 利用TreeMap的floorKey来判断区间是否重合,
 * 利用subMap来快速删除重合的区间.
 */
class RangeModule {

    private TreeMap<Integer, Integer> ranges = new TreeMap<>(); // key: left boundary, value: right boundary

    public void addRange(int left, int right) {
        Integer l = ranges.floorKey(left); // 比left小一点的key
        Integer r = ranges.floorKey(right); // 比right小一点的key

        // left落入某个已有的range
        if (l != null && ranges.get(l) >= left) {
            // l存在并且对应的右边界比left大, 说明left落入了这个range
            left = l; // 新的range一定是有重合的, 更新left为已有的range的左边界
        }

        // right落入某个已有的range
        if (r != null && ranges.get(r) > right) {
            right = ranges.get(r);
        }

        ranges.subMap(left, right).clear(); // 删掉[left, right]区间内的所有key. 这一步是保证区间没有重合的关键
        ranges.put(left, right);
    }

    public boolean queryRange(int left, int right) {
        Integer l = ranges.floorKey(left);
        return l == null ? false : ranges.get(l) >= right;
    }

    public void removeRange(int left, int right) {
        Integer l = ranges.floorKey(left);
        Integer r = ranges.floorKey(right);

        // 如果右边界"切"到了某个range, 就更新这个range的左边界为right的值
        if (r != null && ranges.get(r) > right) {
            ranges.put(right, ranges.get(r));
        }
        // 如果左边界"切"到了某个range, 就更新这个range的右边界为left的值
        if (l != null && ranges.get(l) > left) {
            ranges.put(l, left);
        }
        ranges.subMap(left, right).clear();
    }
}
