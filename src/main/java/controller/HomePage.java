package controller;

import controller.TaiKhoan.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private Label storageLabel;
    @FXML
    private Label exportLabel;
    @FXML
    private Label repairLabel;
    @FXML
    private Label customerLabel;
    @FXML
    private Label managementLabel;
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
    @FXML
    private ImageView imageHome;
    private Image imageHomeFull = new Image(getClass().getResourceAsStream("/image/1946433.png"));
    private Image imageHomeLine = new Image(getClass().getResourceAsStream("/image/1946488.png"));
    @FXML
    private ImageView imageStorage;
    private Image imageStorageFull = new Image(getClass().getResourceAsStream("/image/2875878.png"));
    private Image imageStorageLine = new Image(getClass().getResourceAsStream("/image/2875986.png"));
    @FXML
    private ImageView imageExport;
    private Image imageExportFull = new Image(getClass().getResourceAsStream("/image/1372789.png"));
    private Image imageExportLine = new Image(getClass().getResourceAsStream("/image/1373452.png"));
    @FXML
    private ImageView imageRepair;
    private Image imageRepairFull = new Image(getClass().getResourceAsStream("/image/4624468.png"));
    private Image imageRepairLine = new Image(getClass().getResourceAsStream("/image/4624415.png"));
    @FXML
    private ImageView imageCustomer;
    private Image imageCustomerFull = new Image(getClass().getResourceAsStream("/image/1077012.png"));
    private Image imageCustomerLine = new Image(getClass().getResourceAsStream("/image/1077063.png"));
    @FXML
    private ImageView imageManagement;
    private Image imageManagementFull = new Image(getClass().getResourceAsStream("/image/681392.png"));
    private Image imageManagementLine = new Image(getClass().getResourceAsStream("/image/681443.png"));
    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Pane trangChuPane =  FXMLLoader.load(main.class.getResource("TrangChuPane.fxml"));
        mainPane.getChildren().add(trangChuPane);
        if(LoginController.role!=0){
            managementButton.setVisible(false);
        }
        homePageButton.focusedProperty();
    }
    public void removeStyleButton() throws IOException{
        homePageButton.setStyle("-fx-background-color: #fed6e3");
        exportInvoiceButton.setStyle("-fx-background-color: #fed6e3");
        storageButton.setStyle("-fx-background-color: #fed6e3");
        managementButton.setStyle("-fx-background-color: #fed6e3");
        customerButton.setStyle("-fx-background-color: #fed6e3");
        repairButton.setStyle("-fx-background-color: #fed6e3");
    }
    public void removeImage() throws IOException{
        imageHome.setImage(imageHomeLine);
        imageStorage.setImage(imageStorageLine);
        imageExport.setImage(imageExportLine);
        imageRepair.setImage(imageRepairLine);
        imageManagement.setImage(imageManagementLine);
        imageCustomer.setImage(imageCustomerLine);
    }
    public void removeTextFill() throws IOException{
        trangChuLabel.setStyle("-fx-text-fill: #000000");
        storageLabel.setStyle("-fx-text-fill: #000000");
        exportLabel.setStyle("-fx-text-fill: #000000");
        repairLabel.setStyle("-fx-text-fill: #000000");
        customerLabel.setStyle("-fx-text-fill: #000000");
        managementLabel.setStyle("-fx-text-fill: #000000");
    }
    public void storageButtonOnAction(ActionEvent event) throws IOException{
        mainPane.getChildren().clear();
        Pane storagePane =  FXMLLoader.load(main.class.getResource("/view/Kho/StoragePane.fxml"));
        mainPane.getChildren().add(storagePane);
        removeTextFill();
        storageLabel.setStyle("-fx-text-fill: linear-gradient(to right, #d6232b 0%, #015edf 100%);");
        removeStyleButton();
        storageButton.setStyle("-fx-background-color: linear-gradient(to top, #ffccff 0%, #fed6e3 100%)");
        removeImage();
        imageStorage.setImage(imageStorageFull);
    }

    public void homePageButtonOnAction(ActionEvent event) throws IOException{
        mainPane.getChildren().clear();
        Pane trangChuPane =  FXMLLoader.load(main.class.getResource("TrangChuPane.fxml"));
        mainPane.getChildren().add(trangChuPane);
        removeTextFill();
        trangChuLabel.setStyle("-fx-text-fill: linear-gradient(to right, #00c7ce 0%, #00cc99 100%);");
        removeStyleButton();
        homePageButton.setStyle("-fx-background-color: linear-gradient(to top, #ffccff 0%, #fed6e3 100%)");
        removeImage();
        imageHome.setImage(imageHomeFull);
    }

    public void exportInvoiceButtonButtonOnAction(ActionEvent event) throws IOException{
        mainPane.getChildren().clear();
        Pane exportInvoicePane =  FXMLLoader.load(main.class.getResource("/view/PhieuXuat/ExportInvoicePane.fxml"));
        mainPane.getChildren().add(exportInvoicePane);
        removeTextFill();
        exportLabel.setStyle("-fx-text-fill: linear-gradient(to right, #00c7ce 0%, #54e360 100%);");
        removeStyleButton();
        exportInvoiceButton.setStyle("-fx-background-color: linear-gradient(to top, #ffccff 0%, #fed6e3 100%)");
        removeImage();
        imageExport.setImage(imageExportFull);
    }

    public void repairButtonButtonOnAction(ActionEvent event) throws IOException{
        mainPane.getChildren().clear();
        Pane BHBTPane =  FXMLLoader.load(main.class.getResource("/view/BaoHanh/BaoHanhPane.fxml"));
        mainPane.getChildren().add(BHBTPane);
        removeTextFill();
        repairLabel.setStyle("-fx-text-fill: linear-gradient(to right, #97b1db 0%, #666999 100%)");
        removeStyleButton();
        repairButton.setStyle("-fx-background-color: linear-gradient(to top, #ffccff 0%, #fed6e3 100%)");
        removeImage();
        imageRepair.setImage(imageRepairFull);
    }

    public void managementButtonOnAction(ActionEvent event) throws IOException{
        mainPane.getChildren().clear();
        Pane trangChuPane =  FXMLLoader.load(main.class.getResource("/view/QuanLy/quanlypage.fxml"));
        mainPane.getChildren().add(trangChuPane);
        removeTextFill();
        managementLabel.setStyle("-fx-text-fill: linear-gradient(to right, #ff604f 0%, #2fd163 100%)");
        removeStyleButton();
        managementButton.setStyle("-fx-background-color: linear-gradient(to top, #ffccff 0%, #fed6e3 100%)");
        removeImage();
        imageManagement.setImage(imageManagementFull);
    }

    public void customerButtonOnAction(ActionEvent event) throws IOException{
        mainPane.getChildren().clear();
        Pane trangChuPane =  FXMLLoader.load(main.class.getResource("/view/KhachHang/CustomerPane.fxml"));
        mainPane.getChildren().add(trangChuPane);
        removeTextFill();
        customerLabel.setStyle("-fx-text-fill: linear-gradient(to right, #2682ff 0%, #ffbb85 100%)");
        removeStyleButton();
        customerButton.setStyle("-fx-background-color: linear-gradient(to top, #ffccff 0%, #fed6e3 100%)");
        removeImage();
        imageCustomer.setImage(imageCustomerFull);
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