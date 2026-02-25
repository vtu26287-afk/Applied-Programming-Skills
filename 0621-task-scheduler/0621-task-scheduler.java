import java.util.*;

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char t : tasks) {
            freq[t - 'A']++;
        }
        
        Arrays.sort(freq);
        int maxFreq = freq[25];
        int maxCount = 1;
        
        // Count how many tasks have the same max frequency
        for (int i = 24; i >= 0; i--) {
            if (freq[i] == maxFreq) maxCount++;
            else break;
        }
        
        return Math.max(tasks.length, (maxFreq - 1) * (n + 1) + maxCount);
    }
}