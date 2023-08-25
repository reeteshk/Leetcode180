/*
 
378. Kth Smallest Element in a Sorted Matrix
Medium
9.3K
320
Companies
Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

You must find a solution with a memory complexity better than O(n2).

 

Example 1:

Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
Output: 13
Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
Example 2:

Input: matrix = [[-5]], k = 1
Output: -5
 

Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 300
-109 <= matrix[i][j] <= 109
All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
1 <= k <= n2

 */

class Solution {
    public int kthSmallest(int[][] matrix, int k) {

     PriorityQueue<Integer>pq=new PriorityQueue<>((a,b)->(b-a));
     for(int i=0;i<matrix.length;i++)
     {
         for(int j=0;j<matrix[i].length;j++)
         {
             pq.add(matrix[i][j]);
             if(pq.size()>k)
             {
                 pq.remove();
             }
         }
     }   
     return pq.poll();
    }
}