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

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
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
    public static ImageView menu_wallnut;

    @FXML
    private Label score;

    @FXML
    private ImageView lawnmower1;

    @FXML
    private ImageView exit_to_menu_btn;

    @FXML
    private ImageView lawnmower2;

    @FXML
    private ImageView lawnmower3;

    @FXML
    private ImageView lawnmower4;

    @FXML
    private ImageView lawnmower5;

    @FXML
    private Label pause_game_btn;
    int counter = 1;
    @FXML
    void saveGameClicked(MouseEvent event) throws IOException{
        FileOutputStream file = new FileOutputStream(username + counter + ".pvz");
        ObjectOutputStream out = new ObjectOutputStream(file);
        try {
            out.writeObject(gameState);
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            out.close();
            file.close();
        }
    }

    @FXML
    private Label timer;

    GameState gameState;

    @FXML
    private ImageView resume_game_btn;

    public int level = 1;
    private String username;

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

    ImageView[] lawnmowers;

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
        lawnmowers = new ImageView[]{lawnmower1, lawnmower2, lawnmower3, lawnmower4, lawnmower5};
        //gameState = new GameState(gameScreenPane, level, timer, score, lawnmowers);
        //gameState.createGraphicObjects();
        new AnimationTimer() {
            private long lastUpdate = 0;
            double numFrame = 0;
            @Override
            public void handle(long now) {
                if(lastUpdate == 0) lastUpdate = now;
                double elapsedNanoSeconds = now - lastUpdate ;
                double numFrames = numFrame + (elapsedNanoSeconds*60) / 1_000_000_000 ;
                numFrame = numFrames - Math.floor(numFrames);
                for(int i = 0; i < Math.floor(numFrames); i++){
                    try {
                        gameState.advance_one_frame();
                    }catch(GameWonException e){
                        System.out.println("You won");
                        System.out.println("You got " + e.numSunTokens + " Points!!");
                        this.stop();
                    }catch(GameLostException e){
                        System.out.println("You Lost");
                        this.stop();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                lastUpdate = now ;
            }
        }.start();
    }

    public void setLevel(int i) {
        System.out.println("level " + i);
        this.level = i;
        gameState = new GameState(gameScreenPane, level, timer, score, lawnmowers);
        try {
            gameState.createGraphicObjects();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setGameState(GameState gameState){
        this.gameState = gameState;
        gameState.setTransientAttributes(gameScreenPane, timer, score, lawnmowers);
        try {
            gameState.createGraphicObjects();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}

