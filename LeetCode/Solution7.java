package acm;

import java.util.*;

public class Solution7 {
    public static void main(String[] args) {
        String str = "5,1,3,4,10,1,4,9,2,1";
        String[] arr = str.split(",");
        int[] coins = new int[arr.length];
        for (int j = 0; j < coins.length; j++) {
            coins[j] = Integer.parseInt(arr[j]);
        }
        System.out.println(new Solution7().minSubArrayLen(15,coins));


        System.out.println(new Solution7().numWaysV1(46));
    }
    /**
     *
     * 给定一个 N 叉树，返回其节点值的 前序遍历 。
     *
     * N 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
     *
     *  
     *
     * 进阶：
     *
     * 递归法很简单，你可以使用迭代法完成此题吗?
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public List<Integer> preorderV1(Node root) {
        List<Integer> res=new LinkedList<>();
        if(root!=null){
            res.add(root.val);
            for(Node temp:root.children){
                res.addAll(preorder(temp));
            }
        }
        return res;
    }


    public List<Integer> preorder(Node root) {
        List<Integer> res=new LinkedList<>();
        Stack<Node> nodeStack=new Stack<>();
        if(root!=null){
            nodeStack.add(root);
        }
        while(!nodeStack.isEmpty()){
            Node temp=nodeStack.pop();
            res.add(temp.val);
            if(temp.children.size()==0){
                continue;
            }
            for(int i=temp.children.size()-1;i>=0;--i ){
                nodeStack.add(temp.children.get(i));
            }
        }
        return res;
    }

    /**
     *给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-subarray
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public int maxSubArray(int[] nums) {
        int max=nums[0];
        /**
         * 记录之前计算的连续子数组和
         */
        int beforeSum=nums[0];
        for(int temp:nums){
            int tempMax;
            if(temp>=beforeSum+temp){
                tempMax=temp;
                beforeSum=tempMax;
            }else {
                beforeSum+=temp;
                tempMax=beforeSum;
            }
            max=Math.max(max,tempMax);
        }
        return max;
    }


    /**
     *给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
     *
     * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
     *
     *  
     *
     * 示例 1：
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public List<List<Integer>> levelOrder(Node root) {
        if(root==null){
            return new ArrayList<>();
        }
        List<List<Integer>> res = new LinkedList<>();
        Stack<List<Node>> nodeStack = new Stack<>();
        nodeStack.add(Collections.singletonList(root));
        List<Integer> oneRes;
        List<Node> oneNodeList;
        while (!nodeStack.isEmpty()) {
            List<Node> nodeList = nodeStack.pop();
            oneRes = new LinkedList<>();
            oneNodeList = new LinkedList<>();
            for (Node node : nodeList) {
                if (node == null) {
                    continue;
                }
                oneRes.add(node.val);
                if (node.children.isEmpty()) {
                    continue;
                }
                oneNodeList.addAll(node.children);
            }
            if (!oneNodeList.isEmpty()) {
                nodeStack.add(oneNodeList);
            }
            res.add(oneRes);
        }
        return res;
    }

    /**
     *209. 长度最小的子数组
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。
     *
     * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：target = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     */
    public int minSubArrayLen(int target, int[] nums) {
        /**
         * 当前子数组的和
         */
        int sum = 0;
        int res = 0;
        int result = 0;
        /**
         * 子数组开始的下标
         */
        int startIndex = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] >= target) {
                return 1;
            }
            if (sum == 0) {
                startIndex = i;
            }
            if (sum >= target && res == 2) {
                continue;
            }
            sum += nums[i];
            res += 1;
            /**
             * 当前数组满足条件 从左向右开始减少
             */
            if (sum >= target) {
                if (result == 0) {
                    result = res;
                }
                for (int j = startIndex; j < i; ++j) {
                    if (sum - nums[j] < target) {
                        startIndex = j;
                        break;
                    }
                    sum -= nums[j];
                    res -= 1;
                    result = Math.min(result, res);
                }
            }

        }

        return result;
    }
    public int numWaysV1(int n) {
        if (n == 0) {
            return 1;
        }
        long[] dp = new long[n + 3];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; ++i) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }
        return (int) (dp[n] % 1000000007);
    }

}
