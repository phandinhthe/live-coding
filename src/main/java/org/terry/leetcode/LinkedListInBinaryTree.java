package org.terry.leetcode;

/**
 * <a href=https://leetcode.com/problems/linked-list-in-binary-tree>Leetcode 1367: Linked List in Binary Tree</a>
 * <p></p>
 * Tuition:
 * - help(head, root) => find the all the head values ~ root values in sequence.
 * - isSubPath(head, root) => find the root of Head ~ the root of Root.
 * <p>
 * Complexity: O(n*m)
 * Space: O(1)
 */
public class LinkedListInBinaryTree {
    public static void main(String[] args) {

    }

    public boolean isSubPath(ListNode head, TreeNode root) {
        if (root==null) return false;
        if (help(head, root)) return true;
        return isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    public boolean help(ListNode head, TreeNode root) {
        if (head==null) return true;
        if (root==null || root.val!=head.val) return false;
        return help(head.next, root.left) || help(head.next, root.right);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
