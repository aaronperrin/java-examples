package com.afpx.exercises;

class BinarySearchTreeTester {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(Node left, int data, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        Node(int data) {
            this.data = data;
        }

        Node(Node left, int data) {
            this.data = data;
            this.left = left;
        }

        Node(int data, Node right) {
            this.data = data;
            this.right = right;
        }

        Node() {

        }
    }

    private static boolean test(int min, Node node, int max) {
        return node == null || node.data <= max && node.data >= min && test(min, node.left, node.data - 1) && test(node.data + 1, node.right, max);

    }

    static boolean checkBST(Node root) {
        return test(Integer.MIN_VALUE, root, Integer.MAX_VALUE);
    }
}
