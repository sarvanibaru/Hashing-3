// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
We use Rabin Karps rolling hash algorithm to compute hash of substrings by maintaining a sliding window
of 4 size given the sequence size is of 4. We check if we have already seen that hash before by referring to
allSeq set, if so, we add it to our result set. If not, we add it allSeq set. This way, we extend the window
by removing the characters at the start of the window.
 */
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<Long> allSeq = new HashSet<>();
        Set<String> result = new HashSet<>();
        long hash = 0;
        int n = s.length();
        long posFac = (long)Math.pow(4, 10);
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 1);
        map.put('C', 2);
        map.put('G', 3);
        map.put('T', 4);

        //Use Rabin KArps rolling hash algo
        for(int i = 0 ; i < n ; i++) {
            char inChar = s.charAt(i);
            hash = hash * 4 + map.get(inChar); //since our DNA seq is of 4 char types

            if(i >= 10) {
                char outChar = s.charAt(i - 10);
                hash = hash - (map.get(outChar) * posFac);
            }

            if(allSeq.contains(hash)) {
                result.add(s.substring(i - 9, i + 1));
            }else {
                allSeq.add(hash);
            }
        }
        return new ArrayList<>(result);
    }
}