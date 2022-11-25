package controller.KhoHang;

import entity.NhanVien;
import entity.ThietBi;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import repository.NhanVienRepository;
import repository.NhanVienRepository_impl;
import repository.ThietBiRepository;
import repository.ThietBiRepository_impl;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class ChinhSuaThietBiController {
    @FXML
    private TextField tenLabel;
    @FXML
    private TextField modelLabel;
    @FXML
    private TextField serialLabel;
    @FXML
    private TextField xuatXuLabel;
    @FXML
    private TextField mauLabel;
    @FXML
    private TextField kichThuocLabel;
    @FXML
    private DatePicker ngayNhapLabel;
    @FXML
    private TextField maNVNhapLabel;
    @FXML
    private TextField tenNVNhapLabel;
    @FXML
    private TextField thoiGianBaoHanhLabel;
    @FXML
    private TextField giaLabel;
    @FXML
    private TextField trangThaiLabel;
    @FXML
    private DatePicker ngayXuatLabel;
    @FXML
    private TextField maNVXuatLabel;
    @FXML
    private TextField tenNVXuatLabel;

    private int idThietBi;
    private ThietBi thietBi = new ThietBi();

    private ThietBiRepository thietBiRepo = new ThietBiRepository_impl();

    @FXML
    public void loadDuLieuChinhSua(){
        NhanVien nhanVien = new NhanVien();
        NhanVienRepository nhanVienRepo = new NhanVienRepository_impl();
        thietBi = thietBiRepo.chiTietThietBi(idThietBi);
        tenLabel.setText(thietBi.getTenThietBi());
        modelLabel.setText(thietBi.getModelThietBi());
        serialLabel.setText(thietBi.getSerialThietBi());
        xuatXuLabel.setText(thietBi.getXuatXuThietBi());
        mauLabel.setText(thietBi.getMauThietBi());
        kichThuocLabel.setText(thietBi.getKichThuocThietBi());
        if(thietBi.getNgayNhapThietBi()!=null){
            ngayNhapLabel.setValue(LocalDate.parse(String.valueOf(thietBi.getNgayNhapThietBi())));
        }
        maNVNhapLabel.setText(thietBi.getMaNVNguoiNhap());
        thoiGianBaoHanhLabel.setText(thietBi.getThoiGianBaoHanh());
        giaLabel.setText(thietBi.getGiaThietBi());
        trangThaiLabel.setText(thietBi.getTrangThaiThietBi());
        if(thietBi.getNgayXuatThietBi()!=null){
            ngayXuatLabel.setValue(LocalDate.parse(String.valueOf(thietBi.getNgayXuatThietBi())));
        }
        maNVXuatLabel.setText(thietBi.getMaNVNguoiXuat());
        nhanVien = nhanVienRepo.getInformationUserByMaNV(thietBi.getMaNVNguoiNhap());
        tenNVNhapLabel.setText(nhanVien.getHoTen());
        nhanVien = nhanVienRepo.getInformationUserByMaNV(thietBi.getMaNVNguoiXuat());
        tenNVXuatLabel.setText(nhanVien.getHoTen());
    }
    @FXML
    public void setChinhSuaThietBi(ThietBi thietBi){
        idThietBi = thietBi.getIdThietBi();
        loadDuLieuChinhSua();
    }
    private void update(){
        thietBiRepo.updateThietBi(idThietBi,new ThietBi(tenLabel.getText(),modelLabel.getText(),serialLabel.getText(),xuatXuLabel.getText(),mauLabel.getText(), kichThuocLabel.getText(),giaLabel.getText(),maNVNhapLabel.getText(),Date.valueOf(ngayNhapLabel.getValue().toString()),maNVXuatLabel.getText(),thoiGianBaoHanhLabel.getText(),trangThaiLabel.getText()));
    }

    @FXML
    private void huyMouseAction(MouseEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void save_ChinhSua(MouseEvent event){
        String ten = tenLabel.getText();
        String model = modelLabel.getText();
        String serial = serialLabel.getText();
        String maNVNhap = maNVNhapLabel.getText();
        String ngayNhap = String.valueOf(ngayNhapLabel.getValue());
        if (ten.isEmpty() || model.isEmpty() || serial.isEmpty()|| maNVNhap.isEmpty() || ngayNhap.isEmpty()) {
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

}
