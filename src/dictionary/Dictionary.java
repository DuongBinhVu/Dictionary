package dictionary;

import java.util.ArrayList;

public class Dictionary {
    /** List of words. */
    protected ArrayList<Word> wordList = new ArrayList<>();

    // TODO: remove after finishing
    public void showAllWords() {
        if (wordList.isEmpty()) {
            System.out.println("Dictionary is empty!");
        } else {
            System.out.printf("%-10s %-30s %-30s%n", "No", "| English", "| Vietnamese");

            for (int i = 0; i < wordList.size(); i++) {
                System.out.printf(
                        "%-10d %-30s %-30s%n",
                        (i + 1), "| " + wordList.get(i).getTrueForm(), "| " + wordList.get(i).getMeaning());
            }
        }
    }
}
