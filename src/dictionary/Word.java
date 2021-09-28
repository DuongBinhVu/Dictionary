package dictionary;

public class Word {
    /**
     * New word.
     */
    private String wordTarget;

    /**
     * Word's explanation.
     */
    private String wordExplain;

    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }

    public String getWordTarget() {
        return this.wordTarget;
    }

    public String getWordExplain() {
        return this.wordExplain;
    }

    /**
     * Constructor.
     */
    public Word() {
        this.wordTarget = "";
        this.wordExplain = "";
    }

    /**
     * Constructor with parameters.
     *
     * @param wordTarget  new word
     * @param wordExplain word's explanation
     */
    public Word(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
    }
}
