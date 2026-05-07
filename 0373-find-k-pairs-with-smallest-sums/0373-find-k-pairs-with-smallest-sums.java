import java.util.*;

class Solution {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        List<List<Integer>> result = new ArrayList<>();

        // Min Heap -> stores {sum, i, j}
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> a[0] - b[0]
        );

        // Add first element from nums2 with each nums1 element
        for (int i = 0; i < Math.min(nums1.length, k); i++) {

            pq.offer(new int[]{
                nums1[i] + nums2[0], // sum
                i,                   // index in nums1
                0                    // index in nums2
            });
        }

        while (k > 0 && !pq.isEmpty()) {

            int[] current = pq.poll();

            int i = current[1];
            int j = current[2];

            result.add(Arrays.asList(nums1[i], nums2[j]));

            k--;

            // Move to next element in nums2
            if (j + 1 < nums2.length) {

                pq.offer(new int[]{
                    nums1[i] + nums2[j + 1],
                    i,
                    j + 1
                });
            }
        }

        return result;
    }
}