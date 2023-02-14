package repository;

import entity.NhanVien;
import javafx.collections.ObservableList;

public interface NhanVienRepository {

    public NhanVien dangNhap(String tentaikhoan, String password);

    public boolean dangNhap1(String username,String password);
    public NhanVien getInformationUser(Integer id);
    public void updateInformation(NhanVien nhanVien);
    public NhanVien getInformationUserByMaNV(String maNV);

    public Integer kiemTraTaiKhoanTonTai(String username);

    public NhanVien xacThucTaiKhoan(String username);

    public void doiMatKhau(String username, String newPassword);

    public void themNV (NhanVien nhanVien);
    public void xoaNV(Integer id);
    public ObservableList<NhanVien> loadDataNV();
    public NhanVien getInformationByName(String hoTen);
    public ObservableList<NhanVien> timNhanVienTheoTruong(String queryTheoTruong, String duLieuTraCuu);
    public int getNextAutoIncrement();
}



