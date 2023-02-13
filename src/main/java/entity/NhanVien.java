package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class NhanVien {

    private String username;

    private String password;

    private Integer role;

    private Integer id;

    private String maNV;

    private String hoTen;

    private Date ngaySinh;

    private String diaChiThuongTru;

    private String CCCD;

    private String soDienThoai;

    private String email;

    private Date ngayVaoLam;

    private String chucVu;

    private String gioiTinh;

    private String cauHoi;

    private String cauTraLoi;

    public NhanVien(String username,String password, String cauHoi, String cauTraLoi){
        this.username = username;
        this.password = password;
        this.cauHoi = cauHoi;
        this.cauTraLoi = cauTraLoi;
    }

}
