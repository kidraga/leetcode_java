package leetcode.sol.P2187MinimumTimeToCompleteTrips;

import java.util.Arrays;

// TLE
// Time: O(NQP). N = time.length, Q = max(time), P = totalTrips
class Solution {
    public long minimumTime(int[] time, int totalTrips) {

        int[] temp = Arrays.copyOf(time, time.length);
        int numTripFinished = 0;
        int totalTime = 0;
        boolean shouldContinue = true;


        // extreme case: time = {Q}, totalTrips = P
        // minimumTime = QP
        // so this while loop may run QP times
        while (shouldContinue) {
            totalTime++;
            for (int i = 0; i < time.length; i++) { // O(N), N = time.length
                temp[i] = temp[i] - 1;
                if (temp[i] == 0) {
                    // one bus finished the trip
                    numTripFinished++;
                    temp[i] = time[i]; // reset
                    if (numTripFinished == totalTrips) {
                        shouldContinue = false;
                    }
                }
            }
        }
        return totalTime;
    }
}
