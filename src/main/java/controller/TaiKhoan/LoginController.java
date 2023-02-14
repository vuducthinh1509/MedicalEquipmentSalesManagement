package controller.TaiKhoan;

import entity.NhanVien;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import repository.NhanVienRepository;
import repository.NhanVienRepository_impl;
import utility.Box;
import utility.Validate;
import view.main;

import java.io.IOException;

public class LoginController {

    public static int idNhanVien = -1;
    public static int role =-1;

    public static String _username;
    @FXML
    private Label loginLabel;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private CheckBox checkBox;

    @FXML
    private TextField passwordText;

    @FXML
    private Button buttonLogin;


    static NhanVienRepository nhanVienRepo = new NhanVienRepository_impl();

    public void dangNhapButtonOnAction(ActionEvent event) throws IOException {
        String pw;
        if(checkBox.isSelected()){
            pw = passwordText.getText();
        } else {
            pw = password.getText();
        }
        if (username.getText().isBlank() == false && pw.isBlank() == false) {
            if(!Validate.validatePassword(pw)){
                loginLabel.setText("Mật khẩu chỉ bao gồm 3-20 kí tự, không chứa khoảng trắng");
                return;
            }
            if (nhanVienRepo.dangNhap1(username.getText(), pw)) {
                NhanVien nhanVien = nhanVienRepo.dangNhap(username.getText(), pw);
                idNhanVien = nhanVien.getId();
                role = nhanVien.getRole();
                _username = nhanVien.getUsername();
                Stage stage = (Stage) buttonLogin.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("HomePage.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("Quản Lý Bán Hàng Thiết Bị Y Tế");
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/image/logo3.png")));
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

    public void changeVisibility(ActionEvent event){
        if(checkBox.isSelected()){
            passwordText.setText(password.getText());
            passwordText.setVisible(true);
            password.setVisible(false);
            return;
        }
        password.setText(passwordText.getText());
        password.setVisible(true);
        passwordText.setVisible(false);
    }
}