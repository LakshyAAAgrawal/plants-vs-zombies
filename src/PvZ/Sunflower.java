package PvZ;

import java.io.Serializable;

public class Sunflower extends Plant implements Serializable {
    public Sunflower(int x,int y){
        super(100,100,50,10,x,y,"sunflower.gif");
    }
}
