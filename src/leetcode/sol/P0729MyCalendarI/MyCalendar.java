package leetcode.sol.P0729MyCalendarI;

import java.util.TreeMap;

/**
 * 这道题就是单纯的考TreeMap, TreeSet
 * 就是可以给key排序的tree, 底层是红黑树
 * 最重要的两个api: floorKey(key), ceilingKey(key)
 */
class MyCalendar {

    private TreeMap<Integer, Integer> calendar;

    public MyCalendar() {
        calendar = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Integer prev = calendar.floorKey(start); // 比start小的最大key
        Integer next = calendar.ceilingKey(start); // 比start大的最小key
        if ((prev == null || calendar.get(prev) <= start)
                && (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }
}
