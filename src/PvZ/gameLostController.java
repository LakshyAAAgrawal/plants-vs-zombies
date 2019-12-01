package PvZ;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class gameLostController {
    @FXML
    private AnchorPane mainAnchor;
    int level;
    String username;

    @FXML
    void backToMainMenu(MouseEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("start_menu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainAnchor.getScene().setRoot(root);
    }


    @FXML
    void exit(MouseEvent event) {
        System.exit(0);
    }
    public void setLevel(int l){
        this.level = l;
    }

    public void setUsername(String username){
        this.username = username;
    }
}
