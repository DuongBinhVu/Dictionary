package dictionary;

import java.util.Scanner;

public class Word {
  // *** Class Variables ***
  /** Spelling of the word (lower case). */
  private String spelling;

  /** Meaning of the word. */
  private String meaning;

  /** Spelling of the word (true form - input form). */
  private String trueForm;

  // *** Setter, Getter Methods ***

  public void setSpelling(String spelling) {
    this.spelling = spelling;
  }

  public void setMeaning(String meaning) {
    this.meaning = meaning;
  }

  public void setTrueForm(String trueForm) {
    this.trueForm = trueForm;
  }

  public String getSpelling() {
    return this.spelling;
  }

  public String getMeaning() {
    return this.meaning;
  }

  public String getTrueForm() {
    return this.trueForm;
  }

  // *** Class Methods ***

  /** Constructor. */
  public Word() {
    this.spelling = "";
    this.meaning = "";
    this.trueForm = "";
  }

  /**
   * Constructor with parameters.
   *
   * @param trueForm input spelling of the word
   * @param meaning meaning of the word
   */
  public Word(String trueForm, String meaning) {
    this.meaning = meaning;
    this.trueForm = trueForm;
    this.spelling = trueForm.toLowerCase();
  }

  /** Read word from screen. */
  public void readWord() {
    Scanner scanner = new Scanner(System.in);

    String[] reader = scanner.nextLine().split("\t");

    this.trueForm = reader[0];
    this.meaning = reader[1];
    this.spelling = this.trueForm.toLowerCase();
  }

  /** Write word to screen. */
  public void writeWord() {
    System.out.println(trueForm + "\t" + meaning);
  }

  /**
   * Check this word spelling.
   *
   * @param spelling input spelling
   * @return if same or not
   */
  public Boolean isSpelling(String spelling) {
    return this.spelling.equals(spelling);
  }

  /**
   * Compare two words spelling.
   *
   * @param thatWord input word
   * @return if same or not
   */
  public Boolean isSpelling(Word thatWord) {
    return this.spelling.equals(thatWord.getSpelling());
  }

  /**
   * Check this word meaning.
   *
   * @param meaning input meaning
   * @return if same or not
   */
  public Boolean isMeaning(String meaning) {
    return this.meaning.equals(meaning);
  }

  /**
   * Compare two words meaning.
   *
   * @param thatWord input word
   * @return if same or not
   */
  public Boolean isMeaning(Word thatWord) {
    return this.meaning.equals(thatWord.getMeaning());
  }

  /**
   * Compare two words are similar or not.
   *
   * @param thatWord input word
   * @return if same or not
   */
  public Boolean isEquals(Word thatWord) {
    return this.spelling.equals(thatWord.getSpelling())
        && this.meaning.equals(thatWord.getMeaning());
  }
}
