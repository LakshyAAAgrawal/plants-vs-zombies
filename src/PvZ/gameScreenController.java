package PvZ;

import javafx.animation.AnimationTimer;
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
import sun.security.provider.Sun;

import java.util.Random;
import java.util.ArrayList;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private ImageView sun;
    private int t=0;
    public SunMover(ImageView sun){
        super();
        this.sun = sun;
    }
    @Override
    public void handle(long now) {
        double currVal = sun.getLayoutY();
        double newVal = currVal + 2;
        if (t==100){
            sun.setVisible(false);
            sun.setLayoutY(-213);
            sun.setVisible(true);
            this.stop();
        }
        else if(newVal >= 490){
            t+=1;
        }else{
            sun.setLayoutY(newVal);
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

class Menu extends AnimationTimer{
    protected int timer=0;
    private int t=0;
    //private ImageView curr_plant;
    protected ImageView fade_plant;
    protected ImageView plant;

    public Menu(String plant1,String fade_plant1){
        super();
        //this.curr_plant=curr_plant;
        initialise_plants(plant1,fade_plant1);
        this.plant=plant;
        this.fade_plant=fade_plant;
    }

    private void initialise_plants(String plant1,String fade_plant1) {
        try {
            plant = new ImageView(new Image(getClass().getResource(plant1).toURI().toString()));
            fade_plant = new ImageView(new Image(getClass().getResource(fade_plant1).toURI().toString()));
            System.out.println("YES");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(long now){
        if (timer==0){ //need to include score also here
            /*double d1=gameScreenController.menu_wallnut.getLayoutX();
            double d2=gameScreenController.menu_wallnut.getLayoutY();
            gameScreenController.menu_wallnut=plant;*/
            gameScreenController.menu_wallnut.setImage(plant.getImage());
        }
        if (timer>0) {
            gameScreenController.menu_wallnut.setImage(fade_plant.getImage());
            t++;
            if (t % 60 == 0) {
                t = 0;
                timer -= 1;
            }
        }
    }
}

class Wallnut extends Menu{
    private int price=50;
    public Wallnut(ImageView menu_wallnut){
        super("menu_wallnut1.png","fade_wallnut.jpg");
        try {
            ImageView fade_plant = new ImageView(new Image(getClass().getResource("fade_wallnut.jpg").toURI().toString())); //try catch
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
            gameScreenController.menu_wallnut.setImage(fade_plant.getImage());
        this.timer=15;
    }
}

/*class Peashooter extends Menu{
    private int price=100;

    public Peashooter(){
        this.timer=15;
    }
}

class CherryBomb extends Menu{
    private int price=150;
    public CherryBomb(){
        this.timer=15;
    }
}

class Tripeater extends Menu{
    private int price=250;
    public Tripeater(){
        this.timer=15;
    }
}

class Sunflower extends Menu{
    private int price=50;
    public Sunflower(){
        this.timer=15;
    }
}

class Repeater extends Menu{
    private int price=200;
    public Repeater(){
        this.timer=15;
    }
}*/

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
        /*new AnimationTimer(){
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

        }.start();*/

        ArrayList<ImageView> suns = new ArrayList<ImageView>();
        suns.add(fall_sun1);
        suns.add(fall_sun2);
        suns.add(fall_sun3);
        suns.add(fall_sun4);
        suns.add(fall_sun5);
        suns.add(fall_sun6);
        suns.add(fall_sun7);
        suns.add(fall_sun8);
        suns.add(fall_sun9);

        new AnimationTimer(){
            private int t=0;
            private int sun_num=0;

            @Override
            public void handle(long now){
                if (t==0 || t==350) {
                    t = 0;
                    sun_num = new Random().nextInt(9);
                    new SunMover(suns.get(sun_num)).start();
                }
                t++;
            }
        }.start();

        new Wallnut(menu_wallnut);
        //new SunMover(fall_sun1).start();
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
                   /* new ZombieMover(zombie1, lawnMowerMover1).start();
                    //new ZombieMover(zombie2).start();
                    new ZombieMover(zombie3, lawnMowerMover3).start();
                    //new ZombieMover(zombie4).start();
                    new ZombieMover(zombie5, lawnMowerMover5).start();*/
                    this.stop();
                }else{
                    scrollPane.setHvalue(newVal);

                }
            }
        }.start();

    }

}

