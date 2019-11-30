package PvZ;

import java.io.Serializable;

public abstract class Shooter extends Plant implements Serializable {
    int numPeas;
    public Shooter(int HP, int attack, int token, int time, double x, double y, String s, int numPeas) {
        super(HP, attack, token, time, x, y, s);
        this.numPeas = numPeas;
    }
}

