package com.newland.structure.tree;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class BinaryTreeGenerator {
    public static void main(String[] args) {
        System.out.println("通过先序中序获取后续遍历：");
        new BinaryTreeGenerator().postBinaryTree(new int[]{1, 4, 5, 8, 2, 3, 6, 7}, new int[]{4, 5, 8, 1, 3, 2, 6, 7});
        System.out.println("通过后续中序获取先序遍历：");
        new BinaryTreeGenerator().preBinnaryTree(new int[]{8, 5, 4, 3, 7, 6, 2, 1}, new int[]{4, 5, 8, 1, 3, 2, 6, 7});
    }

    public void postBinaryTree(int[] pres, int[] ins) {
        Deque<Integer> deque = new LinkedList<>();
        postBinnaryTree(pres, 0, pres.length - 1, ins, 0, ins.length - 1, deque);
        System.out.println(Arrays.toString(deque.toArray(new Integer[]{})));
    }

    public void preBinnaryTree(int[] post, int[] ins) {
        Deque<Integer> deque = new LinkedList<>();
        preBinnaryTree(post, 0, post.length - 1, ins, 0, ins.length - 1, deque);
        System.out.println(Arrays.toString(deque.toArray(new Integer[]{})));
    }

    /**
     * 通过先序遍历和中序遍历获取后续遍历
     *
     * @param preOrder 先序遍历
     * @param start1   先序开始位置
     * @param end1     先序结束位置
     * @param inOrder  中序遍历数组
     * @param start2   中序开始位置
     * @param end2     中序结束位置
     * @param deque    后续遍历结果保存集合
     */
    private void postBinnaryTree(int[] preOrder, int start1, int end1, int[] inOrder, int start2, int end2, Deque<Integer> deque) {
        if (start1 > end1 || start2 > end2) return;
        int i = start2;
        //获取根节点在中序队列中位置
        for (; inOrder[i] != preOrder[start1]; i++) ;
        //左子树
        postBinnaryTree(preOrder, start1 + 1, start1 + i - start2, inOrder, start2, i - 1, deque);
        //右子树
        postBinnaryTree(preOrder, start1 + i - start2 + 1, end1, inOrder, i + 1, end2, deque);
        //保存节点
        deque.add(preOrder[start1]);
    }

    /**
     * 通过后续和中序获取先序
     *
     * @param postOrder 后续
     * @param start1    先序开始位置
     * @param end1      先序结束位置
     * @param inOrder   中序
     * @param start2    中序开始位置
     * @param end2      中序end位置
     * @param deque     用于保存先序遍历结果
     */
    private void preBinnaryTree(int[] postOrder, int start1, int end1, int[] inOrder, int start2, int end2, Deque<Integer> deque) {
        if (start1 > end1 || start2 > end2 || end1 >= postOrder.length) return;
        int i = start2;
        //获取根节点在中序队列中位置
        for (; inOrder[i] != postOrder[end1]; i++) ;
        //保存节点
        deque.add(postOrder[end1]);
        //左子树
        preBinnaryTree(postOrder, start1, end1 - (end2 - i) - 1, inOrder, start2, i - 1, deque);
        //右子树
        preBinnaryTree(postOrder, end1 - (end2 - i), end1 - 1, inOrder, i + 1, end2, deque);
    }

}
