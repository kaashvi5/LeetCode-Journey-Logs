class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();

        // Frequency of characters in s
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        char[] ans = new char[n];

        // Try to match target from left to right
        for (int i = 0; i < n; i++) {
            int cur = target.charAt(i) - 'a';

            // Use target[i] if possible
            if (freq[cur] > 0) {
                ans[i] = target.charAt(i);
                freq[cur]--;
            } else {
                // Cannot match target[i].
                // Need to make the answer greater at some previous position.
                break;
            }
        }

        // Rebuild frequency array
        freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Find the rightmost position where we can make
        // the answer greater than target
        for (int i = n - 1; i >= 0; i--) {

            // Characters before i must exactly match target
            boolean possible = true;

            int[] remaining = freq.clone();

            for (int j = 0; j < i; j++) {
                int c = target.charAt(j) - 'a';

                if (remaining[c] == 0) {
                    possible = false;
                    break;
                }

                remaining[c]--;
            }

            if (!possible) {
                continue;
            }

            int cur = target.charAt(i) - 'a';

            // Find smallest character greater than target[i]
            for (int c = cur + 1; c < 26; c++) {

                if (remaining[c] > 0) {

                    char[] result = new char[n];

                    // Copy prefix
                    for (int j = 0; j < i; j++) {
                        result[j] = target.charAt(j);
                    }

                    // Put the smallest greater character
                    result[i] = (char) ('a' + c);
                    remaining[c]--;

                    // Fill suffix in sorted order
                    int pos = i + 1;

                    for (int x = 0; x < 26; x++) {
                        while (remaining[x] > 0) {
                            result[pos++] = (char) ('a' + x);
                            remaining[x]--;
                        }
                    }

                    return new String(result);
                }
            }
        }

        return "";
    }
}