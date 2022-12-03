package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class KhachHang {
    private int idKhachHang;

    private String tenKhachHang;

    private String phoneKhachHang;

    private String diaChiKhachHang;

    public KhachHang(String tenKhachHang,String phoneKhachHang,String diaChiKhachHang){
        this.tenKhachHang = tenKhachHang;
        this.phoneKhachHang = phoneKhachHang;
        this.diaChiKhachHang = diaChiKhachHang;
    }
    public void cloneKhachHang(KhachHang khachHang){
        this.idKhachHang = khachHang.getIdKhachHang();
        this.tenKhachHang = khachHang.getTenKhachHang();
        this.phoneKhachHang = khachHang.getPhoneKhachHang();
        this.diaChiKhachHang = khachHang.getDiaChiKhachHang();
    }
}
