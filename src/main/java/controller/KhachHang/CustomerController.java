package controller.KhachHang;

import entity.KhachHang;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lombok.SneakyThrows;
import repository.KhachHangRepository;
import repository.KhachHangRepository_impl;
import utility.Box;
import utility.Validate;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerController implements Initializable {
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
    private Button cancelButton;

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

    ObservableList<String> listTruongSearch = FXCollections.observableArrayList("ID Khách Hàng", "Tên Khách Hàng","Số điện thoại");

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

    public void reloadPane(){
        clearDataPane();
        notificationLabel.setText("");
        duLieuSearch.setText("");
        saveButton.setDisable(true);
        addButton.setDisable(false);
        editButton.setDisable(true);
        table.setItems(khachHangList);
    }

    public void loadDataCustomer() {
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

    public void setOnMouseClicked(MouseEvent event) {
        if (isSelectionMode) {
            khachHang = table.getSelectionModel().getSelectedItem();
            notificationLabel.setText("");
            editButton.setDisable(false);
            if (khachHang != null) {
                nameLabel.setText(khachHang.getTenKhachHang());
                phoneLabel.setText(khachHang.getPhoneKhachHang());
                addressLabel.setText(khachHang.getDiaChiKhachHang());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Box.alertBox_Warning("Thông báo!","Bạn không thể lựa chọn lúc này.","Hoàn thành tác vụ hiện tại và ấn Lưu");
        }
    }

    public void editButtonOnClicked(MouseEvent event) {
        notificationLabel.setText("Bạn đang chỉnh sửa thông tin khách hàng");
        isSelectionMode = false;
        editModeOnAction = true;
        editButton.setVisible(false);
        addButton.setVisible(false);
        saveButton.setVisible(true);
        cancelButton.setVisible(true);
        onEdit(true);
    }

    @SneakyThrows
    @FXML
    private void saveButtonOnClicked(MouseEvent event) {
        String tenKH = nameLabel.getText();
        String phoneKH = phoneLabel.getText();
        String addressKH = addressLabel.getText();
        if (editModeOnAction) {
            if (tenKH.isEmpty() || phoneKH.isEmpty() || addressKH.isEmpty()) {
                Box.alertBox_None_Full_Fill();
            } else if (!Validate.validatePhoneVN(phoneKH)) {
                Box.alertBox("Thất bại!", "Số điện thoại không hợp lệ", "Vui lòng thử lại");
            } else {
                updateDataCustomer();
                notificationLabel.setText("Chỉnh sửa thành công!");
                isSelectionMode = true;
                editModeOnAction = false;
                onEdit(false);
                editButton.setVisible(true);
                addButton.setVisible(true);
                saveButton.setVisible(false);
                cancelButton.setVisible(false);
                loadDataCustomer();
                clearDataPane();
            }
        } else {
            if (tenKH.isEmpty() || phoneKH.isEmpty() || addressKH.isEmpty()) {
                Box.alertBox_None_Full_Fill();
            } else if (!Validate.validatePhoneVN(phoneKH)) {
                Box.alertBox("Thất bại!", "Số điện thoại không hợp lệ", "Vui lòng thử lại");
            } else if (khachHangRepo.kiemTraTonTai(phoneKH) != -1) {
                Box.alertBox("Thất bại!", "Khách hàng đã tồn tại", "Vui lòng thử lại");
            } else {
                isSelectionMode = true;
                KhachHang ctm = new KhachHang(tenKH, phoneKH, addressKH);
                khachHangRepo.addCustomer(ctm);
                khachHangList.add(ctm);
                notificationLabel.setText("Thêm thành công!");
                addButton.setVisible(true);
                editButton.setVisible(true);
                saveButton.setVisible(false);
                cancelButton.setVisible(false);
                editButton.setDisable(true);
                loadDataCustomer();
                clearDataPane();
            }
        }
    }

    public void cancelButtonOnClicked(MouseEvent event){
        isSelectionMode = true;
        clearDataPane();
        addButton.setVisible(true);
        editButton.setVisible(true);
        saveButton.setVisible(false);
        cancelButton.setVisible(false);
        editButton.setDisable(true);
        notificationLabel.setText("");
        onEdit(false);
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
        addButton.setVisible(false);
        editButton.setVisible(false);
        saveButton.setVisible(true);
        cancelButton.setVisible(true);
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
            } else if (fieldSearch.equals("Tên Khách Hàng")) {
                for (KhachHang currentKhachHang : khachHangList) {
                    if ((currentKhachHang.getTenKhachHang().toLowerCase()).contains(textSearch)) {
                        KhachHang clone = new KhachHang();
                        clone.cloneKhachHang(currentKhachHang);
                        searchList.add(clone);
                    }
                }
                table.setItems(searchList);
            } else if (fieldSearch.equals("Số điện thoại")){
                for (KhachHang currentKhachHang : khachHangList) {
                    if ((currentKhachHang.getPhoneKhachHang().toLowerCase()).contains(textSearch)) {
                        KhachHang clone = new KhachHang();
                        clone.cloneKhachHang(currentKhachHang);
                        searchList.add(clone);
                    }
                }
                table.setItems(searchList);
            }
            if(searchList.isEmpty()&&isValid&&!textSearch.isEmpty()){
                table.setItems(khachHangList);
                Box.alertBox_No_Result();
            }
        } catch (NullPointerException ex) {
            Box.alertBox_None_Field_Search();
        }
    }

    public void deleteButtonOnAction(ActionEvent event){
        KhachHang ctm = table.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xoá");
        alert.setHeaderText("Bạn có chắc chắn muốn xóa không?");
        alert.setContentText("Khi bạn ấn\"Có\" dữ liệu sẽ bị xóa không thể khôi phục.");
        ButtonType buttonYes = new ButtonType("Yes",ButtonBar.ButtonData.YES);
        ButtonType buttonNo = new ButtonType("No",ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(buttonYes,buttonNo);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get().getButtonData() == ButtonBar.ButtonData.YES){
            int idKhachHang = ctm.getIdKhachHang();
            khachHangRepo.deleteCustomer(idKhachHang);
            khachHangList.remove(ctm);
            Box.alertBox("Thành công!","Xóa thành công","");
            reloadPane();
        }

    }
}
