package acm;

import java.util.Arrays;

public class Solution2 {


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
        System.out.println(new Solution2().beibao(coins, coins2, 4));

//        int amount = sc.nextInt();
//        System.out.println(new Solution().coinChange(coins, amount));


    }


    /**
     * 输入: amount = 5, coins = [1, 2, 5]
     * 输出: 4
     * 解释: 有四种方式可以凑成总金额:
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/coin-change-2
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * 状态只有amount 硬币选择不限制 数量也不限制
     */
    public int change(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }
        if (coins.length == 0) {
            return 0;
        }
//        int result = 0;
//        Set<List<Integer>> filterSet = new HashSet<>();
        //声明一个amount+1长度的数组dp，代表各个价值的钱包，第0个钱包可以容纳的总价值为0，其它全部初始化为无穷大
        //dp[j]代表当钱包的总价值为j时，所需要的最少硬币的个数
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, 0, dp.length, 0);
        /**
         * base case
         */
//        for (int i = 0; i < coins.length; i++) {
//
//        }
        for (int i = 0; i < coins.length; ++i) {
            for (int j = 0; j <= amount; ++j) {
                if (j - coins[i] < 0) {
                    dp[j] = dp[j];
                    continue;
                } else if (j - coins[i] == 0) {
                    dp[j] = dp[j] + 1;
                    continue;
                }
                dp[j] = dp[j] + dp[j - coins[i]];
            }
        }

        /**
         * 最后取值
         */
        return dp[amount];
    }


    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * <p>
     * 你可以按任意顺序返回答案。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/two-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 没有排序 排序才能用二分查找
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];

        flag:
        for (int i = 0; i < nums.length; ++i) {
            if (i == nums.length - 1) {
                break;
            }
            int find = target - nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left <= right) {
                int middle = (left + right) / 2;
                if (nums[middle] == find) {
                    result[0] = i;
                    result[1] = middle;
                    break flag;
                } else if (nums[middle] < find) {
                    left = middle + 1;
                } else if (nums[middle] > find) {
                    right = middle - 1;
                }
            }


        }
        return result;
    }

    public int[] twoSumOnly(int[] nums, int target) {
        int[] result = new int[2];
        flag:
        for (int i = 0; i < nums.length; ++i) {
            if (i == nums.length - 1) {
                break;
            }
            for (int j = i + 1; j < nums.length; ++j) {
                int find = target - nums[i];
                if (nums[j] == find) {
                    result[0] = i;
                    result[1] = j;
                    break flag;
                }

            }

        }
        return result;
    }


    /**
     * 输入: [1, 5, 11, 5]
     * <p>
     * 输出: true
     * <p>
     * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
     * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     * 前n个，m当前重量
     * dp【n】[m]
     * <p>
     * 这种方法解的是连续的数组 切分
     */
    public boolean canPartitionV1(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        /**
         * 重量为sum
         */
        sum = sum / 2;
        boolean[][] dp = new boolean[nums.length + 1][sum + 1];

        /**
         * 输入: [1, 5, 11, 5]
         * <p>
         * 输出: true
         */
        for (int i = 0; i <= nums.length; ++i) {
            for (int j = 0; j <= sum; ++j) {
                if (i == 0) {
                    dp[i][j] = false;
                    continue;
                }
//                if (j == 0) {
//                    dp[i][j] = true;
//                    continue;
//                }
                /**
                 * 大于和
                 */
                if (j - nums[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else if (j - nums[i - 1] == 0) {
                    if (i == 1) {
                        dp[i][j] = true;
                        continue;
                    }
                    dp[i][j] = dp[i - 1][0];
                } else if (j - nums[i - 1] > 0) {
//                    if (i == nums.length) {
//                        dp[i][j] = false;
//                        continue;
//                    }
                    dp[i][j] = dp[i - 1][j - nums[i - 1]];
                }


            }
        }


        return dp[nums.length][sum];
    }

    /**
     * 无序拆分
     * <p>
     * 前n个一起做选择，m当前重量
     * dp【n】[m]
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        /**
         * 重量为sum
         */
        sum = sum / 2;
        boolean[][] dp = new boolean[nums.length + 1][sum + 1];

        /**
         * 输入: [1, 5, 11, 5]
         * <p>
         * 输出: true
         */
        for (int i = 0; i <= nums.length; ++i) {

            for (int j = 0; j <= sum; ++j) {
                if (i == 0) {
                    dp[i][j] = false;
                    continue;
                }
                if (j == 0) {
                    dp[i][j] = true;
                    continue;
                }
                /**
                 * 大于和
                 */
                if (j - nums[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else if (j - nums[i - 1] == 0) {
                    dp[i][j] = true;
                } else if (j - nums[i - 1] > 0) {
//                    if (i == nums.length) {
//                        dp[i][j] = false;
//                        continue;
//                    }
                    /**
                     * 这里有2种方式  放或者不放 要取舍
                     */
                    dp[i][j] = dp[i - 1][j - nums[i - 1]]
                            || dp[i - 1][j];
                }


            }
        }


        return dp[nums.length][sum];
    }


    /**
     * 解法二 01背包问题  是否可以重复选择物品  应该不会
     * nums重量数组  values价格
     * dp[前n个做选择][当前的剩余重量]
     */
    public int beibao(int[] nums, int[] values, int mount) {
        int m = mount;
        /**
         *
         * 数量 N = 3,重量  W = 4
         * wt = [2, 1, 3]
         * val = [4, 2, 3]
         */
        int[][] dp = new int[nums.length + 1][m + 1];
        for (int i = 0; i <= nums.length; ++i) {
            for (int j = 0; j <= m; ++j) {
                if (i == 0 || j == 0) {
                    /**
                     * base case 0
                     */
                    dp[i][j] = 0;
                    continue;
                }
                if (j - nums[i - 1] < 0) {
//                    if (i == nums.length) {
//                        dp[i][j] = dp[i - 1][j];
//                        continue;
//                    }
//                    dp[i][j] = Math.max(dp[i - 1][j], dp[i + 1][j]);
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j]);

                } else if (j - nums[i - 1] == 0) {
                    /**
                     * 面临选择 要还是不要
                     */
//                    if (i == nums.length) {
//                        dp[i][j] = dp[i - 1][j-nums[i-1]] + values[i - 1];
//                        continue;
//                    }

                    /**
                     * （1）在不包括第i个物品的子集中,最优子集的价值是F(i-1,j);
                     *   (2) 在包括第i个物品的子集中（j-wi>=0），最优子集是由该物品和前i-1个物品所能够放进承重为j-wi的背包最优子集构成。这种最优子集的总价值为
                     * vi+F(i-1,j-wi)；
                     */
                    /**
                     * 思考 ？这里同样不能取 i+1最大
                     * 我的理解 前n个物品取最大 知道的值只有前面的
                     * Math.max(
                     *                             dp[i - 1][j-nums[i-1]] + values[i - 1],
                     *                             dp[i + 1][j]);
                     *                 }
                     *     dp[i - 1][j]);
                     * 其他解释
                     * 如果你没有把这第i个物品装入背包，那么很显然，最大价值dp[i][w]应该等于dp[i-1][w]。你不装嘛，那就继承之前的结果。
                     *
                     * 如果你把这第i个物品装入了背包，那么dp[i][w]应该等于dp[i-1][w-wt[i-1]] + val[i-1]
                     */
                    dp[i][j] = Math.max(
                            dp[i - 1][j - nums[i - 1]] + values[i - 1],
                            dp[i - 1][j]);
                } else if (j - nums[i - 1] > 0) {
//                    if (i == nums.length) {
//                        dp[i][j] = dp[i - 1][j-nums[i-1]] + values[i - 1] ;
//                        continue;
//                    }
                    /**
                     *     dp[i - 1][j-nums[i-1]] + values[i - 1] + dp[i + 1][j - nums[i - 1]],
                     修改不能用 i+1 不对
                     */
                    dp[i][j] = Math.max(
                            dp[i - 1][j - nums[i - 1]] + values[i - 1],
                            dp[i - 1][j]);

                }

            }
        }
        return dp[nums.length][mount];
    }
}
