package controller.QuanLy;

import controller.TaiKhoan.LoginController;
import entity.NhanVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.NhanVienRepository;
import repository.NhanVienRepository_impl;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditNV implements Initializable {
    public Integer idNhanVien;
    @FXML
    private TextField hoTenLabel;

    @FXML
    private ComboBox gioiTinhComboBox;

    @FXML
    private DatePicker ngaySinhLabel;

    @FXML
    private TextField diaChiThuongTruLabel;

    @FXML
    private TextField CCCDLabel;

    @FXML
    private TextField soDienThoaiLabel;

    @FXML
    private TextField emailLabel;

    @FXML
    private DatePicker ngayVaoLamLabel;


    @FXML
    private Label labelMaNV;

    @FXML
     private  Label gioiTinh1;

    @FXML
    private  Label chucvu1 ;

    @FXML
     private ComboBox chucVuComboBox;

    //String gioiTinhC = null;
    //String chucvu = null;

    private final NhanVienRepository nhanVienRepo1 = new NhanVienRepository_impl();
    private NhanVien nhanVien1 = new NhanVien();

    ObservableList<String> Listchucvu = FXCollections.observableArrayList("Quản Lý","Nhân viên bán hàng","Nhân viên kho");

    ObservableList<String> listGioiTinh3 = FXCollections.observableArrayList("Nam","Nữ");

    public void edit(){

        loadData_Edit();
    }
    public void setGioiTinhComboBox( ActionEvent event ){
        gioiTinh1.setText((String) gioiTinhComboBox.getValue());
    }

    public void setChucVuComboBox( ActionEvent event ){chucvu1.setText((String) chucVuComboBox.getValue());
    }

    public void loadData_Edit(){
        nhanVien1 = nhanVienRepo1.getInformationUser(idNhanVien);
        hoTenLabel.setText(nhanVien1.getHoTen());
        diaChiThuongTruLabel.setText(nhanVien1.getDiaChiThuongTru());
        CCCDLabel.setText(nhanVien1.getCCCD());
        soDienThoaiLabel.setText(nhanVien1.getSoDienThoai());
        emailLabel.setText(nhanVien1.getEmail());
        chucVuComboBox.getSelectionModel().select(nhanVien1.getChucVu());
        if(nhanVien1.getNgaySinh() != null){
            ngaySinhLabel.setValue(LocalDate.parse(String.valueOf(nhanVien1.getNgaySinh())));
        }
        if(nhanVien1.getNgayVaoLam()!=null){
            ngayVaoLamLabel.setValue(LocalDate.parse(String.valueOf(nhanVien1.getNgayVaoLam())));
        }
        gioiTinhComboBox.getSelectionModel().select(nhanVien1.getGioiTinh());
        labelMaNV.setText(nhanVien1.getMaNV());
    }

    @FXML
    private void huyMouseAction(MouseEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @SneakyThrows
    @FXML
    private void save_ChinhSua(MouseEvent event) {
        String ngaySinh = String.valueOf(ngaySinhLabel.getValue());
        String ngayVaoLam = String.valueOf(ngayVaoLamLabel.getValue());
        String diaChiThuongTru = diaChiThuongTruLabel.getText();
        String soDienThoai = soDienThoaiLabel.getText();
        String email = emailLabel.getText();

        if ( ngaySinh.isEmpty() || ngayVaoLam.isEmpty() || diaChiThuongTru.isEmpty()||soDienThoai.isEmpty() || email.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Cần nhập đủ các trường dữ liệu");
            alert.showAndWait();
        } else {
            update();
            Alert alert_TC = new Alert(Alert.AlertType.INFORMATION);
            alert_TC.setHeaderText(null);
            alert_TC.setContentText("Chỉnh sửa thành công");
            alert_TC.showAndWait();
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }

    private void update() {
        nhanVienRepo1.updateInformation(new NhanVien(nhanVien1.getUsername(),nhanVien1.getPassword(),nhanVien1.getRole(),nhanVien1.getId(),nhanVien1.getMaNV(),nhanVien1.getHoTen(), Date.valueOf(ngaySinhLabel.getValue().toString()),
                diaChiThuongTruLabel.getText(), nhanVien1.getCCCD(), soDienThoaiLabel.getText(),emailLabel.getText(),Date.valueOf(ngayVaoLamLabel.getValue().toString()),
                chucVuComboBox.getValue().toString(),gioiTinhComboBox.getValue().toString(),nhanVien1.getCauHoi(),nhanVien1.getCauTraLoi()));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gioiTinhComboBox.setItems(listGioiTinh3);
        chucVuComboBox.setItems(Listchucvu);
    }
}

