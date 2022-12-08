package repository;

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
}
