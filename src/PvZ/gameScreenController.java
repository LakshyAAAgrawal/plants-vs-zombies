package PvZ;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;


public class gameScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ScrollPane scrollPane;


    @FXML
    void initialize() throws InterruptedException{
        assert scrollPane != null : "fx:id=\"scrollPane\" was not injected: check your FXML file 'GameScreen.fxml'.";
        scrollPane.setHvalue(1);
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double currVal = scrollPane.getHvalue();
                double newVal = currVal - 0.02;
                if(newVal <= 0){
                    scrollPane.setHvalue(0);
                    scrollPane.setHmax(0);
                    this.stop();
                }else{
                    scrollPane.setHvalue(newVal);

                }
            }
        }.start();

    }

}
