package PvZ;

import java.io.Serializable;

public class ConeheadZombie extends Zombie implements Serializable {
    public ConeheadZombie(int l_n, double x, double y) {
        super(125, 75, l_n, x, y, "cone_head_zombie.gif", "basic_zombie_dying.gif");
    }
}
