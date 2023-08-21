package leetcode.sol.P0061RotateList;

import leetcode.sol.util.ListNode;

class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) return head;

        ListNode p1 = head;
        ListNode p2 = head;

        // find the length of the list
        int len = 0;
        while (p1 != null) {
            len++;
            p1 = p1.next;
        }

        if (len == 1 || k % len == 0) return head;

        k = k % len;
        p1 = head;

        while (k > 0) {
            k--;
            p1 = p1.next;
        }

        ListNode oldTail = p1;
        ListNode newTail = p2;
        while (p1 != null) {
            oldTail = p1;
            newTail = p2;
            p1 = p1.next;
            p2 = p2.next;
        }


        newTail.next = null;
        oldTail.next = head;

        return p2;
    }
}
