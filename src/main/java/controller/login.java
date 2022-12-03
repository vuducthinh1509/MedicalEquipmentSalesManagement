package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import repository.NguoiDungRepository;
import repository.NguoiDungRepository_impl;
import view.main;

import java.io.IOException;

public class login {

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
    public NguoiDungRepository NguoiDungRepo = new NguoiDungRepository_impl();

    public void dangNhapButtonOnAction(ActionEvent event) throws IOException {
        if (username.getText().isBlank() == false && password.getText().isBlank() == false) {
            if (NguoiDungRepo.dangnhap(username.getText(), password.getText()).getId() != null) {
                idNhanVien = NguoiDungRepo.dangnhap(username.getText(), password.getText()).getId();
                role = NguoiDungRepo.dangnhap(username.getText(), password.getText()).getRole();
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("/view/home_page.fxml"));
//                Parent chinhSuaNKView = loader.load();
//                primaryPane controller = loader.getController();
                Stage stage = (Stage) buttonLogin.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("home_page.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("Quản lý bán hàng thiết bị y tế");
                stage.setScene(scene);
                stage.setWidth(1280);
                stage.setHeight(760);
                stage.setResizable(true);
                stage.centerOnScreen();
                stage.show();
            } else {
                loginLabel.setText("Sai tài khoản hoặc mật khẩu");
            }
        } else {
            loginLabel.setText("Vui lòng nhập đầy đủ các trường");
        }
    }
}