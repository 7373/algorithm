package acm;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution5 {


    public static void main(String[] args) {
        String str = "1,2,3,4";
        String[] arr = str.split(",");
        int[] coins = new int[arr.length];
        for (int j = 0; j < coins.length; j++) {
            coins[j] = Integer.parseInt(arr[j]);
        }
        System.out.println(new Solution5().productExceptSelf(coins));
    }

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


    /**
     *给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: [2,3,-2,4]
     * 输出: 6
     * 解释: 子数组 [2,3] 有最大乘积 6。
     * 示例 2:
     *
     * 输入: [-2,0,-1]
     * 输出: 0
     * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public int maxProductV1(int[] nums) {
        /**
         * 记录当前最大值
         */
        int max=Integer.MIN_VALUE;
        int beforeSum=1;
        for(int i=0;i<nums.length;++i){

            int tempMax=Math.max(beforeSum*nums[i],nums[i]);
            /**
             * 乘法不能这么算  有符号
             * 输入：
             * [-2,3,-4]
             * 输出：
             * 3
             * 预期：
             * 24
             */
            if(tempMax==beforeSum*nums[i]){
                beforeSum=beforeSum*nums[i];
            }
            else {
                beforeSum=nums[i];
            }
            int resultMax=Math.max(tempMax,max);
            max=resultMax;
        }

        return max;
    }


    public int maxProduct(int[] nums) {
        /**
         * 记录当前最大值
         */
        int max = Integer.MIN_VALUE;
        if (nums.length == 1) {
            return nums[0];
        }
        for (int i = 0; i < nums.length; ++i) {
            int tempI = nums[i];
            max = Math.max(tempI, max);
            if (i == nums.length - 1) {
                break;
            }
            for (int j = i + 1; j < nums.length; ++j) {
                tempI = tempI * nums[j];
                max = Math.max(tempI, max);
            }
        }

        return max;
    }


    /**
     *713. 乘积小于K的子数组
     * 给定一个正整数数组 nums。
     *
     * 找出该数组内乘积小于 k 的连续的子数组的个数。
     *
     * 示例 1:
     *
     * 输入: nums = [10,5,2,6], k = 100
     * 输出: 8
     * 解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
     * 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
     */
    //超时  全是1
    public int numSubarrayProductLessThanK(int[] nums, int k) {

        int result=0;
        for (int i = 0; i < nums.length; ++i) {
            int tempI = nums[i];
            if(tempI<k){
                result++;
            }
            if (i == nums.length - 1) {
                break;
            }
            for (int j = i + 1; j < nums.length; ++j) {
                tempI = tempI * nums[j];
                if(tempI>=k){
                    break;
                }
                result++;
            }
        }
        return result;
    }


    /**
     * 238. 除自身以外数组的乘积
     * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
     *
     *
     *
     * 示例:
     *
     * 输入: [1,2,3,4]
     * 输出: [24,12,8,6]
     *
     * 异或运算  a^a=0  0^a=a
     */

    public int[] productExceptSelfV1(@NotNull int[] nums) {
        int []result=new int[nums.length];
        int product=1;
        for(int num:nums){
            product*=num;
        }
        for(int i=0;i<nums.length;++i){
            result[i]=product^nums[i];
        }
        return result;
    }

    /**
     * 超出时间限制
     *
     */
    public int[] productExceptSelfV2(int[] nums) {
        int []result=new int[nums.length];
        Arrays.fill(result,1);
//        int product=1;
//        for(int num:nums){
//            product*=num;
//        }
//        int beforeProduct=1;
        for(int i=0;i<nums.length;++i){
//            result[i]=beforeProduct;
//            beforeProduct*=nums[i];
            for(int j=0;j<nums.length;++j){
                if(j==i){
                    continue;
                }
                result[j]*=nums[i];

            }
        }
        return result;
    }


    /**
     * 超过内存限制
     */
    public int[] productExceptSelfV22(int[] nums) {
        int[] result = new int[nums.length];
//        Arrays.fill(result,1);
        int[][] dp = new int[nums.length][nums.length];
        int beforeSum = 1;
        int afterSum = 1;
        for (int i = 0; i < nums.length; ++i) {
            beforeSum *= nums[i];
            afterSum *= nums[nums.length - 1 - i];
            dp[0][i] = beforeSum;
//            if(i==nums.length-1){
//                continue;
//            }
            dp[nums.length - 1][nums.length - 1 - i] = afterSum;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (i == 0) {
                result[i] = dp[nums.length - 1][i + 1];
                continue;
            }
            if (i == nums.length - 1) {
                result[i] = dp[0][i - 1];
                continue;
            }
            result[i] = dp[0][i - 1] * dp[nums.length - 1][i + 1];
        }
        return result;
    }


    /**
     * 优化
     * 当前位置的结果就是它左部分的乘积再乘以它右部分的乘积。因此需要进行两次遍历，第一次遍历用于求左部分的乘积，第二次遍历在求右部分的乘积的同时，再将最后的计算结果一起求出来
     */
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int len = nums.length;
        int[] res = new int[len];
        int left = 1;
        for (int i = 0; i < len; i++) {
            // 防止越界
            if (i > 0) {
                left = left * nums[i - 1];
            }
            res[i] = left;
        }
        int right = 1;
        for (int i = len - 1; i >= 0; i--) {
            // 防止越界
            if (i < len - 1) {
                right = right * nums[i + 1];
            }
            res[i] *= right;
        }
        return res;
    }
}
