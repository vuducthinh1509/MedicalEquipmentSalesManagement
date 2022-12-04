package controller.XuatHang;

import controller.KhachHang.AddCustomerController;
import entity.Item;
import entity.KhachHang;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import repository.KhachHangRepository;
import repository.KhachHangRepository_impl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

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
    private TextField paidLabel;
    @FXML
    private TextField returnLabel;
    @FXML
    private TextField totalLabel;
    /* Các biến */

    static Integer subtotal = 0;

    KhachHang khachHang = new KhachHang();

    static KhachHangRepository khachHangRepo = new KhachHangRepository_impl();

    ObservableList<Item> itemList = FXCollections.observableArrayList();

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
        paidLabel.clear();
        returnLabel.clear();
        totalLabel.clear();
        table.setItems(null);
        itemList.clear();
    }
    public void loadDataPane(){
        for(Item item : itemList){
            Integer var = item.getSoLuongItem()*item.getGiaItem();
            item.setTotalPriceItem(var);
            subtotal += var;
        }
        table.setItems(itemList);
        subTotalLabel.setText(String.valueOf(subtotal));
        sttColumn.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue())+1));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("tenItem"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("modelItem"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("soLuongItem"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("giaItem"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("totalPriceItem"));
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
        idCtmLabel.setDisable(true);
        nameCtmLabel.setDisable(true);
        phoneCtmLabel.setDisable(true);
        addressCtmLabel.setDisable(true);
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

    public void addCtmButtonOnClicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/KhachHang/addCustomerPane.fxml"));
        Parent addCustomer = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Thêm Khách Hàng");
        Scene scene = new Scene(addCustomer);
        stage.setScene(scene);
        stage.show();

    }

}
