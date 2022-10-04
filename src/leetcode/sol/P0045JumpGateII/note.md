## Solution 2
What's being memorized?

In the loop:
```java
    int minSteps = n;
    for (int i = 1; i <= nums[pos]; i++) {
        minSteps = Math.min(minSteps, 1 + jump(pos + i, nums));
    }
```
remember the `jump(pos + i, nums)` already returns the least step from (pos + i) to the end.
Let's see a case:
[1, 100, x, x, 3, x, x, ...]
Let's say that we are at index 1, inside the loop, i will increase from 1 to 100.
When i is 1, there's maybe a way to get to position 4 (where element 3 is). And in that recursive call, jump(4, nums) will be calculated already.
When i is 4, it comes to element 3 again. Since we already calculated jump(4, nums), we can return directly.

## Solution 3
The dp solution is essentially the same as the recursive brute force solution.
For any dp solution, we are trying to resolve problem Xn. But we need to know Xn-1 to resolve Xn. Eventually, we need to resolve X0.
And we know the initial state X0, so we can resolve X1, then eventually we resolve Xn.

In the recursion, same thing is happening. When we call recursion we are not finishing the upper level function yet (the Xn).
The recursive call keeps going until it hit base case X0, then the return begins and functions are terminated level by level, until Xn.