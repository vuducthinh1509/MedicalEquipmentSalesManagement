package entity;

import javafx.collections.ObservableList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import repository.KhachHangRepository;
import repository.KhachHangRepository_impl;
import repository.NhanVienRepository;
import repository.NhanVienRepository_impl;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class PhieuXuat {
    private int idInvoice;

    private double subTotalInvoice;

    private Integer vatInvoice;

    private double discountInvoice;

    private double discount1Invoice;

    private double totalInvoice;

    private Date exportDateInvoice;

    private Integer idEmployeeInvoice;

    private Integer idCustomerInvoice;

    private String nameCustomer;

    private String nameEmployee;

    public PhieuXuat(int idInvoice,double subTotalInvoice,int vatInvoice,double discountInvoice,double discount1Invoice,double totalInvoice,Date exportDateInvoice,Integer idEmployeeInvoice,Integer idCustomerInvoice){
        KhachHang khachHang = new KhachHang();
        KhachHangRepository khachHangRepo = new KhachHangRepository_impl();
        NhanVien nhanVien = new NhanVien();
        NhanVienRepository nhanVienRepo = new NhanVienRepository_impl();
        this.idInvoice = idInvoice;
        this.subTotalInvoice = subTotalInvoice;
        this.vatInvoice = vatInvoice;
        this.discountInvoice = discountInvoice;
        this.discount1Invoice = discount1Invoice;
        this.totalInvoice = totalInvoice;
        this.exportDateInvoice = exportDateInvoice;
        this.idEmployeeInvoice = idEmployeeInvoice;
        this.idCustomerInvoice = idCustomerInvoice;
        khachHang = khachHangRepo.getInformationCustomerByID(idCustomerInvoice);
        this.nameCustomer = khachHang.getTenKhachHang();
        nhanVien = nhanVienRepo.getInformationUser(idEmployeeInvoice);
        this.nameEmployee = nhanVien.getHoTen();
    }

}
