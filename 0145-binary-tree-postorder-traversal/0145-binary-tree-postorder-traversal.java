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

    public List<Integer> postorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<>();

        postorder(root, result);

        return result;
    }

    private void postorder(TreeNode node, List<Integer> result) {

        if (node == null) {
            return;
        }

        // Left
        postorder(node.left, result);

        // Right
        postorder(node.right, result);

        // Root
        result.add(node.val);
    }
}