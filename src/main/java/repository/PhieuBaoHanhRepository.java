package repository;

import entity.PhieuBaoHanh;
import javafx.collections.ObservableList;

import java.sql.Date;

public interface PhieuBaoHanhRepository {
    int getNextAutoIncrement();

    void taoPhieuBaoHanh(PhieuBaoHanh phieuBaoHanh);

    ObservableList<PhieuBaoHanh> layTatCaDuLieuPhieuBaoHanh();

    PhieuBaoHanh layDuLieuPhieuBaoHanhTheoID(Integer id);

    void capNhatTrangThai(Integer id, Date ngayBanGiao, String chiPhi, String noteNhanVien);

    void capNhatTrangThai1(Integer id);

    void capNhatTrangThai2(Integer id,String chiPhi, String noteNhanVien);

    void capNhatNoteNhanVien(Integer id, String note);

    void capNhatNoteKhachHang(Integer id, String note);

    void deletePhieuBaoHanh(int id);
}
