package acm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Solution11 {

    public static void main(String[] args) {
        String str = "0,3,6,7,1";
        String[] arr = str.split(",");
        int[] coins = new int[arr.length];
        for (int j = 0; j < coins.length; j++) {
            coins[j] = Integer.parseInt(arr[j]);
        }
        System.out.println(new Solution11().countSubstrings("ABC"));
    }

    /**
     * 300. 最长递增子序列
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * <p>
     * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     */

    //子串了写成
    public int findLengthOfLCIS(int[] nums) {
        int res = 0, pre = Integer.MIN_VALUE;
        HashSet<Integer> sets = new HashSet<>();
        for (int temp : nums) {
            if (temp <= pre) {
                res = Math.max(res, sets.size());
                sets.clear();
                sets.add(temp);
                pre = temp;
                continue;
            }
            sets.add(temp);
            pre = temp;
            res = Math.max(res, sets.size());
        }
        return res;
    }

    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return len;
        }
        int[] dp = new int[len];
        Arrays.fill(dp, 1);

        int res = dp[0];
        for (int i = 1; i < len; i++) {
            // 看以前的，比它小的，说明可以接在后面形成一个更长的子序列
            // int curMax = Integer.MIN_VALUE; 不能这样写，万一前面没有比自己小的，
            // 这个值就得不到更新
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            // 在遍历的时候同时找 dp 数组的最大值
            res = Math.max(res, dp[i]);
        }
        return res;
    }


    /**
     * 面试题 17.18. 最短超串
     * 假设你有两个数组，一个长一个短，短的元素均不相同。找到长数组中包含短数组所有的元素的最短子数组，其出现顺序无关紧要。
     * <p>
     * 返回最短子数组的左端点和右端点，如有多个满足条件的子数组，返回左端点最小的一个。若不存在，返回空数组。
     */
    //提交记录
    //40 / 42 个通过测试用例 超时
    public int[] shortestSeq(int[] big, int[] small) {
        HashSet<Integer> set = new HashSet<>();
        for (int s : small) {
            set.add(s);
        }
        HashSet<Integer> set2 = new HashSet<>(set);
        int start, end, min = Integer.MAX_VALUE;
        int[] res = new int[2];
        for (int i = 0; i < big.length; ++i) {
            if (set2.contains(big[i])) {
                /**
                 * 不满足长度case
                 */
                if (set2.size() > big.length - i) {
                    break;
                }
                set2.remove(big[i]);
                start = i;
                //一个值 直接返回
                if (set2.size() == 0) {
                    end = i;
                    //更新值
                    res = new int[2];
                    res[0] = start;
                    res[1] = end;
                    return res;
                }
                for (int j = i + 1; j < big.length; ++j) {
                    set2.remove(big[j]);
                    if (set2.size() == 0) {
                        end = j;
                        if (end - start < min) {
                            //更新值
                            res = new int[2];
                            res[0] = start;
                            res[1] = end;
                            min = end - start;
                        }
                    }
                }
                //重置参数
                start = 0;
                end = 0;
                set2 = new HashSet<>(set);
            }
        }
        return min == Integer.MAX_VALUE ? (new int[0]) : res;
    }


    /**
     * 647. 回文子串
     * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
     * <p>
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
     */
    //129 / 130 个通过测试用例
    public int countSubstringsV1(String s) {
        HashMap<String, Boolean> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i < s.length(); ++i) {
            for (int j = i + 1; j <= s.length(); ++j) {
                String str = s.substring(i, j);
                Boolean has = map.get(str);
                if (Boolean.TRUE.equals(has)) {
                    res++;
                } else if (Boolean.FALSE.equals(has)) {
                    //
                } else {
                    boolean result = isReverse(str);
                    if (result) {
                        res++;
                    }
                    map.put(str, result);
                }
            }
        }
        return res;
    }

    //ac
    public int countSubstrings(String s) {
        HashSet<String> map = new HashSet<>();
        int res = 0;
        for (int i = 0; i < s.length(); ++i) {
            for (int j = i + 1; j <= s.length(); ++j) {
                String str = s.substring(i, j);
                if (map.contains(str)) {
                    res++;
                    continue;
                }
                boolean result = isReverse(str);
                if (result) {
                    res++;
                    map.add(str);
                }
            }
        }
        return res;
    }

    static boolean isReverse(String str) {
        for (int i = 0; i < str.length() / 2; ++i) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
