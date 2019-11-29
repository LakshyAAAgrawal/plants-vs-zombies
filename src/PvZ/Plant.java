package PvZ;

import java.io.Serializable;

public abstract class Plant extends Character implements Serializable {
    protected int token_cost, time_cost;
    protected int x,y;
    public Plant(int HP,int attack,int token,int time,int x,int y,String img){
        super(HP,attack,img);
        this.token_cost=token;
        this.time_cost=time;
        this.x=x;
        this.y=y;
    }
    public void attack(Zombie z){
        z.reduce_HP(this.attack_power);
    }
}
