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
    private int idInvoice;

    private double subTotalInvoice;

    private Integer vatInvoice;

    private double discountInvoice;

    private double discount1Invoice;

    private double totalInvoice;

    private Date exportDateInvoice;

    private Integer idEmployeeInvoice;

    private Integer idCustomerInvoice;

}
