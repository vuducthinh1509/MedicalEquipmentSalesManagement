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
    private final Statement stmt = null;
    private PreparedStatement pstmt = null;
    private final CallableStatement cstmt = null;
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

    public PhieuBaoHanh layDuLieuPhieuBaoHanhTheoID(Integer id){
        PhieuBaoHanh phieuBaoHanh = new PhieuBaoHanh();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.PhieuBaoHanh_QUERY_GET_DATE_BY_ID);
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                phieuBaoHanh.setId(rs.getInt("id"));
                phieuBaoHanh.setNgayBaoHanh(rs.getDate("ngayBaoHanh"));
                phieuBaoHanh.setNoteKhachHang(rs.getString("noteKhachHang"));
                phieuBaoHanh.setNoteNhanVien(rs.getString("noteNhanVien"));
                phieuBaoHanh.setChiPhiBaoHanh(rs.getString("chiPhiBaoHanh"));
                phieuBaoHanh.setIdThietBi(rs.getInt("idThietBi"));
                phieuBaoHanh.setTrangThai(rs.getString("trangThai"));
                phieuBaoHanh.setIdKhachHang(rs.getInt("idKhachHang"));
                phieuBaoHanh.setIdNhanVienTaoPhieu(rs.getInt("idNhanVienTaoPhieu"));
                phieuBaoHanh.setNgayBanGiao(rs.getDate("ngayBanGiao"));
            }
            return phieuBaoHanh;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } return phieuBaoHanh;
    }

    public void capNhatTrangThai(Integer id, Date ngayBanGiao, String chiPhi,String noteNhanVien){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.PhieuBaoHanh_QUERY_UPDATE_TrangThai + id);
            pstmt.setDate(1,ngayBanGiao);
            pstmt.setString( 2,chiPhi);
            pstmt.setString(3,noteNhanVien);
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

    public void capNhatTrangThai1(Integer id){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.PhieuBaoHanh_QUERY_UPDATE_TrangThai1);
            pstmt.setInt(1,id);
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

    public void capNhatTrangThai2(Integer id,String chiPhi, String noteNhanVien){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.PhieuBaoHanh_QUERY_UPDATE_TrangThai2 + id);
            pstmt.setString(1,chiPhi);
            pstmt.setString(2,noteNhanVien);
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

    public void capNhatNoteNhanVien(Integer id, String note){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.PhieuBaoHanh_QUERY_UPDATE_NoteNhanVien + id);
            pstmt.setString(1,note);
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

    public void capNhatNoteKhachHang(Integer id, String note){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.PhieuBaoHanh_QUERY_UPDATE_NoteKhachHang + id);
            pstmt.setString(1,note);
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

    public void deletePhieuBaoHanh(int id){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.PhieuBaoHanh_DELTE_BY_ID);
            pstmt.setInt(1, id );
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
}
