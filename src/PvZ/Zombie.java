package PvZ;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.net.URISyntaxException;

public abstract class Zombie extends Character implements Serializable {
    protected int laneNumber;
    protected final int movePerFrame=10;
    String dyingImageUrl;
    public Zombie(int HP, int attack_power, int l_n, double x, double y, String normalURL, String dyingURL){
        super(HP,attack_power,normalURL,x,y);
        this.dyingImageUrl = dyingURL;
        this.laneNumber=l_n;
    }
    public void attack(Plant plant) {
        plant.reduce_HP(this.attack_power/60);
    }
    public int getLaneNumber(){
        return laneNumber;
    }

    public void die() {
        this.HP = -1;
        try {
            this.image.setImage(new Image(getClass().getResource(dyingImageUrl).toURI().toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Zombie z = this;
        new AnimationTimer(){
            int x = 0;
            @Override
            public void handle(long now) {
                x++;
                if(x > 50){
                    z.setX(-150);
                    z.setY(-150);
                    this.stop();
                }
            }
        }.start();
    }
}
