package PvZ;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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
    private TextField usernameText;

    @FXML
    private ImageView load_btn;

    @FXML
    private ImageView slct_lvl_btn;

    @FXML
    private ImageView start_btn;
    private String username = "Rob";

    @FXML
    void updateUsername(KeyEvent event){
        username = usernameText.getText();
        if(!username.matches("[a-zA-Z]+")){
            usernameText.setText("");
            username = "Rob";
        }
        System.out.println(username);
    }

    @FXML
    void exitBtnClicked(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void loadGameClicked(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loadMenu.fxml"));
        Parent root = fxmlLoader.load();
        loadMenuController controller = fxmlLoader.<loadMenuController>getController();
        controller.setUsername(username);
        load_btn.getScene().setRoot(root);
    }

    @FXML
    void selectLevelClicked(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("selectLevelScreen.fxml"));
        Parent root = fxmlLoader.load();
        selectLevelScreenController controller = fxmlLoader.<selectLevelScreenController>getController();
        controller.setUsername(username);
        load_btn.getScene().setRoot(root);
    }

    @FXML
    void startNewGameClicked(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameScreen.fxml"));
        Parent root = fxmlLoader.load();
        gameScreenController controller = fxmlLoader.<gameScreenController>getController();
        controller.setLevel(1);
        controller.setUsername(username);
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
