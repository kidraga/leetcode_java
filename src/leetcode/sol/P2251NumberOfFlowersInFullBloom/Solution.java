package leetcode.sol.P2251NumberOfFlowersInFullBloom;

import java.util.PriorityQueue;

class Solution {
    public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        /**
         * 3 events:
         * 0 - bloom
         * 1 - person
         * 2 - die
         */

        // {time position, event type, if person -> index in persons}
        // first sort by time, then by event type
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        // add the person events
        for (int i = 0; i < persons.length; i++) {
            // {time, person event -> 1, index in persons[]}
            pq.offer(new int[]{persons[i], 1, i});
        }

        // add the blossom events
        for (int[] flower : flowers) {
            // {time of bloom, event id -> 0}
            pq.offer(new int[]{flower[0], 0});
            // {time of die, event id -> 2}
            pq.offer(new int[]{flower[1], 1});
        }

        // traverse them all
        int[] res = new int[persons.length];
        int numEvents = pq.size();
        int currBlooms = 0;
        for (int i = 0; i < numEvents; i++) {
            int[] currEvent = pq.poll();
            // if bloom, increment blooms
            if (currEvent[1] == 0) {
                currBlooms++;
                // if die, decrement blooms
            } else if (currEvent[1] == 2) {
                currBlooms--;
                // if person, set their index to # blooms
            } else {
                int personIndex = currEvent[2];
                res[personIndex] = currBlooms;
            }
        }
        return res;
    }
}
