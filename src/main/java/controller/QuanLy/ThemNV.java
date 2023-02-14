package controller.QuanLy;

import entity.NhanVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.NhanVienRepository;
import repository.NhanVienRepository_impl;
import utility.Validate;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ThemNV implements Initializable {

    public TextField hoTenLabel;
    public TextField SDTLabel;
    public TextField emailLabel;
    public TextField diaChiThuongTruLabel;
    public TextField CCCDLabel;
    public ComboBox chucVuComboBox;
    public DatePicker ngaySinhLabel;
    public Button saveButton;
    public ComboBox gioiTinhComboBox;
    public DatePicker ngayVaoLamLabel;
    public TextField maNVLabel;
    public Button cancelButton;
    public TextField usernameLabel;
    public TextField passwordLabel;
    public TextField roleLabel ;
    public ComboBox cauHoiComboBox;
    public TextField cauTraLoiLabel;


    NhanVien nhanVien2 = new NhanVien();
    NhanVienRepository nhanVienRepo2 = new NhanVienRepository_impl();
    Integer id = nhanVienRepo2.getNextAutoIncrement();
    ObservableList<String>listCauHoi= FXCollections.observableArrayList("Nhóm KTPMUD của bạn tên gì?",
            "Thầy hướng dẫn bài tập lớn KTPMUD là ai?",
            "Mã học phần KTPMUD?" ,
            "Nhóm 5BROS có mấy thành viên?" ,
            "Chủ đề ứng dụng của nhóm 5BROS liên quan đến?");

    ObservableList<String> Listchucvu = FXCollections.observableArrayList("Quản Lý","Nhân viên bán hàng","Nhân viên kho");

    ObservableList<String> listGioiTinh2 = FXCollections.observableArrayList("Nam","Nữ");
    @SneakyThrows
    @FXML
    public void saveAction(MouseEvent event) {
        String hoTen = hoTenLabel.getText();
        String soDienThoai = SDTLabel.getText();
        String email = emailLabel.getText();
        String diaChiThuongTru = diaChiThuongTruLabel.getText();
        String CCCD = CCCDLabel.getText();
        String chucVu = null;
        String gioiTinh = null;
        if(chucVuComboBox.getValue()!=null){
           chucVu = chucVuComboBox.getValue().toString();
        }
        String maNV = "NV" + id;
        String ngaySinh = null;
        String ngayVaoLam = null;
        if(ngaySinhLabel.getValue()!=null){
            ngaySinh = ngaySinhLabel.getValue().toString();
        }
        if(ngayVaoLamLabel.getValue()!=null){
            ngayVaoLam = ngayVaoLamLabel.getValue().toString();
        }
        if (gioiTinhComboBox.getValue()!= null){
         gioiTinh = gioiTinhComboBox.getValue().toString();}
        String cauHoi = null;
        if(cauHoiComboBox.getValue()!= null){
         cauHoi = cauHoiComboBox.getValue().toString();}
        String cauTraLoi = cauTraLoiLabel.getText();
        String username = usernameLabel.getText();
        String password = passwordLabel.getText();
        Integer role = 1;

        if (username.isEmpty()|| password.isEmpty()||hoTen.isEmpty()|| ngaySinh==null||diaChiThuongTru.isEmpty()||CCCD.isEmpty()||soDienThoai.isEmpty()||email.isEmpty()||ngayVaoLam==null||chucVu == null||gioiTinh==null||cauHoi==null||cauTraLoi.isEmpty()){
            Alert alert_TC = new Alert(Alert.AlertType.INFORMATION);
            alert_TC.setHeaderText(null);
            alert_TC.setContentText(" Mời nhập đủ thông tin ");
            alert_TC.showAndWait();
        }
         else if (!Validate.validatePassword(password)){
            Alert alert_TC = new Alert(Alert.AlertType.INFORMATION);
            alert_TC.setHeaderText(null);
            alert_TC.setContentText("Mật khẩu không hợp lệ");
            alert_TC.showAndWait();
            //final Node source = (Node) event.getSource();
            //final Stage stage = (Stage) source.getScene().getWindow();
            //stage.close();
        }
        else if (!Validate.validatePhoneVN(soDienThoai)){
            Alert alert_TC = new Alert(Alert.AlertType.INFORMATION);
            alert_TC.setHeaderText(null);
            alert_TC.setContentText("Số điện thoại không hợp lệ ");
            alert_TC.showAndWait();
            //final Node source = (Node) event.getSource();
           // final Stage stage = (Stage) source.getScene().getWindow();
            //stage.close();
        }
        else if (nhanVienRepo2.kiemTraTaiKhoanTonTai(username) != -1  ){
            Alert alert_TC = new Alert(Alert.AlertType.INFORMATION);
            alert_TC.setHeaderText(null);
            alert_TC.setContentText(" Tài khoản đã tồn tại ");
            alert_TC.showAndWait();
        }

        else {
        if(!chucVu.isBlank()){
                if(chucVu.equals("Quản lý")){
                    role = 0;
                }
        }
        NhanVien nhanVien =  new NhanVien(username, password,role, -1, maNV,hoTen, Date.valueOf(ngaySinh),diaChiThuongTru,CCCD,soDienThoai,email,Date.valueOf(ngayVaoLam),chucVu,gioiTinh,cauHoi,cauTraLoi);
        nhanVienRepo2.themNV(nhanVien);
        Alert alert_TC = new Alert(Alert.AlertType.INFORMATION);
        alert_TC.setHeaderText(null);
        alert_TC.setContentText("Thêm thành công");
        alert_TC.showAndWait();
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }}
    @FXML
    public void cancelAction(MouseEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chucVuComboBox.setItems(Listchucvu);
        gioiTinhComboBox.setItems(listGioiTinh2);
        cauHoiComboBox.setItems(listCauHoi);
    }
}

