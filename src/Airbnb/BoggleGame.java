package Airbnb;
import java.util.*;
public class BoggleGame {
	public int boggleGame(char[][] board, String[] words) {
        // Write your code here   
        // 构建字典树加速查找
        Trie trie = new Trie();
        for(String word : words) {
            trie.insert(word);
        }

        int m = board.length;
        int n = board[0].length;
        List<String> result = new ArrayList<>();
        // 每个点作为起点，可能会有不一样的结果
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean[][] visited = new boolean[m][n];
                List<String> path = new ArrayList<>();
                findWords(result, board, visited, path, i, j, trie.root);
            }
        }
        return result.size();
    }

    // 从i,j开始递归找到所有单词组合
    public void findWords(List<String> result, char[][] board, boolean[][] visited, List<String> words, int x, int y, TrieNode root) {

        int m = board.length;
        int n = board[0].length;
        for (int i = x; i < m; i++) {
            for (int j = y; j < n; j++) {
                List<List<Integer>> nextWordIndexes = new ArrayList<>();
                List<Integer> path = new ArrayList<>();
                // 获得从当前点开始的所有可能单词的indexes
                getNextWords(nextWordIndexes, board, visited, path, i, j, root);
                for (List<Integer> indexes : nextWordIndexes) {
                    // 设置visited为当前使用单词
                    String word = "";
                    for (int index : indexes) {
                        int row = index / n;
                        int col = index % n;
                        visited[row][col] = true;
                        word += board[row][col];
                    }

                    words.add(word);
                    // 只要更新了words，就保存一次words
                    if (words.size() > result.size()) {
                        result.clear();
                        result.addAll(words);
                    }
                    findWords(result, board, visited, words, i, j, root);

                    // 恢复visited
                    for (int index : indexes) {
                        int row = index / n;
                        int col = index % n;
                        visited[row][col] = false;
                    }
                    words.remove(words.size() - 1);
                }
            }
            // 只有第x行是从y开始，后面都从0开始
            y = 0;
        }
    }

    private void getNextWords(List<List<Integer>> words, char[][] board, boolean[][] visited, List<Integer> path, int i, int j, TrieNode root) {
        if(i < 0 | i >= board.length || j < 0 || j >= board[0].length
            || visited[i][j] == true || root.children[board[i][j] - 'a'] == null) {
            return;
        }

        root = root.children[board[i][j] - 'a'];
        if(root.isWord) {
            List<Integer> newPath = new ArrayList<>(path);
            newPath.add(i * board[0].length + j);
            words.add(newPath);
            return;
        }

        visited[i][j] = true;
        path.add(i * board[0].length + j);
        getNextWords(words, board, visited, path, i + 1, j, root);
        getNextWords(words, board, visited, path, i - 1, j, root);
        getNextWords(words, board, visited, path, i, j + 1, root);
        getNextWords(words, board, visited, path, i, j - 1, root);
        path.remove(path.size() - 1);
        visited[i][j] = false;
    }

    class Trie {
        TrieNode root;

        Trie() {
            root = new TrieNode('0');
        }

        public void insert(String word) {
            if(word == null || word.length() == 0) {
                return;
            }
            TrieNode node = root;
            for(int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if(node.children[ch - 'a'] == null) {
                    node.children[ch - 'a'] = new TrieNode(ch);
                }
                node = node.children[ch - 'a'];
            }
            node.isWord = true;
        }
    }

    class TrieNode {
        char value;
        boolean isWord;
        TrieNode[] children;

        TrieNode(char v) {
            value = v;
            isWord = false;
            children = new TrieNode[26];
        }
    }
}
