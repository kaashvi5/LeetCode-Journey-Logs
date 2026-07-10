public class Solution {
    public String countAndSay(int n) {
        if (n <= 0) return "";
        StringBuilder sb = new StringBuilder("1");
        while (--n > 0) {
            StringBuilder next = new StringBuilder();
            for (int i = 0; i < sb.length(); ++i) {
                int count = 1;
                while (i + 1 < sb.length() && sb.charAt(i) == sb.charAt(i + 1)) {
                    ++count;
                    ++i;
                }
                next.append(count).append(sb.charAt(i));
            }
            sb = next;
        }
        return sb.toString();
    }

    // quick test
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.countAndSay(1)); // "1"
        System.out.println(sol.countAndSay(4)); // "1211"
        System.out.println(sol.countAndSay(5)); // "111221"
    }
}