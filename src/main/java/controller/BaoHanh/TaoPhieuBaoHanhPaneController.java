package controller.BaoHanh;

import controller.PhieuXuat.DetailInvoice;
import controller.KhachHang.DetailCustomerController;
import controller.TaiKhoan.LoginController;
import entity.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.*;
import utility.Box;
import utility.SQLCommand;
import utility.Validate;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
    private Label tenThietBiLabel;
    @FXML
    private Label modelThietBiLabel;
    @FXML
    private Label serialThietBiLabel;
    @FXML
    private Button findCtmButton;
    @FXML
    private Button addCtmButton;
    @FXML
    private Button cancelButton1;
    @FXML
    private Button addPBHButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button saveButton;

    @FXML
    private TextField noteCtmLabel;

    static PhieuBaoHanhRepository phieuBaoHanhRepo = new PhieuBaoHanhRepository_impl();

    static NhanVienRepository nhanVienRepo = new NhanVienRepository_impl();

    static KhachHangRepository khachHangRepo = new KhachHangRepository_impl();

    static ThietBiRepository thietBiRepo = new ThietBiRepository_impl();

    static PhieuXuatRepository phieuXuatRepo = new PhieuXuatRepository_impl();

    ObservableList<ThietBi> thietBiList = FXCollections.observableArrayList();

    KhachHang khachHang = new KhachHang();
    static Integer idPBH = -1;

    static Integer idThietBi = -1;

    @FXML
    private TextField duLieuSearch;
    @FXML
    private ImageView searchButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataPane();
    }

    @SneakyThrows
    @FXML
    private void searchButtonOnClicked(MouseEvent event) {
        ObservableList<ThietBi> thietBiList_temp = FXCollections.observableArrayList();
        String duLieuTraCuu = duLieuSearch.getText();
        try{
            thietBiList_temp.addAll(thietBiRepo.timTatCaThietBiDaXuatTheoSerial(duLieuTraCuu));
            if(thietBiList_temp.size()==0){
                table.setItems(thietBiList);
                Box.alertBox_No_Result();
                return;
            }
            for(ThietBi curr: thietBiList_temp){
                PhieuXuat phieuXuat = new PhieuXuat();
                phieuXuat = phieuXuatRepo.getDetailInvoiceByID(curr.getIdPhieuXuat());
                phieuXuat.setNameCtmAndEpl();
                curr.setTenKhachHang(phieuXuat.getNameCustomer());
            }
            table.setItems(thietBiList_temp);
        }
        catch (NullPointerException ex){
            table.setItems(thietBiList);
            return;
        }
    }
    private boolean isFullFillCustomerPane(){
        String id = idCtmLabel.getText();
        String name = nameCtmLabel.getText();
        String phone = phoneCtmLabel.getText();
        String address = addressCtmLabel.getText();
        if(id.isEmpty()||name.isEmpty()||phone.isEmpty()||address.isEmpty()){
            return false;
        } else {
            return true;
        }
    }

    public void loadDataPane(){
        KhachHang ctm = new KhachHang();
        thietBiList.clear();
        idPBH = phieuBaoHanhRepo.getNextAutoIncrement();
        idPBHLabel.setText(String.valueOf(idPBH));
        ngayXuatLabel.setText(String.valueOf(java.time.LocalDate.now()));
        maNVXuatLabel.setText(nhanVienRepo.getInformationUser(LoginController.idNhanVien).getMaNV());
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
        duLieuSearch.setText("");
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
        if(!Validate.validatePhoneVN(phone)){
            clearDataCustomer();
            Box.alertBox("Thông báo","Số điện thoại không hợp lệ","Vui lòng thử lại");
        } else {
            khachHang = khachHangRepo.getInformationCustomerByPhone(phone);
            String phoneCtm = khachHang.getPhoneKhachHang();
            if(phoneCtm == null){
                clearDataCustomer();
                Box.alertBox_No_Result();
            } else {
                loadDataCustomerPane();
            }
        }
    }


    public void deleteButtonOnClicked(MouseEvent event){
        clearDataCustomer();
        khachHang.setDefault();
    }
    public void detailInvoice(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/PhieuXuat/DetailInvoice.fxml"));
        Parent detailInvoice  = loader.load();
        DetailInvoice detailInvoiceController = loader.getController();
        ThietBi selectedDevice = table.getSelectionModel().getSelectedItem();
        if (selectedDevice == null) {
            Box.alertBox_None_Selection("thiết bị");
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

    public void detailCustomer(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/KhachHang/DetailCustomer.fxml"));
        Parent detailCustomer  = loader.load();
        DetailCustomerController detailCustomerController = loader.getController();
        ThietBi selectedThietBi = table.getSelectionModel().getSelectedItem();
        PhieuXuat selectedInvoice = phieuXuatRepo.getDetailInvoiceByID(selectedThietBi.getIdPhieuXuat());
        if (selectedInvoice == null) {
            Box.alertBox_None_Selection("thiết bị");
            return;
        }
        khachHang = khachHangRepo.getInformationCustomerByID(selectedInvoice.getIdCustomerInvoice());
        detailCustomerController.setCustomer(khachHang);
        detailCustomerController.loadDataPane();
        Stage stage = new Stage();
        stage.setTitle("Thông tin khách hàng");
        Scene scene = new Scene(detailCustomer);
        stage.setScene(scene);
        stage.show();
    }

    public void setOnMouseClicked(MouseEvent event){
        ThietBi selectedThietBi = table.getSelectionModel().getSelectedItem();
        idThietBi = selectedThietBi.getIdThietBi();
        tenThietBiLabel.setText(selectedThietBi.getTenThietBi());
        modelThietBiLabel.setText(selectedThietBi.getModelThietBi());
        serialThietBiLabel.setText(selectedThietBi.getSerialThietBi());
    }

    public void addPBHButtonOnClicked(MouseEvent event){
        if(isFullFillCustomerPane()&& idThietBi != -1){
            thietBiRepo.updateIDPhieuBaoHanh(idThietBi,idPBH);
            String ngayTao = String.valueOf(ngayXuatLabel.getText());
            String noteKhachHang = noteCtmLabel.getText();
            Integer idTB = idThietBi;
            Integer idKH = Integer.valueOf(idCtmLabel.getText());
            Integer idNV = LoginController.idNhanVien;
            PhieuBaoHanh phieuBaohanh = new PhieuBaoHanh(Date.valueOf(ngayTao),noteKhachHang,idTB,idKH,idNV);
            phieuBaoHanhRepo.taoPhieuBaoHanh(phieuBaohanh);
            Box.alertBox("Thành công!","Tạo phiếu bảo hành thành công","");
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } else {
            Box.alertBox("Thất bại!","Cần nhập đủ thông tin của khách hàng và lựa chọn thiết bị.","Vui lòng thử lại");
        }
    }

    public void cancelButtonOnClicked(MouseEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
