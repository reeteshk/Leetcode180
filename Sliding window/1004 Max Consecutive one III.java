/*
 
1004. Max Consecutive Ones III
Medium
6.7K
73
Companies
Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.

 

Example 1:

Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
Example 2:

Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 

Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
0 <= k <= nums.length

 */

 /*
   TC- O(n)
   SC - O(1)
  */

class Solution {
    public int longestOnes(int[] nums, int K) {
        int start = 0, end = 0;
        int n = nums.length;
        int numZeroes = 0;
        int max = 0;
        
        while(start != n){
            if(nums[start] == 1){
                start++;
                max = Math.max(start - end, max);
            }else if(numZeroes < K){
                start++;
                numZeroes++;
                max = Math.max(start - end, max);
            }else{
                while(numZeroes >= K){
                    if(nums[end] == 0) numZeroes--;
                    end++;
                }
                
            }
        }
        
        return max;
    }
}