/*
 56. Merge Intervals
Medium
19.6K
664
Companies
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

 

Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 

Constraints:

1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104

 */

class Solution {
    public int[][] merge(int[][] intervals) {
        

        Arrays.sort(intervals,(a,b) -> a[0]-b[0]);
      
       ArrayList<ArrayList<Integer>>al=new ArrayList<ArrayList<Integer>>();
       ArrayList<Integer>al1=new ArrayList<Integer>();
       al1.add(intervals[0][0]);
       al1.add(intervals[0][1]);
       al.add(al1);
       int k=0;
        for(int i=1;i<intervals.length;i++)
        {
            int start=al.get(k).get(0);
            int end=al.get(k).get(1);
            if(intervals[i][0]>=start && intervals[i][0]<=end)
            {
                al.remove(k);
                ArrayList<Integer>alq=new ArrayList<Integer>();
                alq.add(start);
                alq.add(Math.max(end,intervals[i][1]));
                al.add(alq);
            }
            else
            {
                ArrayList<Integer>al2=new ArrayList<Integer>();
                al2.add(intervals[i][0]);
                al2.add(intervals[i][1]);
                al.add(al2);
                k++;
            }
        }
        System.out.println(al);
        int ans[][]=new int[al.size()][2];
        for(int i=0;i<al.size();i++)
        {
            ans[i][0]=al.get(i).get(0);
            ans[i][1]=al.get(i).get(1);
        }
        return ans;
    }
}

