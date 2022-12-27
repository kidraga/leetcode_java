package leetcode.sol.P0556NextGreaterElementIII;

import java.util.Arrays;

class Solution {
    public int nextGreaterElement(int n) {
        /**
         * xxx6234 -> xxx6243
         * xx56432 -> xx62345
         * from right to left, find the first digit that is declining
         * for example, first one is 4 to 3, second one is 6 to 5.
         * From there, we need to swap the digit at left of the peak, i.e. 3 in the first one, and 5 in the second one.
         * Which digit to swap with?
         * We should swap with the smallest digit that is still larger than the declined digit, on the right side of thee declined digit.
         * 1. larger than declined digit.
         * 2. on the right side of the declined digit.
         * 3. as small as possible.
         * For example:
         * xxx6234
         * First right peak is at 4, declined digit is 3.
         * 3 needs to swap with something.
         * It needs to be:
         * 1. larger than 3.
         * 2. one the right side of 3.
         * 3. as small as possible.
         * So 3 should be swapped with 4.
         * same as xx56432 -> xx62345
         * xxx26543, 2 should swap with 3
         *
         * Then all digits on the right side should be sorted.
         *
         * If the digits are reverse-sorted, like 654321, return -1.
         * 12364321
         */

        String digits = String.valueOf(n);
        int firstDeclineIndex = digits.length() - 1; // the right most first declining
        while (firstDeclineIndex >= 0) {
            if (firstDeclineIndex + 1 <= digits.length() - 1) {
                char charLeft = digits.charAt(firstDeclineIndex);
                char charRight = digits.charAt(firstDeclineIndex + 1);
                if (charLeft < charRight) { // found first decline
                    // find the least larger digit of charLeft, on right side of charLeft
                    int scanIndex = digits.length() - 1;
                    int leastLargerDigitOnRightIndex = digits.length() - 1;
                    char leastLargerDigitOnRight = '9';
                    while (scanIndex > firstDeclineIndex) {
                        if (digits.charAt(scanIndex) > charLeft
                        && digits.charAt(scanIndex) < leastLargerDigitOnRight) {
                            leastLargerDigitOnRight = digits.charAt(scanIndex);
                            leastLargerDigitOnRightIndex = scanIndex;
                        }
                        scanIndex--;
                    }
                    // swap
                    char[] digitChars = digits.toCharArray();
                    digitChars[firstDeclineIndex] = leastLargerDigitOnRight;
                    digitChars[leastLargerDigitOnRightIndex] = charLeft;
                    Arrays.sort(digitChars, firstDeclineIndex+1, digitChars.length);
                    String ansStr = String.valueOf(digitChars);
                    long ansLong = Long.valueOf(ansStr);
                    if (ansLong > Integer.MAX_VALUE) {
                        return -1;
                    }
                    int ans = Integer.valueOf(ansStr);
                    return ans;
                }
            }
            firstDeclineIndex--;
        }
        return -1;
    }
}
