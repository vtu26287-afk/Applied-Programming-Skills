import java.util.*;

class Solution {

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        // Adjacency list
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // Indegree array
        int[] indegree = new int[numCourses];

        // Build graph
        for (int[] pre : prerequisites) {

            int course = pre[0];
            int prerequisite = pre[1];

            graph.get(prerequisite).add(course);
            indegree[course]++;
        }

        // Queue for courses with indegree 0
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {

            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int[] order = new int[numCourses];
        int index = 0;

        // Topological Sort (Kahn's Algorithm)
        while (!queue.isEmpty()) {

            int current = queue.poll();
            order[index++] = current;

            for (int neighbor : graph.get(current)) {

                indegree[neighbor]--;

                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // If not all courses processed, cycle exists
        if (index != numCourses) {
            return new int[0];
        }

        return order;
    }
}