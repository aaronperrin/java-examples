package com.afpx.exercises;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestBinarySearchTreeTester {
    @Test
    public void testNullRoot() throws Exception {
        assertTrue(BinarySearchTreeTester.checkBST(null));
    }

    @Test
    public void testOneNode() throws Exception {
        BinarySearchTreeTester.Node tree = new BinarySearchTreeTester.Node(
                0
        );
        assertTrue(BinarySearchTreeTester.checkBST(tree));
    }

    @Test
    public void testValid() throws Exception {
        BinarySearchTreeTester.Node tree = new BinarySearchTreeTester.Node(
                new BinarySearchTreeTester.Node(
                        -1
                ),
                0
        );
        assertTrue(BinarySearchTreeTester.checkBST(tree));
    }

    @Test
    public void testValidGrandchildenLeftLeft() throws Exception {
        BinarySearchTreeTester.Node tree = new BinarySearchTreeTester.Node(
                new BinarySearchTreeTester.Node(
                        new BinarySearchTreeTester.Node(
                                -2
                        ),
                        -1
                ),
                0
        );
        assertTrue(BinarySearchTreeTester.checkBST(tree));
    }

    @Test
    public void testValidGrandchildenLeftRight() throws Exception {
        BinarySearchTreeTester.Node tree = new BinarySearchTreeTester.Node(
                new BinarySearchTreeTester.Node(
                        -2,
                        new BinarySearchTreeTester.Node(
                                -1
                        )
                ),
                0
        );
        assertTrue(BinarySearchTreeTester.checkBST(tree));
    }

    @Test
    public void testValidChildRight() throws Exception {
        BinarySearchTreeTester.Node tree = new BinarySearchTreeTester.Node(
                0,
                new BinarySearchTreeTester.Node(
                        1
                )
        );
        assertTrue(BinarySearchTreeTester.checkBST(tree));
    }

    @Test
    public void testValidThreeNodeBalanced() throws Exception {
        BinarySearchTreeTester.Node tree = new BinarySearchTreeTester.Node();
        tree.data = 0;
        tree.left = new BinarySearchTreeTester.Node();
        tree.left.data = -1;
        tree.right = new BinarySearchTreeTester.Node();
        tree.right.data = 1;
        assertTrue(BinarySearchTreeTester.checkBST(tree));
    }

    @Test
    public void testValidThreeNodeUnbalanced() throws Exception {
        BinarySearchTreeTester.Node tree = new BinarySearchTreeTester.Node();
        tree.data = 0;
        tree.left = new BinarySearchTreeTester.Node();
        tree.left.data = -2;
        tree.left.right = new BinarySearchTreeTester.Node();
        tree.left.right.data = -1;
        assertTrue(BinarySearchTreeTester.checkBST(tree));
    }

    @Test
    public void testNotValidTwoNodeLeft() throws Exception {
        BinarySearchTreeTester.Node tree = new BinarySearchTreeTester.Node();
        tree.data = 0;
        tree.left = new BinarySearchTreeTester.Node();
        tree.left.data = 1; // should be right
        assertFalse(BinarySearchTreeTester.checkBST(tree));
    }

    @Test
    public void testNotValidTwoNodeRight() throws Exception {
        BinarySearchTreeTester.Node tree = new BinarySearchTreeTester.Node();
        tree.data = 0;
        tree.right = new BinarySearchTreeTester.Node();
        tree.right.data = -1; // should be left
        assertFalse(BinarySearchTreeTester.checkBST(tree));
    }

    @Test
    public void testNotValidThreeNodeBalanced() throws Exception {
        BinarySearchTreeTester.Node tree = new BinarySearchTreeTester.Node();
        tree.data = 0;
        tree.left = new BinarySearchTreeTester.Node();
        tree.left.data = 2; // should be on right
        tree.right = new BinarySearchTreeTester.Node();
        tree.right.data = 1;
        assertFalse(BinarySearchTreeTester.checkBST(tree));
    }

    @Test
    public void testNotValidThreeNodeUnbalanced() throws Exception {
        BinarySearchTreeTester.Node tree = new BinarySearchTreeTester.Node();
        tree.data = 0;
        tree.left = new BinarySearchTreeTester.Node();
        tree.left.data = -2;
        tree.left.right = new BinarySearchTreeTester.Node();
        tree.left.right.data = -3; // should be on left
        assertFalse(BinarySearchTreeTester.checkBST(tree));
    }
}