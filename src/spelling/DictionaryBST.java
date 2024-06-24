package spelling;

import java.util.TreeSet;

/**
 * A class that implements the Dictionary interface using a TreeSet
 */
public class DictionaryBST implements Dictionary {

    private TreeSet<String> dict;

    // Constructor
    public DictionaryBST() {
        dict = new TreeSet<>();
    }

    /**
     * Add this word to the dictionary. Convert it to lowercase first
     * for the assignment requirements.
     * 
     * @param word The word to add
     * @return true if the word was added to the dictionary
     *         (it wasn't already there).
     */
    @Override
    public boolean addWord(String word) {
        String lowerCaseWord = word.toLowerCase();
        return dict.add(lowerCaseWord);
    }

    /** Return the number of words in the dictionary */
    @Override
    public int size() {
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    @Override
    public boolean isWord(String s) {
        return dict.contains(s.toLowerCase());
    }
}
