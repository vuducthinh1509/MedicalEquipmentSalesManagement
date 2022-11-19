package controller.KhoHang;

import entity.ThietBi;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.ThietBiRepository;
import repository.ThietBiRepository_impl;

import java.time.LocalDate;

public class ThemThietBiController {
    @FXML
    private TextField tenLabel;
    @FXML
    private TextField modelLabel;
    @FXML
    private TextField serialLabel;
    @FXML
    private TextField xuatXuLabel;
    @FXML
    private TextField thoiGianBaoHanhLabel;
    @FXML
    private TextField mauLabel;
    @FXML
    private TextField giaLabel;
    @FXML
    private TextField kichThuocLabel;
    @FXML
    private TextField maNVNhapLabel;
    @FXML
    private DatePicker ngayNhapLabel;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    ThietBi thietBi = new ThietBi();
    ThietBiRepository thietBiRepo = new ThietBiRepository_impl();

    @FXML
    private void cancelAction(MouseEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @SneakyThrows
    @FXML
    private void saveAction(MouseEvent event) {
        String _ten = tenLabel.getText();
        String _model = modelLabel.getText();
        String _serial = serialLabel.getText();
        String _gia = giaLabel.getText();
        String _ngayNhap = String.valueOf(ngayNhapLabel.getValue());
        String _maNVNhap = maNVNhapLabel.getText();

        if ( _ten.isEmpty() || _model.isEmpty() || _serial.isEmpty()||_gia.isEmpty()|| _ngayNhap.isEmpty() || _maNVNhap.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Cần nhập đủ các trường dữ liệu");
            alert.showAndWait();
        } else {
            insert();
            Alert alert_TC = new Alert(Alert.AlertType.INFORMATION);
            alert_TC.setHeaderText(null);
            alert_TC.setContentText("Thêm thành công");
            alert_TC.showAndWait();
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }
    private void insert() {
        String ten = tenLabel.getText();
        String model = modelLabel.getText();
        String serial = serialLabel.getText();
        String xuatXu = xuatXuLabel.getText();
        String thoiGianBaoHanh = thoiGianBaoHanhLabel.getText();
        String mau = mauLabel.getText();
        String kichThuoc = kichThuocLabel.getText();
        String gia = giaLabel.getText();
        String trangThai = "Trong kho";
        String maNVNhap = maNVNhapLabel.getText();
        String ngayNhap = String.valueOf(ngayNhapLabel.getValue());
        thietBiRepo.themThietBi(new ThietBi(ten,model,serial,xuatXu, thoiGianBaoHanh,mau,kichThuoc,gia,trangThai,maNVNhap, ngayNhap));
    }
}
