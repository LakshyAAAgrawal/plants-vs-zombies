package PvZ;

import java.io.Serializable;

public class Walnut extends Plant implements Serializable {
    public Walnut(double x,double y){
        super(150,150,50,10,x,y,"walnut.gif");
    }
}
