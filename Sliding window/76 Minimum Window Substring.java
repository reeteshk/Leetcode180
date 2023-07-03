/* 
76. Minimum Window Substring
Hard
15.4K
643
Companies
Given two strings s and t of lengths m and n respectively, return the minimum window 
substring
 of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

 

Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.
 

Constraints:

m == s.length
n == t.length
1 <= m, n <= 105
s and t consist of uppercase and lowercase English letters.
 

Follow up: Could you find an algorithm that runs in O(m + n) time?
*/

//Using Hashmap and window sizing

class Solution {
    public String minWindow(String s, String t) {
        String ans="";
        HashMap<Character,Integer>hm1=new HashMap<>();
        HashMap<Character,Integer>hm2=new HashMap<>();

        for(int i=0;i<t.length();i++)
        {
            hm2.put(t.charAt(i),hm2.getOrDefault(t.charAt(i),0)+1);
        }
        int mct=0;
        int dmct=t.length();

        int i=-1;
        int j=-1;

        while(true)
        {
            //acquire
            boolean f1=false;
            boolean f2=false;
            while(i<s.length()-1 && mct<dmct)
            {
                i++;
                char ch=s.charAt(i);
                hm1.put(ch,hm1.getOrDefault(ch,0)+1);
                
                if(hm1.getOrDefault(ch,0)<=hm2.getOrDefault(ch,0))
                {
                    mct++;
                }
                f1=true;
            }
            //collect answers ans release

            while(j<i && mct==dmct)
            {
                String pans=s.substring(j+1,i+1);
                if(ans.length()==0 || pans.length()<ans.length())
                {
                    ans=pans;
                }
                j++;
                char ch=s.charAt(j);
                if(hm1.get(ch)==1)
                {
                    hm1.remove(ch);
                }
                else
                {
                    hm1.put(ch,hm1.get(ch)-1);
                }
                if(hm1.getOrDefault(ch,0)<hm2.getOrDefault(ch,0))
                {
                    mct--;
                }
                f2=true;
            }

            if(f1==false && f2==false)
            {
                break;
            }
        }
        return ans;

    }
}