package com.ywb.longestsubstringwithoutrepeatingcharacters;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author yaowenbin
 * @Date 2022/10/24
 */
class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstring("pwwkew"));
    }

    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0;
        Map<Character, Integer> window = new HashMap<>();

        int ret= 0;

        while(right < s.length()) {
            char c = s.charAt(right);
            right++;
            window.compute(c, (k, v) -> (v == null ? 1 : ++v));
            System.out.println(window.get(c));
            while(window.getOrDefault(c, 0) > 1) {
                char d = s.charAt(left);
                left++;
                window.computeIfPresent(d, (k, v) -> (--v));
            }
            ret = Math.max(ret, right - left);
        }
        return ret;
    }
}
