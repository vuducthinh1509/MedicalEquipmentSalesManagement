package repository;

import entity.PhieuXuat;
import entity.ThietBi;
import javafx.collections.ObservableList;

public interface PhieuXuatRepository {

    public int getNextAutoIncrement();

    public void addInvoice(PhieuXuat phieuXuat);

    public PhieuXuat getDetailInvoiceByID(Integer idPhieuXuat);

    public ObservableList<PhieuXuat> getAllDataInvoice();
}
