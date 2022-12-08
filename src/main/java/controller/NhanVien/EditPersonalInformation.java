package controller.NhanVien;

import controller.login;
import entity.NhanVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.NhanVienRepository;
import repository.NhanVienRepository_impl;
import utility.DbUtil;
import view.main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditPersonalInformation {
    @FXML
    private TextField hoTenLabel;

    @FXML
    private ComboBox gioiTinhComboBox;

    @FXML
    private DatePicker ngaySinhLabel;

    @FXML
    private TextField diaChiThuongTruLabel;

    @FXML
    private TextField CCCDLabel;

    @FXML
    private TextField soDienThoaiLabel;

    @FXML
    private TextField emailLabel;

    @FXML
    private DatePicker ngayVaoLamLabel;

    @FXML
    private TextField chucVuLabel;

    @FXML
    private Label labelMaNV;

    String gioiTinhC = null;

    private NhanVienRepository nhanVienRepo = new NhanVienRepository_impl();
    private NhanVien nhanVien = new NhanVien();

    public void edit(){
        ObservableList<String> listGioiTinh2 = FXCollections.observableArrayList("Nam","Nữ");
        gioiTinhComboBox.setItems(listGioiTinh2);
        loadData_Edit();
    }

    public void loadData_Edit(){
        nhanVien = nhanVienRepo.getInformationUser(login.idNhanVien);
        hoTenLabel.setText(nhanVien.getHoTen());
        diaChiThuongTruLabel.setText(nhanVien.getDiaChiThuongTru());
        CCCDLabel.setText(nhanVien.getCCCD());
        soDienThoaiLabel.setText(nhanVien.getSoDienThoai());
        emailLabel.setText(nhanVien.getEmail());
        chucVuLabel.setText(nhanVien.getChucVu());
        if(nhanVien.getNgaySinh() != null){
            ngaySinhLabel.setValue(LocalDate.parse(String.valueOf(nhanVien.getNgaySinh())));
        }
        if(nhanVien.getNgayVaoLam()!=null){
            ngayVaoLamLabel.setValue(LocalDate.parse(String.valueOf(nhanVien.getNgayVaoLam())));
        }
        gioiTinhComboBox.getSelectionModel().select(nhanVien.getGioiTinh());
        labelMaNV.setText(nhanVien.getMaNV());
    }

    @FXML
    private void huyMouseAction(MouseEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @SneakyThrows
    @FXML
    private void save_ChinhSua(MouseEvent event) {
        String ngaySinh = String.valueOf(ngaySinhLabel.getValue());
        String ngayVaoLam = String.valueOf(ngayVaoLamLabel.getValue());
        String diaChiThuongTru = diaChiThuongTruLabel.getText();
        String soDienThoai = soDienThoaiLabel.getText();
        String email = emailLabel.getText();

        if ( ngaySinh.isEmpty() || ngayVaoLam.isEmpty() || diaChiThuongTru.isEmpty()||soDienThoai.isEmpty() || email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Cần nhập đủ các trường dữ liệu");
            alert.showAndWait();
        } else {
            update();
            Alert alert_TC = new Alert(Alert.AlertType.INFORMATION);
            alert_TC.setHeaderText(null);
            alert_TC.setContentText("Chỉnh sửa thành công");
            alert_TC.showAndWait();
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }

    private void update() {
        nhanVienRepo.updateInformation(new NhanVien(nhanVien.getId(),nhanVien.getMaNV(),nhanVien.getHoTen(), Date.valueOf(ngaySinhLabel.getValue().toString()),
                diaChiThuongTruLabel.getText(), nhanVien.getCCCD(), soDienThoaiLabel.getText(),emailLabel.getText(),Date.valueOf(ngayVaoLamLabel.getValue().toString()),
                nhanVien.getChucVu(),gioiTinhComboBox.getSelectionModel().getSelectedItem().toString()));
    }


}
