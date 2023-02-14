package controller.BaoHanh;

import controller.KhoHang.XemChiTietController;
import entity.KhachHang;
import entity.PhieuBaoHanh;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import repository.PhieuBaoHanhRepository;
import repository.PhieuBaoHanhRepository_impl;
import repository.ThietBiRepository;
import repository.ThietBiRepository_impl;
import utility.Box;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaoHanhController implements Initializable {
    @FXML
    private TableView<PhieuBaoHanh> table;

    @FXML
    private TableColumn<PhieuBaoHanh, Number> sttColumn;
    @FXML
    private TableColumn<PhieuBaoHanh, Integer> idColumn;
    @FXML
    private TableColumn<PhieuBaoHanh, String> serialThietBiColumn;
    @FXML
    private TableColumn<PhieuBaoHanh, String> trangThaiColumn;
    @FXML
    private TableColumn<PhieuBaoHanh, String> tenKhachHangColumn;
    @FXML
    private TableColumn<PhieuBaoHanh, String> ngayBaoHanhColumn;
    @FXML
    private TableColumn<PhieuBaoHanh, String> ngayBanGiaoColumn;

    @FXML
    private Pane mainPane;

    @FXML
    private Button createButton;


    @FXML
    private ComboBox<String> truongSearch;
    @FXML
    private TextField duLieuSearch;
    @FXML
    private ImageView searchButton;

    ObservableList<String> listTruongSearch = FXCollections.observableArrayList("Serial","Tên Khách Hàng");
    static PhieuBaoHanhRepository phieuBaoHanhRepo = new PhieuBaoHanhRepository_impl();

    static ThietBiRepository thietBiRepo = new ThietBiRepository_impl();

    ObservableList<PhieuBaoHanh> phieuBaoHanhList = FXCollections.observableArrayList();

    @FXML
    ObservableList<PhieuBaoHanh> searchList = FXCollections.observableArrayList();

    public void createButtonOnClicked(MouseEvent event) throws IOException {
//        Pane taoPhieuBaoHanhPane = FXMLLoader.load(getClass().getResource("/view/BaoHanh/TaoPhieuBaoHanhPane.fxml"));
//        mainPane.getChildren().add(taoPhieuBaoHanhPane);
//        mainPane.toFront();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/BaoHanh/TaoPhieuBaoHanhPane.fxml"));
        Parent taoPhieuBaoHanh = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Tạo phiếu bảo hành");
        Scene scene = new Scene(taoPhieuBaoHanh);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataTable();
    }

    public void loadDataTable(){
        phieuBaoHanhList.clear();
        phieuBaoHanhList.addAll(phieuBaoHanhRepo.layTatCaDuLieuPhieuBaoHanh());
        for(PhieuBaoHanh i : phieuBaoHanhList){
            i.loadData();
        }
        table.setItems(phieuBaoHanhList);
        sttColumn.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue())+1));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        serialThietBiColumn.setCellValueFactory(new PropertyValueFactory<>("serialThietBi"));
        trangThaiColumn.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
        tenKhachHangColumn.setCellValueFactory(new PropertyValueFactory<>("tenKhachHang"));
        ngayBaoHanhColumn.setCellValueFactory(new PropertyValueFactory<>("ngayBaoHanh"));
        ngayBanGiaoColumn.setCellValueFactory(new PropertyValueFactory<>("ngayBanGiao"));
        truongSearch.valueProperty().set(null);
        duLieuSearch.setText("");
        truongSearch.setItems(listTruongSearch);
    }

    public void xemChiTietPhieuBaoHanh(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/BaoHanh/ChiTietPBHPane.fxml"));
        Parent chiThietPBH  = loader.load();
        ChiTietPBHController chiTietPBHController = loader.getController();
        PhieuBaoHanh selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Box.alertBox_None_Selection("phiếu bảo hành");
            return;
        }
        chiTietPBHController.setPhieuBaoHanh(selected.getId());
        chiTietPBHController.loadDuLieuPhieuBaoHanh();
        Stage stage = new Stage();
        stage.setTitle("Thông tin thiết bị");
        Scene scene = new Scene(chiThietPBH);
        stage.setScene(scene);
        stage.show();
    }

    public void xemChiTietThietBi(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Kho/XemChiTietThietBi.fxml"));
        Parent chiTietTB  = loader.load();
        XemChiTietController chiTietTBController = loader.getController();
        PhieuBaoHanh selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Box.alertBox_None_Selection("thiết bị");
            return;
        }
        chiTietTBController.setThietBi(selected.getIdThietBi());
        Stage stage = new Stage();
        stage.setTitle("Thông tin thiết bị");
        Scene scene = new Scene(chiTietTB);
        stage.setScene(scene);
        stage.show();
    }

    public void chinhSuaTrangThai(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/BaoHanh/ChinhSuaTrangThaiPane.fxml"));
        Parent chinhSuaTrangThai  = loader.load();
        ChinhSuaTrangThaiController chinhSuaTrangThaiController = loader.getController();
        PhieuBaoHanh selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Box.alertBox_None_Selection("thiết bị");
            return;
        }
        chinhSuaTrangThaiController.id = selected.getId();
        chinhSuaTrangThaiController.trangThaiCuoiCung = selected.getTrangThai();
        chinhSuaTrangThaiController.loadData();
        Stage stage = new Stage();
        stage.setTitle("Chỉnh sửa trạng thái bảo hành");
        Scene scene = new Scene(chinhSuaTrangThai);
        stage.setScene(scene);
        stage.show();
    }

    public void chinhSuaGhiChuNhanVien(ActionEvent event){
        PhieuBaoHanh selected = table.getSelectionModel().getSelectedItem();
        Integer id = selected.getId();
        TextInputDialog td = new TextInputDialog();
        td.setTitle("Input");
        td.setHeaderText("Nhập ghi chú của nhân viên:");
        String note;
        if(td.showAndWait().isPresent()){
            note = td.getEditor().getText();
            if(note.isEmpty()){
                Box.alertBox("Thông báo!", "Ghi chú trống","Vui lòng nhập lại");
            } else {
                phieuBaoHanhRepo.capNhatNoteNhanVien(id,note);
            }
        }
    }

    public void chinhSuaGhiChuKhachHang(ActionEvent event){
        PhieuBaoHanh selected = table.getSelectionModel().getSelectedItem();
        Integer id = selected.getId();
        TextInputDialog td = new TextInputDialog();
        td.setTitle("Input");
        td.setHeaderText("Nhập ghi chú của khách hàng:");
        String note;
        if(td.showAndWait().isPresent()){
            note = td.getEditor().getText();
            if(note.isEmpty()){
                Box.alertBox("Thông báo!", "Ghi chú trống","Vui lòng nhập lại");
            } else {
                phieuBaoHanhRepo.capNhatNoteKhachHang(id,note);
            }
        }
    }

    public void searchButtonOnClicked(MouseEvent mouseEvent) {
        searchList.clear();
        String textSearch = duLieuSearch.getText().trim().toLowerCase();
        String fieldSearch = truongSearch.getValue();
        try {
            if (fieldSearch.equals("Serial")) {
                if(textSearch.isEmpty()){
                    table.setItems(phieuBaoHanhList);
                } else {
                    for (PhieuBaoHanh current : phieuBaoHanhList) {
                            if ((current.getSerialThietBi().trim().toLowerCase().equals(textSearch))) {
                                PhieuBaoHanh clone = new PhieuBaoHanh();
                                clone.copyPhieuBaoHanh(current);
                                searchList.add(clone);
                            }
                            table.setItems(searchList);
                    }
                }
            } else if (fieldSearch.equals("Tên Khách Hàng")) {
                for (PhieuBaoHanh current : phieuBaoHanhList) {
                    if ((current.getTenKhachHang().toLowerCase()).contains(textSearch)) {
                        PhieuBaoHanh clone = new PhieuBaoHanh();
                        clone.copyPhieuBaoHanh(current);
                        searchList.add(clone);
                    }
                }
                table.setItems(searchList);
            }
            if(searchList.isEmpty()&&!textSearch.isEmpty()){
                table.setItems(phieuBaoHanhList);
                Box.alertBox_No_Result();
            }
        } catch (NullPointerException ex) {
            Box.alertBox("Thông báo!","Chưa chọn trường tìm kiếm","Vui lòng chọn lại");
            return;
        }
    }

    public void xoaPhieuBaoHanhOnAction(ActionEvent event){
        PhieuBaoHanh selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Box.alertBox_None_Selection("phiếu xuất");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xóa thiết bị");
        alert.setHeaderText("Bạn có thực sự muốn xóa phiếu xuất này ?");
        alert.setContentText("Nếu bạn đồng ý xóa dữ liệu sẽ bị xóa và không thể khôi phục!");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == null) {}
        else if (option.get() == ButtonType.OK) {
            phieuBaoHanhRepo.deletePhieuBaoHanh(selected.getId());
            thietBiRepo.updatePhieuBaoHanh_Delete(selected.getIdThietBi());
            Box.alertBox("Thành công!","Xóa phiếu bảo hành thành công","");
            phieuBaoHanhList.remove(selected);
        } else if (option.get() == ButtonType.CANCEL) {}
    }
}
