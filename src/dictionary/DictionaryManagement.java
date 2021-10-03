package dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
  /** Insert new words. */
  public void insertFromCommandline() {
    Scanner scanner = new Scanner(System.in);

    int numOfWords = scanner.nextInt(); // Number of words inserted.

    scanner.nextLine();

    for (int i = 1; i <= numOfWords; i++) {
      String newWord = scanner.nextLine(); // English word.
      String wordExplain = scanner.nextLine(); // Explanation of word.

      this.wordList.add(new Word(newWord, wordExplain));
    }
    scanner.close();
  }

  /** Insert words from file. */
  public void insertFromFile() {
    File file = new File("src/dictionary/dictionaries.txt");

    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      String line;
      while ((line = br.readLine()) != null) {
        String[] tmp = line.split("\t");
        this.wordList.add(new Word(tmp[0], tmp[1]));
      }
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** Find all meaning of the input word. */
  public void dictionaryLookup() {
    Scanner scanner = new Scanner(System.in);
    String targetWord = scanner.nextLine();
    ArrayList<String> Meaning = new ArrayList<String>();
    for (int i = 0; i < this.wordList.size(); i++) {
      if (this.wordList.get(i).getWordTarget().equals(targetWord)) {
        Meaning.add(this.wordList.get(i).getWordExplain());
      }
    }
    if (Meaning.isEmpty()) {
      System.out.printf("Từ cần tìm không tồn tại");
    } else {
      System.out.printf("%-10s %-30s %-30s%n", "No", "| English", "| Vietnamese");

      for (int i = 0; i < Meaning.size(); i++) {
        System.out.printf("%-10d %-30s %-30s%n", (i + 1), "| " + targetWord, "| " + Meaning.get(i));
      }
    }
    scanner.close();
  }
}
