package controller.PhieuXuat;

import controller.KhachHang.DetailCustomerController;
import entity.KhachHang;
import entity.PhieuXuat;
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
import javafx.stage.Stage;
import repository.*;
import utility.Box;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExportInvoiceController implements Initializable {
    @FXML
    private TableView<PhieuXuat> table;
    @FXML
    private TableColumn<PhieuXuat, Number> sttColumn;

    @FXML
    private TableColumn<PhieuXuat,Integer> idColumn;
    @FXML
    private TableColumn<PhieuXuat,Double> totalColumn;
    @FXML
    private TableColumn<PhieuXuat,String> nameCtmColumn;
    @FXML
    private TableColumn<PhieuXuat,String> nameEplColumn;
    @FXML
    private TableColumn<PhieuXuat, Date> dateColumn;

    @FXML
    private ComboBox<String> truongSearch;
    @FXML
    private TextField duLieuSearch;
    @FXML
    private ImageView searchButton;

    ObservableList<String> fieldSearchList = FXCollections.observableArrayList("ID Khách Hàng", "Tên Khách Hàng","Số điện thoại");
    @FXML
    ObservableList<PhieuXuat> invoiceList = FXCollections.observableArrayList();

    @FXML
    ObservableList<PhieuXuat> searchList = FXCollections.observableArrayList();

    static PhieuXuatRepository phieuXuatRepo = new PhieuXuatRepository_impl();

    static KhachHangRepository khachHangRepo = new KhachHangRepository_impl();

    static ThietBiRepository thietBiRepo = new ThietBiRepository_impl();

    KhachHang khachHang = new KhachHang();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataAllInvoice();
    }

    public void loadDataAllInvoice(){
        invoiceList.clear();
        invoiceList.setAll(phieuXuatRepo.getAllDataInvoice());
        for(PhieuXuat phieuXuat:invoiceList){
            phieuXuat.setNameCtmAndEpl();
        }
        table.setItems(invoiceList);
        sttColumn.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue())+1));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idInvoice"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("totalInvoice"));
        nameCtmColumn.setCellValueFactory(new PropertyValueFactory<>("nameCustomer"));
        nameEplColumn.setCellValueFactory(new PropertyValueFactory<>("nameEmployee"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("exportDateInvoice"));
        truongSearch.valueProperty().set(null);
        duLieuSearch.setText("");
        truongSearch.setItems(fieldSearchList);
    }

    public void detailInvoice(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/PhieuXuat/DetailInvoice.fxml"));
        Parent detailInvoice  = loader.load();
        DetailInvoice detailInvoiceController = loader.getController();
        PhieuXuat selectedInvoice = table.getSelectionModel().getSelectedItem();
        if (selectedInvoice == null) {
            Box.alertBox_None_Selection("phiếu xuất");
            return;
        }
        detailInvoiceController.setInvoice(selectedInvoice);
        detailInvoiceController.loadDataInvoice();
        Stage stage = new Stage();
        stage.setTitle("CHI TIẾT PHIẾU XUẤT");
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
        PhieuXuat selectedInvoice = table.getSelectionModel().getSelectedItem();
        if (selectedInvoice == null) {
            Box.alertBox_None_Selection("phiếu xuất");
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

    public void deleteInvoice(ActionEvent event) throws IOException{
        PhieuXuat phieuXuat = table.getSelectionModel().getSelectedItem();
        if (phieuXuat == null) {
            Box.alertBox_None_Selection("phiếu xuất");
            return;
        }
        int id = phieuXuat.getIdInvoice();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xóa thiết bị");
        alert.setHeaderText("Bạn có thực sự muốn xóa phiếu xuất này ?");
        alert.setContentText("Nếu bạn đồng ý xóa dữ liệu sẽ bị xóa và không thể khôi phục!");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == null) {}
        else if (option.get() == ButtonType.OK) {
            phieuXuatRepo.deleteInvoice(id);
            ObservableList<Integer> idList = FXCollections.observableArrayList();
            idList = thietBiRepo.findAllDeviceByIdInvoice(id);
            for(Integer _id : idList){
                thietBiRepo.updatePhieuXuatThietBi_Delete(_id);
            }
            loadDataAllInvoice();
        } else if (option.get() == ButtonType.CANCEL) {}
    }

    public void searchButtonOnClicked(MouseEvent event) {
        boolean isValid = true;
        searchList.clear();
        String text = duLieuSearch.getText().trim().toLowerCase();
        String field = truongSearch.getValue();
        try {
            if (field.equals("ID Khách Hàng")) {
                if(text.isEmpty()){
                    table.setItems(invoiceList);
                } else {
                    Pattern pattern = Pattern.compile("\\d*");
                    Matcher matcher = pattern.matcher(text);
                    if (!matcher.matches()&&!text.isEmpty()) {
                        isValid = false;
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Thông báo!");
                        alert.setHeaderText("ID Khách Hàng chỉ bao gồm các kí tự số [0-9]");
                        alert.setContentText("Vui lòng nhập lại");
                        alert.show();
                    } else {
                        for (PhieuXuat cur : invoiceList) {
                            if ((cur.getIdCustomerInvoice() == Integer.valueOf(text))) {
                                PhieuXuat clone = new PhieuXuat();
                                clone.cloneInvoice(cur);
                                searchList.add(clone);
                            }
                            table.setItems(searchList);
                        }
                    }
                }
            } else if (field.equals("Tên Khách Hàng")) {
                for (PhieuXuat curr : invoiceList) {
                    if ((curr.getNameCustomer().toLowerCase()).contains(text)) {
                        PhieuXuat clone = new PhieuXuat();
                        clone.cloneInvoice(curr);
                        searchList.add(clone);
                    }
                }
                table.setItems(searchList);
            } else if (field.equals("Số điện thoại")){
                for (PhieuXuat curr : invoiceList) {
                    if ((curr.getPhoneCustomer().toLowerCase()).contains(text)) {
                        PhieuXuat clone = new PhieuXuat();
                        clone.cloneInvoice(curr);
                        searchList.add(clone);
                    }
                }
                table.setItems(searchList);
            }
            if(searchList.isEmpty()&&isValid&&!text.isEmpty()){
                table.setItems(invoiceList);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo!");
                alert.setHeaderText("Không tìm thấy kết quả phù hợp");
                alert.setContentText("Vui lòng thử lại");
                alert.showAndWait();
            }
        } catch (NullPointerException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo!");
            alert.setHeaderText("Chưa chọn trường tìm kiếm!");
            alert.setContentText("Vui lòng chọn lại");
            alert.show();
        }
    }
}
