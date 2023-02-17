package repository;

import entity.KhachHang;
import javafx.collections.ObservableList;

public interface KhachHangRepository {
    void addCustomer(KhachHang khachHang);
    KhachHang getInformationCustomerByID(Integer id);

    KhachHang getInformationCustomerByPhone(String phone);

    ObservableList<KhachHang> getAllDataCustomer();

    void updateDataCustomer(KhachHang khachHang);

    void deleteCustomer(int id);

    Integer getCountCustomer();

    Integer kiemTraTonTai(String phone);
}
