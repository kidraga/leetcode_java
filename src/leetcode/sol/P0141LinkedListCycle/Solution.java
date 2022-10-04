package leetcode.sol.P0141LinkedListCycle;

import leetcode.sol.util.ListNode;

public class Solution {

    public boolean hasCycle(ListNode head) {
        /**
         * [1,2,1]
         * [1]
         */
        if (head == null) return false;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && slow != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) fast = fast.next;
            if (fast != null && slow != null && fast == slow) return true;
        }
        return false;
    }
}
