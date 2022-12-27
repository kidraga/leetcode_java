package leetcode.sol.P0086PartitionList;

import leetcode.sol.util.ListNode;

class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null) return null;

        ListNode dummy = new ListNode(-1);
        ListNode dummy2 = new ListNode(-1);
        ListNode smallPointer = dummy;
        ListNode largePointer = dummy2;
        ListNode p = head;

        while (p != null) {
            if (p.val < x) {
                smallPointer.next = p;
                smallPointer = smallPointer.next;
            } else {
                largePointer.next = p;
                largePointer = largePointer.next;
            }
            ListNode temp = p;
            p = p.next;
            temp.next = null; // break the link, otherwise there will be circle
        }

        smallPointer.next = dummy2.next;
        return dummy.next;
    }
}
