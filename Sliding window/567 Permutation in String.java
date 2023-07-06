/*
 567. Permutation in String
Medium
10.1K
322
Companies
Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.

In other words, return true if one of s1's permutations is the substring of s2.

 

Example 1:

Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:

Input: s1 = "ab", s2 = "eidboaoo"
Output: false
 

Constraints:

1 <= s1.length, s2.length <= 104
s1 and s2 consist of lowercase English letters.
Accepted
672.2K
Submissions
1.5M
Acceptance Rate
44.2%
Seen this question in a real interview before?
1/4
 */

class Solution {
    public boolean checkInclusion(String s1, String s2) {
       if(s1.length() > s2.length()) return false;
       
       int[] arr1 = new int[26];
       int[] arr2 = new int[26];
       
       for(int i = 0; i < s1.length(); i++){
           arr1[s1.charAt(i) - 'a']++;
           arr2[s2.charAt(i) - 'a']++;
       }
       
       if(Arrays.equals(arr1, arr2)) return true;
       
       int front = 0;
       int back = s1.length();
       while(back < s2.length()){
           arr2[s2.charAt(front) - 'a']--;
           arr2[s2.charAt(back) - 'a']++;
           
           if(Arrays.equals(arr1, arr2)) return true;
           front++;
           back++;
       }
       return false;
   }

//Method 2 with decrease Runtime
//    public boolean checkInclusion(String s1, String s2) {
        
//     int arr1[]=new int[26];
//     //int arr2[]=new int [26];
//     if(s2.length()<s1.length())
//     {
//         return false;
//     }
//     for(int i=0;i<s1.length();i++)
//     {
//         arr1[s1.charAt(i)-'a']++;
//         arr1[s2.charAt(i)-'a']--;
//     }
//     if(allzero(arr1))
//     {
//         return true;
//     }
//     int k=s1.length();
//     for(int i=s1.length();i<s2.length();i++)
//     {
        
//         arr1[s2.charAt(i-k)-'a']++;
//         arr1[s2.charAt(i)-'a']--;
//         if(allzero(arr1))
//         {
//             return true;
//         }
//     }
//     return false;
// }
public static boolean allzero(int arr[])
{
    for(int i=0;i<26;i++)
    {
        if(arr[i]!=0)
        {
            return false;
        }
    }
    return true;
}
}