package dictionary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.speech.EngineException;
import java.io.IOException;

public class ControlTranslateText {
    @FXML
    private Button Search;
    private DictionaryManagement Dicmana = new DictionaryManagement();
    @FXML
    private TextField Text;
    @FXML
    private TextArea TextTrans;

    public ControlTranslateText() throws EngineException {
    }

    public void Trans(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = stage.getScene();
        String TextType = Text.getText();
        TextArea Meaning = (TextArea) scene.lookup("#TextTrans");
        Meaning.setText(Dicmana.translate("vi", "en", TextType));
    }
    public void LisTrans(ActionEvent event) throws EngineException {
        String TextTr = TextTrans.getText();
        Dicmana.textToSpeech(TextTr);
    }
    public void LisText(ActionEvent event) throws EngineException {
        String TextType = Text.getText();
        Dicmana.textToSpeech(TextType);
    }
}
