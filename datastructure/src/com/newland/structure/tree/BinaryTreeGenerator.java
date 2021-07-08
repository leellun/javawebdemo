package com.newland.structure.tree;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class BinaryTreeGenerator {
    public static void main(String[] args) {
        //8 5 4 3 7 6 2 1
        new BinaryTreeGenerator().generatorBinaryTree(new int[]{1, 4, 5, 8, 2, 3, 6, 7}, new int[]{4, 5, 8, 1, 3, 2, 6, 7});
    }

    public void generatorBinaryTree(int[] pres, int[] ins) {
        Deque<Integer> deque = new LinkedList<>();
        generatorBinnaryTree(pres, 0, pres.length - 1, ins, 0, ins.length - 1, deque);
        System.out.println(Arrays.toString(deque.toArray(new Integer[]{})));
    }


    private void generatorBinnaryTree(int[] preOrder, int start1, int end1, int[] inOrder, int start2, int end2, Deque<Integer> deque) {
        if (start1 > end1 || start2 > end2) return;
        int i = start2;
        //获取根节点在中序队列中位置
        for (; inOrder[i] != preOrder[start1]; i++) ;
        //左子树
        generatorBinnaryTree(preOrder, start1 + 1, start1 + i - start2, inOrder, start2, i - 1, deque);
        //右子树
        generatorBinnaryTree(preOrder, start1 + i - start2 + 1, end1, inOrder, i + 1, end2, deque);
        deque.add(preOrder[start1]);
    }

//    void preOrder(int post[], int in[], int length) {
//        if (length < 1) return;
//        int i = 0;
//        while (in[i] != post[length - 1]) ++i;
//        //根+左+右
//        ans.push_back(post[length - 1]);//根是最后一个元素
//        preOrder(post, in, i);
//        preOrder(post + i, in + i + 1, length - i - 1);
//    }
}
