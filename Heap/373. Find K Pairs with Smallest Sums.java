/*
 373. Find K Pairs with Smallest Sums
Medium
5.6K
352
Companies
You are given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer k.

Define a pair (u, v) which consists of one element from the first array and one element from the second array.

Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.

 

Example 1:

Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]]
Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:

Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [[1,1],[1,1]]
Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
Example 3:

Input: nums1 = [1,2], nums2 = [3], k = 3
Output: [[1,3],[2,3]]
Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
 

Constraints:

1 <= nums1.length, nums2.length <= 105
-109 <= nums1[i], nums2[i] <= 109
nums1 and nums2 both are sorted in non-decreasing order.
1 <= k <= 104
Accepted
250.2K
Submissions
624.7K
Acceptance Rate
40.1%
Seen this question in a real interview before?
1/4

 */







class Solution {


 // Approach 1: Brute Force - TLE
// Time complexity: O(n^2)
// Space complexity: O(n^2)

public List<List<Integer>> kSmallestPairsBrute(int[] nums1, int[] nums2, int k) {
	Queue<Integer[]> queue = new PriorityQueue<>((a, b) -> (a[0] + a[1]) - (b[0] + b[1]));

	for (int i = 0; i < nums1.length; i++) {
		for (int j = 0; j < nums2.length; j++) {
			queue.add(new Integer[] {nums1[i], nums2[j]});
		}
	}

	List<List<Integer>> answer = new ArrayList<>();

	for (int i = 0; i < k; i++) {
		if (queue.size() > 0) {
			Integer[] arr = queue.remove();
			answer.add(Arrays.asList(arr));
		}
	}

	return answer;
    }



    //approach 2 Optimized one 
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        
		//In the minHeap array, 0th element refers to the curr element in nums1 and 1st element refers to curr element in nums2 
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> (a[0] + a[1]) - (b[0] + b[1]));
        
		// The 2nd element in the minHeap is the index of nums2, the corresponding element of nums2 is in index1 of minHeap
        for(int i=0; i < nums1.length && i < k; i++)
            minHeap.add(new int[]{nums1[i], nums2[0], 0});
        
        List<List<Integer>> result = new ArrayList<>();
        
        for(int i=0; i < k && !minHeap.isEmpty(); i++){
            int[] curr = minHeap.poll();
            result.add(List.of(curr[0], curr[1]));
            int nums2Idx = curr[2];
            if(nums2Idx < nums2.length - 1)
                minHeap.add(new int[]{curr[0], nums2[nums2Idx + 1], nums2Idx + 1});
        }
        return result;
    }

  


    //Approach 3 easy to understand optimized solution
    class Data {
        int a;
        int b;
        int sum;

        public Data(int a, int b, int sum) {
            this.a = a;
            this.b = b;
            this.sum = sum;
        }
    }
    public List<List<Integer>> kSmallestPairsOpt(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Data> maxHeap = new PriorityQueue<>((a, b) -> b.sum - a.sum);
        for(int i=0; i< nums1.length; i++) {
            for(int j=0; j< nums2.length; j++) {
                Data data = new Data(nums1[i], nums2[j], nums1[i]+nums2[j]);
                if(maxHeap.size() < k) {
                    maxHeap.offer(data);
                }
                else if(maxHeap.peek().sum > data.sum) {
                    maxHeap.poll();
                    maxHeap.offer(data);
                }
                else {
                    break; // This must be noted that the once the sum exceeds the heap top we dont want to traverse completly till the j pointer till the end.. 
                }
            }
        }
        List<List<Integer>> ans = new ArrayList<>();

        while(!maxHeap.isEmpty()) {
            Data polledData = maxHeap.poll();
            List<Integer> list = new ArrayList<>();
            list.add(polledData.a);
            list.add(polledData.b);
            ans.add(new ArrayList<>(list));
        } 
        return ans;
    }

}