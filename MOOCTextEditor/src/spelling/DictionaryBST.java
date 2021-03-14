package spelling;

import java.util.TreeSet;

/**
 * @author UC San Diego Intermediate MOOC team
 */
public class DictionaryBST implements Dictionary {

    private TreeSet<String> dict;

    // TODO: Implement the dictionary interface using a TreeSet.  
    // You'll need a constructor here
    public DictionaryBST() {
        this.dict = new TreeSet<>();
    }

    /**
     * Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     *
     * @param word The word to add
     * @return true if the word was added to the dictionary
     * (it wasn't already there).
     */
    public boolean addWord(String word) {
        // TODO: Implement this method
        word = word.toLowerCase();
        return dict.add(word);
    }

    /**
     * Return the number of words in the dictionary
     */
    public int size() {
        // TODO: Implement this method
        return dict.size();
    }

    /**
     * Is this a word according to this dictionary?
     */
    public boolean isWord(String s) {
        //TODO: Implement this method
        s = s.toLowerCase();
        return dict.contains(s);
    }
}
