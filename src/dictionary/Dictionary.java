package dictionary;

import java.util.ArrayList;

public class Dictionary {
    /**
     * List of words.
     */
    protected ArrayList<Word> wordList = new ArrayList<>();

    // TRIE PART

    static public class TrieNode {
        protected ArrayList<TrieNode> children = new ArrayList<>();

        private char characterRepresent;

        private Word indexWord;

        public void setIndexWord(Word indexWord) {
            this.indexWord = indexWord;
        }

        public void setCharacterRepresent(char characterRepresent) {
            this.characterRepresent = characterRepresent;
        }

        public Word getIndexWord() {
            return indexWord;
        }

        public char getCharacterRepresent() {
            return characterRepresent;
        }

        /**
         * Constructor.
         */
        public TrieNode() {
            indexWord = null;
        }

        /**
         * Constructor.
         */
        public TrieNode(char characterRepresent) {
            indexWord = null;

            this.characterRepresent = characterRepresent;
        }

        /**
         * Find out if this TrieNode has a chill represented by input character or not.
         *
         * @param index input character
         * @return return TrieNode represented by input character, if not return null
         */
        public TrieNode isExistInChildren(char index) {
            for (TrieNode chill : this.children) {
                if (index == chill.getCharacterRepresent()) {
                    return chill;
                }
            }

            return null;
        }

        /**
         * Add a new chill has characterRepresent is addedCharacter.
         *
         * @param addedCharacter input character
         */
        public void addChill(char addedCharacter) {
            children.add(new TrieNode(addedCharacter));
        }
    }

    public TrieNode root = new TrieNode();

    /**
     * Search result list ( Must be clear everytime using DictionaryManagement.dictionarySearcher ).
     */
    public ArrayList<Word> resultSearcher = new ArrayList<>();

    public void addNode(Word insertWord) {
        if (insertWord == null) {
            return;
        }

        String key = insertWord.getWord();

        TrieNode pointer = root;

        for (int i = 0; i < key.length(); i++) {
            char index = key.charAt(i);

            if (pointer.isExistInChildren(index) == null) {
                pointer.addChill(index);
            }

            pointer = pointer.isExistInChildren(index);
        }

        pointer.setIndexWord(insertWord);
    }

    public TrieNode searchNode(String key) {
        TrieNode pointer = root;

        for (int i = 0; i < key.length(); i++) {
            char index = key.charAt(i);

            if (pointer.isExistInChildren(index) == null) {
                return null;
            }

            pointer = pointer.isExistInChildren(index);
        }

        return pointer;
    }

    public void getAllWordInNode(TrieNode node) {
        if (node == null) {
            return;
        }

        if (node.getIndexWord() != null) {
            resultSearcher.add(node.getIndexWord());
        }

        for (TrieNode chill : node.children) {
            getAllWordInNode(chill);
        }
    }

    public void deleteNode(TrieNode node) {
        if (node == null) {
            return;
        }

        node.setIndexWord(null);

        if (node.children.isEmpty()) {
            node = null;
        }
    }
}
