package utility;

import javafx.scene.control.Alert;

public class Box {
    public static void alertBox( String title, String header, String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
