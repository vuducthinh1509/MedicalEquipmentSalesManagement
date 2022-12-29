package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;

public class main_test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        TextInputDialog td = new TextInputDialog();
        td.setTitle("input");
        td.setHeaderText("nhap so luong");
        String quantity = "";
        td.showAndWait();
        quantity = td.getEditor().getText();
//        Integer i = Integer.parseInt(quantity);
            System.out.println(quantity);
//            System.out.println(i + 1);
    }
}