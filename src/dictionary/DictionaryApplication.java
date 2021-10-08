package dictionary;

import dictionary.Dictionary;
import dictionary.DictionaryManagement;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DictionaryApplication extends Application {
    Stage window = new Stage();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("/Dictionary.fxml"));
        window = stage;
        Scene scene1 = new Scene(root1, 600, 400);
        window.setScene(scene1);
        Parent root2 = FXMLLoader.load(getClass().getResource("/TranslateText.fxml"));
        Scene scene2 = new Scene(root2, 600, 400);
        Button buttonTransText = (Button) scene1.lookup("#TransText");
        buttonTransText.setOnAction(actionEvent -> {
            window.setScene(scene2);
        });
        Button buttonSearch = (Button) scene2.lookup("#Search");
        buttonSearch.setOnAction(actionEvent -> {
            window.setScene(scene1);
        });

        window.show();
    }

}