package repository;

import entity.KhachHang;
import javafx.collections.ObservableList;

public interface KhachHangRepository {
    public void addCustomer(KhachHang khachHang);
    public KhachHang getInformationCustomerByID(Integer id);

    public KhachHang getInformationCustomerByPhone(String phone);

    public ObservableList<KhachHang> getAllDataCustomer();

    public void updateDataCustomer(KhachHang khachHang);

    public void deleteCustomer(int id);

    public Integer getCountCustomer();

    public Integer kiemTraTonTai(String phone);
}
