package dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Comparator;
import java.util.Locale;
import javax.speech.Engine;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.io.*;
import java.util.ArrayList;

public class DictionaryManagement extends Dictionary {
    private final String IMPORT_FILE_PATH = "resource/data/dictionaries.txt";
    private final String EXPORT_FILE_PATH = "resource/data/outfile.txt";
    private final String url =
            "https://script.google.com/macros/s/AKfycbz_g0cKMWhvQsyk4n83kwywXZRVauZ-Pjor6LHy9ZbsGM_Szia83P4DMySl34HevphM9w/exec";

    /** Import dictionary from file. */
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

    /** Export dictionary to file. */
    public void exportToFile() {
        File file = new File(EXPORT_FILE_PATH);

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            for (Word word : wordList) {
                bw.write(word.getTrueForm() + "\t" + word.getMeaning() + "\n");
            }

            bw.close();

            System.out.println("Exported!");
            System.out.println("File path: " + EXPORT_FILE_PATH);
        } catch (IOException e) {
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
     * @param removingWord removing Word, its meaning is empty in case of removing all homographs.
     * @return result state
     */
    public String removeWord(Word removingWord) {

        if (removingWord.isMeaning("")) {
            if (wordList.removeIf(word -> word.isSpelling(removingWord))) {
                return "Removed all homographs!";
            } else {
                return "Not found!";
            }
        } else {
            if (wordList.removeIf(word -> word.isEquals(removingWord))) {
                return "Removed!";
            } else {
                return "Not found!";
            }
        }
    }

    /**
     * Edit a word in dictionary.
     *
     * @param editingWord old word
     * @param newWord new word
     * @return result state
     */
    public String editWord(Word editingWord, Word newWord) {

        boolean isFound = false;

        for (int i = 0; i < wordList.size(); i++) {
            if (wordList.get(i).isEquals(editingWord)) {
                wordList.set(i, newWord);

                isFound = true;
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
        searchWord = searchWord.toLowerCase();

        ArrayList<Word> resultList = new ArrayList<>();

        for (Word word : wordList) {
            if (word.getSpelling().startsWith(searchWord)) {
                resultList.add(word);
            }
        }

        resultList.sort(Comparator.comparing(Word::getSpelling));
        return resultList;
    }

    /**
     * text to speech
     * @param text input text
     * @throws EngineException
     */
    public void textToSpeech(String text) throws EngineException {
        try
        {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");
            Central.registerEngineCentral("com.sun.speech.freetts" + ".jsapi.FreeTTSEngineCentral");
            Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
            synthesizer.allocate();
            synthesizer.resume();
            synthesizer.speakPlainText(text, null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
            synthesizer.deallocate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * translate a text.
     * call translate("en", "vi", text) to translate form english to vietnamese
     * call translate("vi", "en", text) to translate form vietnamese to english
     * @param langFrom
     * @param langTo
     * @param text
     * @return translated text
     * @throws IOException
     */
    public String translate(String langFrom, String langTo, String text) throws IOException {
        String urlStr = this.url +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
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
