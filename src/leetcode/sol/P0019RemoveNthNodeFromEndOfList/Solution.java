package leetcode.sol.P0019RemoveNthNodeFromEndOfList;

import leetcode.sol.util.ListNode;

public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p1 = head;
        int steps = n;
        while (p1 != null && steps != 0) {
            p1 = p1.next;
            steps--;
        }

        if (steps > 0) {
            // n is larger than list length
            return dummy.next;
        }

        ListNode p2 = head;
        ListNode p2Prev = dummy;
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
            p2Prev = p2Prev.next;
        }
        p2Prev.next = p2.next;
        p2.next = null;
        return dummy.next;
    }
}
