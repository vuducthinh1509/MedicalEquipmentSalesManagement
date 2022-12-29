package controller.BaoHanhBaoTri.BaoHanh;

import controller.BaoHanhBaoTri.BHBTController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.IOException;

public class BaoHanhController {
    @FXML
    private Pane mainPane;
    @FXML
    private Button backButton;

    @FXML
    private Button createButton;

    @SneakyThrows
    public void backButtonOnClicked(ActionEvent mouseEvent) {
        Pane pane = FXMLLoader.load(getClass().getResource("/view/BaoHanhBaoTri/BHBTPane.fxml"));
        mainPane.getChildren().clear();
        mainPane.getChildren().add(pane);
    }

    public void createButtonOnClicked(MouseEvent event) throws IOException {
        Pane taoPhieuBaoHanhPane = FXMLLoader.load(getClass().getResource("/view/BaoHanhBaoTri/BaoHanh/TaoPhieuBaoHanhPane.fxml"));
        mainPane.getChildren().add(taoPhieuBaoHanhPane);
        mainPane.toFront();
    }
}
