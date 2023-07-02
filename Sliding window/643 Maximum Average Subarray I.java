/*

643. Maximum Average Subarray I
Easy
2.6K
207
Companies
You are given an integer array nums consisting of n elements, and an integer k.

Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value. Any answer with a calculation error less than 10-5 will be accepted.

 

Example 1:

Input: nums = [1,12,-5,-6,50,3], k = 4
Output: 12.75000
Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
Example 2:

Input: nums = [5], k = 1
Output: 5.00000
 

Constraints:

n == nums.length
1 <= k <= n <= 105
-104 <= nums[i] <= 104

 */

/*
    Using Sliding window Technique
    Space Complexity - O(1)
    Time Complexity O(N)
 */

class Solution {
    public double findMaxAverage(int[] nums, int k) {
        
        double max=Double.MIN_VALUE;
        double sum=0;
        for(int i=0;i<k;i++)
        {
            sum+=nums[i];
        }
        max=sum;
        for(int i=k;i<nums.length;i++)
        {
            sum=sum-nums[i-k]+nums[i];
            double val=sum;
            max=Math.max(max,val);
        }
        return max/k;
    }
}