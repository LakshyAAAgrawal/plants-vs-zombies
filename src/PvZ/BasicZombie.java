package PvZ;

import java.io.Serializable;

public class BasicZombie extends Zombie implements Serializable {
    public BasicZombie(int l_n, double x, double y, String img) {
        super(100, 50, l_n, x, y, "basic_zombie.gif", "basic_zombie_dying.gif");
    }
}
