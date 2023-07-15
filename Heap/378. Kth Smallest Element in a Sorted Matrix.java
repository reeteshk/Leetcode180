/*
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


/*
Approach 1 (Using Sorting) //it is the first approach that usually comes into mind
Time complexity : N * log(N);  //N == n^2
Auxiliary space complexity: O(N)
 */


class Solution {
   public int kthSmallest(int[][] matrix, int k) {
       int n = matrix.length;
       int [] arr = new int[n*n];
       int idx = 0;
       for(int i = 0;i<n;i++){
           for(int j = 0;j<n;j++){
               arr[idx++] = matrix[i][j];
           }
       }
       
       Arrays.sort(arr);
       
       return arr[k - 1];
   }
}

/*
 Approach 2 (Using Priority Queue) //By reading that we have to return kth of something,this approach usually comes into mind
Time Complexity: N * log(K) //N== n^2
Auxiliary Space Complexity: O(K)
 */
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
       PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        int n = matrix.length;
        
        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                if(pq.size() < k){
                    pq.add(matrix[i][j]);
                }else{ //equal to k
                    if(matrix[i][j] < pq.peek()){ //if incoming element is less than peek
                        pq.poll();
                        pq.add(matrix[i][j]);
                    }
                }
            }
        }
        
        return pq.peek();
    }
}

/*
 Approach 3 (Best Approach) /* By noticing the Sorted array, Binary Search 
Comes into play but , the implementation is a little different in 2d matrix
case so if it is known beforehand then only it strikes

Time Complexity:O(N) //N== n^2
Auxiliary Space Complexity: O(1)
 
*/
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int rows = matrix.length, cols = matrix[0].length;
        
        int lo = matrix[0][0], hi = matrix[rows - 1][cols - 1] ;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0,  maxNum = lo;
           
            for (int r = 0, c = cols - 1; r < rows; r++) {
                while (c >= 0 && matrix[r][c] > mid) c--;   
                
                if (c >= 0) {
                    count += (c + 1); // count of nums <= mid in matrix
                    maxNum = Math.max(maxNum, matrix[r][c]); 
         // mid might be value not in  matrix, we need to record the actually max num;
                }else{ //it means c < 0
                    break;
                } 
            }
            
            // adjust search range
            if (count == k) return maxNum;
            else if (count < k) lo = mid + 1;
            else hi = mid - 1;
        }
        return lo;
    }

}