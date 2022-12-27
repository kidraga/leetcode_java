package leetcode.sol.P0022GenerateParentheses;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private void backtrack(
            List<String> result,
            StringBuilder cur,
            int open,
            int close,
            int n
    ) {
        if (cur.length() == n * 2) {
            result.add(cur.toString());
            return;
        }

        if (open < n) {
            cur.append("(");
            backtrack(result, cur, open + 1, close, n);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(")");
            backtrack(result, cur, open, close + 1, n);
            cur.deleteCharAt(cur.length() - 1);
        }

    }
}
