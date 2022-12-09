package controller;

import controller.NhanVien.EditPersonalInformation;
import entity.NhanVien;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import repository.NhanVienRepository;
import repository.NhanVienRepository_impl;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryPane implements Initializable {
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

    @FXML
    private Button editButton;

    @FXML
    private Button reloadButton;


    NhanVienRepository nhanVienRepository = new NhanVienRepository_impl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        load_data();
        if(LoginPage.role ==0){
            editButton.setDisable(true);
            editButton.setVisible(false);
            reloadButton.setDisable(true);
            reloadButton.setVisible(false);
            labelMaNV.setText("admin");
            labelHoTen.setText("admin");
        }
    }
    public void load_data(){
        NhanVien nhanVien = nhanVienRepository.getInformationUser(LoginPage.idNhanVien);
        labelMaNV.setText(nhanVien.getMaNV());
        labelGioiTinh.setText(nhanVien.getGioiTinh());
        labelHoTen.setText(nhanVien.getHoTen());
        labelNgaySinh.setText(String.valueOf(nhanVien.getNgaySinh()));
        labelDiaChiThuongTru.setText(nhanVien.getDiaChiThuongTru());
        labelCCCD.setText(nhanVien.getCCCD());
        labelSoDienThoai.setText(nhanVien.getSoDienThoai());
        labelEmail.setText(nhanVien.getEmail());
        labelNgayVaoLam.setText(String.valueOf(nhanVien.getNgayVaoLam()));
        labelChucVu.setText(nhanVien.getChucVu());
    }
    public void editPersonalInformationOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/NhanVien/editPersonalInformation.fxml"));
        Parent chinhSuaThongTinCaNhan = loader.load();
        EditPersonalInformation controller = loader.getController();
        Stage stage = new Stage();
        controller.edit();
        stage.setTitle("CHỈNH SỬA THÔNG TIN CÁ NHÂN");
        Scene scene = new Scene(chinhSuaThongTinCaNhan);
        stage.setScene(scene);
        stage.show();
    }
}