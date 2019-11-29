package PvZ;

import java.io.Serializable;

public class Cherrybomb extends Plant implements Serializable {
    public Cherrybomb(int x,int y){
        super(150,150,150,10,x,y,"cherry-bomb.gif");
    }
}
