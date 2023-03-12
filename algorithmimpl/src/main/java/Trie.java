import java.util.HashMap;
import java.util.Map;

/**
 * @author chengtong
 * @date 2023/3/12 17:43
 */
public class Trie {

    private TrieNode root = new TrieNode();

    public static class TrieNode {
        private String value;
        private TrieNode parent;
        private Map<String, TrieNode> children = new HashMap<>();
        private boolean isEnd;

        TrieNode() {

        }

        public TrieNode(String value, TrieNode parent, Map<String, TrieNode> children, boolean isEnd) {
            this.value = value;
            this.parent = parent;
            this.children = children;
            this.isEnd = isEnd;
        }
    }

    public Trie() {
    }

    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }

        insertTree(word);

    }

    public boolean search(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        char[] chars = word.toCharArray();

        Map<String, TrieNode> temps = root.children;
        for (int i = 0; i < word.length(); i++) {
            TrieNode temp;
            if ((temp = temps.get(String.valueOf(chars[i]))) == null) {
                return false;
            } else if (i == word.length() - 1) {
                return temp.isEnd;
            } else {
                temps = temp.children;
            }
        }
        return false;
    }

    private void insertTree(String word) {
        TrieNode par = root;
        for (int i = 0; i < word.length(); i++) {
            String v = String.valueOf(word.charAt(i));
            if (!par.children.containsKey(v)) {
                TrieNode n = new TrieNode(v, par, new HashMap<>(), i == word.length() - 1);
                par.children.put(v, n);
            }else {
                TrieNode n = par.children.get(v);
                n.isEnd |= i == word.length() - 1;
            }
            par = par.children.get(v);
        }
    }

    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return false;
        }
        char[] chars = prefix.toCharArray();

        Map<String, TrieNode> temps = root.children;
        for (int i = 0; i < prefix.length(); i++) {
            TrieNode temp;
            if ((temp = temps.get(String.valueOf(chars[i]))) == null) {
                return false;
            } else {
                temps = temp.children;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("app");
        trie.insert("apple");
        System.err.println(trie.search("apple"));
        System.err.println(trie.search("app"));
        assert trie.search("app");
        assert trie.search("apple");
        System.err.println( trie.startsWith("apple"));
    }

}
