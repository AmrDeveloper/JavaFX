package spelling;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 *
 * @author You
 */
public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    private LinkedList<String> words;

    public AutoCompleteDictionaryTrie() {
        root = new TrieNode();
        size = 0;
        words = new LinkedList<>();
    }

    /**
     * Insert a word into the trie.
     * For the basic part of the assignment (part 2), you should convert the
     * string to all lower case before you insert it.
     * <p>
     * This method adds a word by creating and linking the necessary trie nodes
     * into the trie, as described outlined in the videos for this week. It
     * should appropriately use existing nodes in the trie, only creating new
     * nodes when necessary. E.g. If the word "no" is already in the trie,
     * then adding the word "now" would add only one additional node
     * (for the 'w').
     *
     * @return true if the word was successfully added or false if it already exists
     * in the dictionary.
     */
    public boolean addWord(String word) {
        TrieNode currentRoot = root;
        word = word.toLowerCase();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            TrieNode node = currentRoot.getChild(c);
            if (node == null) {
                currentRoot = currentRoot.insert(c);
                if (i == word.length() - 1) {
                    currentRoot.setEndsWord(true);
                    size = size + 1;
                    words.addLast(word);
                    return true;
                }
            } else {
                if (i == word.length() - 1 && !node.endsWord()) {
                    node.setEndsWord(true);
                    size = size + 1;
                    words.addLast(word);
                }
                currentRoot = node;
            }
        }
        return false;
    }

    /**
     * Return the number of words in the dictionary.  This is NOT necessarily the same
     * as the number of TrieNodes in the trie.
     */
    public int size() {
        return size;
    }


    /**
     * Returns whether the string is a word in the trie, using the algorithm
     * described in the videos for this week.
     */
    @Override
    public boolean isWord(String s) {
        TrieNode currentRoot = root;
        s = s.toLowerCase();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            TrieNode node = currentRoot.getChild(c);
            if (node == null) {
                return false;
            }
            if (i == s.length() - 1) {
                return node.endsWord();
            }
            currentRoot = node;
        }
        return false;
    }

    /**
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions
     * of the prefix string. All legal completions must be valid words in the
     * dictionary. If the prefix itself is a valid word, it is included
     * in the list of returned words.
     * <p>
     * The list of completions must contain
     * all of the shortest completions, but when there are ties, it may break
     * them in any order. For example, if there the prefix string is "ste" and
     * only the words "step", "stem", "stew", "steer" and "steep" are in the
     * dictionary, when the user asks for 4 completions, the list must include
     * "step", "stem" and "stew", but may include either the word
     * "steer" or "steep".
     * <p>
     * If this string prefix is not in the trie, it returns an empty list.
     *
     * @param prefix         The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */
    @Override
    public List<String> predictCompletions(String prefix, int numCompletions) {
        if (prefix.isEmpty()) {
            LinkedList<String> tempWords = getWordsFromNode(root);
            int numToRemove = tempWords.size() - numCompletions;
            for (int i = 0; i < numToRemove; i++) {
                tempWords.removeLast();
            }
            return tempWords;
        }
        prefix = prefix.toLowerCase();
        TrieNode prefixNode = getWordNode(prefix);
        if (prefixNode == null) {
            LinkedList<String> tempWords = new LinkedList<>();
            for (String s : words) {
                if (s.startsWith(prefix)) {
                    tempWords.addLast(s);
                }
            }
            return tempWords;
        } else {
            LinkedList<String> completions = getWordsFromNode(prefixNode);
            completions.addFirst(prefix);
            int numToRemove = completions.size() - numCompletions;
            for (int i = 0; i < numToRemove; i++) {
                completions.removeLast();
            }
            return completions;
        }
    }

    // For debugging
    public void printTree() {
        printNode(root);
    }

    /**
     * Do a pre-order traversal from this node down
     */
    public void printNode(TrieNode curr) {
        if (curr == null)
            return;

        System.out.println(curr.getText());

        TrieNode next = null;
        for (Character c : curr.getValidNextCharacters()) {
            next = curr.getChild(c);
            printNode(next);
        }
    }

    private TrieNode getWordNode(String s) {
        if (s.isEmpty()) {
            return null;
        }
        TrieNode currentRoot = root;
        s = s.toLowerCase();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            TrieNode node = currentRoot.getChild(c);
            if (node == null) {
                return null;
            }
            if (i == s.length() - 1 && node.endsWord()) {
                return node;
            }
            currentRoot = node;
        }
        return null;
    }

    private LinkedList<String> getWordsFromNode(TrieNode root) {
        LinkedList<String> completions = new LinkedList<>();
        for (Character c : root.getValidNextCharacters()) {
            TrieNode currentNode = root.getChild(c);

            if (currentNode.endsWord()) {
                completions.addLast(currentNode.getText());
            }
            completions.addAll(getWordsFromNode(currentNode));
        }
        completions.sort(Comparator.comparingInt(String::length));
        return completions;
    }

}