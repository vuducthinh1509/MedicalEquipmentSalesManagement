package repository;

import entity.NhanVien;

public interface NhanVienRepository {

    public NhanVien dangNhap(String tentaikhoan, String password);
    public NhanVien getInformationUser(Integer id);
    public void updateInformation(NhanVien nhanVien);
    public NhanVien getInformationUserByMaNV(String maNV);

    public Integer kiemTraTaiKhoanTonTai(String username);

    public NhanVien xacThucTaiKhoan(String username);

}
