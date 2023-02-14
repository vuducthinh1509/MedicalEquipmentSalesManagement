package controller.TaiKhoan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import repository.NhanVienRepository;
import repository.NhanVienRepository_impl;
import utility.Box;
import utility.Validate;

public class DoiMatKhauController {
    @FXML
    private PasswordField oldPassword;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField newPassword1;

    @FXML
    private TextField oldPasswordText;
    @FXML
    private TextField newPasswordText;
    @FXML
    private TextField newPassword1Text;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    private boolean isShowed = false;

    @FXML
    private CheckBox checkBox;
    private String username;

    public void setUsername(String user){
        username = user;
    }
    public static NhanVienRepository nhanVienRepository = new NhanVienRepository_impl();
    public void saveButtonOnAction(ActionEvent event){
        String old;
        String newpw;
        String newpw1;
        if(isShowed){
            old = oldPasswordText.getText();
            newpw = newPasswordText.getText();
            newpw1 = newPassword1Text.getText();
        } else {
            old = oldPassword.getText();
            newpw = newPassword.getText();
            newpw1 = newPassword1.getText();
        }
        if(old.isBlank()||newpw.isBlank()||newpw1.isBlank()){
            Box.alertBox("Thông báo!","Cần nhập đầy đủ các trường","Vui lòng thử lại");
            return;
        }
        if(!newpw.equals(newpw1)){
            Box.alertBox("Thất bại!","Mật khẩu mới không trùng hợp","Vui lòng thử lại");
            return;
        } else if(newpw.equals(newpw1) && newpw.equals(old)){
            Box.alertBox("Thất bại!","Mật khẩu mới không được trùng với mật khẩu cũ","Vui lòng thử lại");
            return;
        } else if(newpw.equals(newpw1) && !old.equals(newpw )) {
            if(!Validate.validatePassword(newpw)){
                Box.alertBox("Thất bại!","Mật khẩu chỉ bao gồm 3-20 kí tự, không chứa khoảng trắng","");
                return;
            }
            if (!nhanVienRepository.dangNhap1(username, old)) {
                Box.alertBox("Thất bại!", "Mật khẩu cũ không chính xác", "Vui lòng thử lại");
                return;
            } else {
                nhanVienRepository.doiMatKhau(username, newpw);
                Box.alertBox("Thành công!", "Đổi mật khẩu thành công", "");
                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
        }
        }

    public void cancelButtonOnAction(ActionEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void changeVisibility(ActionEvent event){
        if(checkBox.isSelected()) {
            isShowed = true;
            oldPasswordText.setText(oldPassword.getText());
            oldPasswordText.setVisible(true);
            oldPassword.setVisible(false);
            newPasswordText.setText(newPassword.getText());
            newPasswordText.setVisible(true);
            newPassword.setVisible(false);
            newPassword1Text.setText(newPassword1.getText());
            newPassword1Text.setVisible(true);
            newPassword1.setVisible(false);
            return;
        }
        isShowed = false;
        oldPassword.setText(oldPasswordText.getText());
        oldPassword.setVisible(true);
        oldPasswordText.setVisible(false);
        newPassword.setText(newPasswordText.getText());
        newPassword.setVisible(true);
        newPasswordText.setVisible(false);
        newPassword1.setText(newPassword1Text.getText());
        newPassword1.setVisible(true);
        newPassword1Text.setVisible(false);
    }
}
