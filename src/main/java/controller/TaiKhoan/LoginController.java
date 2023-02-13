package controller.TaiKhoan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import repository.NhanVienRepository;
import repository.NhanVienRepository_impl;
import view.main;

import java.io.IOException;

public class LoginController {

    public static int idNhanVien = -1;
    public static int role =-1;
    @FXML
    private Label loginLabel;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button buttonLogin;

    @FXML
    private Button forgotButton;

    static NhanVienRepository nhanVienRepo = new NhanVienRepository_impl();

    public void dangNhapButtonOnAction(ActionEvent event) throws IOException {
        if (username.getText().isBlank() == false && password.getText().isBlank() == false) {
            if (nhanVienRepo.dangNhap(username.getText(), password.getText()).getId() != null) {
                idNhanVien = nhanVienRepo.dangNhap(username.getText(), password.getText()).getId();
                role = nhanVienRepo.dangNhap(username.getText(), password.getText()).getRole();
                Stage stage = (Stage) buttonLogin.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("HomePage.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("Quản Lý Bán Hàng Thiết Bị Y Tế");
                stage.setScene(scene);
                stage.setWidth(1366);
                stage.setHeight(780);
                stage.centerOnScreen();
                stage.setResizable(true);
                stage.show();
            } else {
                loginLabel.setText("Sai tài khoản hoặc mật khẩu");
            }
        } else {
            loginLabel.setText("Vui lòng nhập đầy đủ các trường");
        }
    }

    public void forgotButtonOnAction(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TaiKhoan/QuenMatKhauPane.fxml"));
        Parent quenMatKhau  = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Quên Mật Khẩu");
        Scene scene = new Scene(quenMatKhau);
        stage.setScene(scene);
        stage.show();
    }
}