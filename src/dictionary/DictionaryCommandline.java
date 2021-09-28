package dictionary;

public class DictionaryCommandline extends DictionaryManagement {
    /**
     * Print list of words.
     */
    public void showAllWords() {
        System.out.printf("%-10s %-30s %-30s%n", "No", "| English", "| Vietnamese");

        for (int i = 0; i < wordList.size(); i++) {
            System.out.printf("%-10d %-30s %-30s%n", (i + 1),
                    "| " + wordList.get(i).getWordTarget(),
                    "| " + wordList.get(i).getWordExplain());
        }
    }

    /**
     * Two basic function.
     */
    public void dictionaryBasic() {
        insertFromCommandline();
        showAllWords();
    }
}
