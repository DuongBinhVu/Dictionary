package dictionary;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    private final String IMPORT_FILE_PATH = "src/resource/dictionaries.txt";
    private final String EXPORT_FILE_PATH = "src/resource/outfile.txt";

    /**
     * Type of command.
     */
    public enum CommandType {
        ADD,
        REMOVE,
        EDIT,
        SEARCH,
        IMPORT,
        EXPORT,
        NO_COMMAND
    }

    /**
     * Import dictionary from file.
     */
    public void importFromFile() {
        File file = new File(IMPORT_FILE_PATH);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;

            while ((line = br.readLine()) != null) {
                String[] tmp = line.split("\t");

                this.wordList.add(new Word(tmp[0], tmp[1]));
            }

            br.close();

            System.out.println("Imported!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Export dictionary to file.
     */
    public void exportToFile() {
        File file = new File(EXPORT_FILE_PATH);

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            for (Word word : wordList) {
                bw.write(word.getTrueForm() + "\t" + word.getMeaning() + "\n");
            }

            bw.close();

            System.out.println("Exported!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add new word to dictionary.
     */
    public void addWord() {
        System.out.println("Write the word you want to add and its meaning (Separated by a tab): ");

        Word addingWord = new Word();
        addingWord.readWord();

        wordList.add(addingWord);

        System.out.println("Added!");
    }

    /**
     * Remove a word from dictionary.
     */
    public void removeWord() {
        System.out.println("Write the word you want to remove and its meaning (Separated by a tab): ");
        System.out.println("(If remove all homographs of that word, type -1 in meaning)");

        Word removingWord = new Word();
        removingWord.readWord();

        if (removingWord.isMeaning("-1")) {
            if (wordList.removeIf(word -> word.isSpelling(removingWord))) {
                System.out.println("Removed all homographs!");
            } else {
                System.out.println("Not found!");
            }
        } else {
            if (wordList.removeIf(word -> word.isEquals(removingWord))) {
                System.out.println("Removed!");
            } else {
                System.out.println("Not found!");
            }
        }
    }

    /**
     * Edit a word in dictionary.
     */
    public void editWord() {
        System.out.println("Write the target word you want to edit and its meaning (Separated by a tab): ");

        Word editingWord = new Word();
        editingWord.readWord();

        System.out.println("Write the right word you want to edit and its meaning (Separated by a tab): ");
        Word newWord = new Word();
        newWord.readWord();

        boolean isFound = false;

        for (int i = 0; i < wordList.size(); i++) {
            if (wordList.get(i).isEquals(editingWord)) {
                wordList.set(i, newWord);

                isFound = true;
            }
        }

        if (isFound) {
            System.out.println("Edited!");
        } else {
            System.out.println("Not found!");
        }
    }

    /**
     * Search words start with.
     */
    public void dictionarySearcher() {
        System.out.println("Write the word you want to search: ");

        Scanner scanner = new Scanner(System.in);
        String searchWord = scanner.nextLine();

        searchWord = searchWord.toLowerCase();

        ArrayList<Word> resultList = new ArrayList<>();

        for (Word word : wordList) {
            if (word.getSpelling().startsWith(searchWord)) {
                resultList.add(word);
            }
        }

        if (resultList.isEmpty()) {
            System.out.println("Not found!");
        } else {
            System.out.println("Words found: ");

            for (Word word : resultList) {
                word.writeWord();
            }
        }
    }

    /**
     * Execute order on dictionary.
     *
     * @param command input command
     */
    public void executeOrder(CommandType command) {
        switch (command) {
            case ADD -> addWord();
            case REMOVE -> removeWord();
            case EDIT -> editWord();
            case SEARCH -> dictionarySearcher();
            case IMPORT -> importFromFile();
            case EXPORT -> exportToFile();
        }
    }
}
