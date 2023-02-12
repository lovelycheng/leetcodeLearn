import java.util.HashMap;
import java.util.HashSet;

/**
 * @author chengtong
 * @date 2023/2/10 13:40
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        System.err.println(lengthOfLongestSubstring("anviaj"));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int p1 = 0;
        int p2 = 1;
        HashMap<Character, Integer> hashMap = new HashMap<>();

        int longest = 1;

        for (; p2 < s.length(); p2++) {
            char c1 = s.charAt(p1);
            char c2 = s.charAt(p2);
            if (p1 == 0 && p2 == 1) {
                hashMap.put(c1, p1);
            }
            if (hashMap.containsKey(c2)) {
                int length = p2 - p1 + 1;
                longest = Math.max(longest, length);
                p1 = hashMap.get(c2) + 1;
                p2 = p1;
                hashMap.clear();
                c1 = s.charAt(p1);
                hashMap.put(c1, p1);
            } else {
                hashMap.put(c2, p2);
                int length = p2 - p1 + 1;
                longest = Math.max(length, longest);
            }
        }
        return longest;
    }

    public static int lengthOfLongestSubstring1(String s) {
        int ans = 0;
        Integer[] dataMap = new Integer[128];
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int start = 0, end = 0; end < s.length(); end++) {
            int pos = s.charAt(end);
            if (dataMap[pos] != null) {
                start = Math.max(start, dataMap[pos] + 1);
            }
            dataMap[pos] = end;
            ans = Math.max(ans, end - start + 1);
        }
        return ans;
    }
}
