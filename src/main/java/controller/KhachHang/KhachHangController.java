package controller.KhachHang;

import entity.KhachHang;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lombok.SneakyThrows;
import repository.KhachHangRepository;
import repository.KhachHangRepository_impl;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KhachHangController implements Initializable {
    @FXML
    private TableView<KhachHang> table;

    @FXML
    private TableColumn<KhachHang, Integer> idColumn;

    @FXML
    private TableColumn<KhachHang, String> nameColumn;

    @FXML
    private TableColumn<KhachHang, String> phoneColumn;

    @FXML
    private TableColumn<KhachHang, String> addressColumn;

    @FXML
    private TextField nameLabel;

    @FXML
    private TextField phoneLabel;

    @FXML
    private TextField addressLabel;

    @FXML
    private Button editButton;

    @FXML
    private Button addButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button deleteButton;

    @FXML
    private ComboBox<String> truongSearch;
    @FXML
    private TextField duLieuSearch;
    @FXML
    private ImageView searchButton;

    @FXML
    private Label notificationLabel;

    static boolean editModeOnAction = false;

    static KhachHangRepository khachHangRepo = new KhachHangRepository_impl();
    @FXML
    ObservableList<KhachHang> khachHangList = FXCollections.observableArrayList();

    static KhachHang khachHang = new KhachHang();
    @FXML
    ObservableList<KhachHang> searchList = FXCollections.observableArrayList();

    static boolean isSelectionMode = true;

    ObservableList<String> listTruongSearch = FXCollections.observableArrayList("ID Khách Hàng", "Tên Khách Hàng");

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataCustomer();
    }

    public void clearDataPane() {
        nameLabel.clear();
        phoneLabel.clear();
        addressLabel.clear();
    }

    public void onEdit(boolean data) {
        nameLabel.setEditable(data);
        addressLabel.setEditable(data);
        phoneLabel.setEditable(data);
        if (data) {
            nameLabel.isFocused();
        }
    }

    public void loadDataCustomer() {
        deleteButton.setDisable(true);
        saveButton.setDisable(true);
        addButton.setDisable(false);
        editButton.setDisable(true);
        onEdit(false);
        khachHangList.clear();
        khachHangList.setAll(khachHangRepo.getAllDataCustomer());
        table.setItems(khachHangList);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idKhachHang"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("tenKhachHang"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneKhachHang"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("diaChiKhachHang"));
        truongSearch.valueProperty().set(null);
        duLieuSearch.setText("");
        truongSearch.setItems(listTruongSearch);
    }

    public void reloadPane(){
        clearDataPane();
        notificationLabel.setText("");
        duLieuSearch.setText("");
        saveButton.setDisable(true);
        addButton.setDisable(false);
        editButton.setDisable(true);
        deleteButton.setDisable(true);
        table.setItems(khachHangList);
    }

    public void setOnMouseClicked(MouseEvent event) {
        if (isSelectionMode) {
            khachHang = table.getSelectionModel().getSelectedItem();
            notificationLabel.setText("");
            editButton.setDisable(false);
            deleteButton.setDisable(false);
            if (khachHang != null) {
                nameLabel.setText(khachHang.getTenKhachHang());
                phoneLabel.setText(khachHang.getPhoneKhachHang());
                addressLabel.setText(khachHang.getDiaChiKhachHang());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông báo!");
            alert.setHeaderText("Bạn không thể lựa chọn lúc này.");
            alert.setContentText("Hoàn thành tác vụ hiện tại và ấn Lưu");
            alert.showAndWait();
        }
    }

    public void editButtonOnClicked(MouseEvent event) {
        notificationLabel.setText("Bạn đang chỉnh sửa thông tin khách hàng");
        isSelectionMode = false;
        editModeOnAction = true;
        editButton.setDisable(true);
        addButton.setDisable(true);
        deleteButton.setDisable(true);
        saveButton.setDisable(false);
        onEdit(true);
    }

    @SneakyThrows
    @FXML
    private void saveButtonOnClicked(MouseEvent event) {
        if (editModeOnAction) {
            String tenKH = nameLabel.getText();
            String phoneKH = phoneLabel.getText();
            String addressKH = addressLabel.getText();
            if (tenKH.isEmpty() || phoneKH.isEmpty() || addressKH.isEmpty()) {
                notificationLabel.setText("Bạn cần nhập đầy đủ các trường.");
                editButton.setDisable(true);
            } else {
                updateDataCustomer();
                notificationLabel.setText("Chỉnh sửa thành công!");
                clearDataPane();
                onEdit(false);
                editButton.setDisable(true);
                loadDataCustomer();
                isSelectionMode = true;
                editModeOnAction = false;
            }
        } else {
            String tenKH = nameLabel.getText();
            String phoneKH = phoneLabel.getText();
            String addressKH = addressLabel.getText();
            if (tenKH.isEmpty() || phoneKH.isEmpty() || addressKH.isEmpty()) {
                notificationLabel.setText("Bạn cần nhập đầy đủ các trường");
            } else {
                isSelectionMode = true;
                KhachHang ctm = new KhachHang(tenKH, phoneKH, addressKH);
                khachHangRepo.addCustomer(ctm);
                khachHangList.add(ctm);
                notificationLabel.setText("Thêm thành công!");
                loadDataCustomer();
            }
        }
    }

    public void updateDataCustomer() {
        KhachHang _ctm = new KhachHang();
        khachHang.setTenKhachHang(nameLabel.getText());
        khachHang.setPhoneKhachHang(phoneLabel.getText());
        khachHang.setDiaChiKhachHang(addressLabel.getText());
        khachHangRepo.updateDataCustomer(khachHang);
        if (!khachHangList.isEmpty()) {
            for (KhachHang ctm : khachHangList) {
                if (ctm.getIdKhachHang() == khachHang.getIdKhachHang()) {
                    _ctm = ctm;
                }
            }
            if (_ctm != null) {
                khachHangList.remove(_ctm);
                khachHangList.add(khachHang);
            }
        }
    }

    public void addButtonOnClicked(MouseEvent event) {
        notificationLabel.setText("Bạn đang thêm khách hàng mới");
        editModeOnAction = false;
        addButton.setDisable(true);
        editButton.setDisable(true);
        saveButton.setDisable(false);
        deleteButton.setDisable(true);
        isSelectionMode = false;
        clearDataPane();
        onEdit(true);
    }

    public void searchButtonOnClicked(MouseEvent mouseEvent) {
        boolean isValid = true;
        searchList.clear();
        String textSearch = duLieuSearch.getText().trim().toLowerCase();
        String fieldSearch = truongSearch.getValue();
        try {
            if (fieldSearch.equals("ID Khách Hàng")) {
                if(textSearch.isEmpty()){
                    table.setItems(khachHangList);
                } else {
                    Pattern pattern = Pattern.compile("\\d*");
                    Matcher matcher = pattern.matcher(textSearch);
                    if (!matcher.matches()&&!textSearch.isEmpty()) {
                        isValid = false;
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Thông báo!");
                        alert.setHeaderText("ID Khách Hàng chỉ bao gồm các kí tự số [0-9]");
                        alert.setContentText("Vui lòng nhập lại");
                        alert.show();
                    } else {
                        for (KhachHang currentKhachHang : khachHangList) {
                            if ((currentKhachHang.getIdKhachHang() == Integer.valueOf(textSearch))) {
                                    KhachHang clone = new KhachHang();
                                    clone.cloneKhachHang(currentKhachHang);
                                    searchList.add(clone);
                            }
                            table.setItems(searchList);
                        }
                    }
                }
            } else if (fieldSearch.equals("Tên Khách Hàng")) {
                for (KhachHang currentKhachHang : khachHangList) {
                    if ((currentKhachHang.getTenKhachHang().toLowerCase()).contains(textSearch)) {
                        KhachHang clone = new KhachHang();
                        clone.cloneKhachHang(currentKhachHang);
                        searchList.add(clone);
                    }
                }
                table.setItems(searchList);
            }
            if(searchList.isEmpty()&&isValid&&!textSearch.isEmpty()){
                table.setItems(khachHangList);
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
            return;
        }
    }

    public void deleteButtonOnClicked(MouseEvent event){
        KhachHang ctm = table.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xoá");
        alert.setHeaderText("Bạn có chắc chắn muốn xóa không?");
        alert.setContentText("Khi bạn ấn\"Có\" dữ liệu sẽ bị xóa không thể khôi phục.");
        ButtonType buttonYes = new ButtonType("Yes",ButtonBar.ButtonData.YES);
        ButtonType buttonNo = new ButtonType("No",ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(buttonYes,buttonNo);
        Optional<ButtonType> result = alert.showAndWait();
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Thông báo!");
        if(result.get().getButtonData() == ButtonBar.ButtonData.YES){
            int idKhachHang = ctm.getIdKhachHang();
            khachHangRepo.deleteCustomer(idKhachHang);
            khachHangList.remove(ctm);
            alert1.setContentText("Thành công");
            alert1.show();
            reloadPane();
        }
        else if(result.get().getButtonData() == ButtonBar.ButtonData.NO){
            alert1.setContentText("Thất bại");
            alert1.show();
        }

    }
}
