package PvZ;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.net.URISyntaxException;

public class Cherrybomb extends Plant implements Serializable {
    public double attackStat;
    public String dyingImageURL;
    public boolean inContact;
    public Cherrybomb(double x, double y){

        super(150,150,150,10,x,y,"cherry-bomb.gif");
        this.attackStat = 50;
        this.dyingImageURL = "cherrybomb_explodeonce.gif";
        this.inContact = false;
    }


    @Override
    public void die(){
        this.HP = -1;
        try {
            this.image.setImage(new Image(getClass().getResource(dyingImageURL).toURI().toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Cherrybomb z = this;
        new AnimationTimer(){
            int x = 0;
            @Override
            public void handle(long now) {
                x++;
                if(x > 100){
                    z.setX(1000);
                    z.setY(-150);
                    this.stop();
                }
            }
        }.start();
    }
}
