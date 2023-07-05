package leetcode.sol.P0021MergeTwoSortedLists;

import leetcode.sol.util.ListNode;

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode p1 = list1;
        ListNode p2 = list2;
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                current.next = p1;
                p1 = p1.next;
            } else {
                current.next = p2;
                p2 = p2.next;
            }
            current = current.next;
        }

        // 处理p1,p2没走完
        if (p1 == null && p2 != null) {
            current.next = p2;
        } else if (p1 != null && p2 == null) {
            current.next = p1;
        }
        return dummy.next;
    }
}
