package PvZ;

import java.io.Serializable;

public abstract class Plant extends Character implements Serializable {
    protected int token_cost, time_cost;
    public Plant(int HP,int attack,int token,int time,int x,int y,String img){
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
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }
}
