package utility;

import javafx.scene.control.Alert;

public class Box {
    public static void alertBox(String title, String header, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void alertBox_Warning(String title, String header, String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void alertBox_No_Result(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo!");
        alert.setHeaderText("Không tìm thấy kết quả phù hợp");
        alert.setContentText("Vui lòng thử lại");
        alert.showAndWait();
    }

    public static void alertBox_None_Selection(String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo!");
        alert.setHeaderText("Không "+text+" nào được lựa chọn");
        alert.setContentText("Vui lòng thử lại");
        alert.showAndWait();
    }

    public static void alertBox_None_Full_Fill(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo!");
        alert.setHeaderText("Cần nhập đầy đủ các trường");
        alert.setContentText("Vui lòng thử lại sau");
        alert.showAndWait();
    }
}
