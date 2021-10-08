package dictionary;

import java.util.ArrayList;

public class Word {
  private String word;
  private String word_type;
  private ArrayList<String> explanations;
  private ArrayList<String> usages;
  private String pronunciation;

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public String getWord_type() {
    return word_type;
  }

  public void setWord_type(String word_type) {
    this.word_type = word_type;
  }

  public ArrayList<String> getExplanations() {
    return explanations;
  }

  public void setExplanations(ArrayList<String> explanations) {
    this.explanations = explanations;
  }

  public ArrayList<String> getUsages() {
    return usages;
  }

  public void setUsages(ArrayList<String> usages) {
    this.usages = usages;
  }

  public String getPronunciation() {
    return pronunciation;
  }

  public void setPronunciation(String pronunciation) {
    this.pronunciation = pronunciation;
  }

  public Word(
      String word,
      String word_type,
      ArrayList<String> explanations,
      ArrayList<String> usages,
      String pronunciation) {
    this.word = word;
    this.word_type = word_type;
    this.explanations = explanations;
    this.usages = usages;
    this.pronunciation = pronunciation;
  }

  public Word() {
    this.word = "";
    this.word_type = "";
    this.explanations = new ArrayList<String>();
    this.usages = new ArrayList<String>();
    this.pronunciation = "";
  }

  /**
   * Compare two words spelling.
   *
   * @param thatWord input word
   * @return if same or not
   */
  public Boolean isSpelling(Word thatWord) {
    return this.word.equals(thatWord.getWord());
  }

  /**
   * compute the levenshtein distance of two word.
   * @param targetWord
   * @return the levenshtein distance of two word
   */
  public int levenshteinDistance(String targetWord) {
    String str1 = this.word;
    String str2 = targetWord;
    int m = str1.length();
    int n = str2.length();
    int[][] dn = new int[m + 1][n + 1];

    for (var i = 0; i <= m; ++i) {
      for (var j = 0; j <= n; ++j) {
        if (i == 0) dn[0][j] = j;
        else if (j == 0) dn[i][0] = i;
        else if (str1.charAt(i - 1) == str2.charAt(j - 1)) dn[i][j] = dn[i - 1][j - 1];
        else dn[i][j] = 1 + Math.min(dn[i - 1][j], Math.min(dn[i][j - 1], dn[i - 1][j - 1]));
      }
    }

    return dn[m][n];
  }
}
