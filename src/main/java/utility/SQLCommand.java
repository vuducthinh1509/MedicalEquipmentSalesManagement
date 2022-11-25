package utility;

public class SQLCommand {
    public static String Nguoi_Dung_Query_Dang_Nhap = "SELECT * FROM nguoidung where username = ? AND password = ?";

    public static String Nhan_Vien_QUERY_LAY_THONG_TIN = "SELECT * FROM nhanvien WHERE id = ? ";

    public static String Nhan_Vien_QUERY_LAY_THONG_TIN_BY_MaNV = "SELECT * FROM nhanvien WHERE maNV = ? ";

    public static String Thiet_Bi_QUERY_LAY_THONG_TIN_BY_id = "SELECT * FROM thietbi WHERE idThietBi = ? ";

    public static String Thiet_Bi_QUERY_LAY_THONG_TIN = "SELECT * FROM thietbi";

    public static String Thiet_Bi_QUERY_LAY_THONG_TIN_BY_tenThietBi = "SELECT * FROM thietbi where tenThietBi like ?";

    public static String Thiet_Bi_QUERY_LAY_THONG_TIN_BY_modelThietBi = "SELECT * FROM thietbi where modelThietBi like ?";

    public static String Thiet_Bi_QUERY_LAY_THONG_TIN_BY_trangThaiThietBi = "SELECT * FROM thietbi where trangThaiThietBi like ?";

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
            "`maNVNguoiXuat`=?," +
            "`thoiGianBaoHanh`=?," +
            "`trangThaiThietBi`=? WHERE idThietBi = ";

}