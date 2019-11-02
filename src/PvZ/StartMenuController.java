package PvZ;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class StartMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView bgImage;

    @FXML
    private ImageView exit_btn;

    @FXML
    private ImageView load_btn;

    @FXML
    private ImageView slct_lvl_btn;

    @FXML
    private ImageView start_btn;

    @FXML
    void exitBtnClicked(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void loadGameClicked(MouseEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("loadMenu.fxml"));
        load_btn.getScene().setRoot(root);
    }

    @FXML
    void selectLevelClicked(MouseEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("selectLevelScreen.fxml"));
        load_btn.getScene().setRoot(root);
    }

    @FXML
    void startNewGameClicked(MouseEvent event) throws IOException{
        System.out.println("Start new game clicked");
        Parent root = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
        start_btn.getScene().setRoot(root);
    }

    @FXML
    void initialize() {
        assert bgImage != null : "fx:id=\"bgImage\" was not injected: check your FXML file 'start_menu.fxml'.";
        assert exit_btn != null : "fx:id=\"exit_btn\" was not injected: check your FXML file 'start_menu.fxml'.";
        assert load_btn != null : "fx:id=\"load_btn\" was not injected: check your FXML file 'start_menu.fxml'.";
        assert slct_lvl_btn != null : "fx:id=\"slct_lvl_btn\" was not injected: check your FXML file 'start_menu.fxml'.";
        assert start_btn != null : "fx:id=\"start_btn\" was not injected: check your FXML file 'start_menu.fxml'.";
    }

}
