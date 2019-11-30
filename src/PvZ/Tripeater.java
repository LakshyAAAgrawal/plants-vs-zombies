package PvZ;

import java.io.Serializable;

public class Tripeater extends Shooter implements Serializable {
    public Tripeater(double x,double y){
        super(300,100,100,10,x,y,"pea_shooter.gif", 3);
    }
}
