package PvZ;

import java.io.Serializable;

public class BucketheadZombie extends Zombie implements Serializable {
    public BucketheadZombie(int l_n, double x, double y, String img) {
        super(150, 100, l_n, x, y, "bucket_head_zombie.gif", "basic_zombie_dying.gif");
    }
}
