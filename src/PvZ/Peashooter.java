package PvZ;

import java.io.Serializable;

public class Peashooter extends Shooter implements Serializable {
    public Peashooter(int x,int y){
        super(100,100,100,10,x,y,"pea_shooter.gif", 1);
    }
}
