package algorithmUtils;


import lombok.*;

/**
 * *********************************************************************
 * <br/>
 *
 * @date 2019/2/13 15:42
 * @vision V1.0.1
 * *********************************************************************
 */

public class RedBlackTree {

    /**
     * 红黑树性质四点：
     * <p>
     * 1、每个节点要么是红色，要么是黑色。
     * 2、根节点必须是黑色
     * 3、 红色节点不能连续（也即是，红色节点的孩子和父亲都不能是红色）。
     * 4、对于每个节点，从该点至null（树尾端）的任何路径，都含有相同个数的黑色节点。
     * 隐藏性质：左右子树高度差不超过一倍，自平衡
     * 中序遍历单调递增
     */

    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private transient TreeNode root;


    /**
     * 新增节点后的调整操作
     * x 表示新增节点
     */
    private void fixAfterInsert(TreeNode x) {
        /**
         *  新增节点的颜色设置为红色
         */
        x.color = RED;
        //循环 :直到 x是根节点，且x的父节点不为红色
        while (x != null && x != root && x.parent.color == RED) {
            /**
             * 判断父亲是否是爷爷的左儿子：产生的两种情况是完全对称的
             * 对称情况一：若父亲节点为左儿子
             */
            //如果X的父节点（P）是其爷爷节点（G）的左节点
            if (parentOf(x) == leftOf(grandpaOf(x))) {
                //获取X的叔叔节点(U)
                TreeNode U = rightOf(grandpaOf(x));
                /**
                 * 如果X的叔节点（U） 为红色（情况一）
                 * 变色
                 * @when
                 */
                if (colorOf(U) == RED) {
                    //将X的父节点（P）设置为黑色
                    setColor(parentOf(x), BLACK);
                    //将X的叔节点（U）设置为黑色
                    setColor(U, BLACK);
                    //将X的爷爷节点（G）设置红色
                    setColor(grandpaOf(x), RED);
                    x = grandpaOf(x);
                }
                //如果X的叔节点（U为黑色）；这里会存在两种情况（情况二、情况三）分别判断X为左节点还是右节点
                else {
                    /**
                     * 如果X节点为其父节点（P）的右子树，则进行左旋转（情况二）
                     * 左旋
                     * @when
                     */
                    if (x == rightOf(parentOf(x))) {
                        //将X的父节点作为X  赋值给X
                        x = parentOf(x);
                        /**
                         *  以父节点为中心旋转  /左旋转
                         */
                        rotateLeft(x);
                    }

                    /**
                     * （情况三）如果X节点为其父节点（P）的左子树
                     * 将X的父节点（P）设置为黑色
                     * @when
                     */
                    setColor(parentOf(x), BLACK);
                    //将X的爷爷节点（G）设置红色
                    setColor(grandpaOf(x), RED);
                    //以X的爷爷节点（G）为中心右旋转
                    rotateRight(grandpaOf(x));
                }
            }
            /**
             *   对称情况二：若父亲节点为右儿子
             */
            //如果X的父节点（P）是其父节点的父节点（G）的右节点
            else {
                //获取X的叔节点（U）
                TreeNode y = leftOf(grandpaOf(x));
                //如果X的叔节点（U） 为红色（情况一）
                if (colorOf(y) == RED) {
                    //将X的父节点（P）设置为黑色
                    setColor(parentOf(x), BLACK);
                    //将X的叔节点（U）设置为黑色
                    setColor(y, BLACK);
                    //将X的父节点的父节点（G）设置红色
                    setColor(grandpaOf(x), RED);
                    x = grandpaOf(x);
                }
                //如果X的叔节点（U为黑色）；这里会存在两种情况（情况二、情况三）
                else {
                    //如果X节点为其父节点（P）的右子树，则进行左旋转（情况二）
                    if (x == leftOf(parentOf(x))) {
                        //将X的父节点作为X
                        x = parentOf(x);
                        //右旋转
                        rotateRight(x);
                    }
                    //（情况三）
                    //将X的父节点（P）设置为黑色
                    setColor(parentOf(x), BLACK);
                    //将X的父节点的父节点（G）设置红色
                    setColor(grandpaOf(x), RED);
                    //以X的父节点的父节点（G）为中心左旋转
                    rotateLeft(grandpaOf(x));
                }
            }
            /**
             *  完成一遍调整：重复操作
             */
        }
        //将根节点G强制设置为黑色
        root.color = BLACK;
    }

    /**
     * 删除节点后的调整操作
     * x 表示删除节点
     */
    private void fixAfterDelete(TreeNode x) {
        // 删除节点需要一直迭代， 直到 x 是根节点，且 x 的颜色是黑色
        while (x != root && colorOf(x) == BLACK) {
            /**
             *
             * 对称情况一：若X节点为左儿子
             */
            if (x == leftOf(parentOf(x))) {
                //获取其兄弟节点
                TreeNode sib = rightOf(parentOf(x));
                /**
                 * 情况一：如果兄弟节点为红色----
                 * 策略：改变W、P的颜色，然后进行一次左旋转
                 */
                if (colorOf(sib) == RED) {
                    setColor(sib, BLACK);
                    setColor(parentOf(x), RED);
                    rotateLeft(parentOf(x));
                    sib = rightOf(parentOf(x));
                }
                /**
                 *  情况二：若兄弟节点的两个子节点都为黑色----
                 * 策略：将兄弟节点变成红色
                 */
                if (colorOf(leftOf(sib)) == BLACK &&
                        colorOf(rightOf(sib)) == BLACK) {
                    setColor(sib, RED);
                    x = parentOf(x);
                } else {
                    /**
                     *  情况三：如果兄弟节点只有右子树为黑色----
                     * 策略：将兄弟节点与其左子树进行颜色互换然后进行右转
                     * 这时情况会转变为3.4
                     */
                    if (colorOf(rightOf(sib)) == BLACK) {
                        setColor(leftOf(sib), BLACK);
                        setColor(sib, RED);
                        rotateRight(sib);
                        sib = rightOf(parentOf(x));
                    }
                    /**
                     * 情况四：排除前两种情况下都要执行
                     *策略：交换兄弟节点和父节点的颜色，
                     *同时将兄弟节点右子树设置为黑色，最后左旋转
                     */
                    setColor(sib, colorOf(parentOf(x)));
                    setColor(parentOf(x), BLACK);
                    setColor(rightOf(sib), BLACK);
                    rotateLeft(parentOf(x));
                    x = root;
                }
            }

            /**
             *   对称情况二：若X节点为右儿子
             */
            else {
                TreeNode sib = leftOf(parentOf(x));

                if (colorOf(sib) == RED) {
                    setColor(sib, BLACK);
                    setColor(parentOf(x), RED);
                    rotateRight(parentOf(x));
                    sib = leftOf(parentOf(x));
                }

                if (colorOf(rightOf(sib)) == BLACK &&
                        colorOf(leftOf(sib)) == BLACK) {
                    setColor(sib, RED);
                    x = parentOf(x);
                } else {
                    if (colorOf(leftOf(sib)) == BLACK) {
                        setColor(rightOf(sib), BLACK);
                        setColor(sib, RED);
                        rotateLeft(sib);
                        sib = leftOf(parentOf(x));
                    }
                    setColor(sib, colorOf(parentOf(x)));
                    setColor(parentOf(x), BLACK);
                    setColor(leftOf(sib), BLACK);
                    rotateRight(parentOf(x));
                    x = root;
                }
            }
        }
        setColor(x, BLACK);
    }

    /**
     * 删除节点：找到一个真后继节点C来替代P，然后直接删除C，最后调整这棵红黑树。下面代码是寻找替代节点、删除替代节点。主要为了方便删除：没有儿子的节点
     * （1）处是寻找替代节点replacement，其实现方法为successor()
     * （2）处是删除该节点过程。它主要分为上面提到的三种情况（分为二种情况即可），它与上面的if…else if… else一一对应 。如下：
     * <p>
     *        1、节点D有两个儿子。用儿子节点C(2)替代待删除节点D，然后删除子节点C(2)即可。
     * <p>
     *        2、节点D没有儿子，即为叶结点。直接把父结点的对应儿子指针设为NULL，删除儿子结点就OK了。
     * <p>
     *        3、节点D只有一个儿子。那么节点D的父节点的儿子指针->指向节点D的儿子 ;节点D的儿子指针指向->节点D的父节点，删除节点D也OK了。
     * <p>
     * （3）删除该节点后调整：
     *        4、 删除完节点D，就要根据情况来对红黑树进行复杂的调整：fixAfterDelete(D)。
     */
    private void delete(TreeNode p) {
        /**
         * 寻找真后继节点并替换P节点操作：
         */
        /*
         * 被删除节点的左子树和右子树都不为空，那么就用 p节点的中序后继节点代替 p 节点
         * successor(P)方法：寻找真后继节点：
         * successor(P)方法为寻找P的替代节点。规则是:右分支不为空？：1、右分支最左边，否则 2、左分支最右边的节点
         * -----------------------（1）
         */
        if (p.left != null && p.right != null) {
            /**
             * 找到真后继
             * @when 2019/4/16
             */
            TreeNode s = successor(p);
            /*这里要知道是值传递,传递的内存地址：KV为泛型*/
            /**
             * 替换P
             * @when 2019/4/16
             */
            p.value = s.value;
            p = s;
        }

        /**
         * 以下是删除操作：实际上就是用左儿子（优先级更高）或者右儿子去替换P节点
         */
        //replacement为替代节点，如果P的左子树存在那么就用左子树替代，否则用右子树替代
        TreeNode replacement = (p.left != null ? p.left : p.right);

        /*
         * 删除节点，分为上面提到的三种情况
         * -----------------------（2）
         */
        //如果替代节点不为空
        /**
         * 情况一：P节点包含儿子：则replacement不为空
         */
        if (replacement != null) {
            /**
             * 1：父指针指向同一个
             */
            replacement.parent = p.parent;
            /**
             * 2：儿子指针指向replacement
             */
            //若P没有父节点，则跟节点直接变成replacement
            if (p.parent == null) {
                root = replacement;
            }
            //如果P为左节点，则用replacement来替代为左节点
            else if (p == p.parent.left) {
                p.parent.left = replacement;
            }
            //如果P为右节点，则用replacement来替代为右节点
            else {
                p.parent.right = replacement;
            }
            /**
             * 3：删除P节点
             */
            //同时将P节点从这棵树中剔除掉
            p.left = p.right = p.parent = null;
            /**
             * 若P为红色直接删除，红黑树保持平衡
             * 但是若P为黑色，则需要调整红黑树使其保持平衡
             */

            /**
             * 4：判断删除后节点颜色进行调整树平衡
             */
            /*这里p没有置空，color属性还在*/
            if (p.color == BLACK) {
                /*一：replacement不为空，P已经删除指针了*/
                /**
                 * replacement：颜色是没有变的，replacement可能是红色也不需要调整，因为判断的是P节点颜色
                 */
                fixAfterDelete(replacement);
            }
        }
        /**
         * 特殊情况：P为根节点，直接置空
         */
        //p没有父节点，表示为P根节点，直接删除即可
        else if (p.parent == null) {
            root = null;
        }
        /**
         * 情况二：P节点不包含儿子（叶子节点）：则replacement为空
         */
        //P节点不存在子节点，直接删除即可
        else {
            //如果P节点的颜色为黑色，对红黑树进行调整
            if (p.color == BLACK) {
                /*二：replacement为空，调整P*/
                fixAfterDelete(p);
            }
            //删除P节点
            if (p.parent != null) {
                if (p == p.parent.left) {
                    p.parent.left = null;
                } else if (p == p.parent.right) {
                    p.parent.right = null;
                }
                p.parent = null;
            }
        }
    }

    /**
     * 找真后继节点方法
     * <p>
     * 1、寻找右子树的最左叶子节点 2、或者左子树的最右叶子节点
     */
    static TreeNode successor(TreeNode t) {
        if (t == null)
            return null;
        /*
         * 寻找右子树的最左子树
         */
        else if (t.right != null) {
            TreeNode p = t.right;
            while (p.left != null)
                p = p.left;
            return p;
        }
        /*
         * 选择左子树的最右子树
         */
        else {
            TreeNode p = t.parent;
            TreeNode ch = t;
            while (p != null && ch == p.right) {
                ch = p;
                p = p.parent;
            }
            return p;
        }
    }


    /**
     * From CLR
     * P      =>     N
     * A    N         P    b
     * a   b     A   a
     */
    /* 所谓左旋转，就是将新增节点（N）当做其父节点（P），将其父节点P当做新增节点（N）的左子节点。即：G.left ---> N ,N.left ---> P。*/
    private void rotateLeft(TreeNode p) {
        if (p != null) {
            //获取P的右子节点，其实这里就相当于新增节点N（情况四而言）
            TreeNode r = p.right;
            //将R的左子树设置为P的右子树
            p.right = r.left;
            //若R的左子树不为空，则将P设置为R左子树的父亲
            if (r.left != null)
                r.left.parent = p;
            //将P的父亲设置R的父亲
            r.parent = p.parent;
            //如果P的父亲为空，则将R设置为跟节点
            if (p.parent == null)
                root = r;
                //如果P为其父节点（G）的左子树，则将R设置为P父节点(G)左子树
            else if (p.parent.left == p)
                p.parent.left = r;
                //否则R设置为P的父节点（G）的右子树
            else
                p.parent.right = r;
            //将P设置为R的左子树
            r.left = p;
            //将R设置为P的父节点
            p.parent = r;
        }

    }

    /**
     * From CLR
     */
    /*  所谓右旋转即，左节点.right ---> 父节点、父节点.parent ---> 左节点。*/
    private void rotateRight(TreeNode p) {
        if (p != null) {
            //将L设置为P的左子树
            TreeNode l = p.left;
            //将L的右子树设置为P的左子树
            p.left = l.right;
            //若L的右子树不为空，则将P设置L的右子树的父节点
            if (l.right != null)
                l.right.parent = p;
            //将P的父节点设置为L的父节点
            l.parent = p.parent;
            //如果P的父节点为空，则将L设置根节点
            if (p.parent == null)
                root = l;
                //若P为其父节点的右子树，则将L设置为P的父节点的右子树
            else if (p.parent.right == p)
                p.parent.right = l;
                //否则将L设置为P的父节点的左子树
            else
                p.parent.left = l;
            //将P设置为L的右子树
            l.right = p;
            //将L设置为P的父节点
            p.parent = l;
        }

    }

    /**
     * <p>
     * 常用方法
     * algorithms.
     */

    private static boolean colorOf(TreeNode p) {
        return (p == null ? BLACK : p.color);
    }

    private static TreeNode parentOf(TreeNode p) {
        return (p == null ? null : p.parent);
    }

    private static void setColor(TreeNode p, boolean c) {
        if (p != null)
            p.color = c;
    }

    private static TreeNode leftOf(TreeNode p) {
        return (p == null) ? null : p.left;
    }

    private static TreeNode rightOf(TreeNode p) {
        return (p == null) ? null : p.right;
    }

    private static TreeNode grandpaOf(TreeNode p) {
        return (p == null) ? null : p.parent.parent;
    }


    /**
     *
     *
     */

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    static final class TreeNode {

        int value;
        TreeNode left;
        TreeNode right;
        TreeNode parent;
        boolean color = BLACK;

        TreeNode(int value, TreeNode parent) {
            this.value = value;
            this.parent = parent;
        }

    }


    /////auto

}
