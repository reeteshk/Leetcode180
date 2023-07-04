/*
 
828. Count Unique Characters of All Substrings of a Given String
Hard
1.9K
238
Companies
Let's define a function countUniqueChars(s) that returns the number of unique characters on s.

For example, calling countUniqueChars(s) if s = "LEETCODE" then "L", "T", "C", "O", "D" are the unique characters since they appear only once in s, therefore countUniqueChars(s) = 5.
Given a string s, return the sum of countUniqueChars(t) where t is a substring of s. The test cases are generated such that the answer fits in a 32-bit integer.

Notice that some substrings can be repeated so in this case you have to count the repeated ones too.

 

Example 1:

Input: s = "ABC"
Output: 10
Explanation: All possible substrings are: "A","B","C","AB","BC" and "ABC".
Every substring is composed with only unique letters.
Sum of lengths of all substring is 1 + 1 + 1 + 2 + 2 + 3 = 10
Example 2:

Input: s = "ABA"
Output: 8
Explanation: The same as example 1, except countUniqueChars("ABA") = 1.
Example 3:

Input: s = "LEETCODE"
Output: 92
 

Constraints:

1 <= s.length <= 105
s consists of uppercase English letters only.

 */

class Solution {
    public int uniqueLetterString(String s) {
        
        int n=s.length();
        ArrayList<ArrayList<Integer>>al=new ArrayList<>();

        for(int i=0;i<26;++i)
        {
            al.add(new ArrayList<Integer>());
            al.get(i).add(-1);
        }

        for(int i=0;i<s.length();++i)
        {
            char ch=s.charAt(i);
            al.get(ch-'A').add(i);
        }

        for(int i=0;i<26;++i)
        {
            al.get(i).add(s.length());
        }

        int ans=0;
        int count=0;
        for(int i=0;i<26;++i)
        {
            for(int j=1;j<al.get(i).size()-1;++j)
            {
                count+=(al.get(i).get(j)-al.get(i).get(j-1))*(al.get(i).get(j+1)-al.get(i).get(j));
            }
        }
        return count;
    }
}