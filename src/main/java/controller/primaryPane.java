package controller;

import entity.NhanVien;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import repository.NhanVienRepository;
import repository.NhanVienRepository_impl;


import java.net.URL;
import java.util.ResourceBundle;

public class primaryPane implements Initializable {
    @FXML
    private Label labelMaNV;

    @FXML
    private Label labelHoTen;

    @FXML
    private Label labelGioiTinh;

    @FXML
    private Label labelNgaySinh;

    @FXML
    private Label labelDiaChiThuongTru;

    @FXML
    private Label labelCCCD;

    @FXML
    private Label labelSoDienThoai;

    @FXML
    private Label labelEmail;

    @FXML
    private Label labelNgayVaoLam;

    @FXML
    private Label labelChucVu;

    NhanVienRepository nhanVienRepository = new NhanVienRepository_impl();


    public NhanVien nhanVien =nhanVienRepository.getInformationUser(login.idNhanVien);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelMaNV.setText(nhanVien.getMaNV());
        labelHoTen.setText(nhanVien.getHoTen());
        labelGioiTinh.setText(nhanVien.getGioiTinh());
        labelNgaySinh.setText(String.valueOf(nhanVien.getNgaySinh()));
        labelDiaChiThuongTru.setText(nhanVien.getDiaChiThuongTru());
        labelCCCD.setText(nhanVien.getCCCD());
        labelSoDienThoai.setText(nhanVien.getSoDienThoai());
        labelEmail.setText(nhanVien.getEmail());
        labelNgayVaoLam.setText(String.valueOf(nhanVien.getNgayVaoLam()));
        labelChucVu.setText(nhanVien.getChucVu());
    }


}