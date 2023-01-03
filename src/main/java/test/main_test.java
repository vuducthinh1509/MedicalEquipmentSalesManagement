package test;

import javafx.application.Application;
import javafx.stage.Stage;
import utility.Box;

import java.io.IOException;

public class main_test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Box.alertBox("loi","loi","abc");
    }
}