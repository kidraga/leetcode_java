package leetcode.sol.P0684ReplaceWords;

import leetcode.sol.util.TrieSet;

import java.util.List;

public class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        // 先将词根存入TrieSet
        TrieSet set = new TrieSet();
        for (String key : dictionary) {
            set.add(key);
        }
        StringBuilder sb = new StringBuilder();
        String[] words = sentence.split(" ");
        // 处理句子中的单词
        for (int i = 0; i < words.length; i++) {
            // 在Trie树中搜索最短词根
            String prefix = set.shortestPrefixOf(words[i]);
            if (!prefix.isEmpty()) {
                // 如果搜索到了，改写词根
                // 最短的prefix会被优先搜索到
                sb.append(prefix);
            } else {
                // 否则原样放回
                sb.append(words[i]);
            }

            if (i != words.length - 1) {
                // 添加空格
                sb.append(' ');
            }
        }
        return sb.toString();
    }
}
