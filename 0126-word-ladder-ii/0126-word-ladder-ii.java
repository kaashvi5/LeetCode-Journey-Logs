import java.util.*;

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();

        Set<String> wordSet = new HashSet<>(wordList);

        if (!wordSet.contains(endWord)) {
            return result;
        }

        // parent[word] = all words that can come immediately before it
        Map<String, List<String>> parent = new HashMap<>();

        Set<String> currentLevel = new HashSet<>();
        currentLevel.add(beginWord);

        boolean found = false;

        while (!currentLevel.isEmpty() && !found) {
            // Remove words visited in the previous levels
            wordSet.removeAll(currentLevel);

            Set<String> nextLevel = new HashSet<>();

            for (String word : currentLevel) {
                char[] chars = word.toCharArray();

                for (int i = 0; i < chars.length; i++) {
                    char original = chars[i];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original) continue;

                        chars[i] = c;
                        String next = new String(chars);

                        if (wordSet.contains(next)) {
                            nextLevel.add(next);

                            parent.putIfAbsent(next, new ArrayList<>());
                            parent.get(next).add(word);

                            if (next.equals(endWord)) {
                                found = true;
                            }
                        }
                    }

                    chars[i] = original;
                }
            }

            currentLevel = nextLevel;
        }

        if (!found) {
            return result;
        }

        // Build paths backwards from endWord to beginWord
        List<String> path = new ArrayList<>();
        path.add(endWord);

        backtrack(endWord, beginWord, parent, path, result);

        return result;
    }

    private void backtrack(
            String word,
            String beginWord,
            Map<String, List<String>> parent,
            List<String> path,
            List<List<String>> result) {

        if (word.equals(beginWord)) {
            List<String> temp = new ArrayList<>(path);
            Collections.reverse(temp);
            result.add(temp);
            return;
        }

        if (!parent.containsKey(word)) {
            return;
        }

        for (String previous : parent.get(word)) {
            path.add(previous);

            backtrack(previous, beginWord, parent, path, result);

            path.remove(path.size() - 1);
        }
    }
}