package leetcode.sol.P0142LinkedListCycleII;

import leetcode.sol.util.ListNode;

/**
 * https://labuladong.github.io/algo/2/19/18/
 * See note
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        // after exiting the while loop,
        // either fast is null, or fast == slow
        if (fast == null || fast.next == null) {
            // fast is null meaning there's no cycle
            return null;
        }

        // there's cycle
        slow = head;
        while (slow != fast) { // no need to check null since there's cycle
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

}
