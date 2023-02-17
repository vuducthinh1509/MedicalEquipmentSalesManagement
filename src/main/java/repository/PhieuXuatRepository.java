package repository;

import entity.PhieuXuat;
import entity.ThietBi;
import javafx.collections.ObservableList;

public interface PhieuXuatRepository {

    int getNextAutoIncrement();

    void addInvoice(PhieuXuat phieuXuat);

    PhieuXuat getDetailInvoiceByID(Integer idPhieuXuat);

    ObservableList<PhieuXuat> getAllDataInvoice();

    void deleteInvoice(int idInvoice);
}
