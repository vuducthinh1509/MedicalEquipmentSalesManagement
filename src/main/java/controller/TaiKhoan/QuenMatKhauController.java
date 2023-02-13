package controller.TaiKhoan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import repository.NhanVienRepository;
import repository.NhanVienRepository_impl;
import utility.Box;

import java.io.IOException;

public class QuenMatKhauController {
    @FXML
    private TextField userText;
    @FXML
    private Button searchButton;
    @FXML
    private Button cancelButton;

    static NhanVienRepository nhanVienRepository = new NhanVienRepository_impl();
    public void searchButtonOnAction(ActionEvent event) throws IOException {
        String user = userText.getText();
        user.toLowerCase();
        Integer id = nhanVienRepository.kiemTraTaiKhoanTonTai(user);
        if(id==-1){
            Box.alertBox("Thông báo!","Tên tài khoản không tồn tại","Vui lòng thử lại sau");
        } else {
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/TaiKhoan/XacThucPane.fxml"));
            Parent xacThuc  = loader.load();
            XacThucController xacThucController = loader.getController();
            xacThucController.setUsername(user);
            xacThucController.loadDataPane();
            Stage stage1 = new Stage();
            stage1.setTitle("Quên Mật Khẩu");
            Scene scene = new Scene(xacThuc);
            stage1.setScene(scene);
            stage1.show();
        }
    }

    public void cancelButtonOnAction(ActionEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
