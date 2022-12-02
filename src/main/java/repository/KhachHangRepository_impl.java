package repository;

import entity.KhachHang;
import entity.ThietBi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;

public class KhachHangRepository_impl implements KhachHangRepository {
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    public void addCustomer(KhachHang khachHang){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.KhachHang_QUERY_INSERT);
            pstmt.setString(1, khachHang.getTenKhachHang());
            pstmt.setString(2, khachHang.getPhoneKhachHang());
            pstmt.setString(3, khachHang.getDiaChiKhachHang());
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

    public void getInformationCustomerByID(Integer id){
        System.out.println("test");
    }

    @Override
    public ObservableList<KhachHang> getAllDataCustomer(){
        ObservableList<KhachHang> customerList = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.KhachHang_QUERY_GETALLDATA);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int idKhachHang = rs.getInt("idKhachHang");
                String tenKhachHang = rs.getString("tenKhachHang");
                String phoneKhachHang = rs.getString("sdtKhachHang");
                String diaChiKhachHang = rs.getString("diaChiKhachHang");
                customerList.add(new KhachHang(idKhachHang,tenKhachHang,phoneKhachHang,diaChiKhachHang));
            }
            return customerList;
        } catch (SQLException e) {
            e.printStackTrace();
            return customerList;
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateDataCustomer(KhachHang khachHang){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.KhachHang_QUERY_UPDATE + khachHang.getIdKhachHang());
            pstmt.setString(1, khachHang.getTenKhachHang());
            pstmt.setString(2, khachHang.getPhoneKhachHang());
            pstmt.setString(3, khachHang.getDiaChiKhachHang());
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

    public void deleteCustomer(int id){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.KhachHang_DELETE_KhachHang);
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
