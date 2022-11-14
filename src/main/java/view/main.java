package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("login_page.fxml"));
        Scene scene =new Scene(fxmlLoader.load());
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.setWidth(600);
        primaryStage.setHeight(400);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}