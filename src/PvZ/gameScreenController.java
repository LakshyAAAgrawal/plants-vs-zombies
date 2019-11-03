package PvZ;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import sun.security.provider.Sun;

class LawnMowerMover extends AnimationTimer{
    private ImageView lawnmower;
    public LawnMowerMover(ImageView lawnmower){
        super();
        this.lawnmower = lawnmower;
    }
    @Override
    public void handle(long now) {
        double currVal = lawnmower.getLayoutX();
        double newVal = currVal + 4;
        if(newVal >= 800){
            this.stop();
        }else{
            lawnmower.setLayoutX(newVal);

        }
    }
}

class SunMover extends AnimationTimer{
    private ImageView lawnmower;
    public SunMover(ImageView lawnmower){
        super();
        this.lawnmower = lawnmower;
    }
    @Override
    public void handle(long now) {
        double currVal = lawnmower.getLayoutY();
        double newVal = currVal + 4;
        if(newVal >= 490){
            this.stop();
        }else{
            lawnmower.setLayoutY(newVal);

        }
    }
}

class ZombieMover extends AnimationTimer{
    private ImageView zombie;
    private AnimationTimer lawnmower;
    public ZombieMover(ImageView zombie2, AnimationTimer lawnmower){
        super();
        this.zombie = zombie2;
        this.lawnmower = lawnmower;
    }
    @Override
    public void handle(long now) {
        double currVal = zombie.getLayoutX();
        double newVal = currVal - 2;
        if(newVal <= 160){
            try {
                zombie.setImage(new Image(getClass().getResource("basic_zombie_dying_loop_once.gif").toURI().toString()));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            zombie.setVisible(false);
            lawnmower.start();
            this.stop();
        }else{
            zombie.setLayoutX(newVal);

        }
    }
}

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
    private ImageView fall_sun;

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
        new AnimationTimer(){
            private int t = 0;
            @Override
            public void handle(long now){
                if(t == 59){
                    ImageView pea = null;
                    try {
                        pea = new ImageView(new Image(getClass().getResource("Pea.png").toURI().toString()));
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    gameScreenPane.getChildren().add(pea);
                    int newX = (int) (lawn_grid.getLayoutX() + (GridPane.getColumnIndex(peashooter1) * (lawn_grid.getWidth()/9)) + lawn_grid.getWidth()/12);
                    int newY = (int) (lawn_grid.getLayoutY() + (GridPane.getRowIndex(peashooter1) * (lawn_grid.getHeight()/5)) + (lawn_grid.getHeight()/20));
                    System.out.println(newX);
                    System.out.println(newX);
                    pea.setX(newX);
                    pea.setY(newY);
                    new LawnMowerMover(pea).start();
                    t++;
                }else{
                    t++;
                    if(t>=60) t=0;
                }
            }

        }.start();
        new SunMover(fall_sun).start();
        LawnMowerMover lawnMowerMover1 = new LawnMowerMover(lawnmower1);
        LawnMowerMover lawnMowerMover3 = new LawnMowerMover(lawnmower3);
        LawnMowerMover lawnMowerMover5 = new LawnMowerMover(lawnmower5);

        new AnimationTimer(){
            private int t = 0;
            @Override
            public void handle(long now){
                t++;
                if(t%60==0){
                    t=0;
                    if(Integer.parseInt(timer.getText()) == 0){
                        this.stop();
                    }else
                    timer.setText(String.valueOf(Integer.parseInt(timer.getText()) - 1));
                }

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
                    new ZombieMover(zombie1, lawnMowerMover1).start();
                    //new ZombieMover(zombie2).start();
                    new ZombieMover(zombie3, lawnMowerMover3).start();
                    //new ZombieMover(zombie4).start();
                    new ZombieMover(zombie5, lawnMowerMover5).start();
                    this.stop();
                }else{
                    scrollPane.setHvalue(newVal);

                }
            }
        }.start();

    }

}
