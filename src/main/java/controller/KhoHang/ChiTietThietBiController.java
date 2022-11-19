package controller.KhoHang;

import entity.NhanVien;
import entity.ThietBi;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import repository.NhanVienRepository;
import repository.NhanVienRepository_impl;
import repository.ThietBiRepository;
import repository.ThietBiRepository_impl;

import java.time.LocalDate;

public class ChiTietThietBiController {
    @FXML
    Label tenLabel;
    @FXML
    Label modelLabel;
    @FXML
    Label serialLabel;
    @FXML
    Label xuatXuLabel;
    @FXML
    Label mauLabel;
    @FXML
    Label kichThuocLabel;
    @FXML
    Label thoiGianBaoHanhLabel;
    @FXML
    Label giaLabel;
    @FXML
    Label trangThaiLabel;
    @FXML
    Label ngayNhapLabel;
    @FXML
    Label maNVNhapLabel;
    @FXML
    Label tenNVNhapLabel;

    @FXML
    Label ngayXuatLabel;
    @FXML
    Label maNVXuatLabel;
    @FXML
    Label tenNVXuatLabel;

    ThietBi thietBi = new ThietBi();

    static ThietBiRepository thietBiRepo = new ThietBiRepository_impl();

    public void setThietBi(ThietBi thietBi){
        clean();
        loadDataThietBi(thietBi.getIdThietBi());
    }

    @FXML
    private void loadDataThietBi(int _idThietBi) {
        NhanVien nhanVien = new NhanVien();
        NhanVienRepository nhanVienRepo = new NhanVienRepository_impl();
        thietBi = thietBiRepo.chiTietThietBi(_idThietBi);
        tenLabel.setText(thietBi.getTenThietBi());
        modelLabel.setText(thietBi.getModelThietBi());
        serialLabel.setText(thietBi.getSerialThietBi());
        mauLabel.setText(thietBi.getMauThietBi());
        kichThuocLabel.setText(thietBi.getKichThuocThietBi());
        xuatXuLabel.setText(thietBi.getXuatXuThietBi());
        thoiGianBaoHanhLabel.setText(thietBi.getThoiGianBaoHanh());
        giaLabel.setText(thietBi.getGiaThietBi());
        trangThaiLabel.setText(thietBi.getTrangThaiThietBi());
        nhanVien = nhanVienRepo.getInformationUserByMaNV(thietBi.getMaNVNguoiNhap());
        tenNVNhapLabel.setText(nhanVien.getHoTen());
        maNVNhapLabel.setText(thietBi.getMaNVNguoiNhap());
        ngayNhapLabel.setText(String.valueOf(thietBi.getNgayNhapThietBi()));
        nhanVien = nhanVienRepo.getInformationUserByMaNV(thietBi.getMaNVNguoiXuat());
        tenNVXuatLabel.setText(nhanVien.getHoTen());
        maNVXuatLabel.setText(thietBi.getMaNVNguoiXuat());
        ngayXuatLabel.setText(String.valueOf(thietBi.getNgayXuatThietBi()));
        trangThaiLabel.setText(thietBi.getTrangThaiThietBi());
    }
    @FXML
    private void clean() {
        tenLabel.setText(null);
        modelLabel.setText(null);
        serialLabel.setText(null);
        xuatXuLabel.setText(null);
        mauLabel.setText(null);
        kichThuocLabel.setText(null);
        thoiGianBaoHanhLabel.setText(null);
        giaLabel.setText(null);
        ngayNhapLabel.setText(null);
        maNVNhapLabel.setText(null);
        tenNVNhapLabel.setText(null);
        ngayXuatLabel.setText(null);
        maNVXuatLabel.setText(null);
        tenNVXuatLabel.setText(null);
        trangThaiLabel.setText(null);
    }
}
