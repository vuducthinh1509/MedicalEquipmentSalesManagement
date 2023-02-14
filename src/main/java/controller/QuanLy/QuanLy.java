package controller.QuanLy;


import controller.NhanVien.EditPersonalInformation;
import entity.NhanVien;

import entity.ThietBi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.NhanVienRepository;
import repository.NhanVienRepository_impl;

import utility.SQLCommand;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class QuanLy implements Initializable {
    public TableColumn gioiTinhColumn;
    public TableColumn diaChiThuongTruColumn;
    @FXML
    private TableView<NhanVien> table;
    @FXML
    private TableColumn<NhanVien,Integer> idColumn;
    @FXML
    private TableColumn<NhanVien, Integer>  maNVColumn;
    @FXML
    private TableColumn<NhanVien,String>  hoTenColumn;
    @FXML
    private Button addProductButton;

    @FXML
    private Button reloadTableButton;
    @FXML
    private ComboBox<String> truongTraCuuF;
    @FXML
    private TextField duLieuF;
    @FXML
    private ImageView confirmF;

    @FXML
    ObservableList<NhanVien> NhanVienList = FXCollections.observableArrayList();
    private String duLieuTraCuu="";
    static NhanVienRepository NhanVienRepo = new NhanVienRepository_impl();

    static NhanVien selectedNhanVien = new NhanVien();
    @SneakyThrows
    @FXML
    private void findF(MouseEvent event) {
        NhanVienList.clear();
        duLieuTraCuu=duLieuF.getText();
        String truongTraCuu = truongTraCuuF.getValue();
        try{
            if(truongTraCuu.equals("Tên")){
                NhanVienList.addAll(NhanVienRepo.timNhanVienTheoTruong(SQLCommand.Nhan_Vien_QUERY_LAY_THONG_TIN_BY_hoTen, duLieuTraCuu));
            } else if(truongTraCuu.equals("Mã nhân viên")) {
                NhanVienList.addAll(NhanVienRepo.timNhanVienTheoTruong(SQLCommand.Nhan_Vien_QUERY_LAY_THONG_TIN_BY_MaNV, duLieuTraCuu));
            }
            table.setItems(NhanVienList);
        }
        catch (NullPointerException ex){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Bạn cần chọn trường tra cứu");
            alert.show();
            table.setItems(NhanVienList);
            return;
        }
    }
    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataNV();
    }
    ObservableList<String> listTruongTraCuu = FXCollections.observableArrayList("Tên","Mã nhân viên");
    @FXML
    private void loadDataNV() {
        NhanVienList.clear();
        NhanVienList.addAll(NhanVienRepo.loadDataNV());
        table.setItems(NhanVienList);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        maNVColumn.setCellValueFactory(new PropertyValueFactory<>("maNV"));
        hoTenColumn .setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        gioiTinhColumn.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        diaChiThuongTruColumn.setCellValueFactory(new PropertyValueFactory<>("diaChiThuongTru"));
        truongTraCuuF.valueProperty().set(null);
        duLieuF.setText("");
        truongTraCuuF.setItems(listTruongTraCuu);
    }

    public void thongTinNV(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/QuanLy/ThongTinNV.fxml"));
        Parent thongTinNV  = loader.load();
        ThongTinNV thongTinNV1 = loader.getController();
        selectedNhanVien = table.getSelectionModel().getSelectedItem();
        if (selectedNhanVien == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không nhân viên nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        thongTinNV1.setNhanVien(selectedNhanVien);
        Stage stage = new Stage();
        stage.setTitle("Thông tin nhân viên");
        Scene scene = new Scene(thongTinNV);
        stage.setScene(scene);
        stage.show();
    }

    public void themNV(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/QuanLy/ThemNV.fxml"));
        Parent themNV = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Thêm nhân viên");
        Scene scene = new Scene(themNV);
        stage.setScene(scene);
        stage.show();
    }


    public void EditNV (ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/QuanLy/EditNV.fxml"));
        Parent thongTinNV  = loader.load();
        EditNV thongTinNV1 = loader.getController();
        selectedNhanVien = table.getSelectionModel().getSelectedItem();
        if (selectedNhanVien == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không nhân viên nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        thongTinNV1.idNhanVien = selectedNhanVien.getId();
        thongTinNV1.loadData_Edit();
        Stage stage = new Stage();
        stage.setTitle("Thông tin nhân viên");
        Scene scene = new Scene(thongTinNV);
        stage.setScene(scene);
        stage.show();
    }

    public void DeleteNV(ActionEvent e) throws IOException {
        NhanVien nhanVien = table.getSelectionModel().getSelectedItem();
        if (nhanVien == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không nhân viên nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        int _idNhanVien= nhanVien.getId();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xóa nhân viên");
        alert.setHeaderText("Bạn có thực sự muốn xóa nhân viên này ?");
        alert.setContentText("Nếu bạn đồng ý xóa dữ liệu sẽ bị xóa và không thể khôi phục!");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == null) {}
        else if (option.get() == ButtonType.OK) {
            NhanVienRepo.xoaNV(_idNhanVien);
            loadDataNV();
        } else if (option.get() == ButtonType.CANCEL) {}

    }
    }



