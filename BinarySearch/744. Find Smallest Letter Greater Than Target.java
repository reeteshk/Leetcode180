/*

 744. Find Smallest Letter Greater Than Target
Easy
3.9K
2.1K
Companies
You are given an array of characters letters that is sorted in non-decreasing order, and a character target. There are at least two different characters in letters.

Return the smallest character in letters that is lexicographically greater than target. If such a character does not exist, return the first character in letters.

 

Example 1:

Input: letters = ["c","f","j"], target = "a"
Output: "c"
Explanation: The smallest character that is lexicographically greater than 'a' in letters is 'c'.
Example 2:

Input: letters = ["c","f","j"], target = "c"
Output: "f"
Explanation: The smallest character that is lexicographically greater than 'c' in letters is 'f'.
Example 3:

Input: letters = ["x","x","y","y"], target = "z"
Output: "x"
Explanation: There are no characters in letters that is lexicographically greater than 'z' so we return letters[0].
 

Constraints:

2 <= letters.length <= 104
letters[i] is a lowercase English letter.
letters is sorted in non-decreasing order.
letters contains at least two different characters.
target is a lowercase English letter.
Accepted
400.7K
Submissions
788.1K
Acceptance Rate
50.8%

 */

class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
       // return UsingLinearSearch(letters,target);
      // return UsingBinarySearch(letters,target);
      return nextGreatestLetter2(letters,target);
    }
    public static char UsingLinearSearch(char [] letters,char target)
    {
             
       char ans=letters[0];
     for(int i=0;i<letters.length;i++)
     {
         if((int)(letters[i])>(int)(target))
         {
             ans=letters[i];
             break;
         }
     }
     return ans;
    }
    public static char UsingBinarySearch(char [] letters,char target)
    {
        int n=letters.length;
        int low=0;
        int high=n-1;

        if(target>=letters[n-1]) target=letters[0];
        else target++;

        while(low<high)
        {
            int mid=low+(high-low)/2;
            if(letters[mid]==target) return letters[mid];
            if(letters[mid]<target) low=mid+1;
            else high=mid;
        }
        return letters[high];
    }

    public char nextGreatestLetter2(char[] a, char x) {
        int n = a.length;

        //hi starts at 'n' rather than the usual 'n - 1'. 
        //It is because the terminal condition is 'lo < hi' and if hi starts from 'n - 1', 
        //we can never consider value at index 'n - 1'
        int lo = 0, hi = n;

        //Terminal condition is 'lo < hi', to avoid infinite loop when target is smaller than the first element
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] > x)     hi = mid;
            else    lo = mid + 1;                 //a[mid] <= x
        }
 
        //Because lo can end up pointing to index 'n', in which case we return the first element
        return a[lo % n];
    }
}