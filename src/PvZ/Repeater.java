package PvZ;

import java.io.Serializable;

public class Repeater extends Shooter implements Serializable {
    public Repeater(int x,int y){
        super(200,100,200,10,x,y,"repeater.gif", 2);
    }
}
