package PvZ;

import java.io.Serializable;

public abstract class Shooter extends Plant implements Serializable {
    public int counter;
    int numPeas;
    int gap_between_peas;
    public Shooter(int HP, int attack, int token, int time, double x, double y, String s, int numPeas) {
        super(HP, attack, token, time, x, y, s);
        this.numPeas = numPeas;
        counter = 0;
        gap_between_peas = 1;
    }

    public boolean update() {
        boolean to_ret = false;
        if(counter == 0) {
            to_ret = true;
        }
        else if((counter < numPeas*10) && (counter % 10 == 0)){
            to_ret = true;
        }
        counter = (counter + 1)%(gap_between_peas * 60);
        return to_ret;
    }
}

