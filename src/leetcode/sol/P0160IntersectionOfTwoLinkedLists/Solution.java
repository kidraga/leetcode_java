package leetcode.sol.P0160IntersectionOfTwoLinkedLists;

import leetcode.sol.util.ListNode;

class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2) {
            // p1 goes one step. If it reaches end of list 1, go to list 2
            if (p1 == null) {
                p1 = headB;
            } else {
                p1 = p1.next;
            }

            // p2 goes one step. If it reaches end of list 2, go to list 1
            if (p2 == null) {
                p2 = headA;
            } else {
                p2 = p2.next;
            }
        }
        return p1;
    }
}
