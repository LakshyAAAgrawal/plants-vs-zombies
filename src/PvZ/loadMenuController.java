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
    void loadSave1(MouseEvent event) throws IOException , ClassNotFoundException{
        GameState saved = null;
        ObjectInputStream in = null;
        if(savefiles.length >= 1 && savefiles[0] != null){
            try {
                in = new ObjectInputStream(new FileInputStream(savefiles[0]));
                // Method for deserialization of object
                saved = (GameState) in.readObject();
            }finally {
                in.close();
            }
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameScreen.fxml"));
        Parent root = fxmlLoader.load();
        gameScreenController controller = fxmlLoader.<gameScreenController>getController();
        controller.setLevel(1);
        controller.setUsername(username);
        controller.setGameState(saved);
        saveGame1.getScene().setRoot(root);
    }

    @FXML
    void loadSave2(MouseEvent event) {
    }

    @FXML
    void loadSave3(MouseEvent event) {
    }

    @FXML
    void loadSave4(MouseEvent event) {
    }

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
        if(files.length == 0){
            gridPane.add(new Label("No Game Saves Found"), 0, 0);
        }else {
            for (int xx = 0; xx < files.length; xx++) {
                String numWithExtension = files[xx].getName().substring(username.length());
                String timeNano = numWithExtension.substring(0, numWithExtension.length() - 4);
                System.out.println(timeNano);
                Date date = new Date(Long.parseLong(timeNano));
                Label label = new Label(date.toString());
                label.setOnMouseClicked(e -> {
                    GameState saved = null;
                    ObjectInputStream in = null;
                    if(savefiles.length >= 1 && savefiles[0] != null){
                        try {
                            in = new ObjectInputStream(new FileInputStream(savefiles[0]));
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
                label.autosize();
            }
            savefiles = files;
        }
    }

}
