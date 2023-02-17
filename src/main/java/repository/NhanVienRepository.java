package repository;

import entity.NhanVien;
import javafx.collections.ObservableList;

public interface NhanVienRepository {

    NhanVien dangNhap(String tentaikhoan, String password);

    boolean dangNhap1(String username,String password);
    NhanVien getInformationUser(Integer id);
    void updateInformation(NhanVien nhanVien);
    NhanVien getInformationUserByMaNV(String maNV);

    Integer kiemTraTaiKhoanTonTai(String username);

    NhanVien xacThucTaiKhoan(String username);

    void doiMatKhau(String username, String newPassword);

    void themNV (NhanVien nhanVien);
    void xoaNV(Integer id);
    ObservableList<NhanVien> loadDataNV();
    NhanVien getInformationByName(String hoTen);
    ObservableList<NhanVien> timNhanVienTheoTruong(String queryTheoTruong, String duLieuTraCuu);
    int getNextAutoIncrement();
}



