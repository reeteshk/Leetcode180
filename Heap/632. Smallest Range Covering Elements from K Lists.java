/*
 632. Smallest Range Covering Elements from K Lists
Hard
3.1K
52
Companies
You have k lists of sorted integers in non-decreasing order. Find the smallest range that includes at least one number from each of the k lists.

We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.

 

Example 1:

Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
Output: [20,24]
Explanation: 
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].
Example 2:

Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
Output: [1,1]
 

Constraints:

nums.length == k
1 <= k <= 3500
1 <= nums[i].length <= 50
-105 <= nums[i][j] <= 105
nums[i] is sorted in non-decreasing order.

 */

class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        
        PriorityQueue<Temp>pq=new PriorityQueue<>((x,y)->(x.value-y.value));
        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;

        int range[]=new int[2];
        int r=Integer.MAX_VALUE;
        for(int i=0;i<nums.size();i++)
        {
            int val=nums.get(i).get(0);
            max=Math.max(max,val);
            min=Math.min(min,val);
            Temp demo=new Temp(val,i,0);
            pq.add(demo);
        }
    
        while(!pq.isEmpty())
        {
            Temp t=pq.remove();
            if(r>max-t.value)
            {
                r=max-t.value;
                min=t.value;
                range[0]=t.value;
                range[1]=max;
            }
            if(t.index+1<nums.get(t.row).size())
            {
                Temp tt=new Temp(nums.get(t.row).get(t.index+1),t.row,t.index+1);
                if(tt.value>max)
                {
                    max=tt.value;
                }
                pq.add(tt);
            }
            else
            {
                break;
            }

        }
        return range;
        
    }
    class Temp{
        int value;
        int row;
        int index;
        public Temp(int value,int row,int index)
        {
            this.value=value;
            this.row=row;
            this.index=index;
        }
    }
}