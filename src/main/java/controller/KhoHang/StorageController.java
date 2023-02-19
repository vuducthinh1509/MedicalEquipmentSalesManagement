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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.*;
import utility.Box;
import utility.SQLCommand;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class StorageController implements Initializable {
    @FXML
    private Pane mainPane;
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
    private TableColumn<ThietBi,Double>  giaColumn;
    @FXML
    private TableColumn<ThietBi,String>  trangThaiColumn;
    @FXML
    private Button addProductButton;

    @FXML
    private Button reloadTableButton;
    @FXML
    private Button exportButton;
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
        ObservableList<ThietBi> thietBiList_temp = FXCollections.observableArrayList();
        duLieuTraCuu = duLieuF.getText();
        String truongTraCuu = truongTraCuuF.getValue();
        try{
            if(truongTraCuu.equals("Tên")){
                thietBiList_temp.addAll(thietBiRepo.timThietBiTheoTruong(SQLCommand.Thiet_Bi_QUERY_LAY_THONG_TIN_BY_tenThietBi, duLieuTraCuu));
            } else if(truongTraCuu.equals("Model")){
                thietBiList_temp.addAll(thietBiRepo.timThietBiTheoTruong(SQLCommand.Thiet_Bi_QUERY_LAY_THONG_TIN_BY_modelThietBi,duLieuTraCuu));
            }else if(truongTraCuu.equals("Trạng thái")){
                thietBiList_temp.addAll(thietBiRepo.timThietBiTheoTruong(SQLCommand.Thiet_Bi_QUERY_LAY_THONG_TIN_BY_trangThaiThietBi,duLieuTraCuu));
            }
            if(thietBiList_temp.size()==0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Không tìm thấy kết quả khả dụng");
                alert.show();
            }
            else {
                table.setItems(thietBiList_temp);
            }
        }
        catch (NullPointerException ex){
            Box.alertBox_None_Field_Search();
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
        loader.setLocation(getClass().getResource("/view/Kho/XemChiTietThietBi.fxml"));
        Parent chiTietTB  = loader.load();
        XemChiTietController chiTietTBController = loader.getController();
        ThietBi selectedThietBi = table.getSelectionModel().getSelectedItem();
        if (selectedThietBi == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo!");
            alert.setHeaderText("Không thiết bị nào được chọn.");
            alert.setContentText("Vui lòng chọn lại.");
            alert.show();
            return;
        }
        chiTietTBController.setThietBi(selectedThietBi.getIdThietBi());
        Stage stage = new Stage();
        stage.setTitle("THÔNG TIN CHI TIẾT THIẾT BỊ");
        Scene scene = new Scene(chiTietTB);
        stage.setScene(scene);
        stage.show();
    }

    public void themThietBi(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Kho/ThemThietBi.fxml"));
        Parent themThietBi = loader.load();
        Stage stage = new Stage();
        stage.setTitle("THÊM THIẾT BỊ");
        Scene scene = new Scene(themThietBi);
        stage.setScene(scene);
        stage.show();
    }

    public void chinhSuaThietBi (ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Kho/ChinhSuaChiTietThietBi.fxml"));
        Parent chinhSuaThietBiView = loader.load();
        ChinhSuaThongTinController controller = loader.getController();
        ThietBi selected = table.getSelectionModel().getSelectedItem();
        if(selected == null){
            Box.alertBox_None_Selection("thiết bị");
            return;
        }
        controller.setChinhSuaThietBi(selected);
        Stage stage = new Stage();
        stage.setTitle("CHỈNH SỬA CHI TIẾT THIẾT BỊ");
        Scene scene = new Scene(chinhSuaThietBiView);
        stage.setScene(scene);
        stage.show();
    }
    public void xoaThietBi(ActionEvent e) throws IOException {
        ThietBi thietBi = table.getSelectionModel().getSelectedItem();
        if (thietBi == null) {
            Box.alertBox_None_Selection("thiết bị");
            return;
        }
        int _idThietBi= thietBi.getIdThietBi();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xóa thiết bị");
        alert.setHeaderText("Bạn có thực sự muốn xóa thiết bị này ?");
        alert.setContentText("Nếu bạn đồng ý xóa dữ liệu sẽ bị xóa và không thể khôi phục!");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == null) {}
        else if (option.get() == ButtonType.OK) {
            thietBiRepo.xoaThietBi(_idThietBi);
            loadDataThietBi();
        } else if (option.get() == ButtonType.CANCEL) {}

    }

    public void exportButtonOnClicked(MouseEvent event) throws IOException{
        Pane exportPane = FXMLLoader.load(getClass().getResource("/view/Kho/XuatHang/XuatHangPane.fxml"));
        mainPane.getChildren().add(exportPane);
        mainPane.toFront();
    }
}