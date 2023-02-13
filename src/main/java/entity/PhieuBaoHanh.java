package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import repository.*;

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

    private String tenThietBi;

    private String modelThietBi;

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
        this.tenThietBi = thietBi.getTenThietBi();
        this.modelThietBi = thietBi.getModelThietBi();
        this.serialThietBi = thietBi.getSerialThietBi();
        KhachHangRepository khachHangRepo = new KhachHangRepository_impl();
        KhachHang khachHang = khachHangRepo.getInformationCustomerByID(this.idKhachHang);
        NhanVienRepository nhanVienRepo = new NhanVienRepository_impl();
        this.tenNhanVienTaoPhieu = nhanVienRepo.getInformationUser(idNhanVienTaoPhieu).getHoTen();
        this.tenKhachHang = khachHang.getTenKhachHang();
        this.phoneKhachHang = khachHang.getPhoneKhachHang();
        this.diaChiKhachHang = khachHang.getDiaChiKhachHang();
    }

    public void copyPhieuBaoHanh(PhieuBaoHanh clone){
        this.id = clone.getId();
        this.ngayBaoHanh = clone.getNgayBaoHanh();
        this.noteKhachHang = clone.getNoteKhachHang();
        this.noteNhanVien = clone.getNoteNhanVien();
        this.chiPhiBaoHanh = clone.getChiPhiBaoHanh();
        this.idThietBi = clone.getIdThietBi();
        this.trangThai = clone.getTrangThai();
        this.idKhachHang = clone.getIdKhachHang();
        this.idNhanVienTaoPhieu = clone.getIdNhanVienTaoPhieu();
        this.tenNhanVienTaoPhieu = clone.getTenNhanVienTaoPhieu();
        this.ngayBanGiao = clone.getNgayBanGiao();
        this.tenKhachHang = clone.getTenKhachHang();
        this.serialThietBi = clone.getSerialThietBi();
    }
}
