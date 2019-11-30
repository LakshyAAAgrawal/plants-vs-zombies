package PvZ;

import java.io.Serializable;

public class Pea extends GraphicObject implements Serializable {
    public double attackStat;
    boolean isActive;
    public Pea(double x, double y) {
        super("PeaFrustration.png", x, y);
        this.attackStat = 5;
        this.isActive = true;
    }

    public boolean isActive() {
        return isActive;
    }
}
