package repository;

import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuXuat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;

public class PhieuXuatRepository_impl implements PhieuXuatRepository {

    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;
    @Override
    public int getNextAutoIncrement(){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.PhieuXuat_QUERY_LAY_NEXT_AUTOINDEX);
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
    public void addInvoice(PhieuXuat phieuXuat){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.PhieuXuat_QUERY_INSERT);
            pstmt.setDouble(1, phieuXuat.getSubTotalInvoice());
            pstmt.setInt(2, phieuXuat.getVatInvoice());
            pstmt.setDouble(3, phieuXuat.getDiscountInvoice());
            pstmt.setDouble(4, phieuXuat.getDiscount1Invoice());
            pstmt.setDouble(5, phieuXuat.getTotalInvoice());
            pstmt.setDate(6, phieuXuat.getExportDateInvoice());
            pstmt.setInt(7, phieuXuat.getIdEmployeeInvoice());
            pstmt.setInt(8, phieuXuat.getIdCustomerInvoice());
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

    public PhieuXuat getDetailInvoiceByID(Integer idInvoice){
        PhieuXuat phieuXuat = new PhieuXuat();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.PhieuXuat_QUERY_LAY_THONG_TIN_BY_ID);
            pstmt.setInt(1,idInvoice);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                phieuXuat.setIdInvoice(rs.getInt("idInvoice"));
                phieuXuat.setSubTotalInvoice(rs.getDouble("subTotalInvoice"));
                phieuXuat.setVatInvoice(rs.getInt("vatInvoice"));
                phieuXuat.setDiscountInvoice(rs.getDouble("discountInvoice"));
                phieuXuat.setDiscount1Invoice(rs.getDouble("discount1Invoice"));
                phieuXuat.setTotalInvoice(rs.getDouble("totalInvoice"));
                phieuXuat.setExportDateInvoice(rs.getDate("exportDateInvoice"));
                phieuXuat.setIdEmployeeInvoice(rs.getInt("idEmployeeInvoice"));
                phieuXuat.setIdCustomerInvoice(rs.getInt("idCustomerInvoice"));
            }
            return phieuXuat;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } return phieuXuat;
    }

    @Override
    public ObservableList<PhieuXuat> getAllDataInvoice(){
        ObservableList<PhieuXuat> invoiceList = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.PhieuXuat_QUERY_LAY_THONG_TIN);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("idInvoice");
                Double subTotal = rs.getDouble("subTotalInvoice");
                Integer vat = rs.getInt("vatInvoice");
                Double discount = rs.getDouble("discountInvoice");
                Double discount1 = rs.getDouble("discount1Invoice");
                Double total = rs.getDouble("totalInvoice");
                Date exportDate = rs.getDate("exportDateInvoice");
                Integer idEmployee = rs.getInt("idEmployeeInvoice");
                Integer idCustomer = rs.getInt("idCustomerInvoice");
                invoiceList.add(new PhieuXuat(id,subTotal,vat,discount,discount1,total,exportDate,idEmployee,idCustomer));
            }
            return invoiceList;
        } catch (SQLException e) {
            e.printStackTrace();
            return invoiceList;
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
