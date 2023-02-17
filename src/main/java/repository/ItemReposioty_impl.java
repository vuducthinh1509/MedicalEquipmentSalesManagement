package repository;

import entity.Item;
import entity.ThietBi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;

public class ItemReposioty_impl implements ItemRepository {
    private ResultSet rs = null;
    private final Statement stmt = null;
    private PreparedStatement pstmt = null;
    private final CallableStatement cstmt = null;
    private Connection conn = null;
    @Override
    public ObservableList<Item> loadDataItem() {
        ObservableList<Item> f = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Thiet_Bi_QUERY_getCountModel);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String tenThietBi = rs.getString("tenThietBi");
                String modelThietBi = rs.getString("modelThietBi");
                int count = rs.getInt("count(*)");
                String xuatXuThietBi = rs.getString("xuatXuThietBi");
                Integer giaThietBi = rs.getInt("giaThietBi");
                f.add(new Item(tenThietBi,modelThietBi,xuatXuThietBi,count,giaThietBi));
            }
            return f;
        } catch (SQLException e) {
            e.printStackTrace();
            return f;
        } finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
