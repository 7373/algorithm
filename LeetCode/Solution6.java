package acm;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Solution6 {

    public static void main(String[] args) {
        TreeNode root=new TreeNode(3);
        TreeNode root1=new TreeNode(3);
        TreeNode root2=new TreeNode(3);
        root.right=root2;
//        root.left=root1;

        System.out.println(new Solution6().isSymmetric(root));


    }
    /**
     *剑指 Offer 43. 1～n 整数中 1 出现的次数
     * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
     *
     * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
     *
     *
     *
     * 示例 1：
     *
     * 输入：n = 12
     * 输出：5
     * 示例 2：
     *
     * 输入：n = 13
     * 输出：6
     */
    public int countDigitOne(int n) {
//        Arrays.sort();
        return n;
    }


    /**
     * 剑指 Offer 27. 二叉树的镜像
     * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
     *
     * 例如输入：
     *
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     * 镜像输出：
     *
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     *
     *
     */

    public TreeNode mirrorTree(TreeNode root) {

//        TreeNode temp=root;
        Stack<TreeNode> treeNodeStack = new Stack<>();
        if(null!=root){
            treeNodeStack.add(root);
        }
        while (!treeNodeStack.isEmpty()) {
            /**
             * 交换左右子树
             */
            TreeNode node = treeNodeStack.pop();
            TreeNode tempNode = node.left;
            node.left = node.right;
            node.right = tempNode;
            if (null != node.right) {
                treeNodeStack.add(node.right);
            }
            if (null != node.left) {
                treeNodeStack.add(node.left);
            }

        }

        return root;
    }

    /**
     *101. 对称二叉树
     * 给定一个二叉树，检查它是否是镜像对称的。
     *
     *
     *
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     *
     *     1
     *    / \
     *   2   2
     *  / \ / \
     * 3  4 4  3
     */
//    public boolean isSymmetricV1(TreeNode root) {
//
//        List<TreeNode>  leftList=new LinkedList<>();
//        List<TreeNode>  rightList=new LinkedList<>();
//        leftList.add(root);
//        rightList.add(root);
//        List<TreeNode>[]arry=new List[2];
//        arry[0]=leftList;
//        arry[1]=rightList;
//        Stack<List<TreeNode>[]> treeNodeStack = new Stack<>();
//        while (!treeNodeStack.isEmpty()){
//            List<TreeNode>[]arry2=treeNodeStack.pop();
//            if(arry2[0].size()!=arry2[1].size()){
//                return false;
//            }
//            leftList=new LinkedList<>();
//            rightList=new LinkedList<>();
//            for(int i=0;i<arry2[0].size();++i){
//                if (arry2[0].get(i) == null) {
//                    if (arry2[1].get(arry2[0].size() - i - 1) != null) {
//                        return false;
//                    }
//                } else if (arry2[1].get(arry2[0].size() - i - 1) == null) {
//                    return false;
//                } else if (arry2[0].get(i).val != arry2[1].get(arry2[0].size() - i - 1).val) {
//                    return false;
//                }
//                leftList.add(arry2[0].get(i).left);
//                leftList.add(arry2[0].get(i).right);
//                rightList.add(arry2[1].get(i).left);
//                rightList.add(arry2[1].get(i).right);
//
//            }
//            if (!leftList.isEmpty() || !rightList.isEmpty()) {
//                List<TreeNode>[] arry21 = new List[2];
//                arry21[0] = leftList;
//                arry21[1] = rightList;
//                treeNodeStack.add(arry21);
//            }
//        }
//        return true;
//    }
    public boolean isSymmetric(TreeNode root) {
        List<TreeNode> rightList = new LinkedList<>();
        rightList.add(root);
        Stack<List<TreeNode>> treeNodeStack = new Stack<>();
        treeNodeStack.add(rightList);
        while (!treeNodeStack.isEmpty()) {
            List<TreeNode> treeNodeList = treeNodeStack.pop();
            rightList = new LinkedList<>();
            //判断是否有值节点
            boolean flag = false;
            for (int i = 0; i < treeNodeList.size(); ++i) {
                if (treeNodeList.get(i) == null) {
                    if (treeNodeList.get(treeNodeList.size() - i - 1) != null) {
                        return false;
                    } else {
                        continue;
                    }
                } else if (treeNodeList.get(treeNodeList.size() - i - 1) == null) {
                    return false;
                } else if (treeNodeList.get(i).val != treeNodeList.get(treeNodeList.size() - i - 1).val) {
                    return false;
                }
                if (treeNodeList.get(i) != null) {
                    flag = true;
                }
                rightList.add(treeNodeList.get(i) == null ? null : treeNodeList.get(i).left);
                rightList.add(treeNodeList.get(i) == null ? null : treeNodeList.get(i).right);

            }
            if (flag) {
                treeNodeStack.add(rightList);
            }
        }
        return true;
    }

    /**
     *
     * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
     *
     * 示例:
     *
     * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
     * 输出: [3,3,5,5,6,7]
     * 解释:
     *
     *   滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length==0){
            return nums;
        }
        int[]res=new int[nums.length-k+1];
        int max=Integer.MIN_VALUE;
        /**
         * 最大值的下标 有相同的 取最右边的那个
         */
        int maxIndex=0;
        for(int i=0;i<k;++i){
//            max=Math.max(max,nums[i]);
            if(nums[i]>=max){
                max=nums[i];
                maxIndex=i;
            }
        }
        res[0]=max;
        for(int j=k;j<nums.length;++j ){
            if(nums[j]>=max){
                res[j-k+1]=nums[j];
                max=nums[j];
                maxIndex=j;
                continue;
            }
//            if(nums[j-k]!=max){
//                res[j-k+1]=max;
//                continue;
//            }
            /**
             * 最大值取到左边的窗口
             */
            //不是最左边那个数取在最大值 说明还在窗口中 可以直接用
            if(j-k!=maxIndex){
                res[j-k+1]=max;
                continue;
            }
            //最大值已经失效 需要重新计算
            max=nums[j-k+1];
            maxIndex=j-k+1;
            /**
             * 特殊case  k=1
             */
            if(k!=1) {
                for (int m = j - k + 2; m <= j; ++m) {
                    if (nums[m] >= max) {
                        max = nums[m];
                        maxIndex = m;
                    }
                }
            }
            res[j-k+1]=max;
        }
        return res;
    }

}
