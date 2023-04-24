package com.ywb.zhengzebiaodashipipei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author yaowenbin
 * @Date 2022/10/8
 */
class Solution {

    public static void main(String[] args) {
        List<String> board = new ArrayList<>(5);
        Collections.fill(board, ".".repeat(5));
        new Solution().isMatch("mississippi", "mis*is*p*.");
    }

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        // 状态定义：前s[i]p[j]字符是否相等
        boolean[][] f = new boolean[m + 1][n + 1];
        // 状态初始：前00字符是相等的
        f[0][0] = true;
        for(int i = 0; i <= m; i++) {

            for(int j = 1; j <= n; j++) {
                // 如果是正常字符串，那么当i、j字符相等，并且两者的前一个字符串也相等时，i和j是匹配的
                if(p.charAt(j - 1) != '*') {
                    if(matches(s, p, i, j) && f[i - 1][j - 1]) {
                        f[i][j] = true;
                    }
                } else {
                    // *的含义是能够出现0次或多次，当出现0次的时候我们要去看看字符i是否能够和正则串的前2位的数对上 例如 s = aa p = aaa*，由于p的aa已经和s的aa匹配上了，而a*即使没有匹配上也符合正则的规则视为匹配上
                    // 如果没有和前2位匹配上的话，我们就按正常的去找一下是否能和j-1位匹配上，即可，即 s = aa ,p = a*.
                    if(f[i][j - 2] || matches(s, p, i, j - 1) && f[i - 1][j]) {
                        f[i][j] = true;
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if(i == 0) {
            return false;
        }

        // .可以匹配任意字符
        if(p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
