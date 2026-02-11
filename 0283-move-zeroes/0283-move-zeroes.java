class Solution {
    public void moveZeroes(int[] nums) {
        int index = 0;

        for (int x : nums)
            if (x != 0)
                nums[index++] = x;

        while (index < nums.length)
            nums[index++] = 0;
    }
}