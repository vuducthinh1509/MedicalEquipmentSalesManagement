package controller;

import controller.TaiKhoan.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import view.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HomePage implements Initializable {

    @FXML
    private Label trangChuLabel;
    @FXML
    private Button homePageButton;

    @FXML
    private Button storageButton;

    @FXML
    private Button exportInvoiceButton;

    @FXML
    private Button repairButton;
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
        Pane trangChuPane =  FXMLLoader.load(main.class.getResource("TrangChuPane.fxml"));
        mainPane.getChildren().add(trangChuPane);
        if(LoginController.role!=0){
            managementButton.setDisable(true);
            managementButton.setVisible(false);
        }
        homePageButton.focusedProperty();
    }
    public void storageButtonOnAction(ActionEvent event) throws IOException{
        mainPane.getChildren().clear();
        Pane storagePane =  FXMLLoader.load(main.class.getResource("/view/Kho/StoragePane.fxml"));
        mainPane.getChildren().add(storagePane);
        trangChuLabel.setStyle("");
    }

    public void homePageButtonOnAction(ActionEvent event) throws IOException{
        mainPane.getChildren().clear();
        Pane trangChuPane =  FXMLLoader.load(main.class.getResource("TrangChuPane.fxml"));
        mainPane.getChildren().add(trangChuPane);
    }

    public void exportInvoiceButtonButtonOnAction(ActionEvent event) throws IOException{
        mainPane.getChildren().clear();
        Pane exportInvoicePane =  FXMLLoader.load(main.class.getResource("/view/PhieuXuat/ExportInvoicePane.fxml"));
        mainPane.getChildren().add(exportInvoicePane);
    }

    public void repairButtonButtonOnAction(ActionEvent event) throws IOException{
        mainPane.getChildren().clear();
        Pane BHBTPane =  FXMLLoader.load(main.class.getResource("/view/BaoHanh/BaoHanhPane.fxml"));
        mainPane.getChildren().add(BHBTPane);
    }

    public void managementButtonOnAction(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("management button");
        alert.setHeaderText("Tính năng đang được xây dựng");
        alert.show();
//        Pane trangChuPane =  FXMLLoader.load(main.class.getResource("TrangChuPane.fxml"));
//        mainPane.getChildren().add(trangChuPane);
    }

    public void customerButtonOnAction(ActionEvent event) throws IOException{
        mainPane.getChildren().clear();
        Pane trangChuPane =  FXMLLoader.load(main.class.getResource("/view/KhachHang/CustomerPane.fxml"));
        mainPane.getChildren().add(trangChuPane);
    }

    public void dangXuatButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("TaiKhoan/LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Đăng nhập");
        stage.setScene(scene);
        stage.setHeight(600);
        stage.setWidth(880);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth()-stage.getWidth())/2);
        stage.setY((screenBounds.getHeight()-stage.getHeight())/2);
        stage.show();
    }
}