package controller.KhoHang.XuatHang;

import controller.TaiKhoan.LoginController;
import entity.Item;
import entity.KhachHang;
import entity.PhieuXuat;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import repository.*;
import utility.Box;
import utility.Validate;

import java.io.IOException;
import java.sql.Date;

public class InvoiceController {
    //table
    @FXML
    private TableView<Item> table;
    @FXML
    private TableColumn<Item,Number> sttColumn;
    @FXML
    private TableColumn<Item,String> nameColumn;
    @FXML
    private TableColumn<Item,String> modelColumn;
    @FXML
    private TableColumn<Item,Integer> priceColumn;
    @FXML
    private TableColumn<Item,Integer> quantityColumn;
    @FXML
    private TableColumn<Item,Number> amountColumn;

    //thông tin phiếu xuất
    @FXML
    private Label idPhieuXuatLabel;
    @FXML
    private Label ngayXuatLabel;
    @FXML
    private Label maNVXuatLabel;
    // thông tin khách hàng
    @FXML
    private Button cancel1Button;
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
    private Button tinhTienButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button saveButton;
    /* thông tin đơn hàng */
    @FXML
    private TextField subTotalLabel;
    @FXML
    private TextField discountLabel;
    @FXML
    private TextField discount1Label;
    @FXML
    private TextField vatLabel;
    @FXML
    private TextField totalLabel;

    /* Các biến */

    static Integer subtotal = 0;

    static Integer idInvoice = -1;

    KhachHang khachHang = new KhachHang();

    static KhachHangRepository khachHangRepo = new KhachHangRepository_impl();

    static PhieuXuatRepository phieuXuatRepo = new PhieuXuatRepository_impl();

    static NhanVienRepository nhanVienRepo = new NhanVienRepository_impl();

    static ThietBiRepository thietBiRepo = new ThietBiRepository_impl();

    static boolean tinhTienButtonIsClicked = false;

    static boolean addCustomer = false;

    ObservableList<Item> itemList = FXCollections.observableArrayList();

    private boolean isFullFillCustomerPane(){
        String id = idCtmLabel.getText();
        String name = nameCtmLabel.getText();
        String phone = phoneCtmLabel.getText();
        String address = addressCtmLabel.getText();
        if(id.isEmpty()||name.isEmpty()||phone.isEmpty()||address.isEmpty()){
            return false;
        }
        if(saveButton.isVisible()==true){
            Box.alertBox("Thất bại!","Ấn Lưu khách hàng","Vui lòng thử lại");
            return false;
        }
        return true;
    }

    public void onEditDataCustomer(boolean key){
        nameCtmLabel.setEditable(key);
        phoneCtmLabel.setEditable(key);
        addressCtmLabel.setEditable(key);
    }
    public void clearDataCustomer(){
        addCustomer = false;
        idCtmLabel.clear();
        nameCtmLabel.clear();
        phoneCtmLabel.clear();
        addressCtmLabel.clear();
    }
    public void clearDataPane(){
        subtotal = 0;
        subTotalLabel.clear();
        discountLabel.clear();
        discount1Label.clear();
        vatLabel.clear();
        totalLabel.clear();
        table.setItems(null);
        itemList.clear();
    }
    public void loadDataItem(){
        for(Item item : itemList){
            Integer var = item.getSoLuongItem()*item.getGiaItem();
            item.setTotalPriceItem(var);
            subtotal += var;
        }
        table.setItems(itemList);
        subTotalLabel.setText(String.valueOf(subtotal));
        subTotalLabel.setEditable(false);
        sttColumn.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue())+1));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("tenItem"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("modelItem"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("soLuongItem"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("giaItem"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("totalPriceItem"));
    }

    public void loadDataPane(){
        idInvoice = phieuXuatRepo.getNextAutoIncrement();
        idPhieuXuatLabel.setText(String.valueOf(idInvoice));
        ngayXuatLabel.setText(String.valueOf(java.time.LocalDate.now()));
        maNVXuatLabel.setText(nhanVienRepo.getInformationUser(LoginController.idNhanVien).getMaNV());
    }
    public  void loadDataCustomerPane(){
        idCtmLabel.setText(String.valueOf(khachHang.getIdKhachHang()));
        nameCtmLabel.setText(khachHang.getTenKhachHang());
        phoneCtmLabel.setText(khachHang.getPhoneKhachHang());
        addressCtmLabel.setText(khachHang.getDiaChiKhachHang());
    }

    public void setCustomer(KhachHang clone){
        khachHang.cloneKhachHang(clone);
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
            Box.alertBox("Thất bại!","Số điện thoại không hợp lệ","Vui lòng thử lại");
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

    @FXML
    private void cancelButtonOnClicked(MouseEvent event) {
        clearDataPane();
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void deleteButtonOnClicked(MouseEvent event){
        clearDataCustomer();
        khachHang.setDefault();
    }
    public void addCtmButtonOnClicked(MouseEvent event) throws IOException {
        clearDataCustomer();
        idCtmLabel.setText(String.valueOf(khachHangRepo.getCountCustomer()));
        onEditDataCustomer(true);
        findCtmButton.setVisible(false);
        deleteButton.setVisible(false);
        addCtmButton.setVisible(false);
        saveButton.setVisible(true);
        cancel1Button.setVisible(true);
    }

    public void cancel1ButtonOnClicked(MouseEvent event){
        addCustomer = false;
        clearDataCustomer();
        addCtmButton.setVisible(true);
        findCtmButton.setVisible(true);
        deleteButton.setVisible(true);
        saveButton.setVisible(false);
        cancel1Button.setVisible(false);
    }

    public void saveButtonOnClicked(MouseEvent event){
        String name = nameCtmLabel.getText();
        String phone = phoneCtmLabel.getText();
        String address = addressCtmLabel.getText();
        if(name.isEmpty()||phone.isEmpty()||address.isEmpty()){
            Box.alertBox("Thông báo!","Cần nhập đầy đủ các trường","Vui lòng thử lại sau");
            return;
        }
        if(!Validate.validatePhoneVN(phone)){
            Box.alertBox("Thất bại!","Số điện thoại không hợp lệ","Vui lòng thử lại");
            return;
        }
        if(khachHangRepo.kiemTraTonTai(phone)!=-1){
            Box.alertBox("Thất bại!","Khách hàng đã tồn tại","Vui lòng thử lại");
            return;
        }
        addCustomer = true;
        onEditDataCustomer(false);
        saveButton.setVisible(false);
        cancel1Button.setVisible(false);
        saveButton.setDisable(true);
        deleteButton.setVisible(true);
        addCtmButton.setVisible(true);
        findCtmButton.setVisible(true);
    }
    public void tinhTienButtonOnClicked(MouseEvent event){
        tinhTienButtonIsClicked = true;
        String discount = discountLabel.getText();
        String discount1 = discount1Label.getText();
        Integer dc1 = 0;
        Integer dc = 0;
        if(!discount.isEmpty()&&!discount1.isEmpty()){
            if(!Validate.validateNumber(discount)||!Validate.validateNumber(discount1)){
                Box.alertBox("Thông báo!","Giá trị nhập phải là số","Vui lòng kiểm tra lại.");
            } else {
                dc = Integer.valueOf(discount);
                dc1 = Integer.valueOf(discount1);
            }
        } else if(discount.isEmpty()&&!discount1.isEmpty()){
            dc = 0;
            dc1 = dc1 = Integer.valueOf(discount1);
        } else if(!discount.isEmpty()&&discount1.isEmpty()){
            dc1 = 0;
            dc = Integer.valueOf(discount);
        }
        Integer totalNoVAT = subtotal - dc - subtotal * dc1/100;
        Integer vat = totalNoVAT/10;
        Integer total = (Integer) totalNoVAT + vat;
        vatLabel.setText(String.valueOf(vat));
        totalLabel.setText(String.valueOf(total));
    }

    public void exportButtonOnClicked(MouseEvent event){
        if(isFullFillCustomerPane() && tinhTienButtonIsClicked){
            String name = nameCtmLabel.getText();
            String phone = phoneCtmLabel.getText();
            String address = addressCtmLabel.getText();
            Integer idInvoice = Integer.valueOf(idPhieuXuatLabel.getText());
            Integer subTotalInvoice = Integer.valueOf(subTotalLabel.getText());
            Integer vatInvoice = Integer.valueOf(vatLabel.getText());
            Integer discountInvoice = 0;
            Integer discount1Invoice = 0;
            if(!discount1Label.getText().isEmpty()){
                discount1Invoice = Integer.valueOf(discount1Label.getText());
            }
            if(!discountLabel.getText().isEmpty()){
                discount1Invoice = Integer.valueOf(discountLabel.getText());
            }
            Integer totalInvoice = Integer.valueOf(totalLabel.getText());
            String exportDateInvoice = String.valueOf(ngayXuatLabel.getText());
            Integer idEmployeeInvoice = LoginController.idNhanVien;
            Integer idCustomerInvoice = Integer.valueOf(idCtmLabel.getText());
            if(addCustomer){
                khachHangRepo.addCustomer(new KhachHang(name,phone,address));
            }
            PhieuXuat phieuXuat = new PhieuXuat(idInvoice,subTotalInvoice,vatInvoice,discountInvoice,discount1Invoice,totalInvoice,Date.valueOf(exportDateInvoice),idEmployeeInvoice,idCustomerInvoice);
            phieuXuatRepo.addInvoice(phieuXuat);
            Box.alertBox("Thông báo!","Xuất hàng thành công","");
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            for(Item item : itemList){
                int index = 0;
                int quantity = item.getSoLuongItem();
                ObservableList<Integer> idList = FXCollections.observableArrayList();
                idList = thietBiRepo.findAllDeviceByModel(item.getModelItem());
                for(Integer i :idList){
                    if(index<quantity){
                        thietBiRepo.updatePhieuXuatThietBi(i,idInvoice);
                        index++;
                    } else {
                        break;
                    }
                }
            }
        } else {
            Box.alertBox("Thông báo!","Cần nhập đủ thông tin của khách hàng và tính tiền","Vui lòng thử lại");
        }
    }
}
