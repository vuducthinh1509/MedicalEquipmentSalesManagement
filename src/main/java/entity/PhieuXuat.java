package entity;

import javafx.collections.ObservableList;
import lombok.*;
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

    private String maNV;

    private String nameCustomer;

    private String nameEmployee;

    private String phoneCustomer;

    public PhieuXuat(int idInvoice,double subTotalInvoice,int vatInvoice,double discountInvoice,double discount1Invoice,double totalInvoice,Date exportDateInvoice,Integer idEmployeeInvoice,Integer idCustomerInvoice){

        this.idInvoice = idInvoice;
        this.subTotalInvoice = subTotalInvoice;
        this.vatInvoice = vatInvoice;
        this.discountInvoice = discountInvoice;
        this.discount1Invoice = discount1Invoice;
        this.totalInvoice = totalInvoice;
        this.exportDateInvoice = exportDateInvoice;
        this.idEmployeeInvoice = idEmployeeInvoice;
        this.idCustomerInvoice = idCustomerInvoice;
    }

    public void setNameCtmAndEpl(){
        KhachHang khachHang = new KhachHang();
        KhachHangRepository khachHangRepo = new KhachHangRepository_impl();
        NhanVien nhanVien = new NhanVien();
        NhanVienRepository nhanVienRepo = new NhanVienRepository_impl();
        khachHang = khachHangRepo.getInformationCustomerByID(idCustomerInvoice);
        this.nameCustomer = khachHang.getTenKhachHang();
        nhanVien = nhanVienRepo.getInformationUser(idEmployeeInvoice);
        this.nameEmployee = nhanVien.getHoTen();
        this.maNV = nhanVien.getMaNV();
        this.phoneCustomer = khachHang.getPhoneKhachHang();
    }

    public void cloneInvoice(PhieuXuat clone){
        this.idInvoice = clone.getIdInvoice();
        this.subTotalInvoice = clone.getSubTotalInvoice();
        this.vatInvoice = clone.getVatInvoice();
        this.discountInvoice = clone.getDiscountInvoice();
        this.discount1Invoice = clone.getDiscount1Invoice();
        this.totalInvoice = clone.getTotalInvoice();
        this.exportDateInvoice = clone.getExportDateInvoice();
        this.idEmployeeInvoice = clone.getIdEmployeeInvoice();
        this.idCustomerInvoice = clone.getIdCustomerInvoice();
        this.nameEmployee = clone.getNameEmployee();
        this.nameCustomer = clone.getNameCustomer();
        this.maNV = clone.getMaNV();
        this.phoneCustomer = clone.getPhoneCustomer();
    }
}
