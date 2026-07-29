class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;
        int n = words.length;

        while (i < n) {
            // Determine how many words fit on this line
            int lineLen = words[i].length();
            int j = i + 1;
            while (j < n && lineLen + 1 + words[j].length() <= maxWidth) {
                lineLen += 1 + words[j].length();
                j++;
            }

            int numWords = j - i;
            StringBuilder line = new StringBuilder();

            if (j == n || numWords == 1) {
                // Last line or single-word line: left-justify
                for (int k = i; k < j; k++) {
                    line.append(words[k]);
                    if (k != j - 1) line.append(' ');
                }
                // pad trailing spaces
                while (line.length() < maxWidth) {
                    line.append(' ');
                }
            } else {
                // Fully justify
                int totalChars = 0;
                for (int k = i; k < j; k++) {
                    totalChars += words[k].length();
                }
                int totalSpaces = maxWidth - totalChars;
                int gaps = numWords - 1;
                int spaceEach = totalSpaces / gaps;
                int extra = totalSpaces % gaps;

                for (int k = i; k < j; k++) {
                    line.append(words[k]);
                    if (k != j - 1) {
                        int spacesToAdd = spaceEach + (k - i < extra ? 1 : 0);
                        for (int s = 0; s < spacesToAdd; s++) {
                            line.append(' ');
                        }
                    }
                }
            }

            result.add(line.toString());
            i = j;
        }

        return result;
    }
}