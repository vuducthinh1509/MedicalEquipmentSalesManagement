package controller.KhoHang.XuatHang;

import entity.Item;
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
import lombok.SneakyThrows;
import repository.ItemReposioty_impl;
import repository.ItemRepository;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ExportController implements Initializable {
    @FXML
    private TableView<Item> table;
    @FXML
    private TableView<Item> tableCart;
    @FXML
    private TableColumn<Item, Number> sttColumn;
    @FXML
    private TableColumn<Item, String> tenColumn;
    @FXML
    private TableColumn<Item, String> modelColumn;
    @FXML
    private TableColumn<Item, String> xuatXuColumn;
    @FXML
    private TableColumn<Item, Integer> soLuongColumn;
    @FXML
    private TableColumn<Item, Integer> giaColumn;
    @FXML
    private TableColumn<ThietBi, Number> sttColumnCart;
    @FXML
    private TableColumn<ThietBi, String> tenColumnCart;
    @FXML
    private TableColumn<ThietBi, String> modelColumnCart;
    @FXML
    private TableColumn<ThietBi, String> xuatXuColumnCart;
    @FXML
    private TableColumn<ThietBi, Integer> giaColumnCart;
    @FXML
    private TableColumn<ThietBi, Integer> soLuongColumnCart;
    @FXML
    private TableColumn<ThietBi, Number> thanhTienColumnCart;
    @FXML
    private Label tongTienThanhToanLabel;
    private String duLieuTraCuu="";
    static ItemRepository itemRepo = new ItemReposioty_impl();
    @FXML
    ObservableList<Item> itemList = FXCollections.observableArrayList();

    @FXML
    ObservableList<Item> selectedItemList = FXCollections.observableArrayList();

    @FXML
    ObservableList<Item> searchList = FXCollections.observableArrayList();
    @FXML
    private TextField duLieuF;
    @FXML
    private ComboBox<String> truongTraCuuF;
    ObservableList<String> listTruongTraCuu = FXCollections.observableArrayList("Tên","Model");
    public void searchClick(MouseEvent mouseEvent) {
        searchList.clear();
        String search_text = duLieuF.getText().trim().toLowerCase();
        String sc = truongTraCuuF.getValue();
        try{
            if(sc.equals("Tên")){
                for(Item a : itemList){
                    if((a.getTenItem().toLowerCase()).contains(search_text)){
                        Item cloneItem = new Item();
                        cloneItem.copyItem(a);
                        searchList.add(cloneItem);
                    }
                }
                table.setItems(searchList);
            }
            else if(sc.equals("Model")){
                for(Item a : itemList){
                    if((a.getModelItem().toLowerCase()).contains(search_text)){
                        Item cloneItem = new Item();
                        cloneItem.copyItem(a);
                        searchList.add(cloneItem);
                    }
                }
                table.setItems(searchList);
            }
        }catch(NullPointerException ex){
            for(Item a : itemList){
                if((a.getTenItem().toLowerCase()).contains(search_text)){
                    Item cloneItem = new Item();
                    cloneItem.copyItem(a);
                    searchList.add(cloneItem);
                }
            }
            table.setItems(searchList);
//            Alert m = new Alert(Alert.AlertType.INFORMATION);
//            m.setTitle("Thông báo!");
//            m.setHeaderText("Chưa chọn trường tìm kiếm!");
//            m.setContentText("Mời chọn lại!");
//            m.show();
            return;
        }
    }
    @FXML
    public void loadDataItem(){
        itemList.clear();
        itemList.addAll(itemRepo.loadDataItem());
        table.setItems(itemList);
        sttColumn.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue())+1));
        tenColumn.setCellValueFactory(new PropertyValueFactory<>("tenItem"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("modelItem"));
        xuatXuColumn.setCellValueFactory(new PropertyValueFactory<>("xuatXuItem"));
        soLuongColumn.setCellValueFactory(new PropertyValueFactory<>("soLuongItem"));
        giaColumn.setCellValueFactory(new PropertyValueFactory<>("giaItem"));
        truongTraCuuF.valueProperty().set(null);
        duLieuF.setText("");
        truongTraCuuF.setItems(listTruongTraCuu);
    }

    public Integer tinhTongGiaTriDonHang(){
        Integer total = 0;
        for(Item item: selectedItemList){
            total += item.getTotalPriceItem();
        }
        return total;
    }

    @FXML
    public void loadItemInCart(){
        for(Item item:selectedItemList){
            item.setTotalPriceItem(item.getSoLuongItem() * item.getGiaItem());
        }
        tongTienThanhToanLabel.setText(String.valueOf(tinhTongGiaTriDonHang()));
        tableCart.setItems(selectedItemList);
        sttColumnCart.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(tableCart.getItems().indexOf(column.getValue())+1));
        tenColumnCart.setCellValueFactory(new PropertyValueFactory<>("tenItem"));
        modelColumnCart.setCellValueFactory(new PropertyValueFactory<>("modelItem"));
        xuatXuColumnCart.setCellValueFactory(new PropertyValueFactory<>("xuatXuItem"));
        soLuongColumnCart.setCellValueFactory(new PropertyValueFactory<>("soLuongItem"));
        giaColumnCart.setCellValueFactory(new PropertyValueFactory<>("giaItem"));
        thanhTienColumnCart.setCellValueFactory(new PropertyValueFactory<>("totalPriceItem"));
//        thanhTienColumnCart.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(tableCart.getItems().get(tableCart.getItems().indexOf(column.getValue())).getGiaItem()
//                *tableCart.getItems().get(tableCart.getItems().indexOf(column.getValue())).getSoLuongItem()));
    }
    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedItemList.clear();
        loadDataItem();
    }

    public void addItemToCart(ActionEvent event){
        Item selectedItem =  table.getSelectionModel().getSelectedItem();
        if(selectedItem==null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Chưa chọn thiết bị");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        TextInputDialog td = new TextInputDialog();
        td.setTitle("Input");
        td.setHeaderText("Nhập số lượng:");
        String quantity;
        if(td.showAndWait().isPresent()){
            quantity = td.getEditor().getText();
            if(!quantity.matches("^\\d+$")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo!");
                alert.setHeaderText("Số lượng không hợp lệ. Chỉ nhập số >=0");
                alert.setContentText("Vui lòng chọn lại");
                alert.show();
            }
            else {
                Integer quantitySelected = Integer.parseInt(quantity);
                if(selectedItem.getSoLuongItem() < quantitySelected || quantitySelected<=0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo!");
                    alert.setHeaderText("Số lượng không hợp lệ (<="+ selectedItem.getSoLuongItem()+")");
                    alert.setContentText("Vui lòng chọn lại");
                    alert.show();
                    return;
                }
                else {
                    Item _item = new Item();
                    selectedItem.setSoLuongItem(quantitySelected);
                    if(!selectedItemList.isEmpty()){
                        for(Item item : selectedItemList){
                            if(item.getModelItem().equals(selectedItem.getModelItem())){
                                _item = item;
                                // nó sẽ bị giật giật ở bảng giỏ hàng
//                        Platform.runLater(() ->  selectedItemList.remove(item));
                            }
                        }
                    }
                    if(_item!=null){
                        selectedItemList.remove(_item);
                    }
                    selectedItemList.add(selectedItem);
                    loadItemInCart();
                    loadDataItem();
                    return;
                }
            }
        }
    }

    public void payButtonOnClicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/KhoHang/XuatHang/invoicePane.fxml"));
        Parent invoicePane = loader.load();
        InvoiceController invoiceController = loader.getController();
        for(Item item : selectedItemList){
            invoiceController.itemList.add(item);
        }
        invoiceController.loadDataItem();
        invoiceController.loadDataPane();
        Stage stage = new Stage();
        stage.setTitle("Đơn hàng");
        Scene scene = new Scene(invoicePane);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void chiTietModel(ActionEvent event) throws IOException{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/KhoHang/XuatHang/detailModelPane.fxml"));
            Parent detailModel  = loader.load();
            DetailModelController detailModelController = loader.getController();
            Item selectedItem = table.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo!");
                alert.setHeaderText("Không thiết bị nào được chọn.");
                alert.setContentText("Vui lòng chọn lại.");
                alert.show();
                return;
            } else {
                detailModelController.modelThietBi = selectedItem.getModelItem();
                detailModelController.loadDataPane();
            }
            Stage stage = new Stage();
            stage.setTitle("Thông tin thiết bị");
            Scene scene = new Scene(detailModel);
            stage.setScene(scene);
            stage.show();
    }
}
