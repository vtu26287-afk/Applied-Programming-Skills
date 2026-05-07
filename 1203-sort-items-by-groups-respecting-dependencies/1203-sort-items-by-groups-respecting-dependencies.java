import java.util.*;

class Solution {

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {

        // Assign unique groups to ungrouped items
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = m++;
            }
        }

        // Item graph
        List<List<Integer>> itemGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            itemGraph.add(new ArrayList<>());
        }

        int[] itemIndegree = new int[n];

        // Group graph
        List<List<Integer>> groupGraph = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            groupGraph.add(new ArrayList<>());
        }

        int[] groupIndegree = new int[m];

        // Build graphs
        for (int i = 0; i < n; i++) {

            for (int prev : beforeItems.get(i)) {

                // Item dependency
                itemGraph.get(prev).add(i);
                itemIndegree[i]++;

                // Group dependency
                if (group[i] != group[prev]) {
                    groupGraph.get(group[prev]).add(group[i]);
                    groupIndegree[group[i]]++;
                }
            }
        }

        // Topological sort for items
        List<Integer> itemOrder = topoSort(itemGraph, itemIndegree, n);

        // Topological sort for groups
        List<Integer> groupOrder = topoSort(groupGraph, groupIndegree, m);

        // If cycle exists
        if (itemOrder.isEmpty() || groupOrder.isEmpty()) {
            return new int[0];
        }

        // Group items together
        Map<Integer, List<Integer>> groupedItems = new HashMap<>();

        for (int item : itemOrder) {
            groupedItems
                .computeIfAbsent(group[item], k -> new ArrayList<>())
                .add(item);
        }

        // Build final result
        List<Integer> result = new ArrayList<>();

        for (int grp : groupOrder) {

            List<Integer> items = groupedItems.getOrDefault(grp, new ArrayList<>());

            result.addAll(items);
        }

        // Convert list to array
        int[] ans = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }

        return ans;
    }

    private List<Integer> topoSort(List<List<Integer>> graph,
                                   int[] indegree,
                                   int size) {

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> order = new ArrayList<>();

        while (!queue.isEmpty()) {

            int node = queue.poll();
            order.add(node);

            for (int neighbor : graph.get(node)) {

                indegree[neighbor]--;

                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Cycle check
        if (order.size() != size) {
            return new ArrayList<>();
        }

        return order;
    }
}