class Solution {
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int total = 0, leftSum = 0;

        for (int x : nums) total += x;

        for (int i = 0; i < n; i++) {
            int rightSum = total - leftSum - nums[i];
            ans[i] = nums[i] * i - leftSum + rightSum - nums[i] * (n - i - 1);
            leftSum += nums[i];
        }
        return ans;
    }
}