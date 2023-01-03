package repository;

import entity.PhieuBaoHanh;
import javafx.collections.ObservableList;

public interface PhieuBaoHanhRepository {
    public int getNextAutoIncrement();

    public void taoPhieuBaoHanh(PhieuBaoHanh phieuBaoHanh);

    public ObservableList<PhieuBaoHanh> layTatCaDuLieuPhieuBaoHanh();
}
