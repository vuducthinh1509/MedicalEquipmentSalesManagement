package repository;

import entity.NhanVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;

public class NhanVienRepository_impl implements NhanVienRepository {
    private ResultSet rs = null;
    private final Statement stmt = null;
    private PreparedStatement pstmt = null;
    private final CallableStatement cstmt = null;
    private Connection conn = null;
    private int id;

    @Override
    public NhanVien getInformationUser(Integer id){
        NhanVien nhanVien = new NhanVien();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Nhan_Vien_QUERY_LAY_THONG_TIN_BY_ID);
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
            pstmt.setString(6, nhanVien.getChucVu());
            pstmt.setString(7, nhanVien.getGioiTinh());
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
    @Override
    public void themNV(NhanVien nhanVien) {
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Nhan_Vien_QUERY_INSERT_Nhan_Vien);
            pstmt.setString(1, nhanVien.getMaNV());
            pstmt.setString(2, nhanVien.getHoTen());
            pstmt.setDate  (3, nhanVien.getNgaySinh());
            pstmt.setString(4, nhanVien.getDiaChiThuongTru());
            pstmt.setString(5, nhanVien.getCCCD());
            pstmt.setString(6, nhanVien.getSoDienThoai());
            pstmt.setString(7, nhanVien.getEmail());
            pstmt.setDate  (8, nhanVien.getNgayVaoLam());
            pstmt.setString(9, nhanVien.getChucVu());
            pstmt.setString(10, nhanVien.getGioiTinh());
            pstmt.setInt(11,nhanVien.getRole());
            pstmt.setString(12,nhanVien.getUsername());
            pstmt.setString(13,nhanVien.getPassword());
            pstmt.setString(14,nhanVien.getCauHoi());
            pstmt.setString(15,nhanVien.getCauTraLoi());
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
    public void xoaNV(Integer id) {

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Nhan_Vien_QUERY_DELETE_Nhan_Vien );
            pstmt.setInt(1,id );
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }}

    @Override
    public ObservableList<NhanVien> loadDataNV() {
        ObservableList<NhanVien> f = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Nhan_Vien_QUERY_LAY_THONG_TIN);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                int role = rs.getInt("role");
                String maNV = rs.getString("maNV");
                String hoTen = rs.getString("hoTen");
                Date ngaySinh = rs.getDate("ngaySinh");
                String diaChiThuongTru = rs.getString("diaChiThuongTru");
                String CCCD = rs.getString("CCCD");
                String soDienThoai = rs.getString("soDienThoai");
                String email = rs.getString("email");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                String chucVu = rs.getString("chucVu");
                String gioiTinh = rs.getString("gioiTinh");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String cauHoi = rs.getString("cauHoi");
                String cauTraLoi = rs.getString("cauTraLoi");
                if(id!=1){
                    f.add(new NhanVien(username, password,role,id,maNV,hoTen,ngaySinh,diaChiThuongTru,CCCD,soDienThoai,email,ngayVaoLam,chucVu,gioiTinh,cauHoi,cauTraLoi));
                }
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
    public NhanVien getInformationByName(String hoTen) {
        NhanVien nhanVien = new NhanVien();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Nhan_Vien_QUERY_LAY_THONG_TIN_BY_hoTen);
            pstmt.setString(1,hoTen);
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
                nhanVien.setUsername(rs.getString("username"));
                nhanVien.setPassword(rs.getString("passwprd"));
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
    public ObservableList<NhanVien> timNhanVienTheoTruong(String queryTheoTruong, String duLieuTraCuu) {
        ObservableList<NhanVien> f = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(queryTheoTruong);
            pstmt.setString(1, "%"+duLieuTraCuu+"%" );
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String maNV = rs.getString("maNV");
                String hoTen = rs.getString("hoTen");
                Date ngaySinh = rs.getDate("ngaySinh");
                String diaChiThuongTru = rs.getString("diaChiThuongTru");
                String CCCD = rs.getString("CCCD");
                String soDienThoai = rs.getString("soDienThoai");
                String email = rs.getString("email");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                String chucVu = rs.getString("chucVu");
                String gioiTinh = rs.getString("gioiTinh");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int role = rs.getInt("role");
                String cauHoi = rs.getString("cauHoi");
                String cauTraLoi = rs.getString("cauTraLoi");

                f.add(new NhanVien(username, password,role,id,maNV,hoTen,ngaySinh,diaChiThuongTru,CCCD,soDienThoai,email,ngayVaoLam,chucVu,gioiTinh,cauHoi,cauTraLoi));
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
    public int getNextAutoIncrement(){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.Nhan_Vien_QUERY_LAY_NEXT_INDEX);
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