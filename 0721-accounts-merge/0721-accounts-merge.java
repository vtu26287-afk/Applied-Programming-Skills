import java.util.*;

class Solution {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        // Graph for emails
        Map<String, Set<String>> graph = new HashMap<>();

        // Email -> Name
        Map<String, String> emailToName = new HashMap<>();

        // Build graph
        for (List<String> account : accounts) {

            String name = account.get(0);
            String firstEmail = account.get(1);

            graph.putIfAbsent(firstEmail, new HashSet<>());

            for (int i = 1; i < account.size(); i++) {

                String email = account.get(i);

                graph.putIfAbsent(email, new HashSet<>());
                emailToName.put(email, name);

                // Connect emails
                graph.get(firstEmail).add(email);
                graph.get(email).add(firstEmail);
            }
        }

        Set<String> visited = new HashSet<>();

        List<List<String>> result = new ArrayList<>();

        // DFS on each email
        for (String email : graph.keySet()) {

            if (!visited.contains(email)) {

                List<String> mergedEmails = new ArrayList<>();

                dfs(email, graph, visited, mergedEmails);

                Collections.sort(mergedEmails);

                // Add name at beginning
                mergedEmails.add(0, emailToName.get(email));

                result.add(mergedEmails);
            }
        }

        return result;
    }

    private void dfs(String email,
                     Map<String, Set<String>> graph,
                     Set<String> visited,
                     List<String> mergedEmails) {

        visited.add(email);

        mergedEmails.add(email);

        for (String neighbor : graph.get(email)) {

            if (!visited.contains(neighbor)) {
                dfs(neighbor, graph, visited, mergedEmails);
            }
        }
    }
}