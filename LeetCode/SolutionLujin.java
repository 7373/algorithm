package acm;

import java.util.Stack;

public class SolutionLujin {
    public static void main(String[] args) {
        String str = "0,3,6,7,1";
        String[] arr = str.split(",");
        int[] coins = new int[arr.length];
        for (int j = 0; j < coins.length; j++) {
            coins[j] = Integer.parseInt(arr[j]);
        }

//[[1,3,1],[1,5,1],[4,2,1]]
        int[][] coin = new int[][]{{1, 3, 1}, {1, 5, 1}};//分行的赋值方法
        char[][] chars = new char[][]{{'C', 'A', 'A'}, {'A', 'A', 'A'}, {'B', 'C', 'D'}};
//        System.out.println(new SolutionLujin().minPathSum(coin));
        System.out.println(new SolutionLujin().exist(chars, "AAB"));

    }

    /**
     * 64. 最小路径和
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * <p>
     * 说明：每次只能向下或者向右移动一步。
     */
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        /**
         * 第一行默认累加
         */
        int sum = 0;
        for (int i = 0; i < grid[0].length; ++i) {
            dp[0][i] = sum + grid[0][i];
            sum += grid[0][i];
        }
        sum = 0;
        for (int i = 0; i < grid.length; ++i) {
            dp[i][0] = sum + grid[i][0];
            sum += grid[i][0];
        }
        /**
         * dp[i][j]=min(dp[i-1][j],dp[i][j-1])+m[i][j]
         */
        for (int i = 1; i < grid.length; ++i) {
            for (int j = 1; j < grid[0].length; ++j) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }


    /**
     * 剑指 Offer 12. 矩阵中的路径
     * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
     * <p>
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     * <p>
     * CAA
     * AAA
     * BCD
     * <p>
     *     [["a","a","a","a"],["a","a","a","a"],["a","a","a","a"]]
     * "aaaaaaaaaaaaa"
     * abb
     * 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。
     */
    public boolean exist(char[][] board, String word) {

        char[] words = word.toCharArray();
        Stack<int[]> stack;
        /**
         * (i,j) 需要比较的下标
         */
//        int[]tempArr=new int[3];
        int maxLie = board[0].length - 1;
        int maxHang = board.length - 1;
        int target = words.length - 1;
        for (int i = 0; i <= maxHang; ++i) {
            for (int j = 0; j <= maxLie; ++j) {
                if (board[i][j] == words[0]) {
                    if (target == 0) {
                        return true;
                    }
                    int[] addOne = new int[4];
                    addOne[0] = i;
                    addOne[1] = j;
                    addOne[2] = 1;
                    /**
                     * 加一个方向参数
                     * 1 2 3 4 上下左右；
                     */
                    addOne[3] = 0;
                    stack = new Stack<>();
                    stack.push(addOne);
                    while (!stack.isEmpty()) {
                        /**
                         * 左边相等
                         */
                        int[] tempOne = stack.pop();
                        /**
                         * 向右走  之前不能向左
                         */
                        if (tempOne[3] != 3 && maxLie >= (tempOne[1] + 1) && board[tempOne[0]][tempOne[1] + 1] == words[tempOne[2]]) {
                            if (tempOne[2] == target) {
                                return true;
                            }
                            /**
                             * 排除走过的路径节点
                             */
                            addOne = new int[4];
                            addOne[0] = tempOne[0];
                            addOne[1] = tempOne[1] + 1;
                            addOne[2] = tempOne[2] + 1;
                            addOne[3] = 4;
                            stack.push(addOne);
                        }
                        /**
                         * 向下走  之前不能向上
                         */
                        if (tempOne[3] != 1 && maxHang >= (tempOne[0] + 1) && board[tempOne[0] + 1][tempOne[1]] == words[tempOne[2]]) {
                            if (tempOne[2] == target) {
                                return true;
                            }
                            addOne = new int[4];

                            addOne[0] = tempOne[0] + 1;
                            addOne[1] = tempOne[1];
                            addOne[2] = tempOne[2] + 1;
                            addOne[3] = 2;
                            stack.push(addOne);
                        }
                        /**
                         * 向左走  之前不能向右
                         */
                        if (tempOne[3] != 4 && 1 <= (tempOne[1]) && board[tempOne[0]][tempOne[1] - 1] == words[tempOne[2]]) {
                            if (tempOne[2] == target) {
                                return true;
                            }
                            addOne = new int[4];
                            addOne[0] = tempOne[0];
                            addOne[1] = tempOne[1] - 1;
                            addOne[2] = tempOne[2] + 1;
                            addOne[3] = 3;
                            stack.push(addOne);
                        }

                        /**
                         * 向上走  之前不能向下
                         */
                        if (tempOne[3] != 2 && 1 <= (tempOne[0]) && board[tempOne[0] - 1][tempOne[1]] == words[tempOne[2]]) {
                            if (tempOne[2] == target) {
                                return true;
                            }
                            addOne = new int[4];
                            addOne[0] = tempOne[0] - 1;
                            addOne[1] = tempOne[1];
                            addOne[2] = tempOne[2] + 1;
                            addOne[3] = 1;
                            stack.push(addOne);
                        }

                    }
                }
            }
        }
        return false;
    }
}
