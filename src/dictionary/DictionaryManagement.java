package dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.io.*;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DictionaryManagement extends Dictionary {
  private final String IMPORT_FILE_PATH = "resource/data/dictionary_final.json";
  private final String url =
      "https://script.google.com/macros/s/AKfycbz_g0cKMWhvQsyk4n83kwywXZRVauZ-Pjor6LHy9ZbsGM_Szia83P4DMySl34HevphM9w/exec";

  public DictionaryManagement() {
    importFromFile();
  }
  /** Import dictionary from file. */
  public void importFromFile() {
    JSONParser parser = new JSONParser();

    try (Reader reader = new FileReader(IMPORT_FILE_PATH)) {
      JSONArray jsonArray = (JSONArray) parser.parse(reader);
      for (Object o : jsonArray) {
        Word newWord = new Word();

        JSONObject obj = (JSONObject) o;

        newWord.setWord((String) obj.get("word"));
        newWord.setWord_type((String) obj.get("word_type"));
        newWord.setWord_type((String) obj.get("pronounciation"));

        ArrayList<String> tmp1 = new ArrayList<String>();
        JSONArray arrays1 = (JSONArray) obj.get("eplanations");
        Iterator<String> iterator1 = arrays1.iterator();

        while (iterator1.hasNext()) {
          tmp1.add(iterator1.next());
        }
        newWord.setExplanations(tmp1);

        ArrayList<String> tmp2 = new ArrayList<String>();
        JSONArray arrays2 = (JSONArray) obj.get("usages");
        Iterator<String> iterator2 = arrays2.iterator();

        while (iterator2.hasNext()) {
          tmp2.add(iterator2.next());
        }
        newWord.setUsages(tmp2);

        wordList.add(newWord);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /**
   * Add new word to dictionary.
   *
   * @param addingWord adding word
   * @return result state
   */
  public String addWord(Word addingWord) {
    if (wordList.contains(addingWord)) {
      return "Existing word";
    }
    wordList.add(addingWord);
    return "Added!";
  }

  /**
   * Remove a word from dictionary.
   *
   * @param removingWord removing Word.
   * @return result state
   */
  public String removeWord(Word removingWord) {
    if (wordList.removeIf(word -> word.isSpelling(removingWord))) {
      return "Removed!";
    } else {
      return "Not found!";
    }
  }

  /**
   * Edit a word in dictionary.
   *
   * @param newWord new word
   * @return result state
   */
  public String editWord(Word newWord) {

    boolean isFound = false;

    for (int i = 0; i < wordList.size(); i++) {
      if (wordList.get(i).isSpelling(newWord)) {
        wordList.set(i, newWord);
        isFound = true;
        break;
      }
    }

    if (isFound) {
      return "Edited!";
    } else {
      return "Not found!";
    }
  }

  /**
   * Search words start with target word.
   *
   * @param searchWord target word
   * @return list of words matching to target word, sorted in lexicographic order
   */
  public ArrayList<Word> dictionarySearcher(String searchWord) {
    ArrayList<Word> resultList = new ArrayList<>();
    if (searchWord.equals("")) {
      return resultList;
    }
    searchWord = searchWord.toLowerCase();

    for (Word word : wordList) {
      if (word.getWord().startsWith(searchWord)) {
        resultList.add(word);
      }
    }

    resultList.sort(Comparator.comparing(Word::getWord));
    return resultList;
  }

  /**
   * search words which is different from target word at no more than 2 positions.
   * @param searchWord
   * @return list of words
   */
  public ArrayList<Word> dictionaryFuzzySearch(String searchWord) {
    ArrayList<Word> resultList = new ArrayList<>();
    if (searchWord.equals("")) {
      return wordList;
    }
    searchWord = searchWord.toLowerCase();
    for (Word word : wordList) {
      if (word.levenshteinDistance(searchWord) <= 2) {
        resultList.add(word);
      }
    }

    return resultList;
  }

  /**
   * text to speech
   *
   * @param text input text
   * @throws EngineException
   */
  public void textToSpeech(String text) throws EngineException {
    try {
      System.setProperty(
          "freetts.voices", "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");
      Central.registerEngineCentral("com.sun.speech.freetts" + ".jsapi.FreeTTSEngineCentral");
      Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
      synthesizer.allocate();
      synthesizer.resume();
      synthesizer.speakPlainText(text, null);
      synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
      synthesizer.deallocate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * translate a text. call translate("en", "vi", text) to translate form english to vietnamese call
   * translate("vi", "en", text) to translate form vietnamese to english
   *
   * @param langFrom
   * @param langTo
   * @param text
   * @return translated text
   * @throws IOException
   */
  public String translate(String langFrom, String langTo, String text) throws IOException {
    String urlStr =
        this.url
            + "?q="
            + URLEncoder.encode(text, "UTF-8")
            + "&target="
            + langTo
            + "&source="
            + langFrom;
    URL url = new URL(urlStr);
    StringBuilder response = new StringBuilder();
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestProperty("User-Agent", "Mozilla/5.0");
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();
    return response.toString();
  }
}
