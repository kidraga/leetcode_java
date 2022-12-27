package leetcode.sol.P1025DivisorGame;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean divisorGame(int n) {
        // n = 1, Alice lose
        // n = 2, Alice win
        // n = 3, Alice lose
        // n = 4, choose 1, 2. 1: Alice win, 2: Alice lose -> Alice win (always play optimal)
        // f(n) = if first player can win with n
        // f(4) = (!f(3) || !f(2))
        // f(n) = for any i that 1<=i<n and n % i = 0, if one of the f(i) is False, then f(n) is true.

        if (n == 1) return false;
        if (n == 2) return true;
        if (n == 3) return false;
        Map<Integer, Boolean> nToWin = new HashMap<>();
        nToWin.put(1, false);
        nToWin.put(2, true);
        nToWin.put(3, false);
        for (int i = 4; i <= n; i++) {
            boolean win = false;
            for (int j = 1; j < i; j++) {
                if (i%j == 0
                   && !nToWin.get(i - j)) {
                    win = true;
                    break;
                }
            }
            nToWin.put(i, win);
        }
        return nToWin.get(n);
    }
}
