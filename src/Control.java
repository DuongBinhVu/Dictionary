package dictionary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class Control {
    @FXML
    private TextField SubmitS;
    DictionaryManagement Dicmana = new DictionaryManagement();

    public void Submit (ActionEvent event) {
        String Word = SubmitS.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ArrayList<Word> resultList = Dicmana.dictionarySearcher(Word);
        if (resultList.size() == 0) {
            alert.setContentText("can't find");
        }
        else {
            alert.setContentText(resultList.get(0).getMeaning());
        }

        alert.show();
    }

}
