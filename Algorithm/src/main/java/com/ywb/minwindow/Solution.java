package com.ywb.minwindow;

import java.util.*;

class Solution {

    public static void main(String[] args) {
        new Solution().minWindow("ab", "a");
    }
    public String minWindow(String s, String t) {
        // 什么时候扩大窗口？ 当窗口中的字符还未满足目标即t字符串时
        // 什么时候应该缩小窗口？ 当满足目标t字符串时
        // 什么时候应该更新答案？应该在缩小窗口时更新结果
        Map<Character, Integer> window = new HashMap<>(), needs = new HashMap<>();
        for (char c : t.toCharArray()) {
            needs.compute(c, (k, v) -> (v == null ? 1 : ++v));
        }

        int left = 0, right = 0;

        int valid = 0, start = 0, end = 0,  len = Integer.MAX_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right++);
            if (needs.get(c) != null) {
                window.compute(c, (k, v) -> (v == null ? 1 : ++v));
                if(window.get(c).equals(needs.get(c))) {
                    valid++;
                }
            }

            // 当window中的有效字符满足need的个数，开始缩小窗口
            while (valid == needs.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                    end = left + len;
                }
                char d = s.charAt(left++);
                // 说明要删除的是一个t字符串中的，字符串
                if(needs.get(d) != null) {
                    // 如果window中的个数不足则有效字符减少
                    if(window.get(d).equals(needs.get(d))) {
                        valid--;
                    }
                    window.compute(d, (k, v) -> (--v));
                }

            }


        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, right);

    }
}
