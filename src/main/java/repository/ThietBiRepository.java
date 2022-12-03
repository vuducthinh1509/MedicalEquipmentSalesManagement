package repository;

import entity.ThietBi;
import javafx.collections.ObservableList;

public interface ThietBiRepository {

    public void themThietBi(ThietBi thietBi);
    //public ThietBi getInformationDevice(Integer id);

    public ObservableList<ThietBi> loadDataThietBi();

    public ThietBi chiTietThietBi(int _idThietBi);
    public ObservableList<ThietBi> timThietBiTheoTruong(String queryTheoTruong,String duLieuTraCuu);

    public void updateThietBi(int idThietBi, ThietBi thietBi);

    public void xoaThietBi(int idThietBi);

    public ThietBi layThongTinThietBiTheoModel(String model);
}
