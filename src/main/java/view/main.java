package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("TaiKhoan/LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Đăng nhập");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/key.png")));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}