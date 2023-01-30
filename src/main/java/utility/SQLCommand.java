package utility;

public class SQLCommand {

    // Người Dùng
    public static String Nguoi_Dung_Query_Dang_Nhap = "SELECT * FROM nguoidung where username = ? AND password = ?";

    // Nhân viên
    public static String Nhan_Vien_QUERY_LAY_THONG_TIN = "SELECT * FROM nhanvien WHERE id = ? ";

    public static String Nhan_Vien_QUERY_LAY_THONG_TIN_BY_MaNV = "SELECT * FROM nhanvien WHERE maNV = ? ";

    //Thiết bị

    public static String Thiet_Bi_QUERY_LAY_THONG_TIN_BY_id = "SELECT * FROM thietbi WHERE idThietBi = ? ";

    public static String Thiet_Bi_QUERY_LAY_THONG_TIN = "SELECT * FROM thietbi";

    public static String Thiet_Bi_QUERY_LAY_THONG_TIN_BY_tenThietBi = "SELECT * FROM thietbi where tenThietBi like ?";

    public static String Thiet_Bi_QUERY_LAY_THONG_TIN_BY_modelThietBi = "SELECT * FROM thietbi where modelThietBi like ?";

    public static String Thiet_Bi_QUERY_LAY_THONG_TIN_BY_trangThaiThietBi = "SELECT * FROM thietbi where trangThaiThietBi like ?";

    public static String Thiet_Bi_QUERY_LAY_THONG_TIN_BY_trangThaiThietBi_Da_Xuat = "SELECT * FROM thietbi where trangThaiThietBi = 'Đã xuất' and idPhieuBaoHanh is null";

    public static String Thiet_Bi_QUERY_LAY_THONG_TIN_BY_idPhieuXuat = "SELECT * FROM thietbi where idPhieuXuat = ?";

    public static String Thiet_Bi_DELETE_ThietBi = "DELETE FROM `thietbi` WHERE idThietBi = ?";
    public static String Thiet_Bi_QUERY_INSERT_ThietBi = "INSERT INTO `thietbi`( `tenThietBi`, `modelThietBi`, `serialThietBi`, `xuatXuThietBi`, `thoiGianBaoHanh`, `mauThietBi`, `kichThuocThietBi`, `giaThietBi`, `trangThaiThietBi`, `maNVNguoiNhap`, `ngayNhapThietBi`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    public static String Nhan_Vien_QUERY_UPDATE =  "UPDATE `nhanvien` SET " +
            "`ngaySinh`=?," +
            "`diaChiThuongTru`=?," +
            "`soDienThoai`=?," +
            "`email`=?," +
            "`ngayVaoLam`=?," +
            "`gioiTinh`=?  WHERE id  = ";
    public static String Thiet_Bi_QUERY_UPDATE = "UPDATE `thietbi` SET " +
            "`tenThietBi`=?," +
            "`modelThietBi`=?," +
            "`serialThietBi`=?," +
            "`xuatXuThietBi`=?," +
            "`mauThietBi`=?," +
            "`kichThuocThietBi`=?," +
            "`giaThietBi`=?," +
            "`maNVNguoiNhap`=?," +
            "`ngayNhapThietBi`=?," +
            "`thoiGianBaoHanh`=?," +
            "`trangThaiThietBi`=? WHERE idThietBi = ";

    public static String Thiet_Bi_QUERY_UPDATE_IDPHIEUXUAT = "UPDATE `thietbi` SET " +
            "`idPhieuXuat`=?," +
            "`trangThaiThietBi`='Đã xuất' WHERE idThietBi = ";

    public static String Thiet_Bi_QUERY_UPDATE_IDPBH = "UPDATE `thietbi` SET" +
            "`idPhieuBaoHanh` = ? WHERE idThietBi = ? ";
    public static String Thiet_Bi_QUERY_CLEAR_IDPHIEUXUAT = "update \t`thietbi` set `idPhieuXuat` = null, `trangThaiThietBi` = 'Trong kho' where idThietBi = ?;";

    public static String Thiet_Bi_QUERY_getCountModel = "select modelThietBi,xuatXuThietBi,tenThietBi,giaThietBi , count(*) from thietbi\n" +
            "where trangThaiThietBi = 'Trong kho'\n" +
            "group by modelThietBi;";

    public static String Thiet_Bi_QUERY_ALL_DEVICE_BY_MODEL = "select idThietBi from thietbi\n" +
            "where trangThaiThietBi = 'Trong kho' and modelThietBi = ?";

    // Khách hàng
    public static String KhachHang_QUERY_INSERT =  "INSERT INTO `khachhang` (`tenKhachHang`,`sdtKhachHang`,`diaChiKhachHang`) VALUES (?,?,?)";


    public static String KhachHang_QUERY_LAY_THONG_TIN_BY_ID = "SELECT * FROM khachhang WHERE idKhachHang = ?";

    public static String KhachHang_QUERY_LAY_THONG_TIN_BY_PHONE = "SELECT * FROM khachhang WHERE sdtKhachHang = ?";
    public static String KhachHang_QUERY_GETALLDATA = "select * from khachhang";

    public static String KhachHang_QUERY_UPDATE =  "UPDATE `khachhang` SET " +
            "`tenKhachHang`=?," +
            "`sdtKhachHang`=?," +
            "`diaChiKhachHang`=?  WHERE idKhachHang  = ";

    public static String KhachHang_DELETE_KhachHang = "DELETE FROM `khachhang` WHERE idKhachHang = ?";

    public static String KhachHang_QUERY_LAY_NEXT_AUTOINDEX = "SELECT AUTO_INCREMENT FROM information_schema.TABLES " +
            "WHERE TABLE_SCHEMA = 'demo' AND TABLE_NAME = 'khachhang'";
    public static String PhieuXuat_QUERY_LAY_NEXT_AUTOINDEX = "SELECT auto_increment FROM information_schema.TABLES \n" +
            "WHERE TABLE_SCHEMA = 'demo' AND TABLE_NAME = 'phieuxuat';";
    public static String PhieuXuat_QUERY_INSERT = "INSERT INTO `phieuxuat` (`subTotalInvoice`,`vatInvoice`,`discountInvoice`,`discount1Invoice`,`totalInvoice`,`exportDateInvoice`,`idEmployeeInvoice`,`idCustomerInvoice`) VALUES (?,?,?,?,?,?,?,?)";

    public static String PhieuXuat_QUERY_LAY_THONG_TIN_BY_ID = "SELECT * FROM phieuXuat where idInvoice = ?";

    public static String PhieuXuat_QUERY_LAY_THONG_TIN = "SELECT * FROM phieuxuat";

    public static String PhieuXuat_DELETE_PhieuXuat = "DELETE FROM `phieuxuat` WHERE idInvoice = ?";

    public static String PhieuBaoHanh_QUERY_LAY_NEXT_AUTOINDEX = "SELECT auto_increment FROM information_schema.TABLES \n" +
            "WHERE TABLE_SCHEMA = 'demo' AND TABLE_NAME = 'phieubaohanh';";

    public static String PhieuBaoHanh_QUERY_INSERT = "INSERT INTO `phieubaohanh` (`ngayBaoHanh`,`noteKhachHang`,`idThietBi`,`idKhachHang`,`idNhanVienTaoPhieu`, `trangThai`) VALUES (?,?,?,?,?,'Đang bảo hành')";

    public static String PhieuBaoHanh_QUERY_GETALLDATA = "SELECT * FROM `phieubaohanh`";

    public static String PhieuBaoHanh_QUERY_GET_DATE_BY_ID = "SELECT * FROM phieubaohanh WHERE id = ?";

    public static String PhieuBaoHanh_QUERY_UPDATE_TrangThai = "update phieubaohanh set trangThai = 'Đã bàn giao', ngayBanGiao = ? , chiPhiBaoHanh = ?, noteNhanVien = ? where id =";

    public static String PhieuBaoHanh_QUERY_UPDATE_TrangThai1 = "update phieubaohanh set trangThai = 'Đang bảo hành', noteNhanVien = null, chiPhiBaoHanh = null, ngayBanGiao = null where id = ?;";

    public static String PhieuBaoHanh_QUERY_UPDATE_TrangThai2 = "update phieubaohanh set trangThai = 'Có thể bàn giao'," +
            "chiPhiBaoHanh = ?," +
            "noteNhanVien = ? where id =";

    public static String PhieuBaoHanh_QUERY_UPDATE_NoteNhanVien = "update phieubaohanh set noteNhanVien = ? where id =";

    public static String PhieuBaoHanh_QUERY_UPDATE_NoteKhachHang = "update phieubaohanh set noteKhachHang = ? where id =";
}

