package spelling;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;

/**
 * A trie data structure that implements the Dictionary and the AutoComplete ADT
 */
public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete {

	private TrieNode root;
	private int size;

	public AutoCompleteDictionaryTrie() {
		root = new TrieNode();
		size = 0;
	}

	/**
	 * Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the
	 * string to all lower case before you insert it.
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 *         in the dictionary.
	 */
	@Override
	public boolean addWord(String word) {
		String lowerCaseWord = word.toLowerCase();
		TrieNode current = root;

		for (char c : lowerCaseWord.toCharArray()) {
			if (current.getChild(c) == null) {
				current = current.insert(c);
			} else {
				current = current.getChild(c);
			}
		}

		if (current.endsWord()) {
			return false;
		} else {
			current.setEndsWord(true);
			size++;
			return true;
		}
	}

	/**
	 * Return the number of words in the dictionary. This is NOT necessarily the
	 * same
	 * as the number of TrieNodes in the trie.
	 */
	@Override
	public int size() {
		return size;
	}

	/** Returns whether the string is a word in the trie. */
	@Override
	public boolean isWord(String s) {
		String lowerCaseWord = s.toLowerCase();
		TrieNode current = root;

		for (char c : lowerCaseWord.toCharArray()) {
			if (current.getChild(c) == null) {
				return false;
			}
			current = current.getChild(c);
		}

		return current.endsWord();
	}

	/**
	 * Return a list containing the up to numCompletions best predictions
	 * of the given prefix.
	 */
	@Override
	public List<String> predictCompletions(String prefix, int numCompletions) {
		List<String> completions = new LinkedList<>();
		TrieNode current = root;
		String lowerCasePrefix = prefix.toLowerCase();

		for (char c : lowerCasePrefix.toCharArray()) {
			if (current.getChild(c) == null) {
				return completions;
			}
			current = current.getChild(c);
		}

		Queue<TrieNode> queue = new LinkedList<>();
		queue.add(current);

		while (!queue.isEmpty() && completions.size() < numCompletions) {
			TrieNode node = queue.remove();
			if (node.endsWord()) {
				completions.add(node.getText());
			}
			for (char c : node.getValidNextCharacters()) {
				queue.add(node.getChild(c));
			}
		}

		return completions;
	}

	// For debugging
	public void printTree() {
		printNode(root);
	}

	/** Do a pre-order traversal from this node down */
	public void printNode(TrieNode curr) {
		if (curr == null)
			return;
		System.out.println(curr.getText());
		for (Character c : curr.getValidNextCharacters()) {
			printNode(curr.getChild(c));
		}
	}

	public static void main(String[] args) {
		AutoCompleteDictionaryTrie dictionary = new AutoCompleteDictionaryTrie();

		// Test addWord and size
		System.out.println("Adding 'hello': " + dictionary.addWord("hello")); // true
		System.out.println("Adding 'world': " + dictionary.addWord("world")); // true
		System.out.println("Adding 'HELLO': " + dictionary.addWord("HELLO")); // false
		System.out.println("Dictionary size: " + dictionary.size()); // 2

		// Test isWord
		System.out.println("Is 'hello' a word? " + dictionary.isWord("hello")); // true
		System.out.println("Is 'HELLO' a word? " + dictionary.isWord("HELLO")); // true
		System.out.println("Is 'world' a word? " + dictionary.isWord("world")); // true
		System.out.println("Is 'java' a word? " + dictionary.isWord("java")); // false

		// Test predictCompletions
		dictionary.addWord("hell");
		dictionary.addWord("helicopter");
		dictionary.addWord("help");
		dictionary.addWord("heap");
		dictionary.addWord("he");

		List<String> completions = dictionary.predictCompletions("he", 3);
		System.out.println("Completions for 'he': " + completions); // [he, hell, hello]
	}

}
