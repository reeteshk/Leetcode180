/*
 
57. Insert Interval
Medium
8.6K
609
Companies
You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.

Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

Return intervals after the insertion.

 

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 

Constraints:

0 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 105
intervals is sorted by starti in ascending order.
newInterval.length == 2
0 <= start <= end <= 105

 */

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        
        int i=0;
        List<int[]>al=new ArrayList<>();
        boolean out=false;
        while(i<intervals.length)
        {
            if(intervals[i][1]<newInterval[0])
            {
                al.add(intervals[i]);
            }
            else if(intervals[i][0]>newInterval[1])
            {
                out=true;
                break;
            }
            else
            {
                newInterval[0]=Math.min(intervals[i][0],newInterval[0]);
                newInterval[1]=Math.max(intervals[i][1],newInterval[1]);
            }
            i++;
        }

       
            al.add(newInterval);
            while(i!=intervals.length)
            {
                al.add(intervals[i]);
                i++;
            }

        int ans[][]=new int[al.size()][2];

        for(int j=0;j<al.size();j++)
        {
            int arr[]=al.get(j);
            ans[j][0]=arr[0];
            ans[j][1]=arr[1];
        }
        return ans;

    }

}