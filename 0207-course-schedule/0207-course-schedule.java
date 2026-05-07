import java.util.*;

class Solution {

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // Create adjacency list
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // Build graph
        for (int[] pre : prerequisites) {
            graph.get(pre[1]).add(pre[0]);
        }

        // 0 = unvisited
        // 1 = visiting
        // 2 = visited
        int[] visited = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {

            if (hasCycle(graph, visited, i)) {
                return false;
            }
        }

        return true;
    }

    private boolean hasCycle(List<List<Integer>> graph, int[] visited, int course) {

        // If currently visiting, cycle exists
        if (visited[course] == 1) {
            return true;
        }

        // Already checked
        if (visited[course] == 2) {
            return false;
        }

        // Mark as visiting
        visited[course] = 1;

        for (int neighbor : graph.get(course)) {

            if (hasCycle(graph, visited, neighbor)) {
                return true;
            }
        }

        // Mark as visited
        visited[course] = 2;

        return false;
    }
}