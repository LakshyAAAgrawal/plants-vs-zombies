package PvZ;

import java.io.Serializable;

public class ZombieFactory implements Serializable {
    public Zombie createZombie(int num, int l_n, double x, double y){
        Zombie zombie = null;
        if(num == 1){
            zombie = new BasicZombie(l_n, x, y);
        }else if(num == 2){
            zombie = new ConeheadZombie(l_n, x, y);
        }else if(num == 3){
            zombie = new BucketheadZombie(l_n, x, y);
        }else{
            zombie = new SpecialZombie(l_n, x, y);
        }
        zombie.createGraphicObject();
        zombie.setDimension(100, 100);
        return zombie;
    }
}
