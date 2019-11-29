package PvZ;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.animation.PathTransition;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.LineTo;
import sun.security.provider.Sun;

import java.util.Random;
import java.util.ArrayList;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
public class gameScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private AnchorPane gameScreenPane;

    @FXML
    private ScrollPane MenuScrollPane;

    @FXML
    private ImageView zombie1;

    @FXML
    private ImageView zombie2;

    @FXML
    private ImageView zombie3;

    @FXML
    private ImageView zombie4;

    @FXML
    private ImageView zombie5;

    @FXML
    public static ImageView menu_wallnut;

    @FXML
    private Label score;

    @FXML
    private ImageView fall_sun1;

    @FXML
    private ImageView fall_sun2;

    @FXML
    private ImageView fall_sun3;

    @FXML
    private ImageView fall_sun4;

    @FXML
    private ImageView fall_sun5;

    @FXML
    private ImageView fall_sun6;

    @FXML
    private ImageView fall_sun7;

    @FXML
    private ImageView fall_sun8;

    @FXML
    private ImageView fall_sun9;

    @FXML
    private ImageView lawnmower1;

    @FXML
    private ImageView exit_to_menu_btn;

    @FXML
    private ImageView lawnmower3;

    @FXML
    private ImageView lawnmower5;

    @FXML
    private Label pause_game_btn;

    @FXML
    private Label timer;

    @FXML
    private GridPane lawn_grid;

    @FXML
    private ImageView resume_game_btn;

    @FXML
    private ImageView peashooter1;

    @FXML
    private ImageView peashooter2;

    public int level = 1;

    @FXML
    void exit_to_main_menu(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("start_menu.fxml"));
        resume_game_btn.getScene().setRoot(root);
    }

    @FXML
    void pause_game(MouseEvent event) {
        MenuScrollPane.setVvalue(1);
    }

    @FXML
    void resume_game(MouseEvent event) {
        MenuScrollPane.setVvalue(0);
    }

    @FXML
    void collect_sun(MouseEvent event) {
        score.setText(String.valueOf(Integer.valueOf(score.getText())+25));
        System.out.println(score.getText());
    }

    @FXML
    void initialize() throws InterruptedException, URISyntaxException {
        assert scrollPane != null : "fx:id=\"scrollPane\" was not injected: check your FXML file 'GameScreen.fxml'.";
        scrollPane.setHvalue(1);

        Media sound = new Media(getClass().getResource("music.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        new AnimationTimer(){
            @Override
            public void handle(long now){
                mediaPlayer.play();
            }
        }.start();
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
        GameState gamestate = new GameState(gameScreenPane, level);
        gamestate.createGraphicObjects();
        new AnimationTimer() {
            private long lastUpdate ;
            @Override
            public void handle(long now) {
                long elapsedNanoSeconds = now - lastUpdate ;
                long numFrames = (elapsedNanoSeconds*60) / 1_000_000_000 ;
                for(int i = 0; i < numFrames; i++){
                    gamestate.advance_one_frame();
                }
                lastUpdate = now ;
            }
        }.start();
    }

}

