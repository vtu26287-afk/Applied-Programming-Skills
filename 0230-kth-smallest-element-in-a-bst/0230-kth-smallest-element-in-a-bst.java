/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

import java.util.*;

class Solution {

    int count = 0;
    int answer = 0;

    public int kthSmallest(TreeNode root, int k) {

        inorder(root, k);

        return answer;
    }

    private void inorder(TreeNode node, int k) {

        if (node == null) {
            return;
        }

        // Left subtree
        inorder(node.left, k);

        // Visit current node
        count++;

        if (count == k) {
            answer = node.val;
            return;
        }

        // Right subtree
        inorder(node.right, k);
    }
}