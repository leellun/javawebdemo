package com.newland.structure.rbtree;

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
        fixNode(node);
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
            if (this.isLeft(node) && node.parent == this.getGrandParent(node).left) {
                this.fixLeftNode(node);
            } else if (!this.isLeft(node) && node.parent == this.getGrandParent(node).right) {
                this.fixRightNode(node);
            } else if (!this.isLeft(node) && node.parent == this.getGrandParent(node).left) {
                rotateLeft(node.parent);
                this.fixLeftNode(node.left);
            } else if (this.isLeft(node) && node.parent == this.getGrandParent(node).right) {
                rotateRight(node.parent);
                this.fixRightNode(node.right);
            }
        }
    }

    private void fixLeftNode(Node node) {
        node.parent.setRed(false);
        this.getGrandParent(node).setRed(true);
        this.rotateRight(getGrandParent(node));
    }

    private void fixRightNode(Node node) {
        node.parent.setRed(false);
        this.getGrandParent(node).setRed(true);
        this.rotateLeft(this.getGrandParent(node));
    }

    private void rotateLeft(Node node) {
        Node right = node.right;
        right.setParent(node.parent);
        if (node != root) {
            if (this.isLeft(node)) {
                node.parent.setLeft(right);
            } else {
                node.parent.setRight(right);
            }
        } else {
            root = right;
        }
        node.setRight(right.left);
        if (right.left != null) {
            right.left.setParent(node);
        }
        node.setParent(right);
        right.setLeft(node);
    }

    private void rotateRight(Node node) {
        Node left = node.left;
        left.setParent(node.parent);
        if (node != root) {
            if (this.isLeft(node)) {
                node.parent.setLeft(left);
            } else {
                node.parent.setRight(left);
            }
        } else {
            root = left;
        }
        node.setLeft(left.right);
        if (left.right != null) {
            left.right.setParent(node);
        }
        node.setParent(left);
        left.setRight(node);
    }

    /**
     * 是否是父节点的左子节点
     *
     * @param node 待确认节点
     * @return 是否是左子节点
     */
    private Boolean isLeft(Node node) {
        return node == node.parent.left;
    }

    /**
     * 叔节点
     *
     * @param node
     * @return
     */
    private Node getUncle(Node node) {
        if (node.parent != root && node.parent == this.getGrandParent(node).left) {
            return this.getGrandParent(node).right;
        } else if (node.parent != root && node.parent == this.getGrandParent(node).right) {
            return getGrandParent(node).left;
        } else {
            return null;
        }
    }

    /**
     * 祖父节点
     *
     * @param node
     * @return
     */
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

        public void setRed(boolean red) {
            this.red = red;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }
    }
}
