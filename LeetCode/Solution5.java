package acm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution5 {




    /**
     *30. 串联所有单词的子串
     * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
     *
     * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
     *
     *
     *
     * 示例 1：
     *
     * 输入：
     *   s = "barfoothefoobarman",
     *   words = ["foo","bar"]
     * 输出：[0,9]
     * 解释：
     * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
     * 输出的顺序不重要, [9,0] 也是有效答案。
     * 示例 2：
     *
     * 输入：
     *   s = "wordgoodgoodgoodbestword",
     *   words = ["word","good","best","word"]
     * 输出：[]
     */

    public List<Integer> findSubstring(String s, String[] words) {

        return null;
    }


    /**
     *41. 缺失的第一个正数
     * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
     *
     *
     *
     * 进阶：你可以实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案吗？
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,0]
     * 输出：3
     * 示例 2：
     *
     * 输入：nums = [3,4,-1,1]
     * 输出：2
     * 示例 3：
     *
     * 输入：nums = [7,8,9,11,12]
     * 输出：1
     */

    public int firstMissingPositive(int[] nums) {

        Set<Integer> filterSet=new HashSet<>();
        for(int temp:nums){
            if(temp>0){
                filterSet.add(temp);
            }
        }
        int i=1;
        do{
            if(!filterSet.contains(i)){
                return i;
            }
            ++i;
        }while (true);
    }
}
