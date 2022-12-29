package controller.KhachHang;

import entity.KhachHang;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import repository.KhachHangRepository;
import repository.KhachHangRepository_impl;

public class DetailCustomerController {
    @FXML
    private Label idLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label addressLabel;

    static KhachHangRepository khachHangRepo = new KhachHangRepository_impl();

    KhachHang khachHang = new KhachHang();

    public void setCustomer(KhachHang clone){
        khachHang.cloneKhachHang(clone);
    }

    public void loadDataPane(){
        idLabel.setText(String.valueOf(khachHang.getIdKhachHang()));
        nameLabel.setText(khachHang.getTenKhachHang());
        phoneLabel.setText(khachHang.getPhoneKhachHang());
        addressLabel.setText(khachHang.getDiaChiKhachHang());
    }

    public void closeButtonOnClicked(MouseEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
