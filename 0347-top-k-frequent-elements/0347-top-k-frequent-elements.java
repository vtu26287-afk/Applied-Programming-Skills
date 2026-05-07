import java.util.*;

class Solution {

    public int[] topKFrequent(int[] nums, int k) {

        // Frequency map
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int num : nums) {
            frequencyMap.put(num,
                frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Bucket sort
        List<Integer>[] buckets = new ArrayList[nums.length + 1];

        for (int key : frequencyMap.keySet()) {

            int frequency = frequencyMap.get(key);

            if (buckets[frequency] == null) {
                buckets[frequency] = new ArrayList<>();
            }

            buckets[frequency].add(key);
        }

        int[] result = new int[k];
        int index = 0;

        // Traverse buckets from high frequency to low
        for (int i = buckets.length - 1; i >= 0 && index < k; i--) {

            if (buckets[i] != null) {

                for (int num : buckets[i]) {

                    result[index++] = num;

                    if (index == k) {
                        break;
                    }
                }
            }
        }

        return result;
    }
}