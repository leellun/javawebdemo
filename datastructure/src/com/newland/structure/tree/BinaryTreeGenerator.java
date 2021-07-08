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
        generatorBinnaryTree(pres, ins, 0, 0, ins.length - 1, deque);
        System.out.println(Arrays.toString(deque.toArray(new Integer[]{})));
    }

    private void generatorBinnaryTree(int[] pres, int[] ins, int preStart, int inStart, int length, Deque<Integer> deque) {
        if (length < 1 || preStart >= pres.length - 1) return;
        System.out.println(preStart + "==>" + inStart + "===<" + length);
        int i = inStart;
        for (; ins[i] != pres[preStart]; i++) ;
        if(pres[preStart]==8){
            System.out.println();
        }

        generatorBinnaryTree(pres, ins, preStart + 1, inStart, i, deque);
        generatorBinnaryTree(pres, ins, preStart + i + 1, inStart + i + 1, length - i - 1, deque);
        deque.add(pres[preStart]);
    }

//    void posttraverse(char *preorder, char *inorder, int len)//求后序
//    {
//        if (len == 0) return;
//        int rootindex = 0;
//        node newnode;
//        newnode.data = preorder[0];//先序字符串的首元素是根节点
//        for (rootindex = 0; preorder[0] != inorder[rootindex]; rootindex++) ;//这一步是找到根节点在中序字符串中的位置
//        posttraverse(preorder + 1, inorder, rootindex);//递归遍历左子树
//        posttraverse(preorder + rootindex + 1, inorder + rootindex + 1, len - rootindex - 1);//递归遍历右子树
//        cout <<*preorder;//打印根节点，为什么要放在最后呢?因为这是求后序遍历，如果是求先序遍历
//        //就在递归之前打印根节点
//    }


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
