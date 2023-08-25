/*
 Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.

There is only one repeated number in nums, return this repeated number.

You must solve the problem without modifying the array nums and uses only constant extra space.

 

Example 1:

Input: nums = [1,3,4,2,2]
Output: 2
Example 2:

Input: nums = [3,1,3,4,2]
Output: 3
 

Constraints:

1 <= n <= 105
nums.length == n + 1
1 <= nums[i] <= n
All the integers in nums appear only once except for precisely one integer which appears two or more times.
 

Follow up:

How can we prove that at least one duplicate number must exist in nums?
Can you solve the problem in linear runtime complexity?

 */

class Solution {
    public int findDuplicate(int[] nums) {
        
    //  return UsingHashMap(nums);
    return UsingMarkingVisited(nums);
    }
    public static int UsingHashMap(int nums[])
    {
           int ans=-1;
        HashMap<Integer,Integer>hm=new HashMap<>();
        for(int i=0;i<nums.length;i++)
        {
            hm.put(nums[i],hm.getOrDefault(nums[i],0)+1);
            if(hm.get(nums[i])==2)
            {
                ans=nums[i];
                break;
            }
        }
        return ans;
    }
    public static int UsingMarkingVisited(int nums[])
    {
        int len=nums.length;
        for(int num:nums)
        {
            int idx=Math.abs(num);
            if(nums[idx]<0)
            {
                return idx;
            }
            nums[idx]=-nums[idx];
        }
        return len;

    }
}