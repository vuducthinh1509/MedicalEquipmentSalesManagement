package repository;

import entity.KhachHang;
import javafx.collections.ObservableList;

public interface KhachHangRepository {
    public void addCustomer(KhachHang khachHang);
    public void getInformationCustomerByID(Integer id);

    public ObservableList<KhachHang> getAllDataCustomer();

    public void updateDataCustomer(KhachHang khachHang);

    public void deleteCustomer(int id);
}
