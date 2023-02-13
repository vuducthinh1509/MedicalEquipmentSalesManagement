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
                thietBi.setGiaThietBi(rs.getInt("giaThietBi"));
                thietBi.setMaNVNguoiNhap(rs.getString("maNVNguoiNhap"));
                thietBi.setNgayNhapThietBi(rs.getDate("ngayNhapThietBi"));
                thietBi.setThoiGianBaoHanh(rs.getString("thoiGianBaoHanh"));
                thietBi.setTrangThaiThietBi(rs.getString("trangThaiThietBi"));
                thietBi.setIdPhieuXuat(rs.getInt("idPhieuXuat"));
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
                Integer giaThietBi = rs.getInt("giaThietBi");
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
                Integer giaThietBi = rs.getInt("giaThietBi");
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
    public ObservableList<Integer> findAllDeviceByModel(String model){
        ObservableList<Integer> idList = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Thiet_Bi_QUERY_ALL_DEVICE_BY_MODEL);
            pstmt.setString(1, model );
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int idThietBi = rs.getInt("idThietBi");
                idList.add(idThietBi);
            }
            return idList;
        } catch (SQLException e) {
            e.printStackTrace();
            return idList;
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ObservableList<Integer> findAllDeviceByIdInvoice(Integer idInvoice){
        ObservableList<Integer> IDs = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Thiet_Bi_QUERY_LAY_THONG_TIN_BY_idPhieuXuat);
            pstmt.setInt(1, idInvoice );
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int idThietBi = rs.getInt("idThietBi");
                IDs.add(idThietBi);
            }
            return IDs;
        } catch (SQLException e) {
            e.printStackTrace();
            return IDs;
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public ThietBi layThongTinThietBiTheoModel(String model){
        ThietBi thietBi = new ThietBi();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Thiet_Bi_QUERY_LAY_THONG_TIN_BY_modelThietBi);
            pstmt.setString(1,model);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                thietBi.setModelThietBi(rs.getString("modelThietBi"));
                thietBi.setXuatXuThietBi(rs.getString("xuatXuThietBi"));
                thietBi.setTenThietBi(rs.getString("tenThietBi"));
                thietBi.setGiaThietBi(rs.getInt("giaThietBi"));
                thietBi.setMauThietBi(rs.getString("mauThietBi"));
                thietBi.setKichThuocThietBi(rs.getString("kichThuocThietBi"));
                thietBi.setThoiGianBaoHanh(rs.getString("thoiGianBaoHanh"));
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
            pstmt.setInt(8, thietBi.getGiaThietBi());
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
            pstmt.setInt(7, thietBi.getGiaThietBi());
            pstmt.setString(8, thietBi.getMaNVNguoiNhap());
            pstmt.setDate(9, thietBi.getNgayNhapThietBi());
            pstmt.setString(10, thietBi.getThoiGianBaoHanh());
            pstmt.setString(11, thietBi.getTrangThaiThietBi());
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
    public void updatePhieuXuatThietBi(int idThietBi, Integer idPhieuXuat){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Thiet_Bi_QUERY_UPDATE_IDPHIEUXUAT + idThietBi);;
            pstmt.setInt(1, idPhieuXuat);
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
    public void updatePhieuXuatThietBi_Delete(int idThietBi){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Thiet_Bi_QUERY_CLEAR_IDPHIEUXUAT);;
            pstmt.setInt(1, idThietBi);
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
    @Override
    public ObservableList<ThietBi> timTatCaThietBiTrangThaiDaXuat(){
        ObservableList<ThietBi> f = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Thiet_Bi_QUERY_LAY_THONG_TIN_BY_trangThaiThietBi_Da_Xuat);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int idThietBi = rs.getInt("idThietBi");
                String tenThietBi = rs.getString("tenThietBi");
                String modelThietBi = rs.getString("modelThietBi");
                String serialThietBi = rs.getString("serialThietBi");
                int idPhieuXuat = rs.getInt("idPhieuXuat");
                f.add(new ThietBi(idThietBi,tenThietBi,modelThietBi,serialThietBi,idPhieuXuat));
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
    public ObservableList<ThietBi> timTatCaThietBiDaXuatTheoSerial(String serial){
        ObservableList<ThietBi> f = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Thiet_Bi_QUERY_LAY_THONG_TIN_BY_trangThaiThietBi_Da_Xuat_SerialThietBi);
            pstmt.setString(1, serial);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int idThietBi = rs.getInt("idThietBi");
                String tenThietBi = rs.getString("tenThietBi");
                String modelThietBi = rs.getString("modelThietBi");
                String serialThietBi = rs.getString("serialThietBi");
                int idPhieuXuat = rs.getInt("idPhieuXuat");
                f.add(new ThietBi(idThietBi,tenThietBi,modelThietBi,serialThietBi,idPhieuXuat));
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

    public void updateIDPhieuBaoHanh(int idThietBi, Integer idPBH){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Thiet_Bi_QUERY_UPDATE_IDPBH);;
            pstmt.setInt(1, idPBH);
            pstmt.setInt(2, idThietBi);
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
