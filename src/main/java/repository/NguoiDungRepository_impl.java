package repository;

import entity.NguoiDung;
import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;

public class NguoiDungRepository_impl implements NguoiDungRepository {
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    @Override
    public NguoiDung dangnhap(String taiKhoan, String matKhau) {

        NguoiDung nguoiDung = new NguoiDung();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Nguoi_Dung_Query_Dang_Nhap);
            pstmt.setString(1, taiKhoan);
            pstmt.setString(2, matKhau);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                nguoiDung.setId(rs.getInt("id"));
                nguoiDung.setRole(rs.getInt("role"));
                nguoiDung.setTentaikhoan(rs.getString("username"));
                nguoiDung.setPassword(rs.getString("password"));
            }
            return nguoiDung;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return nguoiDung;
    }
}