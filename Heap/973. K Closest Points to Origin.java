/*
 973. K Closest Points to Origin
Medium
7.7K
270
Companies
Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).

The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).

You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).

 

Example 1:

Input: points = [[1,3],[-2,2]], k = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].

Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], k = 2
Output: [[3,3],[-2,4]]
Explanation: The answer [[-2,4],[3,3]] would also be accepted.
 

Constraints:

1 <= k <= points.length <= 104
-104 < xi, yi < 104

 */
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        if(K == points.length) return points;
        
        //create a PQ witha comparator that puts the greates Euclid distance at the top so that we can remove it when the PQ size exceeds k.
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> (b[0]*b[0] + b[1]*b[1]) - (a[0]*a[0] + a[1]*a[1]));
            
       
        
        for(int[] point: points) {
            pq.add(point);
            if(pq.size() > K) pq.poll();
        }
        return pq.toArray(new int[0][0]);
    }
}

//Method 2
/*
 class Solution {
    class temp{
        int x;
        int y;
        double dis;
        public temp(int x,int y,double dis)
        {
            this.x=x;
            this.y=y;
            this.dis=dis;
        }
    }
    public int[][] kClosest(int[][] points, int k) {
        
        PriorityQueue<temp> pq = new PriorityQueue<temp>((x, y) -> Double.compare(y.dis, x.dis));
        for(int i=0;i<points.length;i++)
        {
            double val=getDistance(points[i][0],points[i][1]);
            temp t=new temp(points[i][0],points[i][1],val);
            pq.add(t);
            if(pq.size()>k)
            {
                pq.remove();
            }
        }
        int arr[][]=new int[k][2];
        int o=0;
        while(!pq.isEmpty())
        {
            temp t=pq.remove();
            arr[o][0]=t.x;
            arr[o][1]=t.y;
            o++;
        }
        return arr;
    }

    public static double getDistance(int x,int y)
    {
        double val=Math.sqrt(x*x+y*y);
        return val;
    }

}
 */