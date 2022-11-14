module com.example.medicaldevicessalesmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;


    opens controller to javafx.fxml;
    exports controller;

    opens view to javafx.fxml;
    exports view;

    opens entity to javafx.fxml;
    exports entity;

    opens controller.NhanVien to javafx.fxml;
    exports controller.NhanVien;

}