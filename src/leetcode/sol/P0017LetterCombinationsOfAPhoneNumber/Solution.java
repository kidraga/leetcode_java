package leetcode.sol.P0017LetterCombinationsOfAPhoneNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Solution {

    private List<String> combinations = new ArrayList<>();
    private Map<Character, String> letters = Stream.of(new Object[][] {
            {'2', "abc"},
            {'3', "def"},
            {'4', "ghi"},
            {'5', "jkl"},
            {'6', "mno"},
            {'7', "pars"},
            {'8', "tuv"},
            {'9', "wxyz"},
    }).collect(Collectors.toMap(data -> (Character) data[0], data -> (String) data[1]));
    private String phoneDigits;

    public List<String> letterCombinations(String digits) {
        // if the input is empty, immediately return an empty answer array
        if (digits.length() == 0) return combinations;

        phoneDigits = digits;
        backtrack(0, new StringBuilder());
        return combinations;
    }

    private void backtrack(int index, StringBuilder path) {
        if (path.length() == phoneDigits.length()) {
            combinations.add(path.toString());
            return;
        }

        // get the letters that the current digit maps to, and loop through them
        String possibleLetters = letters.get(phoneDigits.charAt(index));
        for (char letter : possibleLetters.toCharArray()) {
            path.append(letter);
            backtrack(index + 1, path);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
