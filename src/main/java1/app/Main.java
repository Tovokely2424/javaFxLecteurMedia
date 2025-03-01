package main.java1.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{

    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main-view.fxml"));
        BorderPane root = fxmlLoader.load();
        Scene scene = new Scene(root, 500, 300);

        primaryStage.setTitle("Lecteur avec Java");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
        System.out.println("Hello and welcome!");

    }
}