package PvZ;

import javafx.scene.image.ImageView;

import java.io.Serializable;

public abstract class GraphicObject implements Serializable {
    transient ImageView image;
    int x, y;

}
