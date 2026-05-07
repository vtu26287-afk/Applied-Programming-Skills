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

    class NodeInfo {

        TreeNode node;
        int row;
        int col;

        NodeInfo(TreeNode node, int row, int col) {
            this.node = node;
            this.row = row;
            this.col = col;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {

        // col -> list of {row, value}
        TreeMap<Integer, List<int[]>> map = new TreeMap<>();

        Queue<NodeInfo> queue = new LinkedList<>();

        queue.offer(new NodeInfo(root, 0, 0));

        // BFS traversal
        while (!queue.isEmpty()) {

            NodeInfo current = queue.poll();

            TreeNode node = current.node;
            int row = current.row;
            int col = current.col;

            map.putIfAbsent(col, new ArrayList<>());

            map.get(col).add(new int[]{row, node.val});

            // Left child
            if (node.left != null) {
                queue.offer(new NodeInfo(node.left, row + 1, col - 1));
            }

            // Right child
            if (node.right != null) {
                queue.offer(new NodeInfo(node.right, row + 1, col + 1));
            }
        }

        List<List<Integer>> result = new ArrayList<>();

        // Process columns in sorted order
        for (List<int[]> list : map.values()) {

            // Sort by row first, then value
            Collections.sort(list, (a, b) -> {

                if (a[0] == b[0]) {
                    return a[1] - b[1];
                }

                return a[0] - b[0];
            });

            List<Integer> column = new ArrayList<>();

            for (int[] pair : list) {
                column.add(pair[1]);
            }

            result.add(column);
        }

        return result;
    }
}