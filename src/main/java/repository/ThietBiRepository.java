package repository;

import entity.ThietBi;
import javafx.collections.ObservableList;

import java.sql.Date;

public interface ThietBiRepository {

    public void themThietBi(ThietBi thietBi);
    //public ThietBi getInformationDevice(Integer id);

    public ObservableList<ThietBi> loadDataThietBi();

    public ThietBi chiTietThietBi(int _idThietBi);
    public ObservableList<ThietBi> timThietBiTheoTruong(String queryTheoTruong,String duLieuTraCuu);

    public void updateThietBi(int idThietBi, ThietBi thietBi);

    public void xoaThietBi(int idThietBi);

    public ThietBi layThongTinThietBiTheoModel(String model);

    public ObservableList<ThietBi> timTatCaThietBiTrangThaiDaXuat();

    public ObservableList<Integer> findAllDeviceByModel(String model);

    public void updatePhieuXuatThietBi(int idThietBi,Integer idPhieuXuat);

    public ObservableList<Integer> findAllDeviceByIdInvoice(Integer idInvoice);

    public void updatePhieuXuatThietBi_Delete(int idThietBi);

    public void updateIDPhieuBaoHanh(int idThietBi, Integer idPBH);

    public ObservableList<ThietBi> timTatCaThietBiDaXuatTheoSerial(String serial);

    public void updatePhieuBaoHanh_Delete(int id);
}
