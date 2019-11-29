package PvZ;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.Serializable;
import java.net.URISyntaxException;

public class Sunflower extends Plant implements Serializable {
    private int give_sun_timer;
    ImageView sun;
    public Sunflower(double x,double y,Pane pane){
        super(100,100,50,10,x,y,"sunflower.gif");
        give_sun_timer=600;
        try {
            sun = new ImageView(new Image(getClass().getResource(image_media).toURI().toString()));
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        sun.setLayoutX(100);
        sun.setLayoutY(-213);
        pane.getChildren().add(sun);
    }
    public void sun_setX_Y(double x,double y){
        sun.setLayoutX(x);
        sun.setLayoutY(y);
    }
    public void update(){
        if (give_sun_timer>0) {
            give_sun_timer -= 1;
            if (give_sun_timer==0){
                sun.setLayoutX(x);
                sun.setLayoutY(y);
            }
        }

        sun.setOnMouseClicked(e -> {
            this.sun_setX_Y(100,-213);
            gameScreenController.score.setText(gameScreenController.score.getText()+25);
        });

        if (give_sun_timer==300) {
            this.sun_setX_Y(100,-213);
        }
    }
}
