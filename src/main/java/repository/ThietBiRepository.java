package repository;

import entity.ThietBi;
import javafx.collections.ObservableList;

import java.sql.Date;

public interface ThietBiRepository {

    void themThietBi(ThietBi thietBi);
    //public ThietBi getInformationDevice(Integer id);

    ObservableList<ThietBi> loadDataThietBi();

    ThietBi chiTietThietBi(int _idThietBi);
    ObservableList<ThietBi> timThietBiTheoTruong(String queryTheoTruong,String duLieuTraCuu);

    void updateThietBi(int idThietBi, ThietBi thietBi);

    void xoaThietBi(int idThietBi);

    ThietBi layThongTinThietBiTheoModel(String model);

    ObservableList<ThietBi> timTatCaThietBiTrangThaiDaXuat();

    ObservableList<Integer> findAllDeviceByModel(String model);

    void updatePhieuXuatThietBi(int idThietBi,Integer idPhieuXuat);

    ObservableList<Integer> findAllDeviceByIdInvoice(Integer idInvoice);

    void updatePhieuXuatThietBi_Delete(int idThietBi);

    void updateIDPhieuBaoHanh(int idThietBi, Integer idPBH);

    ObservableList<ThietBi> timTatCaThietBiDaXuatTheoSerial(String serial);

    void updatePhieuBaoHanh_Delete(int id);
    public String kiemTraTonTai(String serial);
}
