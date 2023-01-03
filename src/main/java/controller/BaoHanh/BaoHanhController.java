package controller.BaoHanh;

import entity.PhieuBaoHanh;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import repository.PhieuBaoHanhRepository;
import repository.PhieuBaoHanhRepository_impl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BaoHanhController implements Initializable {
    @FXML
    private TableView<PhieuBaoHanh> table;

    @FXML
    private TableColumn<PhieuBaoHanh, Number> sttColumn;
    @FXML
    private TableColumn<PhieuBaoHanh, Integer> idColumn;
    @FXML
    private TableColumn<PhieuBaoHanh, String> serialThietBiColumn;
    @FXML
    private TableColumn<PhieuBaoHanh, String> trangThaiColumn;
    @FXML
    private TableColumn<PhieuBaoHanh, String> tenKhachHangColumn;
    @FXML
    private TableColumn<PhieuBaoHanh, String> ngayBaoHanhColumn;
    @FXML
    private TableColumn<PhieuBaoHanh, String> ngayBanGiaoColumn;

    @FXML
    private Pane mainPane;

    @FXML
    private Button createButton;

    static PhieuBaoHanhRepository phieuBaoHanhRepo = new PhieuBaoHanhRepository_impl();

    ObservableList<PhieuBaoHanh> phieuBaoHanhList = FXCollections.observableArrayList();

    public void createButtonOnClicked(MouseEvent event) throws IOException {
        Pane taoPhieuBaoHanhPane = FXMLLoader.load(getClass().getResource("/view/BaoHanh/TaoPhieuBaoHanhPane.fxml"));
        mainPane.getChildren().add(taoPhieuBaoHanhPane);
        mainPane.toFront();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataTable();
    }

    public void loadDataTable(){
        phieuBaoHanhList.clear();
        phieuBaoHanhList.addAll(phieuBaoHanhRepo.layTatCaDuLieuPhieuBaoHanh());
        for(PhieuBaoHanh i : phieuBaoHanhList){
            i.loadData();
        }
        table.setItems(phieuBaoHanhList);
        sttColumn.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue())+1));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        serialThietBiColumn.setCellValueFactory(new PropertyValueFactory<>("serialThietBi"));
        trangThaiColumn.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
        tenKhachHangColumn.setCellValueFactory(new PropertyValueFactory<>("tenKhachHang"));
        ngayBaoHanhColumn.setCellValueFactory(new PropertyValueFactory<>("ngayBaoHanh"));
        ngayBanGiaoColumn.setCellValueFactory(new PropertyValueFactory<>("ngayBanGiao"));
    }

    public void xemChiTietPhieuBaoHanh(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/BaoHanh/ChiTietPBHPane.fxml"));
        Parent chiThietPBH  = loader.load();
        ChiTietPBHController chiTietPBHController = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Thông tin thiết bị");
        Scene scene = new Scene(chiThietPBH);
        stage.setScene(scene);
        stage.show();
    }
}
