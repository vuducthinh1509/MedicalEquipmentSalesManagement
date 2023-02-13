package controller.TaiKhoan;

import entity.NhanVien;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import org.w3c.dom.events.MouseEvent;
import repository.NhanVienRepository;
import repository.NhanVienRepository_impl;
import utility.Box;

import java.net.URL;
import java.util.ResourceBundle;

public class XacThucController {
    @FXML
    private TextField cauTraLoiTextField;
    @FXML
    private Label cauHoiLabel;
    @FXML
    private Button xacThucButton;
    @FXML
    private Label usernameLabel;

    private String username = "";

    static NhanVien nhanVien = new NhanVien();

    static NhanVienRepository nhanVienRepository = new NhanVienRepository_impl();

    public void setUsername(String user){
        username = user;
    }
    public void loadDataPane(){
        nhanVien = nhanVienRepository.xacThucTaiKhoan(username);
        cauHoiLabel.setText(nhanVien.getCauHoi());
        usernameLabel.setText(nhanVien.getUsername());
    }

    public void xacThucButtonOnAction(ActionEvent event){
        String ctl = cauTraLoiTextField.getText();
        if(ctl.equals(nhanVien.getCauTraLoi())){
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            Box.alertBox("Thành công!","Mật khẩu của bạn là: " + nhanVien.getPassword(),"" );
        } else {
            Box.alertBox("Thất bại","Câu trả lời chưa chính xác","Vui lòng thử lại sau");
        }
    }

    public void cancelButtonOnAction(ActionEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
