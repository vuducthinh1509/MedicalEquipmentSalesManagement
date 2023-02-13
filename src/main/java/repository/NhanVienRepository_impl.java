package repository;

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

    @Override
    public NhanVien getInformationUserByMaNV(String subMaNV){
        NhanVien nhanVien = new NhanVien();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Nhan_Vien_QUERY_LAY_THONG_TIN_BY_MaNV);
            pstmt.setString(1,subMaNV);
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

    @Override
    public void updateInformation(NhanVien nhanVien){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Nhan_Vien_QUERY_UPDATE + nhanVien.getId());
            pstmt.setDate(1, nhanVien.getNgaySinh());
            pstmt.setString(2, nhanVien.getDiaChiThuongTru());
            pstmt.setString(3, nhanVien.getSoDienThoai());
            pstmt.setString(4, nhanVien.getEmail());
            pstmt.setDate(5, nhanVien.getNgayVaoLam());
            pstmt.setString(6, nhanVien.getGioiTinh());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public NhanVien dangNhap(String tentaikhoan, String password){
        NhanVien nhanVien = new NhanVien();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Nhan_Vien_QUERY_DANG_NHAP);
            pstmt.setString(1, tentaikhoan);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                nhanVien.setId(rs.getInt("id"));
                nhanVien.setRole(rs.getInt("role"));
                nhanVien.setUsername(rs.getString("username"));
                nhanVien.setPassword(rs.getString("password"));
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
        }
        return nhanVien;
    }

    public boolean dangNhap1(String username,String password){
        boolean isValid = false;
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Nhan_Vien_QUERY_DANG_NHAP);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                isValid = true;
            }
            return isValid;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isValid;
    }

    public Integer kiemTraTaiKhoanTonTai(String username){
        Integer id = -1;
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Nhan_Vien_QUERY_KIEM_TRA_TON_TAI);
            pstmt.setString(1,username);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                id = rs.getInt("id");
            }
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } return id;
    }

    public NhanVien xacThucTaiKhoan(String username){
        NhanVien nhanVien = new NhanVien();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Nhan_Vien_QUERY_KIEM_TRA_TON_TAI);
            pstmt.setString(1,username);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                nhanVien.setCauHoi(rs.getString("cauHoi"));
                nhanVien.setCauTraLoi(rs.getString("cauTraLoi"));
                nhanVien.setPassword(rs.getString("password"));
                nhanVien.setUsername(rs.getString("username"));
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

    public void doiMatKhau(String username, String newPassword){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Nhan_Vien_QUERY_UPDATE_PASSWORD);
            pstmt.setString(1, newPassword);
            pstmt.setString(2,username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}