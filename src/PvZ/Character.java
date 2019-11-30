package PvZ;

import java.io.Serializable;

public abstract class Character extends GraphicObject implements Serializable {
    protected int HP;
    protected final int attack_power;
    public Character(int HP,int attack_power,String img,int x,int y){
        super(img,x,y);
        this.HP=HP;
        this.attack_power=attack_power;
    }
    public void reduce_HP(int reduce) {
        HP -= reduce;
    }

    public boolean isDead() {
        if(HP <= 0) return true;
        return false;
    }
}
