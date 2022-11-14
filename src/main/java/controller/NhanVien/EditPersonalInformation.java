package controller.NhanVien;

import controller.login;
import entity.NhanVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import repository.NhanVienRepository;
import repository.NhanVienRepository_impl;
import utility.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

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

    private int idNhanVien;
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
        DateFormat df = new SimpleDateFormat();
        String dateString = df.format(nhanVien.getNgaySinh());
        ngaySinhLabel.setValue(LocalDate.parse(dateString));
        dateString = df.format(nhanVien.getNgayVaoLam());
        ngayVaoLamLabel.setValue(LocalDate.parse(dateString));
        gioiTinhComboBox.getSelectionModel().select(nhanVien.getGioiTinh());

        labelMaNV.setText(nhanVien.getMaNV());
    }
}
