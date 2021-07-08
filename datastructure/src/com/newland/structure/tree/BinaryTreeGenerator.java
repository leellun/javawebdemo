package com.newland.structure.tree;

import java.util.Deque;
import java.util.LinkedList;

public class BinaryTreeGenerator {
    public static void main(String[] args) {
        new BinaryTreeGenerator().generatorBinaryTree(new int[]{1, 4, 5, 8, 2, 3, 6, 7}, new int[]{4, 5, 8, 1, 3, 2, 6, 7});
    }

    public void generatorBinaryTree(int[] pres, int[] ins) {
        Deque<Integer> deque = new LinkedList<>();
        generatorBinnaryTree(pres, ins, 0, 0, ins.length - 1, deque);
    }

    private void generatorBinnaryTree(int[] pres, int[] ins, int preStart, int inStart, int inEnd, Deque<Integer> deque) {
        if (inEnd < 0) return;
        int i = inStart;
        for (; i <= inEnd && ins[i] != pres[preStart]; i++) ;


        generatorBinnaryTree(pres, ins, preStart + 1, inStart, i - 1, deque);
        generatorBinnaryTree(pres, ins, i + 1, i + 1, inEnd, deque);
        deque.push(pres[inStart]);
    }

//    vector<int> ans;
//
//    void postOrder(int pre[], int in[], int length) {//同一二叉树的各种遍历序列一样长
//        if (length < 1) return;
//        int i = 0;
//        while (in[i] != pre[0]) ++i;
//        //左+右+根
//        postOrder(pre + 1, in, i);
//        postOrder(pre + i + 1, in + i + 1, length - i - 1);
//        ans.push_back(pre[0]);
//    }
//
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
