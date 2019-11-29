package PvZ;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.io.Serializable;
import java.net.URISyntaxException;

public abstract class GraphicObject implements Serializable {
    transient ImageView image;
    protected int x;
    protected int y;
    String image_media;

    public GraphicObject(String img,int x,int y){
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
    }
}
