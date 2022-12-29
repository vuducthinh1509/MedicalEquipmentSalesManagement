package controller.KhoHang.XuatHang;

import controller.LoginPage;
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

    ObservableList<Item> itemList = FXCollections.observableArrayList();

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
        maNVXuatLabel.setText(nhanVienRepo.getInformationUser(LoginPage.idNhanVien).getMaNV());
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
        findCtmButton.setDisable(true);
        idCtmLabel.setEditable(false);
        deleteButton.setVisible(false);
        deleteButton.setDisable(true);
        saveButton.setVisible(true);
        saveButton.setDisable(false);
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
    public void tinhTienButtonOnClicked(MouseEvent event){
        tinhTienButtonIsClicked = true;
        String regex = "[0-9]+";
        String discount = discountLabel.getText();
        String discount1 = discount1Label.getText();
        if(discount.isEmpty()||discount1.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo!");
            alert.setHeaderText("Cần nhập đầy đủ các trường");
            alert.setContentText("Vui lòng thử lại sau");
            alert.showAndWait();
        } else {
            if(!discount.matches(regex)||!discount1.matches(regex)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo!");
                alert.setHeaderText("Giá trị nhập phải là số");
                alert.setContentText("Vui lòng kiểm tra lại.");
            } else {
                Integer dc = Integer.valueOf(discount);
                Integer dc1 = Integer.valueOf(discount1);
                Integer totalNoVAT = subtotal - dc - subtotal * dc1/100;
                Integer vat = totalNoVAT/10;
                Float total = (float) totalNoVAT+vat;
                Integer _total = Math.round(total / 1000+1) * 1000;
                vatLabel.setText(String.valueOf(vat));
                totalLabel.setText(String.valueOf(_total));
            }
        }
    }

    public void exportButtonOnClicked(MouseEvent event){
        if(isFullFillCustomerPane() && tinhTienButtonIsClicked){
            String maNVXuat = maNVXuatLabel.getText();
            Integer idInvoice = Integer.valueOf(idPhieuXuatLabel.getText());
            Double subTotalInvoice = Double.valueOf(subTotalLabel.getText());
            Integer vatInvoice = Integer.valueOf(vatLabel.getText());
            Double discountInvoice = Double.valueOf(discountLabel.getText());
            Double discount1Invoice = Double.valueOf(discount1Label.getText());
            Double totalInvoice = Double.valueOf(totalLabel.getText());
            String exportDateInvoice = String.valueOf(ngayXuatLabel.getText());
            Integer idEmployeeInvoice = LoginPage.idNhanVien;
            Integer idCustomerInvoice = Integer.valueOf(idCtmLabel.getText());
            PhieuXuat phieuXuat = new PhieuXuat(idInvoice,subTotalInvoice,vatInvoice,discountInvoice,discount1Invoice,totalInvoice,Date.valueOf(exportDateInvoice),idEmployeeInvoice,idCustomerInvoice);
            phieuXuatRepo.addInvoice(phieuXuat);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo!");
            alert.setHeaderText("Xuất hàng thành công");
            alert.showAndWait();
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo!");
            alert.setHeaderText("Cần nhập đủ thông tin của khách hàng và tính tiền");
            alert.setContentText("Vui lòng thử lại");
            alert.showAndWait();
        }
    }
}
