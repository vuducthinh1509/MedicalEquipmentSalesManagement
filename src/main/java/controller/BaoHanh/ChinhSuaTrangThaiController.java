package controller.BaoHanh;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import repository.PhieuBaoHanhRepository;
import repository.PhieuBaoHanhRepository_impl;
import utility.Box;

import java.sql.Date;

public class ChinhSuaTrangThaiController {

    @FXML
    private ComboBox trangThaiLabel;

    @FXML
    private DatePicker ngayBanGiaoLabel;

    @FXML
    private TextField chiPhiLabel;

    @FXML
    private TextField ghiChuLabel;

    static String trangThaiCuoiCung = "";

    static  Integer id;

    static PhieuBaoHanhRepository phieuBaoHanhRepository = new PhieuBaoHanhRepository_impl();

    public void loadData(){
        ObservableList<String> listTrangThai = FXCollections.observableArrayList("Đã bàn giao","Có thể bàn giao","Đang bảo hành");
        trangThaiLabel.setItems(listTrangThai);
        trangThaiLabel.getSelectionModel().select(trangThaiCuoiCung);
    }
    public void cancelButtonOnAction(MouseEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void saveButtonOnClicked(MouseEvent event) {
        String ngayBanGiao = String.valueOf(ngayBanGiaoLabel.getValue());
        String chiPhi = chiPhiLabel.getText();
        String ghiChu = ghiChuLabel.getText();
        String _trangThai = trangThaiLabel.getSelectionModel().getSelectedItem().toString();
        if (trangThaiCuoiCung.equals(_trangThai)) {
            Box.alertBox("Thông báo!", "Không được chọn trạng thái trước đó", "Vui lòng chọn lại");
        } else {
            if (_trangThai.equals("Có thể bàn giao")) {
                phieuBaoHanhRepository.capNhatTrangThai2(id, chiPhi, ghiChu);
            } else if(_trangThai.equals("Đã bàn giao")){
                phieuBaoHanhRepository.capNhatTrangThai(id, Date.valueOf(ngayBanGiao), chiPhi, ghiChu);
            } else if(_trangThai.equals("Đang bảo hành")){
                phieuBaoHanhRepository.capNhatTrangThai1(id);
            } else {
                Box.alertBox("Thông báo!","Có lỗi xảy ra","Vui lòng thử lại");
            }
        }
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        }
}
