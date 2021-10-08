package dictionary;

import dictionary.DictionaryManagement;
import dictionary.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Control {
    @FXML
    private TextField SubmitS;
    DictionaryManagement Dicmana = new DictionaryManagement();

    public void Submit (ActionEvent event) {
        String Word = SubmitS.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ArrayList<dictionary.Word> resultList = Dicmana.dictionarySearcher(Word);
        if (resultList.size() == 0) {
            alert.setContentText("can't find");
        }
        else {
            ArrayList<String> explanations = resultList.get(0).getExplanations();
            String res = "";
            for (int i = 0; i < explanations.size(); i++) {
                res += explanations.get(i);
                res += "\n";
            }
            alert.setContentText(res);
        }

        alert.show();
    }


}
