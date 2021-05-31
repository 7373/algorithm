package acm;

// 本题为考试多行输入输出规范示例，无需提交，不计分。

import java.util.*;

public class Main2 {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int res = 0;
//        int[] arr = new int[n];
//        for (int i = 0; i < n; i++) {
//            arr[i] = sc.nextInt();
//        }
//
//        int[] dp = new int[25];
//        for (int i = 0; i < arr.length; ++i) {
//            for (int j = arr[i]; j <= 24; ++j) {
//                if (j == arr[i]) {
//                    dp[j] += 1;
//                } else if (j < arr[i]) {
//                    continue;
//                } else {
//                    dp[j] = Math.max(dp[j - arr[i]],dp[j]);
//                }
//            }
//        }
////        if(n==1){
////            dp[24]=arr[0]==24?1:0;
////        }
//        System.out.print(dp[24]);
//
//
//
//    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int res = 0;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

//        int[] dp = new int[25];

        System.out.print(getNum(arr,24));



    }


    public  static int getNum(int[] candidates, int target) {
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

        return result.size();
    }
}