/*
 
3. Longest Substring Without Repeating Characters
Medium
35K
1.6K
Companies
Given a string s, find the length of the longest 
substring
 without repeating characters.

 

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 

Constraints:

0 <= s.length <= 5 * 104
s consists of English letters, digits, symbols and spaces.
 */

class Solution {
    public int lengthOfLongestSubstring(String s) {

         //Using HashSet and sliding window
       // return slidingwindow(s);

        //Using HashMap
        return usingHashMap(s);
      
    }
    public static int slidingwindow(String s)
    {
        //Using HashSet and sliding window
        /*
        Time complexity: O(n)
        Space complexity: O(k), where k is the number of distinctive characters prsent in the hashset.
        */
        HashSet<Character>hs=new HashSet<>();

        int l=0;
        int r=0;
        int max=0;

        while(r<s.length())
        {
            if(hs.contains(s.charAt(r))==false)
            {
                hs.add(s.charAt(r));
                r++;
            }
            else
            {
                hs.remove(s.charAt(l));
                l++;
            }
            max=Math.max(max,r-l);
        }
        return max;
    }
    public static int usingHashMap(String s)
    {
         Map<Character, Integer> seen = new HashMap<>();
        int start = 0;
        int maxLen = 0;

        for(int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);
            if(seen.containsKey(currChar)) {
                start = Math.max(seen.get(currChar) + 1, start);
            }
            seen.put(currChar, i);
            maxLen = Math.max(i - start + 1, maxLen);
        } 
        return maxLen;  
    }
}