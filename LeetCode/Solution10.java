package acm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class Solution10 {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);

        treeNode.left = treeNode2;
        treeNode.right = treeNode3;
        treeNode3.left = treeNode4;
        treeNode3.right = treeNode5;
        display(treeNode);
    }

    static class Node {
        int value;
        Node next;
    }

    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    /**
     * 一个单链表,尾节点指向链表中间的某个节点
     * <p>
     * 找出这个被尾节点指向的节点
     */

    static Node getTailNode(Node node) {
        HashSet<Node> sets = new HashSet<>();
        while (node != null) {
            if (sets.contains(node)) {
                return node;
            }
            sets.add(node);
            node = node.next;
        }

        return null;
    }

    static Node getTailNode2(Node node) {
        Node fast = node;
        Node slow = node;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                fast = node;
                while (fast != slow) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return fast;
            }
        }

        return null;
    }

    /**
     * 数据结构：非递归实现，中根遍历二叉树
     */

//    static void displayV1(TreeNode head) {
//
//        Stack<TreeNode> stack = new Stack<>();
//        stack.add(head);
//        TreeNode treeNode;
//        do {
//            treeNode = stack.pop();
//            if (treeNode.right != null) {
//                stack.add(treeNode.right);
//            }
//            System.out.print(treeNode.value);
//            if (treeNode.left != null) {
//                stack.add(treeNode.left);
//            }
//        } while (!stack.isEmpty());
//
//    }
    static void display(TreeNode head) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        while (head != null || !stack.isEmpty()) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                res.add(head.value);
                head = head.right;
            }
        }

    }

    /**
     * 1
     * 2 3
     * 4 5
     */
    static void display2(TreeNode head) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        while (head != null || !stack.isEmpty()) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                res.add(head.value);
                head = head.right;
            }
        }

    }

    public void behindOrder2(TreeNode node) {
        if (node == null) {
            return;

        }
        Stack<TreeNode> s = new Stack<>();
        TreeNode curNode; //当前访问的结点
        TreeNode lastVisitNode; //上次访问的结点
        curNode = node;
        lastVisitNode = node;

        //把currentNode移到左子树的最下边
        while (curNode != null) {
            s.push(curNode);
            curNode = curNode.left;
        }
        while (!s.empty()) {
            curNode = s.pop();  //弹出栈顶元素
            //一个根节点被访问的前提是：无右子树或右子树已被访问过
            if (curNode.right != null && curNode.right != lastVisitNode) {
                //根节点再次入栈
                s.push(curNode);
                //进入右子树，且可肯定右子树一定不为空
                curNode = curNode.right;
                while (curNode != null) {
                    //再走到右子树的最左边
                    s.push(curNode);
                    curNode = curNode.left;
                }
            } else {
                //访问
                System.out.print(curNode.value + " ");
                //修改最近被访问的节点
                lastVisitNode = curNode;
            }
        } //while
    }


    /**
     * 后序遍历
     * 非递归
     */
    public void posOrder1(TreeNode Node)
    {
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        int i = 1;
        while(Node != null || !stack1.empty())
        {
            while (Node != null)
            {
                stack1.push(Node);
                stack2.push(0);
                Node = Node.left;
            }

            while(!stack1.empty() && stack2.peek() == i)
            {
                stack2.pop();
                System.out.print(stack1.pop().value + "   ");
            }

            if(!stack1.empty())
            {
                stack2.pop();
                stack2.push(1);
                Node = stack1.peek();
                Node = Node.right;
            }
        }
    }


}
