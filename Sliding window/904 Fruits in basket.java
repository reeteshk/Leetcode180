/* 
904. Fruit Into Baskets
Medium
4K
291
Companies
You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.

You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:

You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
Given the integer array fruits, return the maximum number of fruits you can pick.

 

Example 1:

Input: fruits = [1,2,1]
Output: 3
Explanation: We can pick from all 3 trees.
Example 2:

Input: fruits = [0,1,2,2]
Output: 3
Explanation: We can pick from trees [1,2,2].
If we had started at the first tree, we would only pick from trees [0,1].
Example 3:

Input: fruits = [1,2,3,2,2]
Output: 4
Explanation: We can pick from trees [2,3,2,2].
If we had started at the first tree, we would only pick from trees [1,2].
 

Constraints:

1 <= fruits.length <= 105
0 <= fruits[i] < fruits.length

*/


class Solution {
    public int totalFruit(int[] fruits) {
        //Using HashMap
        int maxLen=0;
        int i=0;
        int j=0;
        HashMap<Integer,Integer> h=new HashMap<>();
        while(j<fruits.length)
        {
            h.put(fruits[j],h.getOrDefault(fruits[j],0)+1);
            while(h.size()>2)
            {
                h.put(fruits[i],h.get(fruits[i])-1);
                if(h.get(fruits[i])==0)
                {
                    h.remove(fruits[i]);
                }
                 i++;
            }
            maxLen=Math.max(maxLen,j-i+1);
            j++;
        }
return maxLen;
    }
    
}