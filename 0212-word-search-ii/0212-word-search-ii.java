class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    TrieNode root = new TrieNode();

    public List<String> findWords(char[][] board, String[] words) {
        for (String word : words) {
            TrieNode node = root;

            for (char c : word.toCharArray()) {
                int idx = c - 'a';

                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }

                node = node.children[idx];
            }

            node.word = word;
        }

        List<String> ans = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, ans);
            }
        }

        return ans;
    }

    void dfs(char[][] board, int i, int j,
             TrieNode node, List<String> ans) {

        if (i < 0 || j < 0 || i >= board.length ||
            j >= board[0].length || board[i][j] == '#') {
            return;
        }

        char c = board[i][j];
        TrieNode next = node.children[c - 'a'];

        if (next == null) return;

        if (next.word != null) {
            ans.add(next.word);
            next.word = null;
        }

        board[i][j] = '#';

        dfs(board, i + 1, j, next, ans);
        dfs(board, i - 1, j, next, ans);
        dfs(board, i, j + 1, next, ans);
        dfs(board, i, j - 1, next, ans);

        board[i][j] = c;
    }
}