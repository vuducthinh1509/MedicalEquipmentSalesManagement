package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import repository.KhachHangRepository;
import repository.KhachHangRepository_impl;
import repository.ThietBiRepository;
import repository.ThietBiRepository_impl;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PhieuBaoHanh {
    private Integer id;

    private Date ngayBaoHanh;

    private String noteKhachHang;

    private String noteNhanVien;

    private String chiPhiBaoHanh;

    private Integer idThietBi;

    private String serialThietBi;

    private Date ngayBanGiao;
    // Đang bảo hành, Đã bảo hành, Đã bàn giao, NULL
    private String trangThai;

    private Integer idKhachHang;

    private String tenKhachHang;

    private String phoneKhachHang;

    private String diaChiKhachHang;

    private Integer idNhanVienTaoPhieu;

    private String tenNhanVienTaoPhieu;

    public PhieuBaoHanh(Date ngayBaoHanh,String noteKhachHang,Integer idThietBi,Integer idKhachHang,Integer idNhanVienTaoPhieu){
        this.ngayBaoHanh = ngayBaoHanh;
        this.noteKhachHang = noteKhachHang;
        this.idThietBi = idThietBi;
        this.idKhachHang = idKhachHang;
        this.idNhanVienTaoPhieu = idNhanVienTaoPhieu;
    }

    public PhieuBaoHanh(Integer id,Date ngayBaoHanh,String noteKhachHang,String noteNhanVien,String chiPhiBaoHanh,Integer idThietBi,String trangThai,Integer idKhachHang,Integer idNhanVienTaoPhieu,Date ngayBanGiao){
        this.id = id;
        this.ngayBaoHanh = ngayBaoHanh;
        this.noteKhachHang = noteKhachHang;
        this.noteNhanVien = noteNhanVien;
        this.chiPhiBaoHanh = chiPhiBaoHanh;
        this.idThietBi = idThietBi;
        this.trangThai = trangThai;
        this.idKhachHang = idKhachHang;
        this.idNhanVienTaoPhieu = idNhanVienTaoPhieu;
        this.ngayBanGiao = ngayBanGiao;
    }

    public void loadData(){
        ThietBiRepository thietBiRepo = new ThietBiRepository_impl();
        ThietBi thietBi = thietBiRepo.chiTietThietBi(this.idThietBi);
        this.serialThietBi = thietBi.getSerialThietBi();
        KhachHangRepository khachHangRepo = new KhachHangRepository_impl();
        KhachHang khachHang = khachHangRepo.getInformationCustomerByID(this.idKhachHang);
        this.tenKhachHang = khachHang.getTenKhachHang();
    }
}
