package dictionary;

import java.util.Scanner;

public class DictionaryCommandline extends DictionaryManagement {
    /**
     * Print list of words.
     */
    public void showAllWords() {
        System.out.printf("%-10s %-30s %-30s%n", "No", "| English", "| Vietnamese");

        for (int i = 0; i < wordList.size(); i++) {
            System.out.printf(
                    "%-10d %-30s %-30s%n",
                    (i + 1), "| " + wordList.get(i).getTrueForm(), "| " + wordList.get(i).getMeaning());
        }
    }

    /**
     * Showing management functions.
     */
    public void showHelp() {
        System.out.println("show         Show all words in current dictionary");
        System.out.println("add          Add a new word to dictionary");
        System.out.println("remove       Remove a word from dictionary");
        System.out.println("edit         Edit a word in dictionary");
        System.out.println("search       Search and show all words start with ...");
        System.out.println("import       Import dictionary data from file");
        System.out.println("export       Export current dictionary to file");
        System.out.println("help         Show all commands");
        System.out.println("exit         Exit program");
    }

    /**
     * Advanced version.
     */
    public void dictionaryAdvanced() {
        Scanner scanner = new Scanner(System.in);

        runningDictionaryAdvanced:
        while (true) {
            String command = scanner.nextLine();

            switch (command) {
                case "show" -> showAllWords();
                case "help" -> showHelp();
                case "exit" -> {
                    System.out.println("Exiting program!");
                    break runningDictionaryAdvanced;
                }
                default -> {
                    CommandType order;

                    order = switch (command) {
                        case "add" -> CommandType.ADD;
                        case "remove" -> CommandType.REMOVE;
                        case "edit" -> CommandType.EDIT;
                        case "search" -> CommandType.SEARCH;
                        case "import" -> CommandType.IMPORT;
                        case "export" -> CommandType.EXPORT;
                        default -> CommandType.NO_COMMAND;
                    };

                    if (order != CommandType.NO_COMMAND) {
                        executeOrder(order);
                    }
                }
            }
        }
    }
}
