package com.newland.structure.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BiTree {
    private Node root;
    public BiTree(){
        root = null;
    }
    //后序遍历方法递归实现
    public void postOrder(Node localRoot){
        if(localRoot!=null){
            postOrder(localRoot.left);
            postOrder(localRoot.right);
            System.out.print(localRoot.value+" ");
        }
    }

    public void postOrder() {
        this.postOrder(this.root);
    }

    public void initTree(int[] preOrder,int[] inOrder){
        this.root = this.initTree(preOrder,0,preOrder.length-1,inOrder,0,inOrder.length-1);
        System.out.println(Arrays.toString(list.toArray(new Integer[]{})));
    }
    private List<Integer> list=new ArrayList<>();
    public Node initTree(int[] preOrder,int start1,int end1,int[] inOrder,int start2,int end2){
        if(start1>end1||start2>end2){
            return null;
        }
        int rootData = preOrder[start1];
        Node head = new Node(rootData);
        //找到根节点所在位置
        int rootIndex = findIndexInArray(inOrder,rootData,start2,end2);
        //构建左子树(不太懂end1表示的含义)
        Node left = initTree(preOrder,start1+1,start1+rootIndex-start2,inOrder,start2,rootIndex-1);
        //构建右子树（不太懂start1表示的含义）
        Node right = initTree(preOrder,start1+rootIndex-start2+1,end1,inOrder,rootIndex+1,end2);
        head.left = left;
        head.right = right;
        list.add(rootData);
        return head;
    }

    public int findIndexInArray(int[] a,int x,int begin,int end){
        for(int i = begin;i<=end; i++){
            if(a[i]==x){
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] preOrder = new int[]{1, 4, 5, 8, 2, 3, 6, 7};
        int[] inOrder = new int[]{4, 5, 8, 1, 3, 2, 6, 7};
        BiTree biTree = new BiTree();
        biTree.initTree(preOrder, inOrder);
        System.out.print("二叉树的后序遍历:");
        biTree.postOrder();
    }
}