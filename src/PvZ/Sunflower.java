package PvZ;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.Serializable;
import java.net.URISyntaxException;

public class Sunflower extends Plant implements Serializable{
    private int give_sun_timer;
    private Sun sun;
    private boolean is_sun_added=false;
    public Sunflower(double x,double y){
        super(100,100,50,10,x,y,"sunflower.gif");
        this.createGraphicObject();
        give_sun_timer=600;
        sun=new Sun(x,y);
        /*try {
            sun = new ImageView(new Image(getClass().getResource(image_media).toURI().toString()));
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }*/
        sun.createGraphicObject();
        sun.image.setFitWidth(100);
        sun.image.setFitHeight(100);
        sun.setX(100);
        sun.setY(-213);
    }
    public void sun_setX_Y(double x,double y){
        sun.setX(x);
        sun.setY(y);
    }
    public void update(Pane pane){
        if(!is_sun_added){
            pane.getChildren().add(sun.image);
            is_sun_added=true;
        }
        if (give_sun_timer>0) {
            give_sun_timer -= 1;
            if (give_sun_timer==0){
                sun.setX(x);
                sun.setY(y);
            }
        }

        sun.image.setOnMouseClicked(e -> {
            this.sun_setX_Y(100,-213);
            //gameScreenController.score.setText(gameScreenController.score.getText()+25);
        });

        if (give_sun_timer==300) {
            this.sun_setX_Y(100,-213);
        }
    }
}
