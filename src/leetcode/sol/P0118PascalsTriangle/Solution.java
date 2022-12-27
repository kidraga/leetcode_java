package leetcode.sol.P0118PascalsTriangle;

import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> triangle = new ArrayList<List<Integer>>();

    // Base case; first row is always [1].
    triangle.add(new ArrayList<>());
    triangle.get(0).add(1);

    for (int rowNum = 1; rowNum < numRows; rowNum++) {
      List<Integer> row = new ArrayList<>();
      List<Integer> prevRow = triangle.get(rowNum-1);

      // First element of each row is always 1.
      row.add(1);

      // calculate each element.
      // each element is equal to element above and above-and-left
      for (int j = 1; j < rowNum; j++) {
        row.add(prevRow.get(j-1) + prevRow.get(j));
      }

      // the last element is always 1.
      row.add(1);

      triangle.add(row);
    }

    return triangle;
  }
}
