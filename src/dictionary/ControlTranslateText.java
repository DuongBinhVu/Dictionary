package dictionary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private Label translating;

    public ControlTranslateText() throws EngineException {
    }

    /*public void LisTrans(MouseEvent event) throws EngineException {
        String TextTr = TextTrans.getText();
        Dicmana.textToSpeech(TextTr);
    }
    public void LisText(MouseEvent event) throws EngineException {
        String TextType = Text.getText();
        Dicmana.textToSpeech(TextType);
    }*/

    public void EnToVi(MouseEvent event) throws IOException {
        from = "en";
        to = "vi";
        String TextType = Text.getText();
        this.translating.toFront();
        String translatedText = Dicmana.translate(from, to, TextType);
        this.TextTrans.setText(translatedText);
        this.translating.toBack();
    }
    public void ViToEn(MouseEvent event) throws IOException {
        from = "vi";
        to = "en";
        String TextType = Text.getText();
        this.translating.toFront();
        String translatedText = Dicmana.translate(from, to, TextType);
        this.TextTrans.setText(translatedText);
        this.translating.toBack();
    }
}