package repository;

import entity.NguoiDung;
import entity.NhanVien;
import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;

public class NhanVienRepository_impl implements NhanVienRepository {
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    @Override
    public NhanVien getInformationUser(Integer id){
        NhanVien nhanVien = new NhanVien();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Nhan_Vien_QUERY_LAY_THONG_TIN);
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                nhanVien.setId(rs.getInt("id"));
                nhanVien.setMaNV(rs.getString("maNV"));
                nhanVien.setHoTen(rs.getString("hoTen"));
                nhanVien.setNgaySinh(rs.getDate("ngaySinh"));
                nhanVien.setDiaChiThuongTru(rs.getString("diaChiThuongTru"));
                nhanVien.setCCCD(rs.getString("CCCD"));
                nhanVien.setSoDienThoai(rs.getString("soDienThoai"));
                nhanVien.setEmail(rs.getString("email"));
                nhanVien.setNgayVaoLam(rs.getDate("ngayVaoLam"));
                nhanVien.setChucVu(rs.getString("chucVu"));
                nhanVien.setGioiTinh(rs.getString("gioiTinh"));
            }
            return nhanVien;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } return nhanVien;
    }
}