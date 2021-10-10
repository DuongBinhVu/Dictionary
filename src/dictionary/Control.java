package dictionary;

import dictionary.DictionaryManagement;
import dictionary.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.speech.EngineException;
import java.io.IOException;
import java.util.ArrayList;

public class Control {
    private DictionaryManagement Dicmana = new DictionaryManagement();
    private ArrayList<dictionary.Word> resultList = new ArrayList<dictionary.Word>();
    private Word WordNow = new Word();
    private String choosingWord = new String();
    @FXML
    private TextField SubmitS;
    @FXML
    private Button TransText;
    @FXML
    private ListView<String> ListWord;
    @FXML
    private Text TextWord;
    @FXML
    private Text sound;
    @FXML
    private TextArea meaning;

    public Control() throws EngineException {
    }

    public void search(KeyEvent event) {
        KeyCode keyCode = event.getCode();
        if (keyCode == KeyCode.ENTER) {
            this.suggest(this.SubmitS.getText());
        } else if (keyCode.isLetterKey()) {
            this.suggest(this.SubmitS.getText().concat(keyCode.toString().trim()));
        }
    }

    public void suggest(String WordSearch) {
        this.resultList = this.Dicmana.dictionarySearcher(WordSearch);
        ArrayList<String> wordsFound = new ArrayList<String>();
        for (int i = 0; i < this.resultList.size(); i++) {
            wordsFound.add(this.resultList.get(i).getWord());
        }
        ObservableList<String> items = FXCollections.observableArrayList(wordsFound);
        this.ListWord.setItems(items);
    }

    public void ShowWord(Word WordS) {
        this.WordNow = WordS;
        this.TextWord.setText(WordS.getWord());
        this.sound.setText(WordS.getPronunciation());
        ArrayList<String> tmp;
        String res = WordS.getWord_type() + ":\n";
        tmp = WordS.getExplanations();
        for (int j = 0; j < tmp.size(); j++) {
            res += " - " + tmp.get(j) + "\n";
        }
        String t = "";
        tmp = WordS.getUsages();
        for (int j = 0; j < tmp.size(); j++) {
            t += " - " + tmp.get(j) + "\n";
        }
        if (!t.equals("")) {
            res += "Ví dụ: \n" + t;
        }
        res += "\n";
        this.meaning.setText(res);
    }
    public void Submit(ActionEvent event) {
        String Word = SubmitS.getText();
        if (this.resultList.size() == 0) {
            this.TextWord.setText("No Result");
            return;
        }
        else {
            if (!this.resultList.get(0).getWord().equals(Word)) {
                this.TextWord.setText("No Result");
                return;
            }
            this.TextWord.setText(this.resultList.get(0).getWord());
            ShowWord(this.resultList.get(0));
        }

    }
    public void ListenWord(MouseEvent event) throws EngineException {
        Dicmana.textToSpeech(this.WordNow.getWord());
    }
    public void WordClicked(MouseEvent event) {
        if (event.getButton() == MouseButton.SECONDARY) {
            this.choosingWord = this.ListWord.getSelectionModel().getSelectedItem();
            this.choosingWord = this.choosingWord.toLowerCase();
        }
    }
    public void ButtonShow(ActionEvent event) {
        for (int i = 0; i < resultList.size(); i++) {
            if (resultList.get(i).getWord().equals(this.choosingWord)) {
                this.ShowWord(resultList.get(i));
                return;
            }
        }
    }
    public void Erase(ActionEvent event) {
        for (int i = 0; i < resultList.size(); i++) {
            if (resultList.get(i).getWord().equals(this.choosingWord)) {
                this.Dicmana.removeWord(resultList.get(i));
                return;
            }
        }
    }

}
