package leetcode.sol.P0082RemoveDuplicatesFromSortedListII;

import leetcode.sol.util.ListNode;

public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // dummy node
        ListNode dummy = new ListNode(0, head);

        // node before the duplicate nodes sublist
        ListNode pred = dummy;
        while (head != null) {
            // if it's the beginning of duplicates
            // skip all duplicates
            if (head.next != null && head.val == head.next.val) {
                // move to the end of the duplicates
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                // skip all duplicates
                pred.next = head.next;
            } else {
                pred = pred.next;
            }

            head = head.next;
        }

        return dummy.next;
    }
}
