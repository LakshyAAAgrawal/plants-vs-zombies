package PvZ;

import java.io.Serializable;

public abstract class Character extends GraphicObject implements Serializable {
    protected int HP;
    protected final int attack_power;
    public Character(int HP,int attack_power,String img,double x,double y){
        super(img,x,y);
        this.HP=HP;
        this.attack_power=attack_power;
    }
    public void reduce_HP(int reduce) {
        HP -= reduce;
    }
}
