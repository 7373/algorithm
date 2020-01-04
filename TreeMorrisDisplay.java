package algorithmUtils;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * *********************************************************************
 * <br/>
 *1. 如果当前节点的左子节点为空时，输出当前节点，并将当前节点置为该节点的右子节点；

 2. 如果当前节点的左子节点不为空，找到当前节点左子树的最右节点（该节点为当前节点中序遍历的前驱节点）；

 2.1. 如果最右节点的右指针为空(right=null)，将最右节点的右指针指向当前节点，当前节点置为其左子节点；

 2.2. 如果最右节点的右指针不为空，将最右节点右指针重新置为空(恢复树的原状)，输出当前节点，并将当前节点置为其右节点；

 3. 重复1~2，直到当前节点为空。
 ---------------------
 */
public class TreeMorrisDisplay {


    /**
     * 左右两边分成2份  左边遍历完通过父节点回调到右节点 如果没有子树直接打印
     * @when 2019/3/27
     */
    public static void main(String[] args) {
        /**
         *              4
         *         2              6
         * 1          3       5       7
         *
         * @when 2019/3/27
         */
        TreeNode head = new TreeNode(4);
        head.left = new TreeNode(2);
        head.right = new TreeNode(6);
        head.left.left = new TreeNode(1);
        head.left.right = new TreeNode(3);
        head.right.left = new TreeNode(5);
        head.right.right = new TreeNode(7);
        System.out.println(Arrays.toString(Morris_InOrder(head).toArray()));
    }

    public static List<Integer> Morris_InOrder(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        if(root == null) {
            /**
             * 返回空
             * @when 2019/3/27
             */
            return resultList;
        }
        /**
         * 当前指针currentNode指向 当前节点
         * 前驱指针prevNode 指向前一个节点
         * @when 2019/3/27
         */
        TreeNode currentNode = root;
        TreeNode prevNode =null;
        /**
         * 当前指针不为空
         * @when 2019/3/27
         */
        while(currentNode != null) {
            /**
             * 如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点。
             * @when 2019/3/27
             */
            if(currentNode.left == null) {
                resultList.add(currentNode.val);

                currentNode = currentNode.right;
            }
            /**
             * 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
             * @when 2019/3/27
             */
            else {
                /**
                 * 前驱节点指针：循环找到当前节点左子树下最右边的节点
                 * @when 2019/3/27
                 */
                prevNode = currentNode.left;
                /**
                 * 前驱节点右节点不等于当前节点
                 */
                while(prevNode.right != null && prevNode.right != currentNode){
                    prevNode = prevNode.right;
                }
                if(prevNode.right == null) {
                    /**
                     * 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
                     * @when 2019/3/27
                     */
                    prevNode.right = currentNode;
                    currentNode = currentNode.left;
                }
                else {
                    /**
                     * 这种情况是父节点为当前节点 左子树的right为父节点 =》相等 ：pre节点已经跟父节点连接好
                     * 此时父节点左子树已经遍历完
                     * 打印父节点 并下一步开始遍历右子树
                     * @when 2019/3/27
                     */

                    /**
                     * 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。输出当前节点。当前节点更新为当前节点的右孩子
                     * @when 2019/3/27
                     */
                    resultList.add(currentNode.val);

                    prevNode.right = null;
                    currentNode = currentNode.right;
                }
            }
        }
        /**
         * 重复直到当前节点为空
         * @when 2019/3/27
         */
        return resultList;
    }


    @Data
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
}
