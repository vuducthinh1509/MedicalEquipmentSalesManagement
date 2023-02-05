package controller.KhachHang;

import controller.KhoHang.XuatHang.InvoiceController;
import entity.KhachHang;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import repository.KhachHangRepository;
import repository.KhachHangRepository_impl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {
    @FXML
    private TextField nameCtmLabel;
    @FXML
    private TextField phoneCtmLabel;
    @FXML
    private TextField addressCtmLabel;

    @FXML
    private TextField idCtmLabel;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    static KhachHangRepository khachHangRepo = new KhachHangRepository_impl();

    public KhachHang newKhachHang=new KhachHang(1,"1","0968968032","1");

    public void cancelButtonOnClicked(MouseEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void saveButtonOnClicked(MouseEvent event) throws IOException {
        String name = nameCtmLabel.getText();
        String phone = phoneCtmLabel.getText();
        String address = addressCtmLabel.getText();
        if(name.isEmpty()||phone.isEmpty()||address.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo!");
            alert.setHeaderText("Cần nhập đầy đủ các trường");
            alert.setContentText("Vui lòng nhập lại");
            alert.showAndWait();
        }
        else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/Storage/XuatHang/HoaDonPane.fxml"));
            Parent invoicePane = loader.load();
            InvoiceController invoiceController = loader.getController();
            invoiceController.setCustomer(new KhachHang(khachHangRepo.getCountCustomer(),name,phone,address));
            invoiceController.loadDataCustomerPane();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Thêm thành công");
            alert.showAndWait();
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataPane();
    }

    public void loadDataPane(){
        idCtmLabel.setText(String.valueOf(khachHangRepo.getCountCustomer()));
    }
}
