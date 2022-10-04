package leetcode.sol.P1641CountSortedVowelStrings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    Map<Integer, Integer> A = new HashMap<>();
    Map<Integer, Integer> E = new HashMap<>();
    Map<Integer, Integer> I = new HashMap<>();
    Map<Integer, Integer> O = new HashMap<>();
    Map<Integer, Integer> U = new HashMap<>();
    Map<Integer, Integer> N = new HashMap<>();
    List<Map<Integer, Integer>> cacheMaps = Arrays.asList(A, E, I, O, U);
    public Solution() {
        this.N.put(1, 5);
        this.A.put(1, 1);
        this.E.put(1, 1);
        this.I.put(1, 1);
        this.O.put(1, 1);
        this.U.put(1, 1);
    }
    public int countVowelStrings(int n) {

        // f(1) = 5 a1=1, e1=1, ...
        // f(2) = 5 + 4 + 3 + 2 + 1 = 15 a2=a1*5, e2=e1*4,...
        // f(3) = (5 + .. + 1) a3=f(2)
        //      + (4 + .. + 1) e3=f(2)-a2
        //      + (3 + .. + 1) i3=f(2)-a2-e2
        //      + (2 + 1)      o3=f(2)-a2-e2-i2
        //      + (1)          u3=f(2)-a2-e2-i2-o2
        // f(4) = (a4=f(3)) + (e4=f(3)-a3) + (i4=f(3)-a3-e3) + ...
        // f(n) = f(n-1) + f(n-1)-a(n-1) + f(n-1)-a(n-1)-e(n-1) + ...
        if (N.containsKey(n)) return N.get(n); // if (n==1) is included.

        int fn = 5;
        for (int i = 2; i <= n; i++) {
            int prevFn = N.get(i - 1); // f(n-1)

            int aN = prevFn;
            int eN = prevFn - A.get(i - 1);
            int iN = eN - E.get(i - 1);
            int oN = iN - I.get(i - 1);
            int uN = oN - O.get(i - 1);

            // update fn maps
            A.put(i, aN);
            E.put(i, eN);
            I.put(i, iN);
            O.put(i, oN);
            U.put(i, uN);

            fn = aN + eN + iN + oN + uN;
            N.put(i, fn);
        }
        return fn;
    }
}
