package repository;

import entity.NhanVien;
import entity.ThietBi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;

public class ThietBiRepository_impl implements ThietBiRepository {
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    @Override
    public ThietBi chiTietThietBi(int _idThietBi){
        ThietBi thietBi = new ThietBi();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Thiet_Bi_QUERY_LAY_THONG_TIN_BY_id);
            pstmt.setInt(1,_idThietBi);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                thietBi.setIdThietBi(rs.getInt("idThietBi"));
                thietBi.setTenThietBi(rs.getString("tenThietBi"));
                thietBi.setModelThietBi(rs.getString("modelThietBi"));
                thietBi.setSerialThietBi(rs.getString("serialThietBi"));
                thietBi.setXuatXuThietBi(rs.getString("xuatXuThietBi"));
                thietBi.setMauThietBi(rs.getString("mauThietBi"));
                thietBi.setKichThuocThietBi(rs.getString("kichThuocThietBi"));
                thietBi.setGiaThietBi(rs.getString("giaThietBi"));
                thietBi.setMaNVNguoiNhap(rs.getString("maNVNguoiNhap"));
                thietBi.setNgayNhapThietBi(rs.getDate("ngayNhapThietBi"));
                thietBi.setMaNVNguoiXuat(rs.getString("maNVNguoiXuat"));
                thietBi.setNgayXuatThietBi(rs.getDate("ngayXuatThietBi"));
                thietBi.setThoiGianBaoHanh(rs.getString("thoiGianBaoHanh"));
                thietBi.setTrangThaiThietBi(rs.getString("trangThaiThietBi"));
            }
            return thietBi;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } return thietBi;
    }
    @Override
        public ObservableList<ThietBi> loadDataThietBi(){
        ObservableList<ThietBi> f = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Thiet_Bi_QUERY_LAY_THONG_TIN);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int idThietBi = rs.getInt("idThietBi");
                String tenThietBi = rs.getString("tenThietBi");
                String modelThietBi = rs.getString("modelThietBi");
                String serialThietBi = rs.getString("serialThietBi");
                String mauThietBi = rs.getString("mauThietBi");
                String kichThuocThietBi = rs.getString("kichThuocThietBi");
                String giaThietBi = rs.getString("giaThietBi");
                String trangThaiThietBi = rs.getString("trangThaiThietBi");
                f.add(new ThietBi(idThietBi,tenThietBi,modelThietBi,serialThietBi,mauThietBi,kichThuocThietBi,giaThietBi,trangThaiThietBi));
            }
            return f;
        } catch (SQLException e) {
            e.printStackTrace();
            return f;
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ObservableList<ThietBi> timThietBiTheoTruong(String queryTheoTruong,String duLieuTraCuu){
        ObservableList<ThietBi> f = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(queryTheoTruong);
            pstmt.setString(1, "%"+duLieuTraCuu+"%" );
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int idThietBi = rs.getInt("idThietBi");
                String tenThietBi = rs.getString("tenThietBi");
                String modelThietBi = rs.getString("modelThietBi");
                String serialThietBi = rs.getString("serialThietBi");
                String mauThietBi = rs.getString("mauThietBi");
                String kichThuocThietBi = rs.getString("kichThuocThietBi");
                String giaThietBi = rs.getString("giaThietBi");
                String trangThai = rs.getString("trangThaiThietBi");
                f.add(new ThietBi(idThietBi,tenThietBi,modelThietBi,serialThietBi,mauThietBi,kichThuocThietBi,giaThietBi,trangThai));
            }
            return f;
        } catch (SQLException e) {
            e.printStackTrace();
            return f;
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void themThietBi(ThietBi thietBi){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Thiet_Bi_QUERY_INSERT_ThietBi);
            pstmt.setString(1, thietBi.getTenThietBi());
            pstmt.setString(2, thietBi.getModelThietBi());
            pstmt.setString(3, thietBi.getSerialThietBi());
            pstmt.setString(4, thietBi.getXuatXuThietBi());
            pstmt.setString(5, thietBi.getThoiGianBaoHanh());
            pstmt.setString(6, thietBi.getMauThietBi());
            pstmt.setString(7, thietBi.getKichThuocThietBi());
            pstmt.setString(8, thietBi.getGiaThietBi());
            pstmt.setString(9, thietBi.getTrangThaiThietBi());
            pstmt.setString(10, thietBi.getMaNVNguoiNhap());
            pstmt.setString(11, String.valueOf(thietBi.getNgayNhapThietBi()));
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

    @Override
    public void updateThietBi(int idThietBi, ThietBi thietBi){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Thiet_Bi_QUERY_UPDATE + idThietBi);
            pstmt.setString(1, thietBi.getTenThietBi());
            pstmt.setString(2, thietBi.getModelThietBi());
            pstmt.setString(3, thietBi.getSerialThietBi());
            pstmt.setString(4, thietBi.getXuatXuThietBi());
            pstmt.setString(5, thietBi.getMauThietBi());
            pstmt.setString(6, thietBi.getKichThuocThietBi());
            pstmt.setString(7, thietBi.getGiaThietBi());
            pstmt.setString(8, thietBi.getMaNVNguoiNhap());
            pstmt.setDate(9, thietBi.getNgayNhapThietBi());
            pstmt.setString(10, thietBi.getMaNVNguoiXuat());
            pstmt.setString(11, thietBi.getThoiGianBaoHanh());
            pstmt.setString(12, thietBi.getTrangThaiThietBi());
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
    @Override
    public void xoaThietBi(int idThietBi){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Thiet_Bi_DELETE_ThietBi);
            pstmt.setInt(1, idThietBi );
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
