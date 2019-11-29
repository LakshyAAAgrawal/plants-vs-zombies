package PvZ;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.io.Serializable;
import java.net.URISyntaxException;

public abstract class GraphicObject implements Serializable {
    transient ImageView image;
    protected final double x;
    protected final double y;
    String image_media;

    public GraphicObject(String img,double x,double y){
        image_media=img;
        this.x=x;
        this.y=y;
    }
    public void createGraphicObject(Pane parent){
        try {
            image = new ImageView(new Image(getClass().getResource(image_media).toURI().toString())); //try catch
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        parent.getChildren().add(image);
        image.setLayoutX(x);
        image.setLayoutY(y);
    }
}