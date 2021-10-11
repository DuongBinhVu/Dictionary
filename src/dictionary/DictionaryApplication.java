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
        window = stage;

        FXMLLoader loaderScene1 = new FXMLLoader(getClass().getResource("/Dictionary.fxml"));
        Scene scene1 = new Scene(loaderScene1.load(), 626, 486);

        FXMLLoader loaderScene2 = new FXMLLoader(getClass().getResource("/TranslateText.fxml"));
        Scene scene2 = new Scene(loaderScene2.load(), 626, 486);

        window.setScene(scene1);

        Button buttonTransText = (Button) scene1.lookup("#TransText");
        buttonTransText.setOnAction(actionEvent -> {
            window.setScene(scene2);
        });

        Button buttonSearch = (Button) scene2.lookup("#Search");
        buttonSearch.setOnAction(actionEvent -> {
            window.setScene(scene1);
        });

        Control temporaryControllerScene1 = loaderScene1.getController();

        temporaryControllerScene1.initResultList();

        window.show();
        return;
    }

}
