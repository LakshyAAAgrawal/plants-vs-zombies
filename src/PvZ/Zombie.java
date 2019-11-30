package PvZ;

import java.io.Serializable;

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
        plant.reduce_HP(this.attack_power);
    }
    public int getLaneNumber(){
        return laneNumber;
    }
}
