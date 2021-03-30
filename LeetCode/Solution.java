package acm;


import java.util.*;

/**
 * @description:
 * @author:
 * @create: 2021-02-18 15:21
 **/
public class Solution {
    HashMap<Integer, Integer> dp = new HashMap<>();
//    protected static final LogUtils2 logger = new LogUtils2(Solution.class, false, false);

    public static void main(String[] args) {

        System.out.println("请输入几个数并用逗号隔开：");
//        Scanner sc = new Scanner(System.in);
//        String str = sc.next().toString();
        String str = "0,1,1,1,1";
        String[] arr = str.split(",");
        int[] coins = new int[arr.length];
        for (int j = 0; j < coins.length; j++) {
            coins[j] = Integer.parseInt(arr[j]);
        }
        System.out.println("请输入几个数并用逗号隔开：");
//        Scanner sc2 = new Scanner(System.in);
//        String str2 = sc2.next().toString();
        String str2 = "0,0,0,0";

        String[] arr2 = str2.split(",");
        int[] coins2 = new int[arr2.length];
        for (int j = 0; j < coins2.length; j++) {
            coins2[j] = Integer.parseInt(arr2[j]);
        }
//        "babgbag"
//        "bag"

//        System.out.println(new Solution().threeSumV3(coins2));
        System.out.println(new Solution().findLengthCls("babgbag","bag"));

//        int amount = sc.nextInt();
//        System.out.println(new Solution().coinChange(coins, amount));


    }

    public int coinChange(int[] coins, int amount) {
        if (null != dp.get(amount)) {
            return dp.get(amount);
        }
        int result = Integer.MAX_VALUE;
        if (0 == amount) {
            dp.put(amount, 0);
            return 0;
        }
        if (0 > amount) {
            dp.put(amount, -1);
            return -1;
        }
        for (int iteam : coins) {
            int subProblem = coinChange(coins, amount - iteam);
            if (-1 == subProblem) {
                /**
                 * 不满足条件
                 */
                continue;
            }
            result = Math.min(result, 1 + subProblem);
        }
        if (result == Integer.MAX_VALUE) {
            dp.put(amount, -1);
            return -1;
        }
        /**
         * 备忘录
         */
        dp.put(amount, result);
        return result;
    }

    /**
     * 这道题类似于完全背包问题，每个物品都可以无限使用，但是要求背包必须装满，而且要求背包中的物品数目最少， 归纳为数学问题就是，
     * <p>
     * v[i]:代表每种硬币的价值
     * x[i]:代表每种硬币拿的个数，0<=x[i]<=amount/v[i]
     * 所求问题可以归纳为：
     * 在满足：amount=v1x1+v2x2+v3x3+...+vnxn 的条件下
     * 求： target=min
     * <p>
     * {x1+x2+x3+....xn}
     * 最简单的一种思路就是把所有{xi}的组合全部拿出来，然后让target最小即可，利用递归就可以解决问题，但是时间复杂度会很高，但是如果有好的剪枝策略，也可以使用
     * 另外一种方法就是常规的动态规划，利用一个amout+1长度的dp数组，记录每一个状态的最优解，过程见程序和注释
     */
    public int coinChange2(int[] coins, int amount) {
        if (coins.length == 0) {
            return -1;
        }

        //声明一个amount+1长度的数组dp，代表各个价值的钱包，第0个钱包可以容纳的总价值为0，其它全部初始化为无穷大
        //dp[j]代表当钱包的总价值为j时，所需要的最少硬币的个数
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, 1, dp.length, Integer.MAX_VALUE);

        //i代表可以使用的硬币索引，i=2代表只在第0个，第1个，第2个这三个硬币中选择硬币
        for (int i = 0; i < coins.length; i++) {
            /**
             * 	当外层循环执行一次以后，说明在只使用前i-1个硬币的情况下，各个钱包的最少硬币个数已经得到，
             * 		有些钱包的值还是无穷大，说明在仅使用前i-1个硬币的情况下，不能凑出钱包的价值
             * 	现在开始再放入第i个硬币，要想放如w[i]，钱包的价值必须满足j>=w[i]，所以在开始放入第i个硬币时，j从w[i]开始
             */
            for (int j = coins[i]; j <= amount; j++) {
                /**
                 * 	如果钱包当前的价值j仅能允许放入一个w[i]，那么就要进行权衡，以获得更少的硬币数
                 * 		如果放入0个：此时钱包里面硬币的个数保持不变： v0=dp[j]
                 * 		如果放入1个：此时钱包里面硬币的个数为：		v1=dp[j-coins[i]]+1
                 * 		 【前提是dp[j-coins[i]]必须有值，如果dp[j-coins[i]]是无穷大，说明无法凑出j-coins[i]价值的钱包，
                 * 	              那么把w[i]放进去以后，自然也凑不出dp[j]的钱包】
                 * 	所以，此时当钱包价值为j时，里面的硬币数目为 dp[j]=min{v0,v1}
                 * 	如果钱包当前价值j能够放入2个w[i]，就要再进行一次权衡
                 * 		如果不放人第2个w[i]，此时钱包里面硬币数目为，v1=dp[j]=min{v0,v1}
                 * 		如果放入第2个w[i],  此时钱包里面硬币数目为，v2=dp[j-coins[i]]+1
                 * 	所以，当钱包的价值为j时，里面的硬币数目为dp[j]=min{v1,v2}=min{v0,v1,v2}
                 * 	钱包价值j能允许放入3个，4个.........w[i]，不断更新dp[j]，最后得到在仅使用前i个硬币的时候，每个钱包里的最少硬币数目
                 */
                if (dp[j - coins[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        if (dp[amount] != Integer.MAX_VALUE) {
            return dp[amount];
        }
        return -1;
    }


    static int dp(int[] coins, int amount) {
        int result = Integer.MAX_VALUE;
        if (0 == amount) {
            return 0;
        }
        if (0 > amount) {
            return -1;
        }
        for (int iteam : coins) {
            int subProblem = dp(coins, amount - iteam);
            if (-1 == subProblem) {
                /**
                 * 不满足条件
                 */
                continue;
            }
            result = Math.min(result, 1 + subProblem);
        }
        if (result == Integer.MAX_VALUE) {
            return -1;
        }
        return result;
    }


    public int maxSubArray(int[] nums) {
        /**
         *
         [-2,1,-3,4,-1,2,1,-5,4]

         */
        int result = Integer.MIN_VALUE;
        int before = 0;
        /**
         * 单个元素的下标
         */
//        int beforeIndex = 0;
        /**
         * 自底向顶
         */
        for (int i = 0; i < nums.length; ++i) {
            int tempMax = Math.max((before + nums[i]), nums[i]);
            if (nums[i] >= before + nums[i]) {
                before = nums[i];
            } else {
                before += nums[i];
            }
            result = Math.max(result, tempMax);
        }

        return result;

    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        double index = (length1 + length2) / 2;
        int[] merge = new int[length1 + length2];

        return 0;
    }


    /**
     * 最长公共子序列  cls
     */
    public int findLength2(int[] A, int[] B) {
        int lengthA = A.length;
        int lengthB = B.length;
        int[][] dp = new int[lengthA + 1][lengthB + 1];
        for (int i = 0; i <= lengthA; ++i) {
            for (int j = 0; j <= lengthB; ++j) {
                if (0 == i || 0 == j) {
                    dp[i][j] = 0;
                } else if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else if (A[i - 1] != B[j - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        for (int i = 0; i <= lengthA; ++i) {
            for (int j = 0; j <= lengthB; ++j) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println("");
        }
        return dp[lengthA][lengthB];
    }


    /**
     * cls  最长子序列
     */
    public int findLengthCls(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int dp[][] = new int[len1 + 1][len2 + 1];
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
        return dp[len1][len2];
    }


    public static void display(int[][] dp) {
        for (int i = 0; i < dp.length; ++i) {
            for (int j = 0; j < dp[i].length; ++j) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println("");
        }
    }


    /**
     * 最长公共子串
     */
    public static int lcs(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int result = 0;     //记录最长公共子串长度
        int c[][] = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0 || j == 0) {
                    c[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    result = Math.max(c[i][j], result);
                } else {
                    c[i][j] = 0;
                }
            }
        }
        return result;
    }


    public int findLength(int[] A, int[] B) {
        int lengthA = A.length;
        int lengthB = B.length;
        int result = 0;
        int[][] dp = new int[lengthA + 1][lengthB + 1];
        for (int i = 0; i <= lengthA; ++i) {
            for (int j = 0; j <= lengthB; ++j) {
                if (0 == i || 0 == j) {
                    dp[i][j] = 0;
                } else if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    result = Math.max(dp[i][j], result);
                } else if (A[i - 1] != B[j - 1]) {
                    dp[i][j] = 0;
                }
            }
        }
//        for (int i = 0; i <= lengthA; ++i) {
//            for (int j = 0; j <= lengthB; ++j) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println("");
//        }
        return result;
    }


    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        int temp = 0;
        char[] chars = s.toCharArray();
        HashSet<Character> filter = new HashSet<>();
        List<Character> targetArr = new LinkedList<>();
        for (int i = 0; i < chars.length; ++i) {
            if (filter.contains(chars[i])) {
                /**
                 * 如果不是首个子串元素
                 */
                int firstIndex = targetArr.indexOf(chars[i]);
                targetArr = targetArr.subList(firstIndex + 1, targetArr.size());
                temp = targetArr.size();
                filter.clear();
                filter.addAll(targetArr);
                /**
                 * 重新开始计算
                 */
            }
            filter.add(chars[i]);
            targetArr.add(chars[i]);
            ++temp;
            result = Math.max(temp, result);
        }
        return result;
    }


    public int maxProfit(int[] prices, int m) {
        int result = 0;
        int beforePrice = 0;
        int[][] dp = new int[prices.length][prices.length];
        for (int i = 0; i < prices.length; ++i) {
            for (int k = 0; k < 1; ++k) {

            }
        }
        return result;
    }


    /**
     * k可以设置次数
     * dp[i][k][bool]
     * i=交易天数，k=限制买入操作次数，bool表示是否持有
     */
    public int maxProfitA(int[] prices) {
        /**
         * 买入次数限制
         */
        int m = 1;
        int[][][] dp = new int[prices.length + 1][m + 1][2];
        for (int i = 0; i <= prices.length; ++i) {
            for (int j = 0; j <= m; ++j) {
                if (0 == i) {
                    dp[i][j][0] = 0;
                    /**
                     *  不可能出现这种情况
                     */
                    dp[i][j][1] = Integer.MIN_VALUE;
                    continue;
                }
                if (0 == j) {
                    dp[i][j][0] = dp[i - 1][j][0];
                } else {
                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j - 1][1] + prices[i - 1]);
                }
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j][0] - prices[i - 1]);
            }
        }
        return dp[prices.length][m][0];
    }


    /**
     * 一次交易
     */
    public int maxProfit(int[] prices) {

        /**
         * 买入次数限制
         */
        int k = 1;
        int[][] dp = new int[prices.length + 1][2];
        for (int i = 0; i <= prices.length; ++i) {
            if (0 == i) {
                dp[i][0] = 0;
                /**
                 *  不可能出现这种情况
                 */
                dp[i][1] = Integer.MIN_VALUE;
                continue;
            }
            /**
             *       dp[i][0] = Math.max(dp[i - 1][0], -dp[i - 1][1] + prices[i - 1]);
             */
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i - 1]);
            /**
             *dp[i][1][0]	=	max(dp[i-1][1][0],	dp[i-1][1][1]	+	prices[i])
             * dp[i][1][1]	=
             * max(dp[i-1][1][1],	dp[i-1][0][0]	-	prices[i])
             *  =	max(dp[i-1][1][1],	-prices[i])
             *  疑问 为什么sale不能省略
             */
            /**
             *             dp[i][1] = Math.max(dp[i - 1][1],  dp[i-1][0]-prices[i - 1]);
             */
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i - 1]);
        }

        return dp[prices.length][0];
    }


    /**
     * 无限制交易
     */
    public int maxProfitM(int[] prices) {
        /**
         * 买入次数限制
         */
        int k = 1;
        int[][] dp = new int[prices.length + 1][2];
        for (int i = 0; i <= prices.length; ++i) {
            if (0 == i) {
                dp[i][0] = 0;
                /**
                 *  不可能出现这种情况
                 */
                dp[i][1] = Integer.MIN_VALUE;
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i - 1]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i - 1]);
        }

        return dp[prices.length][0];
    }

//    public static void main(String[] args) {
//        String str2 = "1,2,3,4,5";
//        String[] arr2 = str2.split(",");
//        int[] coins2 = new int[arr2.length];
//        for (int j = 0; j < coins2.length; j++) {
//            coins2[j] = Integer.parseInt(arr2[j]);
//        }
//        ListNode head1 = new ListNode(1);
//
//        ListNode head2 = new ListNode(2);
//        head1.next = head2;
//        ListNode head3 = new ListNode(3);
//        head2.next = head3;
//        ListNode head4 = new ListNode(4);
//        head3.next = head4;
//        ListNode head5 = new ListNode(5);
//        head4.next = head5;
//        System.out.println(new Solution().reverseKGroup(head1, 2));
//    }

    public ListNode reverseKGroup(ListNode head, int k) {
        List<ListNode> nodeList = new LinkedList<>();
        ListNode point = null;
        do {
            if (point == null) {
                point = head;
            }
            nodeList.add(point);
            point = point.next;
        } while (point != null);
        int countMo = 0;
        int index = 0;
        List<Integer> nodeSort = new ArrayList<>(nodeList.size());
        /**
         * 判断需要进行翻转的最后个节点
         */
        int other = nodeList.size() % k;
        for (int i = 0; i < nodeList.size(); ++i) {
            /**
             * 不需要翻转的节点
             */
            if (i >= nodeList.size() - other) {
                nodeSort.add(i);
                continue;
            }
            int quYu = (i + 1) % k;
            if (quYu == 0) {
                nodeSort.add(countMo * k + index);
                ++countMo;
            }
            /**
             * 不是反转的头结点
             */
            else {
//                ++index;
                nodeSort.add(countMo * k + k - quYu);
            }
        }
//        logger.error(nodeSort);
        ListNode result = null;
        ListNode tempPoint = null;
        for (int j = 0; j < nodeSort.size(); ++j) {
            if (result == null) {
                result = new ListNode(nodeList.get(nodeSort.get(j)).val);
                if (j != nodeSort.size() - 1) {
                    /**
                     * next没有清空
                     */
                    result.next = new ListNode(nodeList.get(nodeSort.get(j + 1)).val);
                    tempPoint = result.next;
                    continue;
                }
            }
            if (j == nodeSort.size() - 1) {
                continue;
            }
            tempPoint.next = new ListNode(nodeList.get(nodeSort.get(j + 1)).val);
            tempPoint = tempPoint.next;
        }

        return result;
    }


    /**
     * 所有组合  三数之和
     */
    public List<List<Integer>> threeSumAll(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
//        HashSet<Integer> filterSet = new HashSet<>();
        List<Integer> oneResult = null;
//        for (int temp : nums) {
//            filterSet.add(temp);
//        }
//        int[][] dp = new int[nums.length][nums.length];
        flag:
        for (int i = 0; i < nums.length; ++i) {
            if (i == nums.length - 2) {
                break;
            }
            for (int j = i + 1; j < nums.length; ++j) {
//                dp[i][j] = nums[i] + nums[j];
                for (int k = j + 1; k < nums.length; ++k) {
                    if (0 == nums[i] + nums[j] + nums[k]) {
                        oneResult = new LinkedList<>();
                        oneResult.add(nums[i]);
                        oneResult.add(nums[j]);
                        oneResult.add(nums[k]);
//                        oneResult.addAll(Lists.asList(nums[i],nums[j],nums[k]));
                        result.add(oneResult);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 过滤结果
     * 2021年02月26日 超过时间限制
     */
    public List<List<Integer>> threeSumV1(int[] nums) {
//        List<List<Integer>> result = new LinkedList<>();
//        HashSet<List> filterSet = new HashSet<>();
        Set<List<Integer>> setResult = new HashSet<>();
        List<Integer> oneResult = null;
//        for (int temp : nums) {
//            filterSet.add(temp);
//        }
//        int[][] dp = new int[nums.length][nums.length];
//        flag:
        for (int i = 0; i < nums.length; ++i) {
            if (i == nums.length - 2) {
                break;
            }
            for (int j = i + 1; j < nums.length; ++j) {
//                dp[i][j] = nums[i] + nums[j];
                for (int k = j + 1; k < nums.length; ++k) {
                    if (0 == nums[i] + nums[j] + nums[k]) {
                        oneResult = new LinkedList<>();
                        oneResult.add(nums[i]);
                        oneResult.add(nums[j]);
                        oneResult.add(nums[k]);
                        Collections.sort(oneResult);
//                        if(!filterSet.contains(oneResult)){
//                            result.add(oneResult);
//                            filterSet.add(oneResult);
//                        }
                        setResult.add(oneResult);
//                        oneResult.addAll(Lists.asList(nums[i],nums[j],nums[k]));
                    }
                }
            }
        }
        return new LinkedList<>(setResult);
    }

    /**
     *16. 最接近的三数之和
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     *
     *
     *
     * 示例：
     *
     * 输入：nums = [-1,2,1,-4], target = 1
     * 输出：2
     * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
     */
    public int threeSumClosest(int[] nums, int target) {

        /**
         * 记录当前最小差距
         */
        int  min=Integer.MAX_VALUE;
        /**
         * 当前选取的组合和
         */
        int sum=Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; ++i) {
            if (i == nums.length - 2) {
                break;
            }
            for (int j = i + 1; j < nums.length; ++j) {
                for (int k = j + 1; k < nums.length; ++k) {
                    int abs=Math.abs((nums[i]+nums[j]+nums[k])-target);
                    if(abs<min){
                        min=abs;
                        sum=(nums[i]+nums[j]+nums[k]);
                    }
                }
            }
        }
        return sum;
    }

    public List<List<Integer>> threeSumV2(int[] nums) {
        Set<List<Integer>> setResult = new HashSet<>();
        ArrayList<Integer> containList = new ArrayList<>(nums.length);
        List<Integer> oneResult = null;
        for (int temp : nums) {
            containList.add(temp);
        }
        for (int i = 0; i < nums.length; ++i) {
            if (i == nums.length - 1) {
                break;
            }
            for (int j = i + 1; j < nums.length; ++j) {
                int index = containList.indexOf(-nums[i] - nums[j]);
                if (-1 != index & index != i && index != j) {
                    oneResult = new LinkedList<>();
                    oneResult.add(nums[i]);
                    oneResult.add(nums[j]);
                    oneResult.add(containList.get(index));
                    Collections.sort(oneResult);
                    setResult.add(oneResult);
                }
            }
        }
        return new LinkedList<>(setResult);
    }

    public List<List<Integer>> threeSumV3(int[] nums) {
        Set<List<Integer>> setResult = new HashSet<>();
        ArrayList<Integer> containList = new ArrayList<>(nums.length);
        HashMap<Integer, Integer> filterSet = new HashMap<>(nums.length);
        List<Integer> oneResult = null;
        for (int temp : nums) {
            if (filterSet.containsKey(temp)) {
                filterSet.put(temp, filterSet.get(temp) + 1);
            } else {
                filterSet.put(temp, 0);
            }
            if (3 > filterSet.get(temp)) {
                containList.add(temp);
            }
        }
        /**
         * [-1,0,1,2,-1,-4]
         * -4,-1,-1,0,1,2
         */
        /**
         * case1 特殊处理 相同元素
         */
//        if (filterSet.size() == 1 && containList.size() > 2) {
//            containList= new ArrayList<>(3);
//            Collections.addAll(containList, nums[0], nums[0], nums[0]);
//        }

        /**
         * case2  相同元素 超过3个 移除
         */

        /**
         * case3  优化 排序完 移动指针大小
         * int target
         */
        Collections.sort(containList);
        for (int i = 0; i < containList.size(); ++i) {
            if (i == containList.size() - 2) {
                break;
            }
            for (int j = i + 1; j < containList.size(); ++j) {
                if (!filterSet.containsKey(-containList.get(i) - containList.get(j))) {
                    continue;
                }
//                for (int k = j + 1; k < containList.size(); ++k) {
//                    if (0 == containList.get(i) + containList.get(j) + containList.get(k)) {
//                        oneResult = new LinkedList<>();
//                        oneResult.add(containList.get(i));
//                        oneResult.add(containList.get(j));
//                        oneResult.add(containList.get(k));
//                        setResult.add(oneResult);
//                    }
//                }
                /**
                 * 2分法
                 */
                /**
                 * [-1,0,1,2,-1,-4]
                 * -4,-1,-1,0,1,2
                 */
                int end = containList.size() - 1;
                int start = j + 1;
                int target = -containList.get(i) - containList.get(j);
                while (start <= end) {
                    int middle = (start + end) / 2;
                    if (target == containList.get(middle)) {
                        oneResult = new LinkedList<>();
                        oneResult.add(containList.get(i));
                        oneResult.add(containList.get(j));
                        oneResult.add(containList.get(middle));
                        setResult.add(oneResult);
                        break;
                    } else if (target < containList.get(middle)) {
                        end = middle - 1;
                    } else if (target > containList.get(middle)) {
                        start = middle + 1;
                    }
                }
            }
        }
        return new LinkedList<>(setResult);
    }

    /**
     * 4数之和
     */
    public List<List<Integer>> threeSumV4(int[] nums, int target) {
        Set<List<Integer>> setResult = new HashSet<>();
        ArrayList<Integer> containList = new ArrayList<>(nums.length);
        HashMap<Integer, Integer> filterSet = new HashMap<>(nums.length);
        List<Integer> oneResult = null;
        for (int temp : nums) {
            if (filterSet.containsKey(temp)) {
                filterSet.put(temp, filterSet.get(temp) + 1);
            } else {
                filterSet.put(temp, 0);
            }
            if (4 > filterSet.get(temp)) {
                containList.add(temp);
            }
        }
        Collections.sort(containList);
        for (int i = 0; i < containList.size(); ++i) {
            if (i == containList.size() - 3) {
                break;
            }
            for (int k = i + 1; k < containList.size(); ++k) {
                for (int j = k + 1; j < containList.size(); ++j) {
                    if (!filterSet.containsKey(target - containList.get(k) - containList.get(i) - containList.get(j))) {
                        continue;
                    }
                    /**
                     * [-1,0,1,2,-1,-4]
                     * -4,-1,-1,0,1,2
                     */
                    int end = containList.size() - 1;
                    int start = j + 1;
                    int target1 = target - containList.get(k) - containList.get(i) - containList.get(j);
                    while (start <= end) {
                        int middle = (start + end) / 2;
                        if (target1 == containList.get(middle)) {
                            oneResult = new LinkedList<>();
                            oneResult.add(containList.get(i));
                            oneResult.add(containList.get(k));
                            oneResult.add(containList.get(j));
                            oneResult.add(containList.get(middle));
                            setResult.add(oneResult);
                            break;
                        } else if (target1 < containList.get(middle)) {
                            end = middle - 1;
                        } else if (target1 > containList.get(middle)) {
                            start = middle + 1;
                        }
                    }
                }
            }
        }
        return new LinkedList<>(setResult);
    }

    /**
     * 解法二 01背包问题  是否可以重复选择物品  应该不会
     * nums重量数组  values价格
     * dp[前n个做选择][当前的剩余重量]
     */
//    public int beibao(int[] nums, int[] values, int mount) {
//        int m = mount;
//        /**
//         *
//         * 数量 N = 3,重量  W = 4
//         * wt = [2, 1, 3]
//         * val = [4, 2, 3]
//         */
//        int[][] dp = new int[nums.length + 1][m + 1];
//        for (int i = 0; i <= nums.length; ++i) {
//            for (int j = 0; j <= m; ++j) {
//                if (i == 0 || j == 0) {
//                    /**
//                     * base case 0
//                     */
//                    dp[i][j] = 0;
//                    continue;
//                }
//                if (j - nums[i - 1] < 0) {
//                    if (i == nums.length) {
//                        dp[i][j] = dp[i - 1][j];
//                        continue;
//                    }
//                    dp[i][j] = Math.max(dp[i - 1][j], dp[i + 1][j]);
//                } else if (j - nums[i - 1] == 0) {
//                    /**
//                     * 面临选择 要还是不要
//                     */
//                    if (i == nums.length) {
//                        dp[i][j] = dp[i - 1][j] + values[i - 1];
//                        continue;
//                    }
//                    dp[i][j] = Math.max(
//                            dp[i - 1][j] + values[i - 1],
//                            dp[i + 1][j]);
//                } else if (j - nums[i - 1] > 0) {
//                    if (i == nums.length) {
//                        dp[i][j] = dp[i - 1][j] + values[i - 1]+ dp[i + 1][j - nums[i - 1]];
//                        continue;
//                    }
//                    dp[i][j] = Math.max(
//                            dp[i - 1][j] + values[i - 1] + dp[i + 1][j - nums[i - 1]],
//                            dp[i + 1][j]);
//
//                }
//
//            }
//        }
//        return dp[nums.length][mount];
//    }

    /**
     * 01  背包   m重量数组  values价格  硬币兑换
     */
    static int dp(int[] m, int[] values, int price, int n) {
        int result = Integer.MIN_VALUE;

        for (int i = 0; i <= m.length; ++i) {
//            for (int j = 0; j <= values; ++j) {
//
//            }
            int subProblem = dp(m, values, price - m[i], n - 1);
            result = Math.max(result, values[i] + subProblem);

        }
        return result;
    }

    /**
     * 01  背包   m限制重量数组  限制values价格  硬币兑换
     * <p>
     * 解法一 不停更换选择列表
     */
    static int dp2(int[] m, int[] values) {
        int result = Integer.MIN_VALUE;
        int[][] dp = new int[m.length + 1][m.length + 1];
        int price = 10;
        int n = 10;
        for (int i = 0; i <= m.length; ++i) {
//            for (int j = 0; j <= values; ++j) {
//
//            }
            int subProblem = dp(m, values, price - m[i], n - 1);
            result = Math.max(result, values[i] + subProblem);

        }
        return result;
    }


    public static int search(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int middle = (start + end) / 2;
            if (key < arr[middle]) {
                end = middle - 1;
            } else if (key > arr[middle]) {
                start = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

}


