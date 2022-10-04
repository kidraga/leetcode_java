package leetcode.sol.P0093RestoreIPAddresses;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    private int n;
    private String s;
    private List<String> segments = new ArrayList<>();
    private List<String> result = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        n = s.length();
        this.s = s;
        backtrack(-1, 3);
        return result;
    }

    /**
     * previousPosition: the position of previously placed dot
     * dots: number of dots to place
     */
    private void backtrack(int previousPosition, int dots) {

        // Calculate the right most position that current dot can be placed.
        // The current dot could be placed in a range from
        // previousPosition + 1 to previousPosition + 4,
        // and cannot be placed after the last character in the string.
        int maxPosition = Math.min(n - 1, previousPosition + 4);

        // loop all possible next position
        for (int currentPosition = previousPosition + 1; currentPosition < maxPosition; currentPosition++) {
            String segment = s.substring(previousPosition + 1, currentPosition + 1);

            if (isValidSegment(segment)) {
                segments.add(segment);

                if (dots - 1 == 0) { // all 3 dots are placed
                    updateResult(currentPosition);
                } else {
                    backtrack(currentPosition, dots - 1);
                }
                segments.remove(segments.size() - 1);
            }
        }
    }

    /**
     * check if segment is valid
     * segment is valid if:
     * 1. less or equal to 255. It may be 2.552.55
     * 2. first number cannot be 0, or if the segment is 0
     */
    private boolean isValidSegment(String segment) {
        if (segment.length() > 3) return false;
        return segment.charAt(0) != '0'
            ? Integer.valueOf(segment) <= 255
            : segment.length() == 1;
    }

    private void updateResult(int position) {
        String segment = s.substring(position + 1, n);
        if (isValidSegment(segment)) {
            segments.add(segment);
            result.add(String.join(".", segments));
            segments.remove(segments.size() - 1);
        }
    }
}
