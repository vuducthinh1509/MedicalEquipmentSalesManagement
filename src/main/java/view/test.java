package view;

import javafx.application.Application;
import javafx.stage.Stage;
import utility.Validate;

public class test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        String password = "abc";
        System.out.println(Validate.validatePassword(password));
    }
}
