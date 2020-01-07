import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTrieSet implements TrieSet61B {

    private static class Node {
        boolean isKey;
        Map<Character, Node> children;

        Node() {
            this.isKey = false;
            this.children = new HashMap<>();
        }

        boolean hasChild(char key) {
            return this.children.containsKey(key);
        }

        Node getChild(char key) {
            return this.children.get(key);
        }

        void setChild(char key) {
            this.children.put(key, new Node());
        }
    }

    private Node root;

    public MyTrieSet() {
        this.root = new Node();
    }

    /**
     * Clears all items out of Trie
     */
    @Override
    public void clear() {
        this.root = new Node();
    }

    private boolean containsHelper(Node node, char[] chars, int index) {
        if (index == chars.length) {
            return node.isKey;
        } else {
            char childKey = chars[index];
            if (node.hasChild(childKey)) {
                return containsHelper(node.getChild(childKey), chars, index + 1);
            }
            return false;
        }
    }

    /**
     * Returns true if the Trie contains KEY, false otherwise
     */
    @Override
    public boolean contains(String key) {
        return containsHelper(root, key.toCharArray(), 0);
    }

    private void addHelper(Node node, char[] chars, int index) {
        if (index == chars.length) {
            node.isKey = true;
        } else {
            char childKey = chars[index];
            if (node.hasChild(childKey)) {
                addHelper(node.getChild(childKey), chars, index + 1);
            } else {
                node.setChild(childKey);
                addHelper(node.getChild(childKey), chars, index + 1);
            }
        }
    }

    /**
     * Inserts string KEY into Trie
     */
    @Override
    public void add(String key) {
        addHelper(root, key.toCharArray(), 0);
    }

    private Node nodeWithPrefix(Node node, char[] chars, int index) {
        if (index == chars.length) {
            return node;
        } else {
            char childKey = chars[index];
            if (node.hasChild(childKey)) {
                return nodeWithPrefix(node.getChild(childKey), chars, index + 1);
            }
            return null;
        }
    }

    private void constructString(List<String> result, Node node, String prefix) {
        if (node.isKey) result.add(prefix);

        for (char childKey : node.children.keySet()) {
            constructString(result, node.getChild(childKey), prefix + childKey);
        }
    }

    /**
     * Returns a list of all words that start with PREFIX
     */
    @Override
    public List<String> keysWithPrefix(String prefix) {
        // first find the node with that prefix
        char[] chars = prefix.toCharArray();
        Node current = nodeWithPrefix(root, chars, 0);
        if (current == null) return null;

        List<String> result = new ArrayList<>();
        constructString(result, current, prefix);
        return result.size() > 0 ? result : null;
    }

    private int longestPrefixOfHelper(Node node, char[] chars, int index, int longestIndex) {
        if (node.isKey) longestIndex = index;
        if (index == chars.length) return longestIndex;

        char childKey = chars[index];
        if (node.hasChild(childKey)) {
            return longestPrefixOfHelper(node.getChild(childKey), chars, index + 1, longestIndex);
        }
        return longestIndex;
    }

    /**
     * Returns the longest prefix of KEY that exists in the Trie.
     */
    @Override
    public String longestPrefixOf(String key) {
        int upTo = longestPrefixOfHelper(root, key.toCharArray(), 0, 0);
        return upTo == 0 ? "" : key.substring(0, upTo);
    }
}
