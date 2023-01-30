package controller.BaoHanh;

import entity.PhieuBaoHanh;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import repository.PhieuBaoHanhRepository;
import repository.PhieuBaoHanhRepository_impl;

public class ChiTietPBHController {
    @FXML
    private Label tenTB;
    @FXML
    private Label modelTB;
    @FXML
    private Label serialTB;
    @FXML
    private Label tenKH;
    @FXML
    private Label sdtKH;
    @FXML
    private Label diaChiKH;
    @FXML
    private Label noteKH;
    @FXML
    private Label id;
    @FXML
    private Label trangThai;
    @FXML
    private Label ngayBanGiao;
    @FXML
    private Label tenNhanVien;
    @FXML
    private Label noteNhanVien;
    @FXML
    private Label ngayTao;
    @FXML
    private Label chiPhi;
    PhieuBaoHanh phieuBaoHanh = new PhieuBaoHanh();

    static PhieuBaoHanhRepository phieuBaoHanhRepo = new PhieuBaoHanhRepository_impl();


    public void loadDuLieuPhieuBaoHanh(){
        tenTB.setText(phieuBaoHanh.getTenThietBi());
        modelTB.setText(phieuBaoHanh.getModelThietBi());
        serialTB.setText(phieuBaoHanh.getSerialThietBi());
        tenKH.setText(phieuBaoHanh.getTenKhachHang());
        sdtKH.setText(phieuBaoHanh.getPhoneKhachHang());
        diaChiKH.setText(phieuBaoHanh.getDiaChiKhachHang());
        noteKH.setText(phieuBaoHanh.getNoteKhachHang());
        id.setText(String.valueOf(phieuBaoHanh.getId()));
        trangThai.setText(phieuBaoHanh.getTrangThai());
        ngayBanGiao.setText(String.valueOf(phieuBaoHanh.getNgayBanGiao()));
        tenNhanVien.setText(phieuBaoHanh.getTenNhanVienTaoPhieu());
        noteNhanVien.setText(phieuBaoHanh.getNoteNhanVien());
        ngayTao.setText(String.valueOf(phieuBaoHanh.getNgayBaoHanh()));
        chiPhi.setText(phieuBaoHanh.getChiPhiBaoHanh());
    }
    public void setPhieuBaoHanh(Integer id){
        PhieuBaoHanh clone = new PhieuBaoHanh();
        clone = phieuBaoHanhRepo.layDuLieuPhieuBaoHanhTheoID(id);
        phieuBaoHanh.copyPhieuBaoHanh(clone);
        phieuBaoHanh.loadData();
    }
    @FXML
    private void cancelButtonOnAction(MouseEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


}
