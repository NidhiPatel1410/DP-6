// In this problem, keeping the string on both row and column side, and running a outer loop from 0 to n and inner loop runs from 0 to
// i, we check that if chars at both i and j are equal, and the number of chars in between them is less than or equal 1 or the subproblem
// ans is true, then we make current cell true. And when we make the cell true then check if the len of current substring is more than
// max then update max and also update the start and end.
// At end return the substring from start to end

// Time Complexity : O(n^2)
// Space Complexity : O(n^2)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
// Dp solution
class Solution {
    public String longestPalindrome(String s) {
        // Base case
        if (s == null || s.length() == 0) {
            return "";
        }
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        // Max, start, end for the answer
        int max = 0;
        int start = 0;
        int end = 0;
        // Outer loop from 0 to n
        for (int i = 0; i < n; i++) {
            // Inner loop from 0 to i
            for (int j = 0; j <= i; j++) {
                // Check if the chars are equal and (chars between them <=1 or the subproblem
                // ans at i-1,j+1 is true)
                if (s.charAt(i) == s.charAt(j) && (i - j - 1 <= 1 || dp[i - 1][j + 1] == true)) {
                    // In that case mark it as true
                    dp[i][j] = true;
                }
                // If the cell value is marked as true
                if (dp[i][j] == true) {
                    // Compute len
                    int len = i - j + 1;
                    // If better than update max, start and end
                    if (len > max) {
                        max = len;
                        start = j;
                        end = i;
                    }
                }

            }
        }
        // Return the longest substring
        return s.substring(start, end + 1);
    }
}

// In this solution we keep L and R pointers on char at i, i starts from 0 to n.
// So for each char at L and R we compare and if they are equal, we move l
// towards left and r towards right, till we find a mismatch, once found bring
// them back to the place where they were matching i.e l++ and r--. Now compute
// len of this palindromic substr and if better than max then update max, start,
// and end.
// Note there is one more case, when string is "bb" the output will be 'b' but
// ans is 'bb', so to handle this we also do same where l=i and r=i+1

// Time Complexity : O(n^2)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
// Pointer solution:
class Solution {
    int max;
    int start;
    int end;

    public String longestPalindrome(String s) {
        // base case
        if (s == null || s.length() == 0) {
            return "";
        }
        int n = s.length();
        // Loop for all chars
        for (int i = 0; i < n; i++) {
            // call with l=i and r=i
            findLongestPalimdrome(s, i, i);
            // second time call with l=i and r=i+1
            findLongestPalimdrome(s, i, i + 1);
        }
        // At end return the longest palindromic substring present between start and end
        return s.substring(start, end + 1);
    }

    private void findLongestPalimdrome(String s, int l, int r) {
        // While the chars match, and the l and r are valid indices keep moving
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            // left towards left
            l--;
            // right towards right
            r++;
        }
        // Once there is mismatch, move one step backwards where they were matching
        l++;
        r--;
        // Now compute len of this palindromic substring and compare with max and update
        // if required
        int len = r - l + 1;
        if (len > max) {
            max = len;
            start = l;
            end = r;
        }
    }
}