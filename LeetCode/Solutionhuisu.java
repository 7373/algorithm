package acm;

import java.util.LinkedList;
import java.util.List;

/**
 * 决策树
 * 回溯法  全排列
 */
public class Solutionhuisu {
    public static void main(String[] args) {
        String str = "0,3,6,7,1";
        String[] arr = str.split(",");
        int[] coins = new int[arr.length];
        for (int j = 0; j < coins.length; j++) {
            coins[j] = Integer.parseInt(arr[j]);
        }
        System.out.println(new Solutionhuisu().permute(coins));
    }
    /**
     * result = []
     * def backtrack(路径, 选择列表):
     * if 满⾜结束条件:
     * result.add(路径)
     * return
     * for 选择 in 选择列表:
     * 做选择
     * backtrack(路径, 选择列表)
     * 撤销选择
     */
    List<List<Integer>> res = new LinkedList<>();

    /* 主函数，输⼊⼀组不重复的数字，返回它们的全排列 */
    List<List<Integer>> permute(int[] nums) {
// 记录「路径」
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return res;
    }

    // 路径：记录在 track 中
// 选择列表：nums 中不存在于 track 的那些元素
// 结束条件：nums 中的元素全都在 track 中出现
    void backtrack(int[] nums, LinkedList<Integer> track) {
// 触发结束条件
        if (track.size() == nums.length) {
            res.add(new LinkedList(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
// 排除不合法的选择
            if (track.contains(nums[i]))
                continue;
// 做选择
            track.add(nums[i]);
// 进⼊下⼀层决策树
            backtrack(nums, track);
// 取消选择

            track.removeLast();
        }
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

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res;
        LinkedList<Integer> track = new LinkedList<>();
        res= backtrack2(candidates, target,track);
        return res;
    }


    /**
     *
     * track已经选择的数字
     * sum当前总合
     */
    List<List<Integer>> backtrack2(int[] nums, int target, LinkedList<Integer> track) {
        List<List<Integer>> res = new LinkedList<>();
        if (target == 0) {
            res.add(new LinkedList(track));
            return res;
        }
        for (int i = 0; i < nums.length; i++) {
            if (track.contains(nums[i])) {
                continue;
            }
            track.add(nums[i]);
            int temp = target - nums[i];
            if (temp < 0) {
                continue;
            }
            res.addAll(backtrack2(nums, temp, track));
            track.removeLast();
        }
        return res;
    }
}
