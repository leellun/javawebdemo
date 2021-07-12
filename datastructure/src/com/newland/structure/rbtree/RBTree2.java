package com.newland.structure.rbtree;

public class RBTree2<T extends Comparable> {
    private Node root;

    public void add(T value) {
        if (root == null) {
            root = new Node(value);
            return;
        }
        Node parent = root;
        Node newNode;
        while (true) {
            if (value.compareTo(parent.value) >= 0) {
                newNode = new Node(value);
                newNode.parent = parent;
                parent.right = newNode;
                break;
            } else {
                newNode = new Node(value);
                newNode.parent = parent;
                parent.left = newNode;
                break;
            }
        }
        fixNode(newNode);
    }

    private void fixNode(Node<T> node) {
        if (node == root) {
            node.red = false;
            return;
        }
        if (!node.parent.red) {
            return;
        }
        Node<T> uncle = node.getUncle();
        if (uncle != null && uncle.red) {
            node.red = true;
            node.parent.red = false;
            node.getUncle().red = false;
            this.fixNode(node);
        } else {
            if (node.isLeft() && node.parent.isLeft()) {
                fixLeftNode(node);
            } else if (node.isRight() && node.parent.isRight()) {
                fixRightNode(node);
            } else if (node.isRight() && node.parent.isLeft()) {
                rotateLeft(node.parent);
                fixLeftNode(node.left);
            } else if (node.isLeft() && node.parent.isRight()) {
                rotateRight(node.parent);
                fixRightNode(node.right);
            }
        }
    }

    private void fixLeftNode(Node node) {
        node.parent.red = false;
        Node grand = node.getGrandParent();
        grand.red = true;
        rotateRight(grand);
    }

    private void fixRightNode(Node node) {
        node.parent.red = false;
        Node grand = node.getGrandParent();
        grand.red = true;
        rotateLeft(grand);
    }

    private void rotateLeft(Node node) {
        Node right = node.right;
        Node parent = node.parent;
        if (node != root) {
            if (node.isLeft()) {
                node.left = right;
            } else {
                parent.right = right;
            }
        } else {
            root = right;
        }
        node.parent = right;
        node.right = right.left;
        if (right.left != null) {
            right.left.parent = node;
        }
        right.left = node;
    }

    private void rotateRight(Node node) {
        Node left = node.left;
        Node parent = node.parent;
        if (parent != root) {
            if (node.isLeft()) {
                node.parent.left = left;
            } else {
                node.parent.right = left;
            }
        } else {
            root = left;
        }
        node.parent = left;
        left.parent = parent;
        left.right = node;
        node.left = left.right;
        if (left.right != null) {
            left.right.parent = node;
        }
    }

    private Node getNode(T val) {
        Node tmp = root;
        while (tmp != null) {
            if (tmp.value == val) {
                return tmp;
            } else if (tmp.value.compareTo(val) < 0) {
                tmp = tmp.left;
            } else {
                tmp = tmp.right;
            }
        }
        return null;
    }

    private Node getLargeReplaceNode(Node node) {
        if (node.left == null) return null;
        Node temp = node.left;
        while (temp.right != null) {
            temp = temp.right;
        }
        return temp;
    }

    public void remove(T value) {
        Node node = getNode(value);
        if (node == null) return;

    }

    private void fixRemoveNode(Node node) {
        if (node.red) {
            if (node.left == null && node.right == null) {
                removeNode(node);
            } else {
                Node large = getLargeReplaceNode(node);
                node.value = large.value;
                fixRemoveNode(large);
            }
        } else {
            if (node.left == null && node.right != null) {
                Node parent = node.parent;
                Node right = node.right;
                if (node.isLeft()) {
                    parent.left = node.left;
                } else {
                    parent.right = node.right;
                }
                right.parent = node.parent;
                right.red = false;
                removeNode(node);
            } else if (node.left != null && node.right == null) {
                Node parent = node.parent;
                Node left = node.left;
                if (node.isLeft()) {
                    parent.left = node.left;
                } else {
                    parent.right = node.right;
                }
                left.parent = node.parent;
                left.red = false;
                removeNode(node);
            } else if (node.left != null && node.right != null) {
                Node large = getLargeReplaceNode(node);
                node.value = large.value;
                fixRemoveNode(large);
            } else {

            }
        }
    }
    private void fixNoSon(Node node,Boolean del){
        Node siblingNode=node.getSiblingNode();
        Node parent=node.parent;
        if(siblingNode.red){
            parent.red=true;
            siblingNode.red=false;

        }else{

        }
    }

    private void removeNode(Node node) {
        if (node.isLeft()) {
            node.parent.left = null;
        } else {
            node.parent.right = null;
        }
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

        public Node<T> getUncle() {
            //父节点是根节点
            if (parent.parent == null) {
                return null;
            } else if (parent.parent.left == this) {
                return parent.parent.right;
            } else {
                return parent.parent.right;
            }
        }

        public boolean isLeft() {
            return parent.left == this;
        }

        public boolean isRight() {
            return parent.right == this;
        }

        public Node<T> getGrandParent() {
            return parent.parent;
        }
        public Node<T>  getSiblingNode() {
            if (this.isLeft()) {
                return parent.right;
            } else {
                return parent.left;
            }
        }
    }
}
