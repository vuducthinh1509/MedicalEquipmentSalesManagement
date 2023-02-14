package controller.KhoHang;

import entity.NhanVien;
import entity.PhieuXuat;
import entity.ThietBi;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import repository.*;

public class XemChiTietController {
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
    Label trangThaiBaoHanhLabel;
    @FXML
    Label ngayXuatLabel;
    @FXML
    Label maNVXuatLabel;
    @FXML
    Label tenNVXuatLabel;

    ThietBi thietBi = new ThietBi();

    static ThietBiRepository thietBiRepo = new ThietBiRepository_impl();

    static PhieuXuatRepository phieuXuatRepo = new PhieuXuatRepository_impl();

    static NhanVienRepository nhanVienRepo = new NhanVienRepository_impl();

    public void setThietBi(Integer id){
        clean();
        loadDataThietBi(id);
    }

    @FXML
    private void loadDataThietBi(int _idThietBi) {
        NhanVien nhanVien = new NhanVien();
        thietBi = thietBiRepo.chiTietThietBi(_idThietBi);
        tenLabel.setText(thietBi.getTenThietBi());
        modelLabel.setText(thietBi.getModelThietBi());
        serialLabel.setText(thietBi.getSerialThietBi());
        mauLabel.setText(thietBi.getMauThietBi());
        kichThuocLabel.setText(thietBi.getKichThuocThietBi());
        xuatXuLabel.setText(thietBi.getXuatXuThietBi());
        thoiGianBaoHanhLabel.setText(thietBi.getThoiGianBaoHanh());
        giaLabel.setText(String.valueOf(thietBi.getGiaThietBi()));
        trangThaiLabel.setText(thietBi.getTrangThaiThietBi());
        nhanVien = nhanVienRepo.getInformationUserByMaNV(thietBi.getMaNVNguoiNhap());
        tenNVNhapLabel.setText(nhanVien.getHoTen());
        maNVNhapLabel.setText(thietBi.getMaNVNguoiNhap());
        ngayNhapLabel.setText(String.valueOf(thietBi.getNgayNhapThietBi()));
        if(thietBi.getIdPhieuXuat()!=0){
            PhieuXuat phieuXuat = phieuXuatRepo.getDetailInvoiceByID(thietBi.getIdPhieuXuat());
            nhanVien = nhanVienRepo.getInformationUser(phieuXuat.getIdEmployeeInvoice());
            tenNVXuatLabel.setText(nhanVien.getHoTen());
            maNVXuatLabel.setText(nhanVien.getMaNV());
            ngayXuatLabel.setText(String.valueOf(phieuXuat.getExportDateInvoice()));
        } else {
            ngayXuatLabel.setText("");
        }
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
