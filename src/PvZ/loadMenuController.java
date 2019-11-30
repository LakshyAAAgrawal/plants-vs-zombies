package PvZ;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;


public class loadMenuController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView saveGame1;

    @FXML
    private ImageView saveGame2;

    @FXML
    private ImageView saveGame3;

    @FXML
    private ImageView saveGame4;
    String username;
    File[] savefiles;

    @FXML
    void initialize() {
        assert saveGame1 != null : "fx:id=\"saveGame1\" was not injected: check your FXML file 'loadMenu.fxml'.";
        assert saveGame2 != null : "fx:id=\"saveGame2\" was not injected: check your FXML file 'loadMenu.fxml'.";
        assert saveGame3 != null : "fx:id=\"saveGame3\" was not injected: check your FXML file 'loadMenu.fxml'.";
        assert saveGame4 != null : "fx:id=\"saveGame4\" was not injected: check your FXML file 'loadMenu.fxml'.";
    }

    void setUsername(String Username){
        this.username = Username;
        updateSaves();
    }

    void updateSaves(){
        File dir = new File(".");
        File [] files = dir.listFiles((d, name) -> name.startsWith(username));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        if(files.length == 0){
            gridPane.add(new Label("No Game Saves Found"), 0, 0);
        }else {
            for(int xx = 0; xx < files.length; xx++) {
                String numWithExtension = files[xx].getName().substring(username.length());
                String timeNano = numWithExtension.substring(0, numWithExtension.length() - 4);
                System.out.println(timeNano);
                Date date = new Date(Long.parseLong(timeNano));
                Label label = new Label(date.toString());
                int finalXx = xx;
                label.setOnMouseClicked(e -> {
                    int fileNum = finalXx;
                    GameState saved = null;
                    ObjectInputStream in = null;
                    if(savefiles.length >= 1 && savefiles[fileNum] != null){
                        try {
                            in = new ObjectInputStream(new FileInputStream(savefiles[fileNum]));
                            // Method for deserialization of object
                            saved = (GameState) in.readObject();
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        } finally {
                            try {
                                in.close();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameScreen.fxml"));
                    Parent root = null;
                    try {
                        root = fxmlLoader.load();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    gameScreenController controller = fxmlLoader.<gameScreenController>getController();
                    controller.setLevel(saved.level);
                    controller.setUsername(username);
                    controller.setGameState(saved);
                    gridPane.getScene().setRoot(root);
                });
                gridPane.add(label, 0, xx);
                label.setStyle("-fx-font-size:20px; -fx-text-fill: red;");
            }
            savefiles = files;
        }
    }

}
