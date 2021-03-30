package acm;

import java.util.*;

public class Solution3Node {


    public static void main(String[] args) {

        System.out.println("请输入几个数并用逗号隔开：");
//        Scanner sc = new Scanner(System.in);
//        String str = sc.next().toString();
        String str = "2,1,3";
        String[] arr = str.split(",");
        int[] coins = new int[arr.length];
        for (int j = 0; j < coins.length; j++) {
            coins[j] = Integer.parseInt(arr[j]);
        }
        System.out.println("请输入几个数并用逗号隔开：");
//        Scanner sc2 = new Scanner(System.in);
//        String str2 = sc2.next().toString();
        String str2 = "4,2,3";

        String[] arr2 = str2.split(",");
        int[] coins2 = new int[arr2.length];
        for (int j = 0; j < coins2.length; j++) {
            coins2[j] = Integer.parseInt(arr2[j]);
        }


//        System.out.println(new Solution2().change(5, coins2));
//        System.out.println(new Solution2().canPartition(coins2));
//        System.out.println(new Solution3Node().beibao(coins, coins2, 4));

        System.out.println(new Solution3Node().numDistinctV3("babgbag", "bag"));
//        int amount = sc.nextInt();
//        System.out.println(new Solution().coinChange(coins, amount));
//        System.out.println(new Solution3Node().longestPalindromeSubseq("aacabdkacaa"));

//        System.out.println(new Solution3Node().longestPalindrome("aacabdkacaa"));

    }

    /**
     * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     * <p>
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (null == root) {
            return result;
        }
        /**
         * fifo
         */
//        Queue<TreeNode> treeNodeQueue = new LinkedBlockingQueue<>();
        /**
         * LIFO
         */
        Stack<TreeNode> treeNodeStack = new Stack<>();
        Stack<TreeNode> treeNodeStack2 = new Stack<>();
        treeNodeStack.add(root);
        List<Integer> oneResult;
        do {
            oneResult = new LinkedList<>();
            /**
             * 一层遍历
             */
            if (!treeNodeStack.isEmpty()) {
                while (!treeNodeStack.isEmpty()) {
                    TreeNode treeNode = treeNodeStack.pop();
                    oneResult.add(treeNode.val);
                    if (null != treeNode.left) {
                        treeNodeStack2.add(treeNode.left);
                    }
                    if (null != treeNode.right) {
                        treeNodeStack2.add(treeNode.right);

                    }
                }
                result.add(oneResult);
            }
            /**
             * 给定二叉树 [3,9,20,null,null,15,7],
             *
             *     3
             *    / \
             *   9  20
             *     /  \
             *    15   7
             * 返回锯齿形层序遍历如下：
             *
             * [
             *   [3],
             *   [20,9],
             *   [15,7]
             * ]
             *
             */
            else {
                while (!treeNodeStack2.isEmpty()) {
                    TreeNode treeNode = treeNodeStack2.pop();
                    oneResult.add(treeNode.val);
                    if (null != treeNode.right) {
                        treeNodeStack.add(treeNode.right);
                    }
                    if (null != treeNode.left) {
                        treeNodeStack.add(treeNode.left);
                    }
                }
                result.add(oneResult);
            }
        } while (!treeNodeStack2.isEmpty() || !treeNodeStack.isEmpty());
        return result;
    }


    /**
     * cls  最长子序列
     */
    public int findLengthCls(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        for (int i = 0; i <= len1; ++i) {
            for (int j = 0; j <= len2; ++j) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println("");
        }
        return dp[len1][len2];
    }


    /**
     * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
     * <p>
     * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/distinct-subsequences
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * dp[][]
     */
    public int numDistinctV1(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();
        Set<List<Integer>> filterSet = new HashSet<>();
        /**
         * 输入：s = "rabbbit", t = "rabbit"
         * 输出：3
         * 解释：
         * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
         * (上箭头符号 ^ 表示选取的字母)
         * rabbbit
         * ^^^^ ^^
         * rabbbit
         * ^^ ^^^^
         * rabbbit
         * ^^^ ^^^
         *   r a b b i t
         * r 1 0 0 0 0 0
         * a 0 1 0 0 0 0
         * b 0 0 1 1 0 0
         * b 0 0 1 1 0 0
         * b 0 0 1 1 0 0
         * i 0 0 0 0 1 0
         * t 0 0 0 0 0 1
         *
         */
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                /**
                 *
                 */
                if (s.charAt(i - 1) == t.charAt(j - 1)) {

                } else if (s.charAt(i - 1) != t.charAt(j - 1)) {

                }


            }
        }

        for (int i = 0; i <= len1; ++i) {
            for (int j = 0; j <= len2; ++j) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println("");
        }
        return 0;
    }

    /**
     * 超出时间限制
     */
    public int numDistinctV3(String s, String t) {
//        Set<Integer>  selects;
        Map<List<Integer>, String> selectMap = new HashMap<>();
//        Set<List<Integer>> filterResult = new HashSet<>();
        Set<String>  filterNo=new HashSet<>();
        Map<List<Integer>, String> addMap;
        selectMap.put(new LinkedList<>(), "");
        int result=0;
        do {
            addMap = new HashMap<>(selectMap.size());
            List<Integer> addSelect;
            for (Map.Entry<List<Integer>, String> oneSelectEntry : selectMap.entrySet()) {
                List<Integer> oneSelect = oneSelectEntry.getKey();
                int last = oneSelect.isEmpty() ? -1 : oneSelect.get(oneSelect.size() - 1);
                String value = oneSelectEntry.getValue();
                for (int i = last + 1; i < s.length(); ++i) {
                    String temp = value + s.charAt(i);
                    if(filterNo.contains(temp)){
                        continue;
                    }
                    if (!t.startsWith(temp)) {
                        filterNo.add(temp);
                        continue;
                    }
                    /**
                     * 相等 或者继续选择
                     */
                    if (t.equals(temp)) {
//                        addSelect.add(i);
//                        filterResult.add(addSelect);
                        result++;
                        continue;
                    }
                    addSelect = new LinkedList<>(oneSelect);
                    addSelect.add(i);
//                    System.out.println("temp:"+temp+addSelect);
                    addMap.put(addSelect, temp);
                }
            }
            selectMap.clear();
            selectMap = new HashMap<>(addMap);
        } while (!selectMap.isEmpty());

//        return filterResult.size();
        return result;
    }


    public int numDistinctV4(String s, String t) {
        Map<List<Integer>, String> selectMap = new HashMap<>(t.length());
//        Set<String>  filterNo=new HashSet<>();
        Map<List<Integer>, String> addMap;
        selectMap.put(new LinkedList<>(), "");
        int result=0;
        do {
            addMap = new HashMap<>(selectMap.size());
            List<Integer> addSelect;
            for (Map.Entry<List<Integer>, String> oneSelectEntry : selectMap.entrySet()) {
                List<Integer> oneSelect = oneSelectEntry.getKey();
                int last = oneSelect.isEmpty() ? -1 : oneSelect.get(oneSelect.size() - 1);
                String value = oneSelectEntry.getValue();
                for (int i = last + 1; i < s.length(); ++i) {
                    String temp = value + s.charAt(i);
//                    if(filterNo.contains(temp)){
//                        continue;
//                    }
//                    if (!t.startsWith(temp)) {
//                        filterNo.add(temp);
//                        continue;
//                    }
                    if (!(t.charAt(temp.length()-1) ==s.charAt(i))) {
//                        filterNo.add(temp);
                        continue;
                    }
                    /**
                     * 相等 或者继续选择
                     */
//                    if (t.equals(temp)) {
//                        result++;
//                        continue;
//                    }
                    if (t.length()==temp.length()) {
                        result++;
                        continue;
                    }
                    addSelect = new LinkedList<>(oneSelect);
                    addSelect.add(i);
//                    System.out.println("temp:"+temp+addSelect);
                    addMap.put(addSelect, temp);
                }
            }
            selectMap.clear();
            selectMap = new HashMap<>(addMap);
        } while (!selectMap.isEmpty());
        return result;
    }


    /**
     * 结果不对
     */
    public int numDistinctV2(String s, String t) {
        if(s.equals(t)){
            return 1;
        }
        int len1 = s.length();
        int len2 = t.length();
        int result = 0;
        int dp[][] = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                if (dp[i][j] == len2 - 1) {
                    result++;
                }
            }
        }
        return result;
    }
    public int numDistinct(String s, String t) {
        if(s.equals(t)){
            return 1;
        }
        HashMap<Character, List<Integer>> characterIntegerHashMap = new HashMap<>();
        /**
         * 过滤选择的结果
         */
        HashSet<String> filterSet = new HashSet<>();
        int len1 = s.length();
        int len2 = t.length();
        int result = 0;
        String filterStringS = "";
        for (int i = 0; i < len2; i++) {
//            List<Integer> integerLinkedList=new ArrayList<>();
            characterIntegerHashMap.put(t.charAt(i), new ArrayList<>());
        }
        for (int i = 0; i < len1; i++) {
            List<Integer> indexList = characterIntegerHashMap.get(s.charAt(i));
            if (null != indexList) {
                indexList.add(i);
                characterIntegerHashMap.put(s.charAt(i),
                        indexList);
                filterStringS = filterStringS + s.charAt(i);
            }
        }
        /**
         * 全排列 排位选择
         */



        return result;
    }

    /**
     * 找出最长子串
     */
    // 如，传递的参数为 "abcdef" 和"defg"
    public static String getMaxSubString(String maxString,String minString){
        //1. 必须保证 第一个字符串的长度是长的。第二个是短的。
        if(minString.length()>maxString.length()){
            // 重新调用这个方法
            return getMaxSubString(minString, maxString);
        }
        //2. 判断一下，是否直接包含，如果是的话，就不用进行阵列转换了。
        if(maxString.contains(minString)){
            return minString;
        }
        //3. 取出长度，转换相对应的矩阵。 通常，长的为y,短的为x.
        int maxLength=maxString.length();
        int minLength=minString.length();
        // 构建二维数组
        int [][] conver=new int[minLength][maxLength];
        int maxValue=0; //最大的值。
        int maxIndex=0;//最大的索引。
        //4. 对这个矩阵进行相应的放值。
        for (int i = 0; i <minLength; i++) {
            for(int j=0;j<maxLength;j++){
                //5.判断一下，值是否相同。 如果相同，
                if(minString.charAt(i)==maxString.charAt(j)){
                    //相同了，看是第几行，第几列。 第1行或者第1列的为1
                    if(i==0||j==0){
                        conver[i][j]=1;
                    }else{
                        conver[i][j]=conver[i-1][j-1]+1; //为左上角的值加1.
                        if(maxValue<conver[i][j]){ // 整个数组的最大值。 也可以是<= < 时表示取第一个，<=为最后一个。(如果存在多个的情况下)
                            maxValue=conver[i][j]; //取出那个最大的值。
                            maxIndex=i; //取出那个最大的列索引。
                        }
                    }

                }else{
                    conver[i][j]=0;  //如果不相同，为0.
                }
            }

        }
        //5. 根据最大的索引和最大的值，来判断截取那个最大的子字符串。
        if(maxValue!=0&&maxIndex!=0){ // 双重判断，如果有值的话。
            // maxIndex 为2  maxValue 为3.  开始处的值为0, 结束处的值为3 (不包括。)
            // 变成 值为  2-3+1=0 到3 ， 长度为 2*maxValue-maxIndex-1  =2*3-2-1=3 ,因为不包括maxValue所以不+1了。
            return minString.substring(maxIndex-maxValue+1,maxValue);
        }
        return null;
    }
    /**
     * 最长回文子串
     * <p>
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     * 输入：s = "babad"
     *      s`=dabab
     *      最长公共子串
     *      通过最大值去找 最大下标 然后截取
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     * - d a b a b
     * b 0 0 1 0 1
     * a 0 1 0 1 0
     * b 0 0 1 0 1
     * a 0 1 0 1 0
     * d 1 0 0 0 0
     *
     *  特殊情况case
     *   前后都有 对称  结果不正确
     * "aacabdkacaa"
     *
     *
     *
     *  aacakdbacaa
     */
    public String longestPalindromeV1(String s) {
//        String result = "";
        if (s.length() == 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        StringBuilder stringBuilder = new StringBuilder(s);
        char[] chars2 = new String(stringBuilder.reverse()).toCharArray();
        int[][] dp = new int[s.length() + 1][s.length() + 1];
        /**
         * cls最长序列长度
         */
        int maxLong=0;
        /**
         * 取最大值的时候下标
         */
        int maxIndex = 0;
        for (int i = 0; i <= chars.length; ++i) {
            for (int j = 0; j <= chars2.length; ++j) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (chars[i - 1] == chars2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] >= maxLong) {
                        maxLong = dp[i][j];
                        maxIndex = i;
                    }
                } else if (chars[i - 1] != chars2[j - 1]) {
                    dp[i][j] = 0;
                }
            }
        }

        /**
         * [ )
         */
        return s.substring(maxIndex-maxLong,maxIndex);
    }

    /**
     * 还能优化
     */
    public String longestPalindrome(String s) {
        String result = "";
        if (s.length() == 1) {
            return s;
        }
        /**
         * 记录最长的子串长度
         */
        int maxLong = 0;
//        char[] chars = s.toCharArray();
        /**
         * 过滤重复的子串
         */
//        HashSet<String> filterSet = new HashSet<>();
        String subString;

        for (int i = 0; i <= s.length() - 2; ++i) {
//            flag:
            for (int j = s.length(); j > i; --j) {
                if (maxLong >= j - i) {
                    break;
                }
                subString = s.substring(i, j);
//                if (filterSet.contains(subString)) {
//                    continue;
//                }
//                /**
//                 * 验证回文；
//                 */
//                if (subString.equals(new StringBuilder(subString).reverse().toString())) {
//                    maxLong = j - i;
//                    result = subString;
//                }
                /**
                 * 判断回文
                 */
                /**
                 * 是否回文
                 */
                boolean revers = true;
                for (int k = 0; k < subString.length() / 2; ++k) {
                    if (subString.charAt(k) != subString.
                            charAt(subString.length() - k - 1)) {
                        revers = false;
                        break;
                    }
                }
                if (revers) {
                    maxLong = j - i;
                    result = subString;
                }
//                filterSet.add(subString);

            }
        }
        return result;
    }
    /**
     * 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
     * <p>
     * 示例 1:
     * 输入:
     * <p>
     * "bbbab"
     * - b a b b b
     * b 1 0 1 1 1
     * b 1 0 1 1 1
     * b 1 0 1 1 1
     * a 0 1 0 0 0
     * b 1 0 1 1 1
     *
     * 输出:
     * <p>
     * 4
     * 一个可能的最长回文子序列为 "bbbb"。
     * 类似于  最长公共子串
     */

    public int longestPalindromeSubseq(String s) {
        if(s.length()==1){
            return 1;
        }
        char[] chars = s.toCharArray();
//        StringBuilder stringBuilder=new StringBuilder(s);
//        char[] chars2=new String(stringBuilder).toCharArray();
        int[][] dp = new int[s.length() + 1][s.length() + 1];
        for (int i = 0; i < chars.length; i++) {
            for (int j = chars.length - 1; j >= 0; j--) {
                if (chars[i] == chars[j]) {
                    if (i == 0||j==chars.length - 1) {
                        dp[i][chars.length-1 - j] = 1;
                        continue;
                    }
                    dp[i][chars.length-1 - j] = dp[i - 1][chars.length-1 - j - 1] + 1;
                } else if (chars[i] != chars[j]) {
                    if (i == 0) {
                        if(j==chars.length - 1){
                            dp[i][chars.length -1- j] =0;
                            continue;
                        }
                        dp[i][chars.length -1- j] = dp[i][chars.length -1- j -1];
                        continue;
                    }
                    if (j==chars.length - 1) {
                        dp[i][chars.length -1- j] = dp[i -1][chars.length-1 - j];
                        continue;
                    }
                    dp[i][chars.length-1 - j] =
                                Math.max(dp[i -1][chars.length-1 - j],
                                        dp[i][chars.length -1- j -1]);
                }
            }
        }
        return dp[chars.length-1][chars.length-1];
    }


}
