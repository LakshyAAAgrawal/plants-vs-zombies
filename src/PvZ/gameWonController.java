package PvZ;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class gameWonController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private ImageView exitBtn;

    @FXML
    private ImageView mainBtn;

    @FXML
    private ImageView nextBtn;

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

    int level;
    String username;

    @FXML
    void exit(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void nextLevel(MouseEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameScreen.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        gameScreenController controller = fxmlLoader.<gameScreenController>getController();
        controller.setLevel(((level+1) % 5) + 1);
        controller.setUsername(username);
        mainAnchor.getScene().setRoot(root);
    }

    public void setLevel(int l){
        this.level = l;
    }

    public void setUsername(String username){
        this.username = username;
    }

    @FXML
    void initialize() {

    }
}
