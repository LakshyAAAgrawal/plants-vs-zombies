package PvZ;

import java.io.Serializable;

public abstract class Character extends GraphicObject implements Serializable {
    protected double HP;
    protected final double attack_power;
    public Character(int HP,int attack_power,String img,double x,double y){
        super(img,x,y);
        this.HP=HP;
        this.attack_power=attack_power;
    }
    public void reduce_HP(double reduce) {
        HP -= reduce;
        if(HP <= 0){
            this.die();
        }
    }

    public boolean isDead() {
        if(HP <= 0) return true;
        return false;
    }
    public abstract void die();
}
