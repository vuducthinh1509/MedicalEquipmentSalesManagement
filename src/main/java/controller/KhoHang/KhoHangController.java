package controller.KhoHang;

import entity.ThietBi;
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
import lombok.SneakyThrows;
import repository.*;
import utility.SQLCommand;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class KhoHangController implements Initializable {
    @FXML
    private TableView<ThietBi> table;
    @FXML
    private TableColumn<ThietBi,Integer>  idColumn;
    @FXML
    private TableColumn<ThietBi,String>  tenColumn;
    @FXML
    private TableColumn<ThietBi,String>  modelColumn;
    @FXML
    private TableColumn<ThietBi,String>  serialColumn;
    @FXML
    private TableColumn<ThietBi,String>  mauColumn;
    @FXML
    private TableColumn<ThietBi,String>  kichThuocColumn;
    @FXML
    private TableColumn<ThietBi,String>  giaColumn;
    @FXML
    private TableColumn<ThietBi,String>  trangThaiColumn;
    @FXML
    private Button addProductButton;

    @FXML
    private Button reloadTableButton;
    @FXML
    private ComboBox<String> truongTraCuuF;
    @FXML
    private TextField duLieuF;
    @FXML
    private ImageView confirmF;
    @FXML
    ObservableList<ThietBi> thietBiList = FXCollections.observableArrayList();
    private String duLieuTraCuu="";
    static ThietBiRepository thietBiRepo = new ThietBiRepository_impl();
    @SneakyThrows
    @FXML
    private void findF(MouseEvent event) {
        thietBiList.clear();
        duLieuTraCuu=duLieuF.getText();
        String truongTraCuu = truongTraCuuF.getValue();
        try{
            if(truongTraCuu.equals("Tên")){
                thietBiList.addAll(thietBiRepo.timThietBiTheoTruong(SQLCommand.Thiet_Bi_QUERY_LAY_THONG_TIN_BY_tenThietBi, duLieuTraCuu));
            } else if(truongTraCuu.equals("Model")){
                thietBiList.addAll(thietBiRepo.timThietBiTheoTruong(SQLCommand.Thiet_Bi_QUERY_LAY_THONG_TIN_BY_modelThietBi,duLieuTraCuu));
            }else if(truongTraCuu.equals("Trạng thái")){
                thietBiList.addAll(thietBiRepo.timThietBiTheoTruong(SQLCommand.Thiet_Bi_QUERY_LAY_THONG_TIN_BY_trangThaiThietBi,duLieuTraCuu));
            }
            table.setItems(thietBiList);
        }
        catch (NullPointerException ex){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Bạn cần chọn trường tra cứu");
            alert.show();
            table.setItems(thietBiList);
            return;
        }
    }
    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataThietBi();
    }


    ObservableList<String> listTruongTraCuu = FXCollections.observableArrayList("Tên","Model","Trạng thái");
    @FXML
    private void loadDataThietBi() {
        thietBiList.clear();
        thietBiList.addAll(thietBiRepo.loadDataThietBi());
        table.setItems(thietBiList);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idThietBi"));
        tenColumn.setCellValueFactory(new PropertyValueFactory<>("tenThietBi"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("modelThietBi"));
        serialColumn.setCellValueFactory(new PropertyValueFactory<>("serialThietBi"));
        mauColumn.setCellValueFactory(new PropertyValueFactory<>("mauThietBi"));
        kichThuocColumn.setCellValueFactory(new PropertyValueFactory<>("kichThuocThietBi"));
        giaColumn.setCellValueFactory(new PropertyValueFactory<>("giaThietBi"));
        trangThaiColumn.setCellValueFactory(new PropertyValueFactory<>("trangThaiThietBi"));
        truongTraCuuF.valueProperty().set(null);
        duLieuF.setText("");
        truongTraCuuF.setItems(listTruongTraCuu);
    }

    public void chiTietThietBi(ActionEvent e) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/KhoHang/chiTietThietBi.fxml"));
        Parent chiTietTB  = loader.load();
        ChiTietThietBiController chiTietTBController = loader.getController();
        ThietBi selectedThietBi = table.getSelectionModel().getSelectedItem();
        if (selectedThietBi == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không thiết bị nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        chiTietTBController.setThietBi(selectedThietBi);
        Stage stage = new Stage();
        stage.setTitle("Thông tin thiết bị");
        Scene scene = new Scene(chiTietTB);
        stage.setScene(scene);
        stage.show();
    }

    public void themThietBi(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/KhoHang/themThietBi.fxml"));
        Parent themThietBi = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Thêm thiết bị");
        Scene scene = new Scene(themThietBi);
        stage.setScene(scene);
        stage.show();
    }
}