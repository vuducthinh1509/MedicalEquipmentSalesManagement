package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import view.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class homepage implements Initializable {
    @FXML
    private BorderPane borderPane;

    @FXML
    private Pane titlePane;

    @FXML
    private Label titleText;

    @FXML
    private Button homePageButton;

    @FXML
    private Button storageButton;

    @FXML
    private Button exportButton;

    @FXML
    private Button repairButton;

    @FXML
    private Button maintenanceButton;

    @FXML
    private Button managementButton;

    @FXML
    private Button customerButton;
    @FXML
    private Button logOutButton;

    @FXML
    private Pane mainPane;

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Pane trangChuPane =  FXMLLoader.load(main.class.getResource("primaryPane.fxml"));
        mainPane.getChildren().add(trangChuPane);
        if(login.role!=0){
            managementButton.setDisable(true);
            managementButton.setVisible(false);
        }
    }
    public void resetBackgroundButton(){
        homePageButton.setStyle("-fx-background-color: #2F333D;");
        storageButton.setStyle("-fx-background-color: #2F333D;");
        exportButton.setStyle("-fx-background-color: #2F333D;");
        storageButton.setStyle("-fx-background-color: #2F333D;");
        repairButton.setStyle("-fx-background-color: #2F333D;");
        maintenanceButton.setStyle("-fx-background-color: #2F333D;");
        managementButton.setStyle("-fx-background-color: #2F333D;");
        customerButton.setStyle("-fx-background-color: #2F333D;");
    }
    public void storageButtonOnAction(ActionEvent event) throws IOException{
        resetBackgroundButton();
        storageButton.setStyle("-fx-background-color: #757C95;");
        mainPane.getChildren().clear();
        Pane storagePane =  FXMLLoader.load(main.class.getResource("/view/KhoHang/storagePane.fxml"));
        mainPane.getChildren().add(storagePane);
    }

    public void homePageButtonOnAction(ActionEvent event) throws IOException{
        resetBackgroundButton();
        homePageButton.setStyle("-fx-background-color: #757C95;");
        mainPane.getChildren().clear();
        Pane trangChuPane =  FXMLLoader.load(main.class.getResource("primaryPane.fxml"));
        mainPane.getChildren().add(trangChuPane);
    }

    public void exportButtonButtonOnAction(ActionEvent event) throws IOException{
        resetBackgroundButton();
        exportButton.setStyle("-fx-background-color: #757C95;");
        mainPane.getChildren().clear();
        Pane trangChuPane =  FXMLLoader.load(main.class.getResource("/view/XuatHang/exportPane.fxml"));
        mainPane.getChildren().add(trangChuPane);
    }

    public void repairButtonButtonOnAction(ActionEvent event) throws IOException{
        resetBackgroundButton();
        repairButton.setStyle("-fx-background-color: #757C95;");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("repair button");
        alert.show();
//        Pane trangChuPane =  FXMLLoader.load(main.class.getResource("primaryPane.fxml"));
//        mainPane.getChildren().add(trangChuPane);
    }

    public void maintenanceButtonOnAction(ActionEvent event) throws IOException{
        resetBackgroundButton();
        maintenanceButton.setStyle("-fx-background-color: #757C95;");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("maintenance button");
        alert.show();
//        Pane trangChuPane =  FXMLLoader.load(main.class.getResource("primaryPane.fxml"));
//        mainPane.getChildren().add(trangChuPane);
    }

    public void managementButtonOnAction(ActionEvent event) throws IOException{
        resetBackgroundButton();
        managementButton.setStyle("-fx-background-color: #757C95;");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("management button");
        alert.show();
//        Pane trangChuPane =  FXMLLoader.load(main.class.getResource("primaryPane.fxml"));
//        mainPane.getChildren().add(trangChuPane);
    }

    public void customerButtonOnAction(ActionEvent event) throws IOException{
        resetBackgroundButton();
        customerButton.setStyle("-fx-background-color: #757C95;");
        mainPane.getChildren().clear();
        Pane trangChuPane =  FXMLLoader.load(main.class.getResource("/view/KhachHang/customerPane.fxml"));
        mainPane.getChildren().add(trangChuPane);
    }

    public void dangXuatButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("login_page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Đăng nhập");
        stage.setScene(scene);
        stage.setWidth(600);
        stage.setHeight(400);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth()-stage.getWidth())/2);
        stage.setY((screenBounds.getHeight()-stage.getHeight())/2);
        stage.show();
    }


}