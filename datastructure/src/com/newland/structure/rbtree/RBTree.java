package com.newland.structure.rbtree;

import java.util.HashMap;

public class RBTree<T extends Comparable> {
    private Node<T> root;

    public void add(T value) {
        if (root == null) {
            root = new Node<>(value);
            root.setRed(false);
            return;
        }
        addNode(value);
    }

    private void addNode(T value) {
        Node parent = root;
        Node<T> node;
        while (true) {
            if (value.compareTo(parent.value) >= 0) {
                if (parent.right == null) {
                    node = new Node<>(value);
                    node.setParent(parent);
                    parent.setRight(node);
                    break;
                } else {
                    parent = parent.right;
                }
            } else {
                if (parent.left == null) {
                    node = new Node<>(value);
                    node.setParent(parent);
                    parent.setLeft(node);
                    break;
                } else {
                    parent = parent.left;
                }
            }
        }
    }

    private void fixNode(Node<T> node) {
        if (node == root) {
            node.setRed(false);
            return;
        }
        if (!node.parent.red) {
            return;
        }
        Node<T> uncle = this.getUncle(node);
        if (uncle != null && uncle.red) {
            node.parent.red = false;
            uncle.red = false;
            this.getGrandParent(node).red = true;
            this.fixNode(this.getGrandParent(node));
        } else if (uncle == null || !uncle.red) {
        }


    }

    private Node getUncle(Node node) {
        if (node.getParent() != root && node.getParent() == this.getGrandParent(node).left) {
            return this.getGrandParent(node).left;
        } else if (node.parent != root && node.getParent() == this.getGrandParent(node).right) {
            return getGrandParent(node).right;
        } else {
            return null;
        }
    }

    private Node getGrandParent(Node node) {
        return node.parent.parent;
    }

    public static class Node<T extends Comparable> {
        private T value;
        private boolean red;
        private Node left;
        private Node right;
        private Node parent;

        public Node(T value) {
            this.value = value;
            this.red = true;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public boolean isRed() {
            return red;
        }

        public void setRed(boolean red) {
            this.red = red;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }
    }
}
