/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root,
                                         TreeNode p,
                                         TreeNode q) {

        while (root != null) {

            // Both nodes are smaller
            if (p.val < root.val && q.val < root.val) {

                root = root.left;
            }

            // Both nodes are greater
            else if (p.val > root.val && q.val > root.val) {

                root = root.right;
            }

            // Split point found
            else {
                return root;
            }
        }

        return null;
    }
}