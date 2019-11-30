package PvZ;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.net.URISyntaxException;

public abstract class Plant extends Character implements Serializable {
    protected int token_cost, time_cost;
    public Plant(int HP,int attack,int token,int time,double x,double y,String img){
        super(HP,attack,img,x,y);
        this.token_cost=token;
        this.time_cost=time;
    }
    public void attack(Zombie z){
        z.reduce_HP(this.attack_power);
    }
    public int getToken_cost(){
        return token_cost;
    }
    public int getTime_cost(){
        return time_cost;
    }

    public void die() {
        this.HP = -1;
        this.setX(-150);
        this.setY(-150);
    }
}
