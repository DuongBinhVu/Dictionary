package dictionary;

import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    /**
     * Insert new words.
     */
    public void insertFromCommandline() {
        Scanner scanner = new Scanner(System.in);

        int numOfWords = scanner.nextInt(); // Number of words inserted.

        scanner.nextLine();

        for (int i = 1; i <= numOfWords; i++) {
            String newWord = scanner.nextLine(); // English word.
            String wordExplain = scanner.nextLine(); // Explanation of word.

            this.wordList.add(new Word(newWord, wordExplain));
        }
    }
}
