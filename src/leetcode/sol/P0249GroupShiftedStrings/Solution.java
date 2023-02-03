package leetcode.sol.P0249GroupShiftedStrings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Block/Square店面题
 * 思路: 判断两个东西相同本质上就是需要把他们转换(encode)成某种hash
 * 只要hash相同就说明相同
 * 针对这道题, 我们可以把每个单词换成字母间的相对距离, 以第一个字母为起始值
 * 例如"abc" -> 0,1,2, bc到a的距离
 * "bcd" -> 0, 1, 2, cd到b的距离
 *
 * 另一种思路: 把所有字母都hash到以a开头, 以及和a的距离, 本质上和上面一样
 * 例如"cdf", c到a有2, 那么所有字母减2 -> 0,1,3
 */
class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result = new ArrayList<>();

        Map<String, List<String>> hashToList= new HashMap<>();
        for (String word : strings) {
            String wordHash = getHash(word);
            List<String> wordList = hashToList.getOrDefault(wordHash, new ArrayList<String>());
            wordList.add(word);
            hashToList.put(wordHash, wordList);
        }

        for (Map.Entry<String, List<String>> entry : hashToList.entrySet()) {
            result.add(entry.getValue());
        }

        return result;
    }

    private String getHash(String word) {
        if (word.equals("")) return word;
        char[] chars = word.toCharArray();
        int startValue = chars[0] - 'a';
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            int distance = (chars[i] - 'a' + 26 - startValue) % 26; // +26 then % to avoid negative
            sb.append("#").append(distance); // use # as separator
        }
        return sb.toString();
    }
}
