package PvZ;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URISyntaxException;

public class Sun extends GraphicObject {
    //private double x,y;
    public Sun() {
        super("sun_original.png", 100, -213);
        this.createGraphicObject();
        this.setDimension(50, 50);
        /* this.image.setFitHeight(50);
        System.out.println(60);
        this.image.setFitWidth(50);
        System.out.println(70);
        */
    }

    public void setX_1(double x) {
        this.image.setLayoutX(x);
    }

    public void setY_1(double y) {
        this.image.setLayoutY(y);
    }

    public double getX_1() {
        return this.image.getLayoutX();
    }

    public double getY_1() {
        return this.image.getLayoutY();
    }

    public void updateY() {   //used for falling sun
        if (this.getY_1() > -213) {
            this.setY_1(this.getY_1() + 1);
            if (this.getY_1() >= 490) {
                this.setY_1(-214);
            }
        }
    }
}