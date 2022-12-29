package controller.BaoHanhBaoTri;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;

public class BHBTController {
    @FXML
    private Pane mainPane;
    @FXML
    private Button baoHanhButton;

    @FXML
    private Button baoTriButton;

    public void baoHanhButtonOnClicked(ActionEvent event) throws IOException {
        Pane baoHanhPane = FXMLLoader.load(getClass().getResource("/view/BaoHanhBaoTri/BaoHanh/BaoHanhPane.fxml"));
        mainPane.getChildren().add(baoHanhPane);
        mainPane.toFront();
    }

    public void baoTriButtonOnClicked(ActionEvent event) throws IOException{
        Pane baoTriPane = FXMLLoader.load(getClass().getResource("/view/BaoHanhBaoTri/BaoTri/BaoTriPane.fxml"));
        mainPane.getChildren().add(baoTriPane);
        mainPane.toFront();
    }

    public void removeBaoHanhPane(){
        mainPane.toBack();
    }
}
