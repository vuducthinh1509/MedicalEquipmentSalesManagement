package repository;

import entity.PhieuBaoHanh;
import entity.PhieuXuat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;

public class PhieuBaoHanhRepository_impl implements PhieuBaoHanhRepository{

    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    @Override
    public int getNextAutoIncrement(){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.PhieuBaoHanh_QUERY_LAY_NEXT_AUTOINDEX);
            rs = pstmt.executeQuery();
            while (rs.next()){
                int a = rs.getInt(1);
                return a;
            }
        } catch (SQLException ee){
            ee.printStackTrace();
            return 0;
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public void taoPhieuBaoHanh(PhieuBaoHanh phieuBaoHanh){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.PhieuBaoHanh_QUERY_INSERT);
            pstmt.setDate(1, phieuBaoHanh.getNgayBaoHanh());
            pstmt.setString(2, phieuBaoHanh.getNoteKhachHang());
            pstmt.setInt(3, phieuBaoHanh.getIdThietBi());
            pstmt.setInt(4, phieuBaoHanh.getIdKhachHang());
            pstmt.setInt(5, phieuBaoHanh.getIdNhanVienTaoPhieu());
            pstmt.execute();
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

    public ObservableList<PhieuBaoHanh> layTatCaDuLieuPhieuBaoHanh(){
        ObservableList<PhieuBaoHanh> list = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.PhieuBaoHanh_QUERY_GETALLDATA);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Integer id = rs.getInt("id");
                Date ngayBaoHanh = rs.getDate("ngayBaoHanh");
                String noteKhachHang = rs.getString("noteKhachHang");
                String noteNhanVien = rs.getString("noteNhanVien");
                String chiPhiBaoHanh = rs.getString("chiPhiBaoHanh");
                Integer idThietBi = rs.getInt("idThietBi");
                String trangThai = rs.getString("trangThai");
                Integer idKhachHang = rs.getInt("idKhachHang");
                Integer idNhanVienTaoPhieu = rs.getInt("idNhanVienTaoPhieu");
                Date ngayBanGiao = rs.getDate("ngayBanGiao");
                PhieuBaoHanh phieuBaoHanh = new PhieuBaoHanh(id,ngayBaoHanh, noteKhachHang,noteNhanVien,chiPhiBaoHanh,idThietBi,trangThai,idKhachHang,idNhanVienTaoPhieu,ngayBanGiao);
                list.add(phieuBaoHanh);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return list;
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
