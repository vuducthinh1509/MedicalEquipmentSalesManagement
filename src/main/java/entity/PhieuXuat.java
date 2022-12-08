package entity;

import javafx.collections.ObservableList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class PhieuXuat {
    private int idPhieuXuat;

    private double tongTien;

    private Integer vat;

    private double giamGiaTheoPhanTram;

    private double giamGiaTheoTien;

    private double tienPhaiTra;

    private Date ngayXuatPhieu;

    private int idNhanVien;

    private int idKhachHang;

    private ObservableList<Item> itemList;
}
