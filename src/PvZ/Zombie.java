package PvZ;

import java.io.Serializable;

public abstract class Zombie extends Character implements Serializable {
    private int laneNumber;
    private int x;
    private final int movePerFrame=10;
    public Zombie(int HP, int attack_power, int l_n, int x,String img){
        super(HP,attack_power,img);
        this.laneNumber=l_n;
        this.x=x;
    }
    public void attack(Plant plant){
        plant.reduce_HP(this.attack_power);
    }
}
