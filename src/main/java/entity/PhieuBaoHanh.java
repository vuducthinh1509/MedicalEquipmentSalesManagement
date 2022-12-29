package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private Integer chiPhiBaoHanh;

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

    private Integer idNhanVienPhuTrach;

    private String tenNhanVienPhuTrach;
}
