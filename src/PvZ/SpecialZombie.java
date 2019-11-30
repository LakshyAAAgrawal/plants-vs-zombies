package PvZ;

import java.io.Serializable;

public class SpecialZombie extends Zombie implements Serializable {
    public SpecialZombie(int l_n, double x, double y) {
        super(175, 125, l_n, x, y, "red_flag_zombie.gif", "basic_zombie_dying.gif");
    }
}
