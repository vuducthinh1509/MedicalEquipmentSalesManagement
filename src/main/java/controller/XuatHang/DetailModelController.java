package controller.XuatHang;

import entity.ThietBi;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import repository.ThietBiRepository;
import repository.ThietBiRepository_impl;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailModelController {
    @FXML
    private Label tenLabel;
    @FXML
    private Label modelLabel;
    @FXML
    private Label mauLabel;
    @FXML
    private Label kichThuocLabel;
    @FXML
    private Label xuatXuLabel;
    @FXML
    private Label giaLabel;
    @FXML
    private Label thoiGianBaoHanhLabel;

    String modelThietBi = "";

    static ThietBiRepository thietBiRepo = new ThietBiRepository_impl();

    public void loadDataPane(){
        ThietBi thietBi  = new ThietBi();
        thietBi = thietBiRepo.layThongTinThietBiTheoModel(modelThietBi);
        tenLabel.setText(thietBi.getTenThietBi());
        modelLabel.setText(thietBi.getModelThietBi());
        mauLabel.setText(thietBi.getMauThietBi());
        kichThuocLabel.setText(thietBi.getKichThuocThietBi());
        xuatXuLabel.setText(thietBi.getXuatXuThietBi());
        giaLabel.setText(String.valueOf(thietBi.getGiaThietBi()));
        thoiGianBaoHanhLabel.setText(thietBi.getThoiGianBaoHanh());
    }
}
