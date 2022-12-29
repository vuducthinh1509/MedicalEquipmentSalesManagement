package controller.ExportInvoice;

import entity.Item;
import entity.PhieuXuat;
import entity.ThietBi;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import repository.PhieuXuatRepository;
import repository.PhieuXuatRepository_impl;
import repository.ThietBiRepository;
import repository.ThietBiRepository_impl;

import java.net.URL;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

public class DetailInvoice implements Initializable {
    @FXML
    private TableView<ThietBi> table;
    @FXML
    private TableColumn<ThietBi, Number> sttColumn;
    @FXML
    private TableColumn<ThietBi, String> tenColumn;
    @FXML
    private TableColumn<ThietBi, String> modelColumn;
    @FXML
    private TableColumn<ThietBi, String> serialColumn;
    @FXML
    private TableColumn<ThietBi, Integer> mauColumn;
    @FXML
    private TableColumn<ThietBi,String> kichThuocColumn;
    @FXML
    private TableColumn<ThietBi, Integer> giaColumn;
    @FXML
    private TextField idLabel;
    @FXML
    private TextField subTotalLabel;
    @FXML
    private TextField vatLabel;
    @FXML
    private TextField discountLabel;
    @FXML
    private TextField discount1Label;
    @FXML
    private TextField totalLabel;
    @FXML
    private DatePicker exportDateLabel;
    @FXML
    private TextField idCtmLabel;
    @FXML
    private TextField nameCtmLabel;
    @FXML
    private TextField phoneCtmLabel;
    @FXML
    private TextField maNVLabel;
    @FXML
    private TextField nameEplLabel;

    PhieuXuat invoice = new PhieuXuat();

    @FXML
    ObservableList<ThietBi> thietBiList = FXCollections.observableArrayList();

    static ThietBiRepository thietBiRepo = new ThietBiRepository_impl();

    static PhieuXuatRepository phieuXuatRepo = new PhieuXuatRepository_impl();
    ObservableList<Integer> idList = FXCollections.observableArrayList();

    public void setInvoice(PhieuXuat clone){
        invoice.cloneInvoice(clone);
    }

    public void setInvoiceByID(Integer id){
        PhieuXuat clone = new PhieuXuat();
        clone = phieuXuatRepo.getDetailInvoiceByID(id);
        clone.setNameCtmAndEpl();
        setInvoice(clone);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataInvoice();
    }
    public void loadDataInvoice(){
        idList = thietBiRepo.findAllDeviceByIdInvoice(invoice.getIdInvoice());
        for(Integer _id : idList){
            thietBiList.add(thietBiRepo.chiTietThietBi(_id));
        }
        table.setItems(thietBiList);
        Locale loc = new Locale("nv","VN");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(loc);
        idLabel.setText(String.valueOf(invoice.getIdInvoice()));
        subTotalLabel.setText(formatter.format(invoice.getSubTotalInvoice()));
        vatLabel.setText(String.valueOf(invoice.getVatInvoice()));
        discountLabel.setText(formatter.format(invoice.getDiscountInvoice()));
        discount1Label.setText(String.valueOf(invoice.getDiscount1Invoice()));
        totalLabel.setText(formatter.format(invoice.getTotalInvoice()));
        if(invoice.getExportDateInvoice()!=null){
            exportDateLabel.setValue(LocalDate.parse(String.valueOf(invoice.getExportDateInvoice())));
        }
        idCtmLabel.setText(String.valueOf(invoice.getIdCustomerInvoice()));
        maNVLabel.setText(String.valueOf(invoice.getMaNV()));
        nameCtmLabel.setText(invoice.getNameCustomer());
        nameEplLabel.setText(invoice.getNameEmployee());
        phoneCtmLabel.setText(invoice.getPhoneCustomer());
        sttColumn.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue())+1));
        tenColumn.setCellValueFactory(new PropertyValueFactory<>("tenThietBi"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("modelThietBi"));
        serialColumn.setCellValueFactory(new PropertyValueFactory<>("serialThietBi"));
        mauColumn.setCellValueFactory(new PropertyValueFactory<>("mauThietBi"));
        kichThuocColumn.setCellValueFactory(new PropertyValueFactory<>("kichThuocThietBi"));
        giaColumn.setCellValueFactory(new PropertyValueFactory<>("giaThietBi"));
    }
    @FXML
    private void cancelButtonOnClicked(MouseEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
