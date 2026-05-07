import java.util.*;

class Solution {

    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {

            // Convert string to char array
            char[] chars = str.toCharArray();

            // Sort characters
            Arrays.sort(chars);

            // Create key
            String key = new String(chars);

            // Add to map
            map.putIfAbsent(key, new ArrayList<>());

            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }
}