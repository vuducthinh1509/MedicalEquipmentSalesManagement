package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.sql.Date;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ThietBi {
    private int idThietBi;
    private String tenThietBi;
    private String modelThietBi;
    private String serialThietBi;

    private String xuatXuThietBi;
    private String mauThietBi;
    private String kichThuocThietBi;
    private Integer giaThietBi;

    private String maNVNguoiNhap;

    private Date ngayNhapThietBi;

    private String maNVNguoiXuat;

    private Date ngayXuatThietBi;

    private String thoiGianBaoHanh;

    private String trangThaiThietBi;

    public ThietBi(int idThietBi,String tenThietBi, String modelThietBi,String serialThietBi,String mauThietBi, String kichThuocThietBi,Integer giaThietBi,String trangThaiThietBi){
        this.idThietBi = idThietBi;
        this.tenThietBi = tenThietBi;
        this.modelThietBi = modelThietBi;
        this.serialThietBi = serialThietBi;
        this.mauThietBi = mauThietBi;
        this.kichThuocThietBi = kichThuocThietBi;
        this.giaThietBi = giaThietBi;
        this.trangThaiThietBi = trangThaiThietBi;
    }

    public ThietBi(String ten,String model,String serial,String xuatXu, String thoiGianBaoHanh,String mau,String kichThuoc,Integer gia,String trangThai,String maNVNhap, String ngayNhap){
        this.tenThietBi = ten;
        this.modelThietBi = model;
        this.serialThietBi = serial;
        this.xuatXuThietBi = xuatXu;
        this.thoiGianBaoHanh = thoiGianBaoHanh;
        this.mauThietBi = mau;
        this.kichThuocThietBi = kichThuoc;
        this.giaThietBi = gia;
        this.trangThaiThietBi = trangThai;
        this.maNVNguoiNhap = maNVNhap;
        this.ngayNhapThietBi = Date.valueOf(ngayNhap);
    }

    public ThietBi(String tenThietBi, String modelThietBi, String serialThietBi, String xuatXuThietBi, String mauThietBi,String kichThuocThietBi, Integer giaThietBi, String maNVNguoiNhap, Date ngayNhapThietBi, String maNVNguoiXuat, String thoiGianBaoHanh, String trangThaiThietBi){
        this.tenThietBi = tenThietBi;
        this.modelThietBi = modelThietBi;
        this.serialThietBi = serialThietBi;
        this.xuatXuThietBi = xuatXuThietBi;
        this.mauThietBi = mauThietBi;
        this.kichThuocThietBi = kichThuocThietBi;
        this.giaThietBi = giaThietBi;
        this.maNVNguoiNhap = maNVNguoiNhap;
        this.ngayNhapThietBi = ngayNhapThietBi;
        this.maNVNguoiXuat = maNVNguoiXuat;
        this.thoiGianBaoHanh = thoiGianBaoHanh;
        this.trangThaiThietBi = trangThaiThietBi;
    }

}
