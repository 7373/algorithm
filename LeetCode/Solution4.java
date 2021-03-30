package acm;

import java.util.*;

public class Solution4 {


    public static void rtrt() {
        /**
         * 数据类型[][] 变量名=new 数据类型[m][];
         * m表示这个二维数组有多少个数组
         */
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        for (int x = 0; x < arr.length; x++) {
            for (int y = 0; y < arr[x].length; y++) {
                System.out.print(arr[x][y]);
            }
            System.out.println();
        }



    }
        public static void main(String[] args) {
            String str2 = "10,1,2,7,6,1,5";

            String[] arr2 = str2.split(",");
            int[] coins2 = new int[arr2.length];
            for (int j = 0; j < coins2.length; j++) {
                coins2[j] = Integer.parseInt(arr2[j]);
            }
        System.out.println(new Solution4().combinationSum2(coins2,8));



    }



    /**
     * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     * 先行后列
     * n行 m 列
     *
     * 示例:
     *
     * 现有矩阵 matrix 如下：
     *
     * [
     *   [1,   4,  7, 11, 15],  [0]
     *   [2,   5,  8, 12, 19],  [1][1]
     *   [3,   6,  9, 16, 22],  [2][2]
     *   [10, 13, 14, 17, 24],
     *   [18, 21, 23, 26, 30]
     * ]
     * 给定 target = 5，返回 true。
     *
     * 给定 target = 20，返回 false。
     *
     * 从右上角开始比较，比它大就往下数一行，比它小就往左数一列
     *
     *  如果当前位置元素比target小，则row++
     * 如果当前位置元素比target大，则col--
     * 如果相等，返回true
     * 如果越界了还没找到，说明不存在，返回false
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int n=matrix.length;
        if(0==n){
            return false;
        }
        int m=matrix[0].length;
        /**
         * 二维二分法
         */
        for(int i=0;i<n;++i){
            int left=0;
            int right=matrix[i].length-1;
            while (left<right){
                /**
                 * 无符号右移 区别>>
                 */
                int middle=left+right>>>1;
                if(matrix[i][middle]==target){
                    return true;
                }
                else if(matrix[i][middle]>target){
                    right=middle-1;
                }
                else if(matrix[i][middle]<target){
                    left=middle+1;
                }
            }
        }

        return false;
    }
    /**
     * 28. 最长连续序列
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     *
     *示例 1：
     *
     * 输入：nums = [100,4,200,1,3,2]
     * 输出：4
     * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4
     *
     * 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？
     */

    public int longestConsecutive(int[] nums) {



        return 0;
    }


    /**
     * 139. 单词拆分 middle
     * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
     *
     * 说明：
     *
     * 拆分时可以重复使用字典中的单词。
     * 你可以假设字典中没有重复的单词。
     * 示例 1：
     *
     * 输入: s = "leetcode", wordDict = ["leet", "code"]
     * 输出: true
     * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
     * 示例 2：
     *
     * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
     * 输出: true
     * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
     *      注意你可以重复使用字典中的单词。
     * 示例 3：
     *
     * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
     * 输出: false
     *
     * 输入：
     * "bb"
     * ["a","b","bbb","bbbb"]
     * 输出：
     * true
     *
     * 输入：
     * "cars"
     * ["car","ca","rs"]
     * 输出：
     * true  为什么 car先被替换了因为
     *
     * 异常case
     * 输入：
     * "cbca"
     * ["bc","ca"]
     * 输出：
     * false
     *
     * 输入：
     * "ddadddbdddadd"
     * ["dd","ad","da","b"]
     *  dd:*a*db*da*
     *
     *  ad :dd*ddbddd*d
     *  交叉replace 不能同时replace all
     * 输出：
     * false
     */
    public boolean wordBreak(String s, List<String> wordDict) {

        String origin=s;
        Set<Boolean> filterResult=new HashSet<>();
        for(String str:wordDict){
            String temp=s;
            s=s.replaceAll(str,"*");
            /**
             * 没有被替换
             */
            if(s.equals(temp)&&origin.contains(str)) {
                filterResult.add(
                        this.wordBreak(origin.replaceAll(str,"*"),
                                wordDict));
            }
        }
        filterResult.add(s.replaceAll("\\*","").equals(""));

        return filterResult.contains(Boolean.TRUE);
    }


    /**
     * 131. 分割回文串
     * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
     *
     * 回文串 是正着读和反着读都一样的字符串。
     *
     *
     *
     * 示例 1：
     *
     * 输入：s = "aab"
     * 输出：[["a","a","b"],["aa","b"]]
     * 示例 2：
     *
     * 输入：s = "a"
     * 输出：[["a"]]
     */
    public List<List<String>> partition(String s) {
        List<List<String>> result=new LinkedList<>();


        return null;
    }


    /**
     *39. 组合总和
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的数字可以无限制重复被选取。
     *
     * 说明：
     *
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。
     * 示例 1：
     *
     * 输入：candidates = [2,3,6,7], target = 7,
     * 所求解集为：
     * [
     *   [7],
     *   [2,2,3]
     * ]
     * 类似于凑硬币 只是要把所有组合找出来
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result=new LinkedList<>();
        List<Integer>[]dp=new ArrayList[target];
        /**
         * List 数组声明 初始化问题
         */
//        List[]dp2=new ArrayList[target];

//        ArrayList<Integer>[]dp3=new ArrayList<Integer>[target];
//        ArrayList[] graphArrayList = new ArrayList[4];

        /**
         * dp[本来要凑多少][还剩多少]
         */
        for(int i=0;i<candidates.length;++i){

            for(int j=0;j<=target;++j){


            }
        }

        return result;
    }
    public List<List<Integer>> combinationSumV1(int[] candidates, int target) {

        Queue<Map<List<Integer>,Integer>> filterQueue=new ArrayDeque<>();
         Map<List<Integer>,Integer>  node = null;
        List<List<Integer>> result;
        Set<List<Integer>> filterResult=new HashSet<>();
        do{
//            System.out.println("外层当前NODE:"+node);
            for(int temp:candidates){

                /**
                 * 差
                 */
                int differ;
                /**
                 * 当前选择列表
                 */
                List<Integer> selects;
                /**
                 * 刚进来
                 */
                if(null==node){
                    differ=target-temp;
                    selects=new LinkedList<>();
                    selects.add(temp);
                }
                /**
                 * 继续选择
                 */
                else {
                  Map.Entry<List<Integer>,Integer>  oneEntry=
                          node.entrySet().iterator().next();
                    /**
                     * 注意！ 这里不能再用之前的list 不然会影响后面的选择
                     */
                    selects=new LinkedList<>(oneEntry.getKey());
                    int target2=oneEntry.getValue();
                    differ=target2-temp;
                    selects.add(temp);
                }
                /**
                 * 找到结果
                 */
                if(differ==0){
                    /**
                     * 过滤重复组合
                     */
                    Collections.sort(selects);
                    filterResult.add(selects);
                }
                /**
                 * 继续做选择
                 */
                else if(differ>0){
                    Map<List<Integer>,Integer> oneNode=
                     new HashMap<>();
                    oneNode.put(selects,differ);
                    filterQueue.add(oneNode);
                }
                /**
                 * 小于0 直接不要了
                 */
                else {

                }

            }
        }
        while (( node=filterQueue.poll())!=null);
        result=new LinkedList<>(filterResult);

        return result;
    }
    /**
     *40. 组合总和 II
     * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的每个数字在每个组合中只能使用一次。
     *
     * 说明：
     *
     * 所有数字（包括目标数）都是正整数。
     * 解集不能包含重复的组合。
     * 示例 1:
     *
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 所求解集为:
     * [
     *   [1, 7],
     *   [1, 2, 5],
     *   [2, 6],
     *   [1, 1, 6]
     * ]
     */
    /**
     * 超时：换回溯法方法
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Queue<Map<Set<Integer>,Integer>> filterQueue=new ArrayDeque<>();
        Map<Set<Integer>,Integer>  node = null;
        List<List<Integer>> result;
        Set<List<Integer>> filterResult=new HashSet<>();
        do{
            for (int i = 0; i < candidates.length; ++i) {
                int temp = candidates[i];
                /**
                 * 差
                 */
                int differ;
                /**
                 * 当前选择列表 存下标 为了去重
                 */
                Set<Integer> selects;
                /**
                 * 刚进来
                 */
                if (null == node) {
                    differ = target - temp;
                    selects = new HashSet<>();
                    selects.add(i);
                }
                /**
                 * 继续选择
                 */
                else {
                    Map.Entry<Set<Integer>, Integer> oneEntry =
                            node.entrySet().iterator().next();
                    /**
                     * 注意！ 这里不能再用之前的list 不然会影响后面的选择
                     */
                    selects = new HashSet<>(oneEntry.getKey());
                    /**
                     * 过滤重复选择
                     */
                    if (selects.contains(i)) {
                        continue;
                    }
                    selects.add(i);
                    int target2 = oneEntry.getValue();
                    differ = target2 - temp;
                }
                /**
                 * 找到结果
                 */
                if (differ == 0) {
                    /**
                     * 过滤重复组合
                     */
                    List<Integer> oneResult = new LinkedList<>();
                    for (int index : selects) {
                        oneResult.add(candidates[index]);
                    }
                    Collections.sort(oneResult);
                    filterResult.add(oneResult);
                }
                /**
                 * 继续做选择
                 */
                else if (differ > 0) {
                    Map<Set<Integer>, Integer> oneNode =
                            new HashMap<>();
                    oneNode.put(selects, differ);
                    filterQueue.add(oneNode);
                }
                /**
                 * 小于0 直接不要了
                 */
                else {

                }

            }
        }
        while ((node = filterQueue.poll()) != null);
        result = new LinkedList<>(filterResult);

        return result;
    }

    public List<List<Integer>> combinationSum2V2(int[] candidates, int target) {
        Queue<Map<Set<Integer>,Integer>> filterQueue=new ArrayDeque<>();
        Map<Set<Integer>,Integer>  node = null;
        List<List<Integer>> result;
        Set<List<Integer>> filterResult=new HashSet<>();
        do{

        }
        while ((node = filterQueue.poll()) != null);
        result = new LinkedList<>(filterResult);

        return result;
    }
    /**
     * 6. Z 字形变换
     * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
     *
     * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
     *
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
     *
     * 请你实现这个将字符串进行指定行数变换的函数：
     *
     * string convert(string s, int numRows);
     *
     *
     * 示例 1：
     *
     * 输入：s = "PAYPALISHIRING", numRows = 3
     * 输出："PAHNAPLSIIGYIR"
     *
     * 示例 2：
     * 输入：s = "PAYPALISHIRING", numRows = 4
     * 输出："PINALSIGYAHRPI"
     * 解释：
     * P     I    N
     * A   L S  I G
     * Y A   H R
     * P     I
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/zigzag-conversion
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        String[] result = new String[numRows];
        Arrays.fill(result, "");
        /**
         * 当前行数
         */
        int row = 1;
        /**
         * 是否递增行数
         */
        boolean asc = true;
        for (int i = 0; i < s.length(); ++i) {
            result[row - 1] = result[row - 1] + s.charAt(i);
            /**
             * 改变行数方向
             */
            if (row == numRows) {
                /**
                 * 递增到最后一行
                 */
                asc = false;
            } else if (row == 1) {
                /**
                 * 递减到第一行
                 */
                if (!asc) {
                    asc = true;
                }
            }
            /**
             * 如果递增行
             */
            if (asc) {
                ++row;
            } else {
                --row;
            }
        }
        StringBuilder temp = new StringBuilder();
        for (String str : result) {
            temp.append(str);
        }

        return temp.toString();
    }
    /**
     * 10. 正则表达式匹配
     * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     *
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
     *
     *
     * 示例 1：
     *
     * 输入：s = "aa" p = "a"
     * 输出：false
     * 解释："a" 无法匹配 "aa" 整个字符串。
     * 示例 2:
     *
     * 输入：s = "aa" p = "a*"
     * 输出：true
     * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
     *
     */

    public boolean isMatchV1(String s, String p) {

        return false;
    }


    /**
     *44. 通配符匹配
     * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
     *
     * '?' 可以匹配任何单个字符。
     * '*' 可以匹配任意字符串（包括空字符串）。
     * 两个字符串完全匹配才算匹配成功。
     *
     * 说明:
     *
     * s 可能为空，且只包含从 a-z 的小写字母。
     * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
     * 示例 1:
     *
     * 输入:
     * s = "aa"
     * p = "a"
     * 输出: false
     * 解释: "a" 无法匹配 "aa" 整个字符串。
     * 示例 2:
     *
     * 输入:
     * s = "aa"
     * p = "*"
     * 输出: true
     * 解释: '*' 可以匹配任意字符串。
     * 示例 3:
     *
     * 输入:
     * s = "cb"
     * p = "?a"
     * 输出: false
     * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
     */

    public boolean isMatch(String s, String p) {

        boolean result=true;
        for(int i=0;i<p.length();++i){
//            for(int j=0;j<s.length();++j){
                if(p.charAt(i)==s.charAt(i)){
                    continue;
                }
                else if(p.charAt(i)=='?'){
                    continue;
                }
                /**
                 * 考虑中间是*的情况
                 */
                else if(p.charAt(i)=='*'){
                    continue;
                }
                else {
                    return false;
                }

//            }
        }


        return true;
    }
}
