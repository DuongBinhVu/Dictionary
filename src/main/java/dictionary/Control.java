package dictionary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class Control extends DictionaryManagement {
    @FXML
    private TextField SubmitS;

    public void Submit (ActionEvent event) {
        String Word = SubmitS.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ArrayList<Word> resultList = this.dictionarySearcher(Word);
        alert.setContentText(resultList.get(0).getTrueForm());
        alert.show();
    }

}
