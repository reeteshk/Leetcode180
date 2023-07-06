/*
239. Sliding Window Maximum
Hard
14.9K
489
Companies
You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the max sliding window.

 

Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation: 
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Example 2:

Input: nums = [1], k = 1
Output: [1]
 

Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
1 <= k <= nums.length
 */

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        
        //return UsingSlidingWindow(nums,k);
        //return UsingDeque(nums,k);
        return usinglist(nums,k);
       
    }

    public static int[] UsingDeque(int []nums,int k)
    {
        Deque<Integer> deq = new ArrayDeque<Integer>();
        ArrayList<Integer>ans=new ArrayList<>();

        for(int i=0;i<nums.length;i++)
        {
            if(!deq.isEmpty() && deq.getFirst()==i-k) deq.removeFirst();

            while(!deq.isEmpty() && nums[deq.getLast()]<nums[i])
            {
                deq.removeLast();
            }
            deq.addLast(i);
            if(i>=k-1) ans.add(nums[deq.getFirst()]);

        }
        int al[]=new int[ans.size()];
        for(int i=0;i<al.length;i++)
        {
            al[i]=ans.get(i);
        }
        return al;
    }
    public static int[] UsingSlidingWindow(int[] nums, int k)
    {
         ArrayList<Integer>al=new ArrayList<>();
        ArrayList<Integer>temp=new ArrayList<>();
        int maxinwin=-100000;

        for(int i=0;i<k;i++)
        {
            maxinwin=Math.max(maxinwin,nums[i]);
            temp.add(nums[i]);
        }
        al.add(maxinwin);
        int j=k;
        while(j<nums.length)
        {
            if(maxinwin==temp.get(0))
            {
                temp.remove(0);
                temp.add(nums[j]);
                maxinwin=getmin(temp);
                al.add(maxinwin);
            }
            else
            {
                temp.remove(0);
                temp.add(nums[j]);
                maxinwin=Math.max(maxinwin,nums[j]);
                al.add(maxinwin);
            }
            j++;
        }

        int ans[]=new int[al.size()];
        for(int i=0;i<ans.length;i++)
        {
            ans[i]=al.get(i);
        }
        return ans;
    }
    public static int[] usinglist(int nums[],int k)
    {
        int n=nums.length;
        int ans[]=new int[n-k+1];
        int t=0;
        List<Integer>deq=new ArrayList<Integer>();
        
        for(int i=0;i<nums.length;i++)
        {
            if(deq.size()!=0 && deq.get(0)==i-k) deq.remove(0);

            while(deq.size()!=0  && nums[deq.get(deq.size()-1)]<nums[i])
            {
                deq.remove(deq.size()-1);
            }
            deq.add(i);
            if(i>=k-1)
            {
                ans[t]=nums[deq.get(0)];
                t++;
            }
        }
        return ans;
    }
     public static int getmin(ArrayList<Integer>al)
        {
            int max=-100000;
            for(int i=0;i<al.size();i++)
            {
                max=Math.max(max,al.get(i));
            }
            return max;
        }
}