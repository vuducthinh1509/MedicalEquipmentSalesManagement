package controller.XuatHang;

import entity.Item;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

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
    private Button addCtmLabel;
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

    ObservableList<Item> itemList = FXCollections.observableArrayList();

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        loadDataPane();
//    }
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

    @FXML
    private void cancelButtonOnClicked(MouseEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
