/* 
1493. Longest Subarray of 1's After Deleting One Element
Medium
1.7K
32
Companies
Given a binary array nums, you should delete one element from it.

Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.

 

Example 1:

Input: nums = [1,1,0,1]
Output: 3
Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
Example 2:

Input: nums = [0,1,1,1,0,1,1,0,1]
Output: 5
Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
Example 3:

Input: nums = [1,1,1]
Output: 2
Explanation: You must delete one element.
 

Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.

*/


class Solution {
    public int longestSubarray(int[] nums) {
        
        int start=0;
        int end=0;
        int len=0;
        int ans=0;
        int cnt=0;

        while(end<nums.length)
        {
            if(nums[end]==0)
            {
                cnt++;
            }
            while(cnt>1)
            {
                if(nums[start]==0)
                {
                    cnt--;
                }
                start++;
            }
            end++;
            ans=Math.max(ans,end-start-1);
        }
        return ans;
    }
}