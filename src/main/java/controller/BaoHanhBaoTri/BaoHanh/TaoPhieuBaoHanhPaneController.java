package controller.BaoHanhBaoTri.BaoHanh;

import controller.ExportInvoice.DetailInvoice;
import controller.LoginPage;
import entity.Item;
import entity.KhachHang;
import entity.PhieuXuat;
import entity.ThietBi;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import repository.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TaoPhieuBaoHanhPaneController implements Initializable {

    @FXML
    private TableView<ThietBi> table;
    @FXML
    private TableColumn<ThietBi, Number> sttColumn;

    @FXML
    private TableColumn<ThietBi, String> tenColumn;

    @FXML
    private TableColumn<ThietBi, String> modelColumn;

    @FXML
    private TableColumn<ThietBi, String> serialColumn ;

    @FXML
    private TableColumn<ThietBi, Integer> idPXColumn ;

    @FXML
    private TableColumn<ThietBi, String> tenKHColumn ;
    //thông tin phiếu xuất
    @FXML
    private Label idPBHLabel;
    @FXML
    private Label ngayXuatLabel;
    @FXML
    private Label maNVXuatLabel;
    // thông tin khách hàng
    @FXML
    private TextField idCtmLabel;
    @FXML
    private TextField nameCtmLabel;
    @FXML
    private TextField addressCtmLabel;
    @FXML
    private TextField phoneCtmLabel;
    @FXML
    private Button findCtmButton;
    @FXML
    private Button addCtmButton;

    @FXML
    private Button deleteButton;
    @FXML
    private Button saveButton;

    static PhieuBaoHanhRepository phieuBaoHanhRepo = new PhieuBaoHanhRepository_impl();

    static NhanVienRepository nhanVienRepo = new NhanVienRepository_impl();

    static KhachHangRepository khachHangRepo = new KhachHangRepository_impl();

    static ThietBiRepository thietBiRepo = new ThietBiRepository_impl();

    static PhieuXuatRepository phieuXuatRepo = new PhieuXuatRepository_impl();

    ObservableList<ThietBi> thietBiList = FXCollections.observableArrayList();

    KhachHang khachHang = new KhachHang();
    static Integer idPBH = -1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataPane();
    }

    public void loadDataPane(){
        KhachHang ctm = new KhachHang();
        thietBiList.clear();
        idPBH = phieuBaoHanhRepo.getNextAutoIncrement();
        idPBHLabel.setText(String.valueOf(idPBH));
        ngayXuatLabel.setText(String.valueOf(java.time.LocalDate.now()));
        maNVXuatLabel.setText(nhanVienRepo.getInformationUser(LoginPage.idNhanVien).getMaNV());
        thietBiList.addAll(thietBiRepo.timTatCaThietBiTrangThaiDaXuat());
        for(ThietBi curr: thietBiList){
            PhieuXuat phieuXuat = new PhieuXuat();
            phieuXuat = phieuXuatRepo.getDetailInvoiceByID(curr.getIdPhieuXuat());
            phieuXuat.setNameCtmAndEpl();
            curr.setTenKhachHang(phieuXuat.getNameCustomer());
        }
        table.setItems(thietBiList);
        sttColumn.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue())+1));
        tenColumn.setCellValueFactory(new PropertyValueFactory<>("tenThietBi"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("modelThietBi"));
        serialColumn.setCellValueFactory(new PropertyValueFactory<>("serialThietBi"));
        idPXColumn.setCellValueFactory(new PropertyValueFactory<>("idPhieuXuat"));
        tenKHColumn.setCellValueFactory(new PropertyValueFactory<>("tenKhachHang"));
    }

    public void onEditDataCustomer(boolean key){
        idCtmLabel.setEditable(key);
        nameCtmLabel.setEditable(key);
        phoneCtmLabel.setEditable(key);
        addressCtmLabel.setEditable(key);
    }

    public void clearDataCustomer(){
        idCtmLabel.clear();
        nameCtmLabel.clear();
        phoneCtmLabel.clear();
        addressCtmLabel.clear();
    }

    public  void loadDataCustomerPane(){
        idCtmLabel.setText(String.valueOf(khachHang.getIdKhachHang()));
        nameCtmLabel.setText(khachHang.getTenKhachHang());
        phoneCtmLabel.setText(khachHang.getPhoneKhachHang());
        addressCtmLabel.setText(khachHang.getDiaChiKhachHang());
    }
    public void findCtmButtonOnClicked(MouseEvent event) throws IOException {
        onEditDataCustomer(false);
        TextInputDialog td = new TextInputDialog();
        td.setTitle("Input");
        td.setHeaderText("Nhập số điện thoại");
        td.showAndWait();
        String phone = "";
        phone = td.getEditor().getText();
        String pattern = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        if(!phone.matches(pattern)){
            clearDataCustomer();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo!");
            alert.setHeaderText("Số điện thoại không hợp lệ");
            alert.setContentText("Vui lòng thử lại");
            alert.showAndWait();
        } else {
            khachHang = khachHangRepo.getInformationCustomerByPhone(phone);
            String phoneCtm = khachHang.getPhoneKhachHang();
            if(phoneCtm == null){
                clearDataCustomer();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo!");
                alert.setHeaderText("Không tìm thấy kết quả phù hợp");
                alert.setContentText("Vui lòng nhập lại");
                alert.show();
            } else {
                loadDataCustomerPane();
            }
        }
    }

    public void addCtmButtonOnClicked(MouseEvent event) throws IOException {
        clearDataCustomer();
        idCtmLabel.setText(String.valueOf(khachHangRepo.getCountCustomer()));
        onEditDataCustomer(true);
        findCtmButton.setDisable(true);
        idCtmLabel.setEditable(false);
        deleteButton.setVisible(false);
        deleteButton.setDisable(true);
        saveButton.setVisible(true);
        saveButton.setDisable(false);
    }

    public void deleteButtonOnClicked(MouseEvent event){
        clearDataCustomer();
        khachHang.setDefault();
    }

    public void saveButtonOnClicked(MouseEvent event){
        String name = nameCtmLabel.getText();
        String phone = phoneCtmLabel.getText();
        String address = addressCtmLabel.getText();
        String pattern = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        if(name.isEmpty()||phone.isEmpty()||address.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo!");
            alert.setHeaderText("Cần nhập đầy đủ các trường");
            alert.setContentText("Vui lòng thử lại sau");
            alert.showAndWait();
        } else if(!phone.matches(pattern)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo!");
            alert.setHeaderText("Số điện thoại không hợp lệ");
            alert.setContentText("Vui lòng thử lại");
            alert.showAndWait();
        } else {
            onEditDataCustomer(false);
            saveButton.setVisible(false);
            saveButton.setDisable(true);
            deleteButton.setVisible(true);
            deleteButton.setDisable(false);
            findCtmButton.setDisable(false);
        }
    }

    public void detailInvoice(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ExportInvoice/DetailInvoice.fxml"));
        Parent detailInvoice  = loader.load();
        DetailInvoice detailInvoiceController = loader.getController();
        ThietBi selectedDevice = table.getSelectionModel().getSelectedItem();
        if (selectedDevice == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo!");
            alert.setHeaderText("Không thiết bị nào được chọn.");
            alert.setContentText("Vui lòng chọn lại.");
            alert.show();
            return;
        }
        detailInvoiceController.setInvoiceByID(selectedDevice.getIdPhieuXuat());
        detailInvoiceController.loadDataInvoice();
        Stage stage = new Stage();
        stage.setTitle("Thông tin thiết bị");
        Scene scene = new Scene(detailInvoice);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
