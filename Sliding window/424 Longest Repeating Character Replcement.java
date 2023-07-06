/*
 424. Longest Repeating Character Replacement
Medium
8.5K
364
Companies
You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.

 

Example 1:

Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
There may exists other ways to achive this answer too.
 

Constraints:

1 <= s.length <= 105
s consists of only uppercase English letters.
0 <= k <= s.length
 */


 /*
    SC- O(1)
    TC- O(n)
  */
  
class Solution {
    public int characterReplacement(String s, int k) {
        
        HashMap<Character,Integer>hm=new HashMap<>();

        int start=0;
        int end=0;
        int length=0;
        while(end<s.length())
        {
            hm.put(s.charAt(end),hm.getOrDefault(s.charAt(end),0)+1);
            end++;
            int mx=MaxFrequency(hm);
            if(end-mx<=k)
            {
                length=Math.max(length,start-end+1);
            }
            else
            {
                start++;
            }
        }
        return length;
    }
    public static int MaxFrequency(HashMap<Character,Integer>hm)
    {
        int max=0;
        for (Map.Entry<Character, Integer> entry : hm.entrySet()) {
            int key = entry.getKey();
            if(key>max)
            {
                max=key;
            }
        }
        return max;
    }
}


//METHOD 2

/*
 
class Solution {
    public int characterReplacement(String s, int k) {
        int n=s.length();
        int start=0;
        int end=0;
        int maxlength=0;
        int maxcount=0;
        int chararr[]=new int[26];

        while(end<n)
        {
            chararr[s.charAt(end)-'A']++;
            int count=chararr[s.charAt(end)-'A'];
            maxcount=Math.max(maxcount,count);
            while(end-start-maxcount+1>k)
            {
                chararr[s.charAt(start)-'A']--;
                start++;
            }
            
            maxlength=Math.max(maxlength,end-start+1);
            end++;

        }
        return maxlength;
    }
}

 */