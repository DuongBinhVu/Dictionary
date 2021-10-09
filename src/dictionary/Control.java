package dictionary;

import dictionary.DictionaryManagement;
import dictionary.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Control {
    DictionaryManagement Dicmana = new DictionaryManagement();
    @FXML
    private TextField SubmitS;
    @FXML
    private Button TransText;
    @FXML
    private ListView<String> ListWord;
    @FXML
    private Text Word;
    @FXML
    private Text sound;
    @FXML
    private TextArea meaning;

    public void search(KeyEvent event) {
        String Word = SubmitS.getText();
        ArrayList<dictionary.Word> resultList = Dicmana.dictionarySearcher(Word);
        ArrayList<String> wordsFound = new ArrayList<String>();
        for (int i = 0; i < resultList.size(); i++) {
            wordsFound.add(resultList.get(i).getWord());
        }
        ObservableList<String> items = FXCollections.observableArrayList(wordsFound);
        System.out.print(wordsFound.size());
        this.ListWord.setItems(items);
    }
    public void Submit(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = stage.getScene();
        String Word = SubmitS.getText();
        ArrayList<dictionary.Word> resultList = Dicmana.dictionarySearcher(Word);
        Text TextWord = (Text) scene.lookup("#Word");
        Text WordSound = (Text) scene.lookup("#sound");
        TextArea Meaning = (TextArea) scene.lookup("#meaning");
        if (resultList.size() == 0 || !resultList.get(0).getWord().equals(Word)) {
            TextWord.setText("No Result");
        } else {
            TextWord.setText(Word);
            ArrayList<String> explanations = resultList.get(0).getExplanations();
            WordSound.setText(resultList.get(0).getPronunciation());
            ArrayList<String> tmp;
            String res = resultList.get(0).getWord_type();
            res += "- " + resultList.get(0).getWord() + ":\n";
            tmp = resultList.get(0).getExplanations();
            for (int j = 0; j < tmp.size(); j++) {
                res += "  " + tmp.get(j) + "\n";
            }
            String t = "";
            tmp = resultList.get(0).getUsages();
            for (int j = 0; j < tmp.size(); j++) {
                t += "  " + tmp.get(j) + "\n";
            }
            if (!t.equals("")) {
                res += "Ví dụ: \n" + t;
            }
            res += "\n";
            Meaning.setText(res);
        }
        stage.setScene(scene);
        ArrayList<String> wordsFound = new ArrayList<String>();
        for (int i = 0; i < resultList.size(); i++) {
            wordsFound.add(resultList.get(i).getWord());
        }
        ObservableList<String> items = FXCollections.observableArrayList(wordsFound);
        System.out.print(wordsFound.size());
        this.ListWord.setItems(items);
    }


}
