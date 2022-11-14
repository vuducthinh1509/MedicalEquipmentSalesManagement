package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class NhanVien {

    private Integer id;

    private String hoTen;

    private String maNV;

    private Date ngaySinh;

    private String diaChiThuongTru;

    private String CCCD;

    private String soDienThoai;

    private String email;

    private Date ngayVaoLam;

    private String chucVu;

    private String gioiTinh;

}
