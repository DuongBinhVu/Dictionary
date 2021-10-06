package dictionary;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DictionaryApplication extends Application {
  Button button = new Button();
  Stage window = new Stage();
  Scene scene1, scene2;

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("Dictionary.fxml"));
    window = stage;
    window.setScene(new Scene(root, 600, 400));
        /*FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        stage.setTitle("Hello!");
        window = stage;
        Button button1 = new Button("go to text1");
        StackPane layout1 = new StackPane();
        layout1.getChildren().add(button1);
        scene1 = new Scene(layout1, 620, 440);
        window.setScene(scene1);
        Button button2 = new Button("go back");
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2, 620, 440);
        button1.setOnAction(actionEvent -> {
            window.setScene(scene2);
        });
        button2.setOnAction(actionEvent -> {
            window.setScene(scene1);
        });*/
    window.show();
  }


}