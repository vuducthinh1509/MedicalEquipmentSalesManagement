package repository;

import entity.PhieuXuat;
import entity.ThietBi;

public interface PhieuXuatRepository {

    public int getNextAutoIncrement();

    public void addInvoice(PhieuXuat phieuXuat);

    public PhieuXuat getDetailInvoiceByID(Integer idPhieuXuat);
}
