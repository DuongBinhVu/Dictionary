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
    private DictionaryManagement Dicmana = new DictionaryManagement();
    private String from = "vi";
    private String to = "en";
    @FXML
    private Button Search;
    @FXML
    private TextField Text;
    @FXML
    private TextArea TextTrans;
    @FXML
    private Button VTE;
    @FXML
    private Button ETV;

    public ControlTranslateText() throws EngineException {
    }

    public void Trans(ActionEvent event) throws IOException {
        String TextType = Text.getText();
        this.TextTrans.setText(Dicmana.translate(from, to, TextType));
    }
    public void LisTrans(ActionEvent event) throws EngineException {
        String TextTr = TextTrans.getText();
        Dicmana.textToSpeech(TextTr);
    }
    public void LisText(ActionEvent event) throws EngineException {
        String TextType = Text.getText();
        Dicmana.textToSpeech(TextType);
    }
    public void EnToVi(ActionEvent event) {
        from = "en";
        to = "vi";
        ETV.setUnderline(true);
        VTE.setUnderline(false);
    }
    public void ViToEn(ActionEvent event) {
        from = "vi";
        to = "en";
        VTE.setUnderline(true);
        ETV.setUnderline(false);
    }
}