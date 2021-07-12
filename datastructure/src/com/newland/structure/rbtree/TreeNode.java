package com.newland.structure.rbtree;

public class TreeNode<T extends Comparable> {
    private T value;
    private boolean red;
    private TreeNode left;
    private TreeNode right;
    private TreeNode parent;

    public TreeNode(T value) {
        this.value = value;
    }

    public void add(T value) {
        TreeNode<T> temp = this;
        TreeNode<T> newNode;
        while (true) {
            if (value.compareTo(temp.value) < 0) {
                if (temp.left == null) {
                    newNode = new TreeNode<>(value);
                    newNode.parent = temp;
                    temp.left = newNode;
                    break;
                } else {
                    temp = temp.left;
                }
            } else {
                if (temp.right == null) {
                    newNode = new TreeNode<>(value);
                    newNode.parent = temp;
                    temp.right = newNode;
                    break;
                } else {
                    temp = temp.right;
                }
            }
        }

    }

    private void fixNode(TreeNode node) {
        if (node.parent == null) {
            node.red = false;
            return;
        }
    }

    /**
     * 获取父节点的父节点
     *
     * @return
     */
    private TreeNode<T> getGrandParent() {
        return parent.parent;
    }

    /**
     * 叔节点
     *
     * @return
     */
    private TreeNode<T> getUncle() {
        TreeNode<T> grantParent = getGrandParent();
        if (grantParent == null) {
            return null;
        }
        return parent == grantParent.left ? grantParent.right : grantParent.left;
    }
}
