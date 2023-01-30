package repository;

import entity.PhieuBaoHanh;
import javafx.collections.ObservableList;

import java.sql.Date;

public interface PhieuBaoHanhRepository {
    public int getNextAutoIncrement();

    public void taoPhieuBaoHanh(PhieuBaoHanh phieuBaoHanh);

    public ObservableList<PhieuBaoHanh> layTatCaDuLieuPhieuBaoHanh();

    public PhieuBaoHanh layDuLieuPhieuBaoHanhTheoID(Integer id);

    public void capNhatTrangThai(Integer id, Date ngayBanGiao, String chiPhi, String noteNhanVien);

    public void capNhatTrangThai1(Integer id);

    public void capNhatTrangThai2(Integer id,String chiPhi, String noteNhanVien);

    public void capNhatNoteNhanVien(Integer id, String note);

    public void capNhatNoteKhachHang(Integer id, String note);
}
