package controller.QuanLy;

import entity.NhanVien;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import repository.NhanVienRepository;
import repository.NhanVienRepository_impl;

import java.net.URL;
import java.util.ResourceBundle;

public class ThongTinNV {

    @FXML
    Label labelHoTen;
    @FXML
    Label labelMaNV;
    @FXML
    Label labelGioiTinh;
    @FXML
    Label labelNgaySinh;
    @FXML
    Label labelCCCD;
    @FXML
    Label labelDiaChiThuongTru;
    @FXML
    Label labelSoDienThoai;
    @FXML
    Label labelEmail;
    @FXML
    Label labelNgayVaoLam;
    @FXML
    Label labelChucVu;

    @FXML
    Label labelUsername;

    @FXML
    Label labelPassword;


    public void setNhanVien(NhanVien nhanVien){
        clean();
        getInformationUser(nhanVien.getId());
    }


    static NhanVienRepository nhanVienRepo = new NhanVienRepository_impl();


    private void getInformationUser(Integer id) {
        NhanVien nhanVien = new NhanVien();
        nhanVien = nhanVienRepo.getInformationUser(id);
        labelMaNV.setText(nhanVien.getMaNV());
        labelHoTen.setText(nhanVien.getHoTen());
        labelNgaySinh.setText(String.valueOf(nhanVien.getNgaySinh()));
        labelCCCD.setText(nhanVien.getCCCD());
        labelChucVu.setText(nhanVien.getChucVu());
        labelGioiTinh.setText(nhanVien.getGioiTinh());
        labelEmail.setText(nhanVien.getEmail());
        labelDiaChiThuongTru.setText(nhanVien.getDiaChiThuongTru());
        labelSoDienThoai.setText(nhanVien.getSoDienThoai());
        labelNgayVaoLam.setText(String.valueOf(nhanVien.getNgayVaoLam()));
//        labelUsername.setText(nhanVien.getUsername());
//        labelPassword.setText(nhanVien.getPassword());
    }
    @FXML
    private void clean() {
        labelMaNV.setText(null);
        labelHoTen.setText(null);
        labelNgaySinh.setText(null);
        labelEmail.setText(null);
        labelCCCD.setText(null);
        labelGioiTinh.setText(null);
        labelSoDienThoai.setText(null);
        labelDiaChiThuongTru.setText(null);
        labelNgayVaoLam.setText(null);
        labelChucVu.setText(null);
//        labelUsername.setText(null);
//        labelPassword.setText(null);
    }

}



