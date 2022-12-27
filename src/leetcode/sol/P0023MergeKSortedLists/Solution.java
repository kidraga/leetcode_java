package leetcode.sol.P0023MergeKSortedLists;

import leetcode.sol.util.ListNode;

import java.util.PriorityQueue;

/**
 * Time: O(Nlogk). N total number of all nodes. k: number of links
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;

        // min heap
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
                lists.length,
                (a, b) -> (a.val - b.val)
        );

        // put k heads into heap
        for (ListNode head : lists) {
            if (head != null) {
                pq.add(head);
            }
        }

        while (!pq.isEmpty()) {
            // get the minimum head from k heads
            ListNode node = pq.poll();
            p.next = node;
            if (node.next != null) {
                pq.add(node.next);
            }
            p = p.next;
        }
        return dummy.next;
    }
}