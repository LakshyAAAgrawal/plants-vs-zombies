package PvZ;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.io.Serializable;
import java.net.URISyntaxException;

public abstract class GraphicObject implements Serializable {
    transient ImageView image;
    protected double x;
    protected double y;
    String image_media;

    public GraphicObject(String img,double x,double y){
        image_media=img;
        this.x=x;
        this.y=y;
    }
    public void createGraphicObject(){
        try {
            image = new ImageView(new Image(getClass().getResource(image_media).toURI().toString())); //try catch
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        image.setLayoutX(x);
        image.setLayoutY(y);
    }

    public void setX(double x){
        this.x = x;
        image.setLayoutX(x);
    }

    public void setY(double y){
        this.y = y;
        image.setLayoutY(y);
    }

    public void addToPane(Pane pane){
        pane.getChildren().add(image);
        image.setLayoutX(x);
        image.setLayoutY(y);
    }
}
