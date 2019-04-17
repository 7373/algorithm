package algorithmUtils;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * *********************************************************************
 * <br/>
 * <p>
 * 二叉树方法：
 * 1. 如果当前节点的左子节点为空时，输出当前节点，并将当前节点置为该节点的右子节点；
 * <p>
 * 2. 如果当前节点的左子节点不为空，找到当前节点左子树的最右节点（该节点为当前节点中序遍历的前驱节点）；
 * <p>
 * 2.1. 如果最右节点的右指针为空(right=null)，将最右节点的右指针指向当前节点，当前节点置为其左子节点；
 * <p>
 * 2.2. 如果最右节点的右指针不为空，将最右节点右指针重新置为空(恢复树的原状)，输出当前节点，并将当前节点置为其右节点；
 * <p>
 * 3. 重复1~2，直到当前节点为空。
 * K叉树方法：
 * 复杂一点 不过原理相同
 * ---------------------
 */
public class KTreeMorrisDisplay {


    /**
     * 左右两边分成2份  左边遍历完通过父节点回调到右节点 如果没有子树直接打印
     *
     * @when 2019/3/27
     */
    public static void main(String[] args) {

        /**
         * 《测试一》
         *              4
         *         2    5     7
         *
         * 正确结果：[2, 5, 4, 7]
         * @when 2019/3/27
         */
        TreeNode root = new TreeNode(4);
        List<TreeNode> childNodes = Lists.newArrayListWithCapacity(3);
        childNodes.add(new TreeNode(2));
        childNodes.add(new TreeNode(5));
        childNodes.add(new TreeNode(7));
        root.setChildNode(childNodes);
        System.out.println("《测试一》\n" + Arrays.toString(morrisKTree(root).toArray()));

        /**
         * 《测试二》
         *                     4
         *         2           5              7
         *      1  3 6        9  0         12  15  18
         *
         * 正确结果：[1, 3, 2, 6, 9, 5, 0, 4, 12, 15, 7, 18]
         * @when 2019/3/27
         */
        TreeNode root2 = new TreeNode(4);
        TreeNode left = new TreeNode(2);
        TreeNode mid = new TreeNode(5);
        TreeNode right = new TreeNode(7);

        childNodes = Lists.newArrayListWithCapacity(3);
        childNodes.add(new TreeNode(1));
        childNodes.add(new TreeNode(3));
        childNodes.add(new TreeNode(6));
        left.setChildNode(childNodes);
        childNodes = Lists.newArrayListWithCapacity(2);
        childNodes.add(new TreeNode(9));
        childNodes.add(new TreeNode(0));
        mid.setChildNode(childNodes);
        childNodes = Lists.newArrayListWithCapacity(3);
        childNodes.add(new TreeNode(12));
        childNodes.add(new TreeNode(15));
        childNodes.add(new TreeNode(18));
        right.setChildNode(childNodes);

        childNodes = Lists.newArrayListWithCapacity(3);
        childNodes.add(left);
        childNodes.add(mid);
        childNodes.add(right);
        root2.setChildNode(childNodes);
        System.out.println("《测试二》\n" + Arrays.toString(morrisKTree(root2).toArray()));

    }

    public static List<Integer> morrisKTree(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        if (root == null) {
            /**
             * 返回空
             * @when 2019/3/27
             */
            return resultList;
        }
        /**
         * 当前指针currentNode指向 ->当前节点
         * 前驱指针prevNode ->指向前一个节点
         * @when 2019/3/27
         */
        TreeNode currentNode = root;
        TreeNode prevNode = null;
        /**
         * 记录指向哪个节点
         * @when 2019/4/10
         */
        int whilchNode = 0;
        /**
         * 当前指针不为空
         * @when 2019/3/27
         */
        flag:
        while (currentNode != null) {
            /**
             * 如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点。
             * @when 2019/3/27
             */
            if (currentNode.head == null) {
                resultList.add(currentNode.val);
                /**
                 * 找一下节点
                 * @when 2019/4/15
                 */
                prevNode = currentNode;
                currentNode = prevNode.tail;
                if (Objects.isNull(currentNode)) {
                    /**
                     * 1、在无回调 2、叶子节点了
                     * end 标志
                     * @when 2019/4/15
                     */
                    break flag;
                }
                if (currentNode.isTailPrev(prevNode)) {
                    /**
                     * 结束回调操作
                     * （一）这种情况是 已经到尾节点前面节点 不需要回调父节点 直接到尾节点
                     * @when 2019/4/10
                     */
                    resultList.add(currentNode.val);
                    prevNode.tail = null;
                    whilchNode = 0;
                    currentNode = currentNode.tail;
//                    continue flag;
                } else {
                    ++whilchNode;
                    /**
                     * 判断是否回调（应该不需要 去掉了）
                     * @when 2019/4/15
                     */
                }
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
                prevNode = currentNode.childNode.get(whilchNode);
                /**
                 * 前驱节点右节点不等于当前节点
                 */
                while (prevNode.tail != null && prevNode.tail != currentNode) {
                    /**
                     * 一直找到到前驱节点右子树的最右叶子节点
                     * @when 2019/4/10
                     */
                    prevNode = prevNode.tail;
                }
                if (prevNode.tail == null) {
                    /**
                     * 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
                     * 连接起来:关键原因 叶子节点的尾节点为空
                     * @when 2019/3/27
                     */
                    prevNode.tail = currentNode;
                    currentNode = currentNode.childNode.get(whilchNode);
                    whilchNode = 0;
                }
                /**
                 * prevNode.tail == currentNode
                 * @when 2019/4/10
                 */
                else {
                    /**
                     * 这种情况是父节点为当前节点 左子树的right为父节点 =》相等 ：pre节点已经跟父节点连接好
                     * 此时父节点左子树已经遍历完
                     * 打印父节点 并下一步开始遍历右子树
                     * @when 2019/3/27
                     */

                    /**
                     * 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。
                     *
                     * 输出当前节点（需要判断是否是尾节点前面节点==）。当前节点更新为当前节点的右孩子
                     * @when 2019/3/27
                     */
                    if (currentNode.isTailPrev(prevNode)) {
                        /**
                         * 结束回调操作
                         *  （一）这种情况是 已经到尾节点前面节点 不需要回调父节点 遍历到尾节点
                         * @when 2019/4/10
                         */
                        resultList.add(currentNode.val);
                        prevNode.tail = null;
                        whilchNode = 0;
                        currentNode = currentNode.tail;
                        continue flag;
                    }
                    /**
                     * （二）这种情况是 不是尾节点前面节点  继续找前面的节点
                     * @when 2019/4/10
                     */
                    else {
                        ++whilchNode;
                    }
                    /**
                     * whilchNode不可能等于K叉
                     * @when 2019/4/10
                     */
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
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    static class TreeNode {
        int val;
        List<TreeNode> childNode;
        /**
         * 子节点中的头
         * childNode[0]
         *
         * @when 2019/4/15
         */
        TreeNode head;
        /**
         * 子节点中的尾
         * childNode[size-1]
         *
         * @when 2019/4/15
         */
        TreeNode tail;
        /**
         * 判断是否是尾节点前面节点
         * 实际上是判断回调方法
         *
         * @when 2019/4/15
         */
        boolean isTailPrev(TreeNode treeNode) {
            /**
             * 当前节点应该为根节点
             * 可以再修改 可能存在多级
             * @when 2019/4/15
             */
            TreeNode tailPrevNode = childNode.get(childNode.size() - 2);
            if (Objects.isNull(tailPrevNode.childNode)) {
                return tailPrevNode == treeNode;
            } else {
                /**
                 * 找root节点->尾节点前面节点的->最右叶子节点
                 * @when 2019/4/15
                 */
                while (tailPrevNode.tail != null && tailPrevNode.tail != this) {
                    /**
                     * 一直找到到前驱节点右子树的最右叶子节点
                     * @when 2019/4/10
                     */
                    tailPrevNode = tailPrevNode.tail;
                }
                return tailPrevNode == treeNode;
            }

            /**
             * 这里改成 1、是否是tail节点的前面节点 2、该节点的祖宗节点（ROOT下一级）是否是尾节点前面节点
             * 判断回调条件
             * @when 2019/4/15
             */
        }

        public void setChildNode(List<TreeNode> childNode) {
            this.childNode = childNode;
            this.tail = childNode.get(childNode.size() - 1);
            this.head = childNode.get(0);

        }
        public void setHead(TreeNode head) {
            this.head = head;
            /**
             * 两层含义：1、添加一个head 2、替换之前的head
             * @when 2019/4/15
             */
        }

        public void setTail(TreeNode tail) {
            this.tail = tail;
            this.childNode.add(tail);
        }
        TreeNode(int x) {
            val = x;
        }
    }
}
