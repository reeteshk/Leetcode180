/*
 1456. Maximum Number of Vowels in a Substring of Given Length
Medium
2.8K
96
Companies
Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.

Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.

 

Example 1:

Input: s = "abciiidef", k = 3
Output: 3
Explanation: The substring "iii" contains 3 vowel letters.
Example 2:

Input: s = "aeiou", k = 2
Output: 2
Explanation: Any substring of length 2 contains 2 vowels.
Example 3:

Input: s = "leetcode", k = 3
Output: 2
Explanation: "lee", "eet" and "ode" contain 2 vowels.
 

Constraints:

1 <= s.length <= 105
s consists of lowercase English letters.
1 <= k <= s.length
 */

class Solution {
    public int maxVowels(String s, int k) {
        
        int i=0;
        int vc=0;
        int maxvc=0;
        int length=0;
        String ans="";
        while(i!=k)
        {
            ans=ans+s.charAt(i);
            if(isVowel(s.charAt(i)))
            {
                vc++;
                maxvc=Math.max(vc,maxvc);
            }
            length++;
            i++;
        }

        for(int j=i;j<s.length();j++)
        {
            if(isVowel(ans.charAt(0)))
            {
                vc--;
            }
            ans=ans.substring(1);
            ans=ans+s.charAt(j);
            if(isVowel(s.charAt(j)))
            {
               vc++;
               maxvc=Math.max(vc,maxvc);
            }
            else
            {
                continue;
            }
        }
        return maxvc;

    }
    public static boolean isVowel(Character ch)
    {
        if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u')
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

/*
 Method 2

 class Solution {
    public int maxVowels(String s, int k) {
        int vc=0;
        int maxvc=0;

        for(int i=0;i<k;i++)
        {
            if(isVowel(s.charAt(i)))
            {
                vc++;
            }
        }
        maxvc=vc;
        for(int i=k;i<s.length();i++)
        {
            if(isVowel(s.charAt(i-k)))
            {
                vc--;
            }
            if(isVowel(s.charAt(i)))
            {
                vc++;
                maxvc=Math.max(vc,maxvc);
            }
            
        }
        return maxvc;
    }
    public boolean isVowel(Character ch)
    {
        if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u')
        {
            return true;
        }
        return false;
    }
}
 */