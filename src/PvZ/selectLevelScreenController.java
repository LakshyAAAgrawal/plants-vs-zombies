package PvZ;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class selectLevelScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView level1Btn;

    @FXML
    private ImageView level2Btn;

    @FXML
    private ImageView level3Btn;

    @FXML
    private ImageView level4Btn;

    @FXML
    private ImageView level5Btn;


    @FXML
    void startLevel1(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameScreen.fxml"));
        Parent root = fxmlLoader.load();
        gameScreenController controller = fxmlLoader.<gameScreenController>getController();
        controller.setLevel(1);
        level1Btn.getScene().setRoot(root);
    }

    @FXML
    void startLevel2(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameScreen.fxml"));
        Parent root = fxmlLoader.load();
        gameScreenController controller = fxmlLoader.<gameScreenController>getController();
        controller.setLevel(2);
        level1Btn.getScene().setRoot(root);
    }

    @FXML
    void startLevel3(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameScreen.fxml"));
        Parent root = fxmlLoader.load();
        gameScreenController controller = fxmlLoader.<gameScreenController>getController();
        controller.setLevel(3);
        level1Btn.getScene().setRoot(root);
    }

    @FXML
    void startLevel4(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameScreen.fxml"));
        Parent root = fxmlLoader.load();
        gameScreenController controller = fxmlLoader.<gameScreenController>getController();
        controller.setLevel(4);
        level1Btn.getScene().setRoot(root);
    }

    @FXML
    void startLevel5(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameScreen.fxml"));
        Parent root = fxmlLoader.load();
        gameScreenController controller = fxmlLoader.<gameScreenController>getController();
        controller.setLevel(5);
        level1Btn.getScene().setRoot(root);
    }

    @FXML
    void initialize() {
        assert level1Btn != null : "fx:id=\"level1Btn\" was not injected: check your FXML file 'selectLevelScreen.fxml'.";
        assert level2Btn != null : "fx:id=\"level2Btn\" was not injected: check your FXML file 'selectLevelScreen.fxml'.";
        assert level3Btn != null : "fx:id=\"level3Btn\" was not injected: check your FXML file 'selectLevelScreen.fxml'.";
        assert level4Btn != null : "fx:id=\"level4Btn\" was not injected: check your FXML file 'selectLevelScreen.fxml'.";
        assert level5Btn != null : "fx:id=\"level5Btn\" was not injected: check your FXML file 'selectLevelScreen.fxml'.";


    }

}
