/* 
209. Minimum Size Subarray Sum
Medium
9.7K
277
Companies
Given an array of positive integers nums and a positive integer target, return the minimal length of a 
subarray
 whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.

 

Example 1:

Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.
Example 2:

Input: target = 4, nums = [1,4,4]
Output: 1
Example 3:

Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0
 

Constraints:

1 <= target <= 109
1 <= nums.length <= 105
1 <= nums[i] <= 104

*/


/*
  Space Complexity O(1)
  TC Complexity O(n)
 */
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        
        int n=nums.length;
        int sum=0;
        int left=0;
        int right=0;
        int shortest=Integer.MAX_VALUE;

        while(right<n)
        {
             // keep a running sum as the end pointer expands your window
            sum=sum+nums[right];

    // this while loop will be skipped until your window meets the condition that 
	// the running sum is equal or greater than the int 's' passed in. aka the window
	// is valid
                while(sum>=target)
                {
                // now you want to condense your window to find the minimum window as the 
                // problem wants
                // updates the min length
                    sum=sum-nums[left];
                    left++;
                shortest=Math.min(shortest,right-left+2);
            }
            right++;
        }
        return shortest==Integer.MAX_VALUE?0:shortest;
    }
}