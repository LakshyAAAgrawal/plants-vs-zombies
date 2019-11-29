package PvZ;

import java.io.Serializable;

public abstract class Zombie extends Character implements Serializable {
    protected int laneNumber;
    protected final int movePerFrame=10;
    public Zombie(int HP, int attack_power, int l_n, int x, int y, String img){
        super(HP,attack_power,img,x,y);
        this.laneNumber=l_n;
    }
    public void attack(Plant plant) {
        plant.reduce_HP(this.attack_power);
    }
    public int getLaneNumber(){
        return laneNumber;
    }
    public int getX(){
        return x;
    }
}
