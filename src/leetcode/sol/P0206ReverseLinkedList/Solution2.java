package leetcode.sol.P0206ReverseLinkedList;

import leetcode.sol.util.ListNode;

class Solution2{
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 原本是最后一个node的last，反转后会变成新list的head
        ListNode last = reverseList(head.next);
        // 注意此时head.next仍然指向之前的第二个node，也就是新list的最后一个
        head.next.next = head;
        head.next = null;
        return last;
    }
}
