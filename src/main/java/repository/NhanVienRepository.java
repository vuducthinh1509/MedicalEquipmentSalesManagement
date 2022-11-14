package repository;

import entity.NhanVien;

public interface NhanVienRepository {
    public NhanVien getInformationUser(Integer id);
    public void updateInformation(NhanVien nhanVien);
}
