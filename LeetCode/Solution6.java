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

}
