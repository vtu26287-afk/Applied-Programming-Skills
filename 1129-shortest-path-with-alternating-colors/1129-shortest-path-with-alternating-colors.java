import java.util.*;

class Solution {

    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {

        // Graph: node -> list of {neighbor, color}
        // 0 = red, 1 = blue
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Add red edges
        for (int[] edge : redEdges) {
            graph[edge[0]].add(new int[]{edge[1], 0});
        }

        // Add blue edges
        for (int[] edge : blueEdges) {
            graph[edge[0]].add(new int[]{edge[1], 1});
        }

        int[] answer = new int[n];
        Arrays.fill(answer, -1);

        // visited[node][color]
        boolean[][] visited = new boolean[n][2];

        Queue<int[]> queue = new LinkedList<>();

        // {node, previous color}
        // Start with both colors
        queue.offer(new int[]{0, 0});
        queue.offer(new int[]{0, 1});

        visited[0][0] = true;
        visited[0][1] = true;

        int distance = 0;

        // BFS
        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                int[] current = queue.poll();

                int node = current[0];
                int prevColor = current[1];

                // Set shortest distance
                if (answer[node] == -1) {
                    answer[node] = distance;
                }

                for (int[] neighbor : graph[node]) {

                    int nextNode = neighbor[0];
                    int edgeColor = neighbor[1];

                    // Alternate colors
                    if (edgeColor != prevColor &&
                        !visited[nextNode][edgeColor]) {

                        visited[nextNode][edgeColor] = true;

                        queue.offer(new int[]{nextNode, edgeColor});
                    }
                }
            }

            distance++;
        }

        return answer;
    }
}